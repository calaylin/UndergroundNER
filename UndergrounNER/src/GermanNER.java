import java.io.*;
import java.util.List;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class GermanNER {

    public String Tokens[];

    public static void main(String[] args) throws IOException
             {

        GermanNER toi = new GermanNER();


        String input;
    	String test_dir = "/Users/Aylin/Desktop/Drexel/2014/underground_forums_continued/dataset/carderscc_01_private/" ;
    	List test_file_paths = Util.listTextFiles(test_dir);
       	for(int i=0; i< test_file_paths.size(); i++){
			String testMessages = Util.readFile(test_file_paths.get(i).toString());
			System.out.println(test_file_paths.get(i));
			int testIDlength = test_file_paths.get(i).toString().length(); 
			String testID = test_file_paths.get(i).toString().substring(91,testIDlength);
			System.out.println(testID);
	       	String output_filename = "/Users/Aylin/Desktop/Drexel/2014/underground_forums_continued/dataset/carderscc_private_ICQ_ner_results/" + testID  ;
	       
	        toi.tokenization(testMessages);
	        
	       	String icq = toi.icqFind(toi.Tokens);
	/*      String phone = toi.phoneFind(toi.Tokens);
	       	String email = toi.emailFind(toi.Tokens);
	       	String address = toi.addressFind(toi.Tokens);
	       	String wme = toi.wmeFind(toi.Tokens);
	       	String aim = toi.aimFind(toi.Tokens);
	       	String jabber = toi.jabberFind(toi.Tokens);
	       	String msn = toi.msnFind(toi.Tokens);*/

	        System.out.println("ICQ number aka UIN is: "+icq);
			if(	(toi.icqCount(toi.Tokens)) != 0)
			{
				File f = new File(output_filename);
				if (!f.getParentFile().exists())
				    f.getParentFile().mkdirs();
				if (!f.exists())
				    f.createNewFile();
			Util.writeFile( icq + "\n", output_filename, true);
/*			Util.writeFile( "Writing phone numbers: \n"+phone + "\n", output_filename, true);
			Util.writeFile( "Writing email: \n"+email + "\n", output_filename, true);
			Util.writeFile( "Writing address: \n"+address + "\n", output_filename, true);
			Util.writeFile( "Writing WME numbers: \n"+wme + "\n", output_filename, true);
			Util.writeFile( "Writing AIM screenname: \n"+aim + "\n", output_filename, true);
			Util.writeFile( "Writing jabber email: \n"+jabber + "\n", output_filename, true);
			Util.writeFile( "Writing msn with screenname or hotmail: \n"+msn + "\n", output_filename, true);*/	
			}
       // input="Ja, melde dich in icq 817063";
       // toi.tokenization(input);

/*        String names = toi.namefind(toi.Tokens);
        String org = toi.orgfind(toi.Tokens);
        String loc = toi.locfind(toi.Tokens);
        String time = toi.timefind(toi.Tokens);
        String date = toi.datefind(toi.Tokens);
        String money = toi.moneyfind(toi.Tokens);
        String percentage = toi.percentagefind(toi.Tokens);*/



/*        System.out.print("person name is: "+names);
        System.out.println("person count is: "+toi.nameCount(toi.Tokens));
        System.out.print("organization name is: "+org);
        System.out.print("location name is: "+loc);
        System.out.print("time name is: "+time);
        System.out.print("date name is: "+date);
        System.out.print("money name is: "+money);
        System.out.print("percentage name is: "+percentage);*/
        
       	}


    }

    
    public int icqCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-icq.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String icqFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-icq.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }
        
    
    
    
    
    
    
    
    public int phoneCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-phone.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String phoneFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-phone.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }

    
    
    
    
    
    
    
    public int emailCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-email.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String emailFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-email.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }

    
    
    
    
    
    
    
    
    public int addressCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-address.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String addressFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-address.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }

    
    
    
    
    
    public int wmeCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-wme.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String wmeFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-wme.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }

    
    
    
    
    
    
    
    
    public int aimCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-aim.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String aimFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-aim.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }

    
    
    
    
    public int jabberCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-jabber.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String jabberFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-jabber.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }

    
    
    
    
    
    
    public int msnCount(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-msn.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;
            return l;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
    
    public String msnFind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "models/opennlp/GermanNerModels/de-ner-msn.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j] + "\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InvalidFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return sd;
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    public void tokenization(String tokens) {

        InputStream is;
        TokenizerModel tm;

        try {
            is = new FileInputStream("models/opennlp/GermanNerModels/de-token.bin");
            tm = new TokenizerModel(is);
            Tokenizer tz = new TokenizerME(tm);
            Tokens = tz.tokenize(tokens);
            // System.out.println(Tokens[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}