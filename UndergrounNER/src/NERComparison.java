import java.io.*;
import java.util.List;

public class NERComparison 
{
	public static void main(String[] args) throws IOException
    {

        String input;
    	String test_dir = "/Users/Aylin/Desktop/Drexel/2014/underground_forums_continued/dataset/carderscc_private_ICQ_ner_results/" ;
    	List test_file_paths = Util.listTextFiles(test_dir);
       	for(int i=0; i< test_file_paths.size(); i++)
       	{
			String testMessages = Util.readFile(test_file_paths.get(i).toString());
			System.out.println(test_file_paths.get(i));
			int testIDlength = test_file_paths.get(i).toString().length(); 
			String testID = test_file_paths.get(i).toString().substring(104,testIDlength-10);
			System.out.println(testID); //testID is the email of the Carders member
	       	String output_filename = "/Users/Aylin/Desktop/Drexel/2014/underground_forums_continued/dataset/results/results.txt";
	    	for(int j=0; j< test_file_paths.size(); j++)
	    	{
	    		while(!test_file_paths.get(i).toString().equals(test_file_paths.get(j).toString()))
	    				{
				String otherMessages = Util.readFile(test_file_paths.get(j).toString());
				System.out.println(test_file_paths.get(j));
				int otherIDlength = test_file_paths.get(j).toString().length(); 
				String otherID = test_file_paths.get(j).toString().substring(104,testIDlength-10);
				System.out.println(otherID); //testID is the email of the Carders member
	        
			int matchCounter =0;
			
			String testLines[] =  testMessages.split("\\n");
			String otherLines[] =  otherMessages.split("\\n");

			   for (int k =0; k<testLines.length; k++)
			   {
				   for (int l =0; l<otherLines.length; l++)
				   {
					   if (testLines[k].equals(otherLines[l]))
					   {
					        System.out.println("There is a match: " +testLines[k]+"equals"+otherLines[l]);
							File f = new File(output_filename);
							if (!f.getParentFile().exists())
							    f.getParentFile().mkdirs();
							if (!f.exists())
							    f.createNewFile();
						Util.writeFile(testID + otherID + matchCounter + "\n", output_filename, true);
						  
					   }
					   
				   }
 
				   
			   } 
			
			
	    	}
	    	}
		}  
    }
}
