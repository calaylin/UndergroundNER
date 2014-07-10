import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderEvaluator;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
import opennlp.tools.util.eval.FMeasure;

public class OpenNLPTool {
	
	private static String modelsDir = "models/opennlp/";
	private static String nerModelsDir = modelsDir + "ner/";
	
	public static void main(String[] args) throws IOException {
		String text = "Simone is looking for some good hotels in Milan costing less than nnn.";
		String[] sentences = detectSentences(text);
		for(int i=0; i<sentences.length; i++) {
			System.out.println(sentences[i].toString());
			String[] tokens = tokenize(sentences[i]);
			List<Span> locations = findLocations(tokens);
			if(locations!=null && !locations.isEmpty()) {
				System.out.print("\nLOCATIONS: ");
				for(Span span : locations)
					System.out.print(span.toString()+"; ");
			}
			List<Span> otherNEs = findOtherNEs(tokens);
			if(otherNEs!=null && !otherNEs.isEmpty()) {
				System.out.print("\nOTHER NEs: ");
				for(Span span : otherNEs)
					System.out.print(span.toString()+"-"+span.getType()+"; ");
			}
		}
	}
	
	public static String[] detectSentences(String text) {
		InputStream modelIn;
		modelIn = null;
		try {
			modelIn = new FileInputStream(modelsDir + "en-sent.bin");
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			return sentenceDetector.sentDetect(text);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}
		return null;		
	}
	
	public static String[] tokenize(String sentence) {
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(modelsDir + "en-token.bin");
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			return tokenizer.tokenize(sentence);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}
		return null;
	}
	
	private static List<Span> findNEs(String[] tokens, String modelName) {
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(nerModelsDir + modelName);
			TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
			NameFinderME nameFinder = new NameFinderME(model);
			return Arrays.asList(nameFinder.find(tokens));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}
		return null;
	}
	
	public static List<Span> findLocations(String[]	tokens) {
		return findNEs(tokens, "en-ner-location.bin");
	}
	
	public static List<Span> findOtherNEs(String[] tokens) {
		List<Span> nes = new ArrayList<Span>();
		nes.addAll(new ArrayList<Span>(findNEs(tokens, "en-ner-date.bin")));		
		nes.addAll(new ArrayList<Span>(findNEs(tokens, "en-ner-money.bin")));
		nes.addAll(new ArrayList<Span>(findNEs(tokens, "en-ner-percentage.bin")));
		nes.addAll(new ArrayList<Span>(findNEs(tokens, "en-ner-person.bin")));
		nes.addAll(new ArrayList<Span>(findNEs(tokens, "en-ner-time.bin")));
		if(nes==null || nes.isEmpty())
			return null;
		return nes;
	}
	
	@SuppressWarnings("deprecation")
	public static void trainNameFinder() throws IOException {
		Charset charset = Charset.forName("UTF-8");
		ObjectStream<String> lineStream =
				new PlainTextByLineStream(new FileInputStream("models/en-ner-location.train"), charset);
		ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

		TokenNameFinderModel model = null;
		BufferedOutputStream modelOut = null;
		try {
		  model = NameFinderME.train("en", "location", sampleStream,
		      Collections.<String, Object>emptyMap(), 100, 5);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
		  sampleStream.close();
		}

		try {
			modelOut = new BufferedOutputStream(new FileOutputStream("models/en-ner-location.bin"));
			model.serialize(modelOut);
		} finally {
		  if (modelOut != null) 
		     modelOut.close();      
		}
	}
	
	
	public static void testNameFinder() throws InvalidFormatException, IOException {
		InputStream modelIn = new FileInputStream("models/en-ner-location.bin");
		TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
		TokenNameFinderEvaluator evaluator = new TokenNameFinderEvaluator(new NameFinderME(model));
		Charset charset = Charset.forName("UTF-8");
		ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream("models/en-ner-location.test"), charset);
		ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

		evaluator.evaluate(sampleStream);
		FMeasure result = evaluator.getFMeasure();

		System.out.println(result.toString());
	}
}
