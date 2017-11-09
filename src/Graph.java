// Java program to print BFS traversal from a given source vertex.
// BFS(int s) traverses vertices reachable from s.
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency list
// representation
public class Graph
{
    private int noOfVertices;
    private ArrayList<Edge>[] vertexList;
    private ArrayList<String> cityNameMapping;
    
    /*
     * Edge
     * int first vertex
     * int second vertex
     * int weight
     * 
     * ArrayList<Edge>[] vertexList
     * [0] -> [][][]
     * [1] -> [][][] -> ArrayList<Edge> e.g. (1,0,1), (1,1,1), (1,2,1) etc
     * [2] -> [][][]
     * [3] -> [][][]
     * [4] -> [][][]
     * [5] -> [][][]
     * [6] -> [][][]
     * 
     * */
 
    /*
     * Constructor:
     * Initialises the Graph based on the noOfCities provided
     * */
    Graph(int noOfCities)
    {
    	noOfVertices = noOfCities;
    	ArrayList<Edge> tempListOfEdges = new ArrayList<Edge>();
        for (int i = 0; i < noOfCities; ++i) {
        	vertexList[i] = tempListOfEdges;
        }
    }
    
    /*
     * Maps first_vertex to cityNameMapping such that cityNameMapping[index] returns the city name for that vertex
     * 
     * @param vertex   : the integer that the city name is going to be associated with
     * @param cityName : the city name to be associated with the integer
     * */
    void addVertex(int vertex, String cityName) {
    	cityNameMapping.set(vertex, cityName);
    }
    
    /*
     * @param first_vertex  : refers to the index of the element in cityNameMapping
     * @param second_vertex : refers to the index of the element in cityNameMapping
     * @param weight        : weight of the edge
     * */
    void addEdge(int first_vertex,int second_vertex, int weight)
    {
    	Edge tempEdge = new Edge (first_vertex, second_vertex, weight);
    	vertexList[first_vertex].add(tempEdge);
    }
 
    // prints BFS traversal from a given source s
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
 
    // Driver method to
    public static void main(String args[])
    {
    	int cities;
    	int[] edges = new int[100];
    	for (int i = 0;i<100;i++) {
    		edges[i]=i;
    	}
    	Scanner sc = new Scanner(System.in);
    	Random rand = new Random();
    	System.out.println("How many cities?");
    	cities = sc.nextInt();
        Graph g = new Graph(cities);
        
        for (int i=0;i<cities;i++) {
        	int paramOne = -1;
        	int paramTwo = -1;
        	int weight = rand.nextInt(99);
        	//param 1
        	while (paramOne == -1) {
        		int index = rand.nextInt(99);
        		if (edges[index]!=-1) {
        			paramOne = edges[index];
        			edges[index]=-1;
        		}
        	}
        	//param 2
        	while (paramTwo == -1) {
        		int index = rand.nextInt(99);
        		if (edges[index]!=-1) {
        			paramTwo = edges[index];
        			edges[index]=-1;
        		}
        	}
        	g.addEdge(paramOne, paramTwo, weight);
        }
 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}