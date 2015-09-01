import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/* Name: Vinoth Kumar Sadagopan
	ID: 800850529
	Description: This class is implementation of first question to calculate Normalized edit distance for given two input DNA Sequence
	Input: To enter the file path in command prompt. For two DNA Sequence ,two file path has to be given as input in Command Prompt
	Output:Edit distance 'D' value and the normalized edit distance gets dispalyed in command prompt
*/
class NormalizedEditDistance
{
	/* This function helps in reading the input from file*/
	static String readFile(String fileName) throws IOException
	 {
   		 BufferedReader br = new BufferedReader(new FileReader(fileName));
    	try {
        		StringBuilder sb = new StringBuilder();
        		String line = br.readLine();

        		while (line != null) {
            		sb.append(line);
            		line = br.readLine();
        		}
        		return sb.toString();
    		} 	
    		finally {
        			br.close();
    		}
	}
	public static void main(String[] args)  throws IOException 
	{
		System.out.println("Please enter the filepath and filename of the first string");
		Scanner in = new Scanner(System.in);
		String filename1 = in.nextLine();
		String X = readFile(filename1);                                                      // Reads the string from input file
		System.out.println("Please enter the filepath and filename of the second string");
		String filename2 = in.nextLine();
		String Y = readFile(filename2);                                                       // Reads the string from input file
		int xlength = X.length();
		int ylength= Y.length();
		if(xlength==0||ylength==0)        // Checks if any string is empty, if any of the string empty, the program breaks and displays message on the output screen
		{
			System.out.println("Sequenes cant be empty");
		}
		else                              // If the string is not empty computation begins..
		{
			X ="0"+ X;
		Y= "0"+Y;
		
		int []A1 = new int[Y.length() ];
		int [] A2 = new int[Y.length()];
		
		for (int i =0;i<Y.length() ;i++ ) { // Fills the first array with 0..10
				A1[i] = i;
				
			}		
		
		for (int j = 1;j< X.length() ;j++ ) {
			A2[0] = j;
			for (int i =1;i<Y.length() ;i++ ) // Array A1 and A2 are dynamiclly populated 
			{
				if(X.charAt(j) == Y.charAt(i))
				{
					A2[i] = A1[i-1];         // Diagonal Copy if characters are same
				}
				else if (A1[i]< A2[i-1]) {   // Compares the number with top and left element
					A2[i] = A1[i] + 1;       // Top element copy
				}
				else
				{
					A2[i] = A2[i-1] +1;      // Left Element Copy.
				}
				
			}
			for(int k=0;k<Y.length();k++)// Array2 copies it content to Array A1 in order to populate other rows 
			{
				A1[k] = A2[k];
			}
			
			
		}
		//System.out.println("2nd Array :"+ Arrays.toString(A2));
		/* to calculate Normalized edit distance */
		double editdistance = A2[Y.length() - 1];
		System.out.println("edit distance d: "+editdistance);
		double NES = xlength+ylength - editdistance;
		NES/= xlength+ylength;
		System.out.println("Normalized edit distance: " + NES); 
		}
		
	}
}