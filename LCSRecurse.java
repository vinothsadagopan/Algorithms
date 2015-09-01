import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
	Name: Vinoth Kumar Sadagopan
	ID: 800850529
	Description: This class computes LCS recursively using Leniar algorithm and implements the third problem mentioned in the programming assignment
	Input: To enter the file path in command prompt. For two DNA Sequence ,two file path has to be given as input in Command Prompt
	Output:Longest Common Subsequence will be displayed
*/

class LCSRecurse
{
	 /*Funciton generatereversemiddlerow
	 Input: String X and Y
	 Output: Array
	 Description: this function computes the reverse middle row recursively.
	 */
	 static int[] generatereversemiddlerow(String X, String Y)
		{
			
			int [] A3 = new int[Y.length()+1];
			int [] A4 = new int[Y.length()+1];
			for(int  i = Y.length(), j =0; i >= 0 ;i--)
			{
				A3[i] = j;
				j = j+1;		
			}
			
			
			for(int i = X.length() ,j=1; i > X.length()/2; i--)
			{
				A4[Y.length()] = j;
				
				for(int k = Y.length(); k >= 1;k--)
				{				
					if(X.charAt(i-1)== Y.charAt(k-1))
					{
						A4[k-1] = A3[k];
					}
					else if(A3[k-1] < A4[k])
					{
						A4[k-1] = A3[k-1] + 1;
					}
					else
					{
						A4[k-1] = A4[k] +1;
					}
					
				}
				for(int l = 0 ;l <= Y.length();l++)
				{
					A3[l] = A4[l];
				}
				j = j+1;
				
			}
			return A4;
	}
	/* Function generatefrontmiddlerow
		Input: String Xand Y
		Output: Array
		Description: Computes the forward middle row recursively
	*/
	static int[] generatefrontmiddlerow (String X,String Y)
		{
			int []A1 = new int[Y.length()+1];
			int [] A2 = new int[Y.length()+1];
			
		for(int i=0; i <= Y.length();i++)
			{A1[i] = i;}
		for (int j = 1;j <= X.length()/2 ;j++ )
		 {
			A2[0] = j;
			for (int i =1;i<=Y.length() ;i++ ) 
			{
				if(X.charAt(j-1) == Y.charAt(i-1))
				{
					A2[i] = A1[i-1];//diagnol copy
				}
				else if (A1[i] < A2[i-1]) {
					A2[i] = A1[i] + 1;// top element copy
				}
				else
				{
					A2[i] = A2[i-1] +1;//left element copy
				}
			}
			for(int k=0;k<Y.length();k++)
			{
				A1[k] = A2[k];
			}
			
		}
		return A2;
	}
	/* function mineditdistance
		Input: Arrays(output of forward middle row and reverse middle row functions)
		Output: integer value
		Description: Computes the minimum edit distance and returns the index
	*/
	static int mineditedistanceindex(int A2[],int A4[])
	{


		int min=0, sum=0, index=0;
		for(int i =0; i < A2.length; i++)
		{
			sum = A2[i] +A4[i];
			
			if(i==0)
			{
				min = sum;
				index = i;

			}
			else if(sum <= min)
			{
				min = sum;
				index = i;
			}
		}
			return index-1;
		}
		/*
			Function LCS_Recurse
			Input: String X and Y and String Builder
			Output: returns string(which is converted from stack in order to display)
			Description: Calls the forward middle row and reverse middle row function recursively to compute the LCS output.
		*/
static String LCS_recurse(String X, String Y, StringBuilder result )
		{
			
			
			
			
			if(X.length() == 0 || Y.length() == 0){
				
			}
			
			else if(X.length()== 1)
			{
				for (int i = 0;i < Y.length()  ; i++)
				 {
					if(X.charAt(0) == Y.charAt(i))
					{
						result.append(X.charAt(0));
					}		
				}	
			}
			else if(Y.length() == 1)
			{
				for (int i = 0;i< X.length()  ; i++)
				 {
					if(Y.charAt(0) == X.charAt(i))
					{
						result.append(Y.charAt(0));
					}		
				}
			}
			
			else
			{


				
				int[] A1 = generatefrontmiddlerow( X,Y);
				int [] A2 = generatereversemiddlerow( X, Y);
						
				int splity = mineditedistanceindex(A1, A2);
				int splitx = X.length()/2 - 1;
								
				String Y_front = Y.substring(0,splity+1);
				String Y_back = Y.substring(splity+1, Y.length());
				String X_front = X.substring(0, splitx+1);
				String X_back = X.substring(splitx+1, X.length());
											
				LCS_recurse(X_front,Y_front,result);
				LCS_recurse(X_back,Y_back,result);
			}
			String output = result.toString();
			return output;

		}


	public static void main(String[] args) throws IOException

	{
		System.out.println("Please enter the filepath and filename of the first string");
		Scanner in = new Scanner(System.in);
		String filename1 = in.nextLine();
		String X = readFile(filename1);                                                    // Reads the string from input file
		System.out.println("Please enter the filepath and filename of the second string");
		String filename2 = in.nextLine();
		String Y = readFile(filename2);                                                    // Reads the string from input file
		if(X.length()==0 || Y.length()==0)                                                 // Checks if any string is empty, if any of the string empty, the program breaks and displays message on the output screen     
		{
			System.out.println("Sequences cant be empty");
		}
		else
		{
			StringBuilder result= new StringBuilder();                                   // If the string is not empty computation begins..
		String output = LCS_recurse(X,Y,result);
		System.out.println("The lcs is .." + output);
		}
		
	}
	/*
		Reads the file from the user
	*/
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
}