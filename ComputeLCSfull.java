import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
	Name: Vinoth Kumar Sadagopan
	ID: 800850529
	Description:This class implements second problem mentioned in the assignment, to compute LCS by storing the whole Table
	Input: To enter the file path in command prompt. For two DNA Sequence ,two file path has to be given as input in Command Prompt
	Output:Longest Common Subsequence will be displayed
*/
class ComputeLCSfull
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

	public static void main(String[] args) throws IOException
	{
		System.out.println("Please enter the filepath and filename of the first string");
		Scanner in = new Scanner(System.in);
		String filename1 = in.nextLine();
		String X = readFile(filename1);                                                    // Reads the string from input file
		
		System.out.println("Please enter the filepath and filename of the second string");
		String filename2 = in.nextLine();
		String Y = readFile(filename2);                                                   // Reads the string from input file
		if(X.length()==0||Y.length()==0)                            // Checks if any string is empty, if any of the string empty, the program breaks and displays message on the output screen     
		{
			System.out.println("Sequences cant be empty");
		}
		else                                                 // If the string is not empty computation begins..
		{
			X ="0"+ X;
		Y= "0"+Y;
		
		int [][] A3 = new int [X.length()][Y.length()];
		for(int i= 0;i<Y.length();i++)                     // Computes the matrix table
		{
			A3[0][i] = i;
			

		}
		for(int j =1; j < X.length();j++ )
		{
			A3[j][0]= j;
			for(int k = 1 ; k< Y.length();k++)
			{
				if(X.charAt(j) == Y.charAt(k))
				{
					A3[j][k] = A3[j-1][k-1];//diagnol copy
				}
				else if (A3[j-1][k]< A3[j][k-1]) {
					A3[j][k] = A3[j-1][k] + 1;// top element copy
				}
				else
				{
					A3[j][k] = A3[j][k-1] +1;//left element copy
				}
				
			}
			
		}
		
		Stack output = new Stack();
		int i = X.length() -1;
		int j = Y.length() -1;

		while (i>0 && j>0)                      // Backtracking starts
		{
			

			if(X.charAt(i) ==Y.charAt(j))
			{
				output.push(X.charAt(i));
				i--;
				j--;
			}
			else if(A3[i][j-1] <=A3[i-1][j])  // left element is less
			{
				
				j--;
			}
			else{
			
			i--;    // top element is less

			}
				
		}
		StringBuilder outputstring = new StringBuilder(); // Copies the values in stack into string and the output is displayed.
		while(!output.isEmpty())
		{
			outputstring.append(output.pop());
		}
		System.out.println("Longest Common Subsequence is: "+outputstring);	
		}
		
	}
}