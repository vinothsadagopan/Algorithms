# Algorithms
README FILE
Name: Vinoth Kumar Sadagopan
ID: 800850529
e-mail: vsadagop@uncc.edu

Introduction
•	I have used Java programming language and Java compiler.
•	I have totally made 3 files for the project which covers three parts given in the assignment
File Name	Description
NormalziedEditDistance.java	This class file computes the Normalized edit distance for the given input string.
ComputeLCSfull.java	This java class file computes LCS by storing the entire table
LCSRecurse.java	This java class file computes LCS using recursive linear memory program

Brief Description
Dynamic programming concept has been applied for computing the longest common subsequence for given two strings.
As per the document description, a matrix has been constructed and values have been computed accordingly(like description given in the document)
Normalized edit distance gives output range between 0 to 1 were  0 means two strings are completely unrelated and 1 is when two strings are identical.
Two approaches have been made to compute LCS
1.	By storing the entire matrix
By storing entire matrix, the space complexity is higher and the whole matrix gets stored to compute the output
2.	Using Leniar Recursive Algorithm
By calculating the matrix just by retaining two rows and the computation is made by recursion
By using this dynamic programming approach, problems like DNA sequence matching can be computed effectively rather tha using conventional approach.
Program Design
For normalized edit distance and LCS(recursion) I have used two single arrays(Say A1[], and A2[]) to compute two row values simultaneously and at the same time I copy the elements one second array to first array dynamically.
For LCS(Storing the entire table), I have used two dimensional array which stores the value upto the length of the two strings (say A1[X.length()][Y.length()]).
The time complexity of this program is O(n^2) and space complexity takes O(mn) if we store the whole matrix table, otherwise it takesO(n) space complexity.
Program Disadvantages and Flaws
The basic disadvantage of this program is that in case of recursion tables values are calculated again and again like calculation of Fibonacci values in recursion.
I haven’t handled to calculate the LCS or edit distance if one string is empty. 
I have placed a validation in such away that both the input strings should not be empty, otherwise the program won’t get executed.
Program execution
1.	Normalized Edit Distance (NormalizedEditDistance.java)
  
Input: to give the path of input file, one input file for one string each.
Output: Output gets displayed on the command prompt
2.	CompleteLCSfull by storing the entire table (ComputeLCSfull.java)
 
Input: to give the path of input file, one input file for one string each.
Output: Output gets displayed on the command prompt

3.	LCS by Recursion(LCSRecurse.java)
 
Input: to give the path of input file, one input file for one string each.
Output: Output gets displayed on the command prompt
----------------------------------------------------------------------------------------------------------------------
Graph Implementation for Networking
Read Me File
Name: Vinoth Kumar Sadagopan
Niner ID: 800850529
Email: vsadagop@uncc.edu
Programming Language Used: Java Programming Language
Java Version: java 1.8.x
Operating System : Windows OS
Description Of the program:
Program consistsof three classes
1. Vertex
2. Graph
3. Min Heap
Vertex Class has two tree map which captures edge list and its status respectively. Since these two data
structure are used, hence there is no necessity to create a separate edge class
Graph class
It consists of a tree map which stores all the vertices in a graph.
Reachable: uses BFS algorithm which runs on all vertices hence
Running Time: V*O(V+E)
Min Heap Class
Min Heap is implementation Of Priority queue which implements the pseudo code given in CLRS
Installation Instructions:
Download JDK 1.8
Set environment path to javac file found in the src\bin folder
Commands to execute java file
Down load the java file and place it in your local path
One command prompt and get into the folder where u have stored the java file
Use javac graph.java command to compile
While running the program
Java graph networkt.xt
This command reads the input file and graph is formed initially.
Then screen appears as follows
Please enter queries as follows
print
path Belk Education
edgedown Health Education
path Health Education
vertexdown Belk
path Health Education
print
reachable
POINTS TO NOTE
BE CAREFUL WITH THE SPELLING OF THE VERTICES NAME, IT IS CASE SENSEITVE
IF VERTICES NAME STARTS WITH A CAPITAL LETTER PLEASE EXCEUTE THE SAME WAY.
EXAMPLE:
edgedown Health Education
LIST OF COMMANDS
addedge
deleteedge
vertexup
vertexdown
edgeup
edgedown
path
print
reachable
Please use the commands as mentioned above.
All your commands would be in small letters
 		
 



