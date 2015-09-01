/*
Program : Programming Project 2 
Author: Vinoth Kumar Saadagopan -800850529
Main Class: Graph


*/

import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.PriorityQueue;
import java.util.*;



class GraphException extends RuntimeException /// Handling exceptions in Code
{
  public GraphException( String name )
  {
      super( name );
  }
}
class Vertex implements Comparable<Vertex>
{
  public String     name;   // Vertex name
  public Map<Vertex,Float> adj;    // Adjacent vertices
  public Vertex     prev;   // Previous vertex on shortest path
  public float   minDistance;   // Distance of path
  public Map<Vertex,Boolean> edgeflag; // Edge flag denotes whether the edge between adjacent vertices are up or down
  boolean isVisited; // TO check whether the Vertices have been visited or not in BFS
  public Vertex( String nm )
  { name = nm; adj = new TreeMap<Vertex,Float>(); edgeflag= new TreeMap<Vertex, Boolean>(); reset( ); }

public void reset( )
  { minDistance = Graph.transittimevalue; prev = null; isVisited = false; }   
public int compareTo(Vertex obj)
{
	return name.compareTo(obj.name);
	
}
public float compare(Vertex obj, Vertex obj2)
{
	return obj.minDistance - obj2.minDistance;
}
 
}
// Main Class Starts Here
public class Graph {

	public static final float transittimevalue =Float.MAX_VALUE;
	private TreeMap<String,Vertex> vertexMap = new TreeMap<String,Vertex>( ); // to store the vertices of a graph in a Tree Map
	private Map<Vertex,Boolean> vertexFlag = new HashMap<Vertex,Boolean>();  // vertexFlag denotes whether the Vertex is up or not.
	/*
		Add Edge: Adds edge between two vertices and if vertices doesnot exist it creates one
	*/
	public void addedge(String tail_vertex,String head_vertex, float transit_time)throws ClassCastException
	{
		Vertex v = getVertex(tail_vertex);
		Vertex w = getVertex(head_vertex);
		v.adj.put(w, transit_time);
		w.adj.put(v, transit_time);
		v.edgeflag.put(w, true);
		w.edgeflag.put(v,true);
		
	}
	/*
		Add Edge from arguement: Adds edge between two vertices and if vertices doesnot exist it creates one and the edge falg is added only one way.
	*/
	public void addedgefromarguement(String tail_vertex,String head_vertex, float transit_time)throws ClassCastException
	{
		if(vertexMap.get(tail_vertex)!= null && vertexMap.get(head_vertex)!=null)
		{
			Vertex v = getVertex(tail_vertex);
			Vertex w = getVertex(head_vertex);
			v.adj.put(w, transit_time);
			w.adj.put(v, transit_time);
			v.edgeflag.put(w, true);
			w.edgeflag.put(v,true);
		}
		else
		{
			Vertex v = getVertex(tail_vertex);
			Vertex w = getVertex(head_vertex);
			v.adj.put(w, transit_time);
			v.edgeflag.put(w, true);
		}
	}
	/* Gets Vertex from the vertex list of the graph, if not present creates one*/
	private Vertex getVertex(String vertexName)
	{
		Vertex v = vertexMap.get(vertexName);
		if(v==null)
		{
			v = new Vertex(vertexName);
			vertexMap.put(vertexName, v);
			vertexFlag.put(v, true);
		}
		return v;
	}
	/*Deletes Edge from the two vertices*/
	public void deleteedge(String tail_vertex, String head_vertex)
	{
		Vertex v = vertexMap.get(tail_vertex);
		Vertex w = vertexMap.get(head_vertex);
		v.adj.remove(w);
		v.edgeflag.remove(w);
	}
	/*
	 Edge Down makes the edge flag flase which denotes that the edge is down.
	*/
	public void edgedown(String tail_vertex, String head_vertex)
	{
		
		Vertex v= vertexMap.get(tail_vertex);
		Vertex w = vertexMap.get(head_vertex);
		if(v!= null)
		{
			v.edgeflag.put(w, false);
		}
		else{
			System.out.println("No Such Vertices are present");
		}
	}
	/* edge up makes the edge which is already down to Up*/
	public void edgeup(String tail_vertex, String head_vertex)
	{
		Vertex v = vertexMap.get(tail_vertex);
		Vertex w = vertexMap.get(head_vertex);
		if(v!=null)
		{
			v.edgeflag.put(w, true);
		}
		else
		{
			System.out.println("No Such Vertices are present");
		}
	}
	/*Puts Vertex flag down which denotes that vertex is down*/
	public void vertexdown(String tail_vertex)
	{
		Vertex v = vertexMap.get(tail_vertex);
		if(v!=null)
		{
			vertexFlag.put(v, false);
			
		}
		
	}
	/*Puts Vertex flag up which denotes that vertex is Down*/
	public void vertexup(String tail_vertex)
	{
		Vertex v = vertexMap.get(tail_vertex);
		Iterator it = v.edgeflag.entrySet().iterator();
		Map.Entry<Vertex,Boolean> KeyValue;
		while(it.hasNext())
		{
			KeyValue =(Map.Entry<Vertex,Boolean>)it.next();
			v.edgeflag.put(KeyValue.getKey(), true);
		}
		Iterator its = vertexMap.entrySet().iterator();
		Map.Entry<String,Vertex> KeyValue2;
		
		while(its.hasNext())
		{
			KeyValue2= (Map.Entry<String, Vertex>)its.next();
			Vertex x = KeyValue2.getValue();
			if(x.edgeflag.containsKey(v))
			{
				x.edgeflag.put(v, true);
			}
			
		}
		
	}
	/*Prints the graph*/
	public void printgraph()
	{
		Map.Entry<String,Vertex> KeyValue2;
		Iterator its = vertexMap.entrySet().iterator();
		while(its.hasNext())
		{
			KeyValue2= (Map.Entry<String, Vertex>)its.next();
			
			Vertex x = KeyValue2.getValue();
			if(vertexFlag.get(x))
			{
				System.out.println("-----"+KeyValue2.getKey()+"------");
			}
			else
			{
				System.out.println("-----"+KeyValue2.getKey()+"------ DOWN");
			}
				//System.out.println("-----"+KeyValue2.getKey()+"------");
				Iterator it = x.edgeflag.entrySet().iterator();
				Map.Entry<Vertex, Boolean> KeyValue3 ;
				while(it.hasNext())
				{
					 KeyValue3 = (Map.Entry<Vertex, Boolean>)it.next();
					 if(KeyValue3.getValue())
					 {
						 Vertex w = KeyValue3.getKey();
						 System.out.println("");
						 System.out.print(" "+w.name);
						 System.out.print(" "+x.adj.get(w));
						 System.out.println(" ");
					 }
					 else
					 {
						 Vertex w = KeyValue3.getKey();
						 System.out.println("");
						 System.out.print(" "+w.name);
						 System.out.print(" "+x.adj.get(w)+"  DOWN");
						 System.out.println(" ");
					 }
					 
				}
				
			
			
		}
		
	}
	/* Computes Dijkstras algorithm*/
	public void computepaths(Vertex source)
	{
		ArrayList<Vertex> vertices=new ArrayList<Vertex>();
		source.minDistance = 0;
		MinHeap heapobj=new MinHeap(vertexMap.size());
		//PriorityQueue<Vertex> qu = new PriorityQueue<Vertex>();
		for(Vertex w: vertexMap.values())
		{
			if(vertexFlag.get(w))
			{
				heapobj.enque(w.minDistance, w);
			}
			
		}
		
		while(!heapobj.isempty())
		{
			heapobj.minHeap();
			Vertex u = heapobj.extractmin();
			vertices.add(u);
			//System.out.println("Vertex"+u.name );
			
			for(Map.Entry<Vertex,Float> entry: u.adj.entrySet())
			{
				if(u.edgeflag.get(entry.getKey()))
				{
					Vertex v = entry.getKey();
					float weight = entry.getValue();
					float distancethrough = u.minDistance + weight;
					if(distancethrough< v.minDistance)
					{
						v.minDistance = distancethrough;
						v.prev = u;
						heapobj.clear();
						for(Vertex us : vertexMap.values() )
							if(!vertices.contains(us))
								heapobj.enque(us.minDistance, us);
						
							
					}
				}
			}
			
		}
		
	}
	/*
		Gets shortest path after computing the Dijkstra's algorithm 
	*/
	 public static List<Vertex> getShortestPathTo(Vertex target)
	    {
	        List<Vertex> path = new ArrayList<Vertex>();
	        for (Vertex vertex = target; vertex != null; vertex = vertex.prev)
	            path.add(vertex);
	        Collections.reverse(path);
	        return path;
	    }
	    /*
	     This is the main function where dijkstras algorithm is called and executed
	    */
	 public void path(String from_vertex, String to_vertex)
	 {
		 Vertex  fromvertex = vertexMap.get(from_vertex);
		 Vertex tovertex = vertexMap.get(to_vertex);
		 if(vertexFlag.get(fromvertex) && vertexFlag.get(tovertex))
		 {
			 computepaths(fromvertex);
			 List<Vertex> path = getShortestPathTo(tovertex);
			 for(Vertex v: path)
			 {
				 System.out.print(v.name+" ");
			 }
			
			 System.out.print(" "+tovertex.minDistance );
			 
			
			 
		 }
		 for(Map.Entry<String, Vertex>em:vertexMap.entrySet())
		 {
			 em.getValue().reset();
		 }
		
	 }
	 /*
	 	REACHABLE: Reachable vertices gives the output of vertices that can be reached from every vertices in a graph.
	 	This algorithm uses BREADTH FIRST SEARCH ALGORITHM which runs for every vertices in a graph.
	 	Running Time: V* o(V+E)
	 	For BFS running time is o(V+E). Since BFS is made to run on every vertex in a  graph hence it is V times o(V+E)
	 */
	 public void reachable()
	 {
		 Queue<Vertex> qu = new LinkedList<Vertex>();
		 for (Map.Entry<String, Vertex>vm : vertexMap.entrySet())
		 {
			 if(vertexFlag.get(vm.getValue()))
			 {
				 Vertex root = vm.getValue();
				 root.isVisited =true;
				 System.out.println("---"+root.name);
				 qu.add(root);
				 while (!qu.isEmpty())
				 {
					 Vertex v = qu.remove();
					 if(vertexFlag.get(v))
					 {
						 for(Map.Entry<Vertex, Float> w : v.adj.entrySet())
						 {
							 Vertex x = w.getKey();
							 if(!x.isVisited)
							 {
								if(vertexFlag.get(x))
								{
									qu.add(x);
									 x.isVisited =true;
									 System.out.println("   "+x.name);
						
								}
								 		 }
					
						 
						 } 
					 }
					 
				 }
				 for(Map.Entry<String, Vertex>vm2 : vertexMap.entrySet())
				 {
					 vm2.getValue().isVisited =false;
				 }
			 }
		 }
		 
				 
			 
	 }
		 
		 
	 
	public static void main(String[] args) throws Exception{
		
		 Graph g = new Graph( );
	        
	            FileReader fin = new FileReader( args[0] );
	            Scanner graphFile = new Scanner( fin );

	            // Read the edges and insert
	            String line;
	            while( graphFile.hasNextLine( ) )
	            {
	                line = graphFile.nextLine( );
	                StringTokenizer st = new StringTokenizer( line );

	                
	                    if( st.countTokens( ) != 3 )
	                    {
	                        System.err.println( "Skipping ill-formatted line " + line );
	                        continue;
	                    }
	                    String source  = st.nextToken( );
	                    String dest    = st.nextToken( );
	                    float transit_time = Float.parseFloat(st.nextToken());
	                    g.addedge( source, dest,transit_time );
	                    
	                }
	            Scanner in = new Scanner( System.in );
	            while( processRequest( in, g ) )
	            	;
	            	
	             }
	         
	        
	  /*Func tions to read from command line*/     
	public static boolean processRequest( Scanner in, Graph g )
    {
        try
        {
            System.out.print( "Enter Queries: " );
            String queryLine = in.nextLine( );
            String tail_vertex, head_vertex,transmit_time;
            StringTokenizer st = new StringTokenizer(queryLine);
            while(st.hasMoreTokens())
            {
            	 String query = st.nextToken();
            	 switch(query)
            	 {
            	 case "addedge":
            		 if(st.countTokens()!=3)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  head_vertex = st.nextToken();
            			  float transittime = Float.parseFloat(st.nextToken());
            			  g.addedgefromarguement(tail_vertex, head_vertex, transittime);
            			  
            		 }
            		 break;
            	 case "deleteedge":
            		 if(st.countTokens()!=2)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  head_vertex = st.nextToken();
            			  g.deleteedge(tail_vertex, head_vertex);
            			  
            			  
            		 }
            		 break;
            	 case "edgeup":
            		 if(st.countTokens()!=2)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  head_vertex = st.nextToken();
            			  g.edgeup(tail_vertex, head_vertex);
            			  
            			  
            		 }
            		 break;
            	 case "edgedown":
            		 if(st.countTokens()!=2)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  head_vertex = st.nextToken();
            			  g.edgedown(tail_vertex, head_vertex);
            			  
            			  
            		 }
            		 break;
            	 case "vertexup":
            		 if(st.countTokens()!=1)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  
            			  g.vertexup(tail_vertex);
            			  
            			  
            		 }
            		 
            		 break;
            	 case "vertexdown":
            		 if(st.countTokens()!=1)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  
            			  g.vertexdown(tail_vertex);
            			  
            			  
            		 }
            		 
            		 break;
            	 case "print":
            		 g.printgraph();
            		 break;
            	 case "reachable":
            		 g.reachable();
            		 break;
            	 case "path":
            		 if(st.countTokens()!=2)
            		 {
            			 System.out.println("Invalid command");
            			 
            		 }
            		 else
            		 {
            			  tail_vertex = st.nextToken();
            			  head_vertex = st.nextToken();
            			  g.path(tail_vertex, head_vertex);
            			  
            			  
            		 }
            		 break;
            	 case "quit":
            		 System.out.println("Program quits....");
                     in.close();
            		 return false;
            		 
            	 }
            }

            
        }
        catch( NoSuchElementException e )
          { return false; }
        catch( GraphException e )
          { System.err.println( e ); }
        return true;
    }


}
/*Implementation OF Binary Heap 
	this Data structure is used in the Dijkstras Algorithm
*/
class MinHeap {
	private static Map<Float,Vertex> mh = new HashMap<Float,Vertex>( ); // Basically Stores the vertices which are passd along with its min distance which are computed.
    
	 private float[] Heap;
	    private int size;
	    private int maxsize;
	 
	    private static final int FRONT = 1;
	
	    public MinHeap(int maxsize)
	    {
	        this.maxsize = maxsize;
	        this.size = 0;
	        Heap = new float[this.maxsize + 1];
	        Heap[0] = Integer.MIN_VALUE;
	    }
	    /*
	    	Adding into  queue
	    */
	    public void enque(float dist,Vertex v)  
	    {
	    		
	    		insert(v.minDistance);
	    			
	    		
	    		mh.put(dist,v);
	    }
	    /* Paren t Element*/
	    private int parent(int pos)
	    {
	        return pos / 2;
	    }
	 	
	    private int leftChild(int pos)
	    {
	        return (2 * pos);
	    }
	 
	    private int rightChild(int pos)
	    {
	        return (2 * pos) + 1;
	    }
	 
	    private boolean isLeaf(int pos)
	    {
	        if (pos >=  (size / 2)  &&  pos <= size)
	        { 
	            return true;
	        }
	        return false;
	    }
	 
	    private void swap(int fpos, int spos)
	    {
	        float tmp;
	        tmp =  Heap[fpos];
	        Heap[fpos] = Heap[spos];
	        Heap[spos] = tmp;
	    }
	 /* Min Heapify is the main logic behind*/
	    private void minHeapify(int pos) 
	    {
	        if (!isLeaf(pos))
	        { 
	            if ( Heap[pos] > Heap[leftChild(pos)]  || Heap[pos] > Heap[rightChild(pos)])
	            {
	                if (Heap[leftChild(pos)] < Heap[rightChild(pos)])
	                {
	                    swap(pos, leftChild(pos));
	                    minHeapify(leftChild(pos));
	                }else
	                {   if(size>0)
	                  {
	                    swap(pos, rightChild(pos));
	                    minHeapify(rightChild(pos));
	                  }
	                }
	            }
	        }
	    }
	 
	    public int insert(float dist)
	    {
	        Heap[++size] = dist;
	        int current = size;
	 
	        while (Heap[current] < Heap[parent(current)])
	        {
	            swap(current,parent(current));
	            current = parent(current);
	        }	
	        return current;
	    }
	 
	   
	    public void minHeap()
	    {
	        for (int pos = (size / 2); pos >= 1 ; pos--)
	        {
	            minHeapify(pos);
	        }
	    }
	 
	    public float remove()
	    {
	        float popped = Heap[FRONT];
	        Heap[FRONT] = Heap[size--]; 
	        minHeapify(FRONT);
	        return popped;
	    }
	 		
	    public boolean isempty()
	    {
	    	if (size ==0)
	    	return true;
	    		else
	    	return false;
	    	
	    }
		public Vertex extractmin()
		{
			Vertex v=null;
			float dist=remove();
			v=mh.get(dist);
			return v;
		}
		public void clear()
		{
			size = 0;
	        Heap = new float[this.maxsize + 1];
	        Heap[0] = Integer.MIN_VALUE;
	   
		}
		
}