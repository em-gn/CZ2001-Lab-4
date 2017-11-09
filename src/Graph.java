// Java program to print BFS traversal from a given source vertex.
// BFS(int s) traverses vertices reachable from s.
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency list
// representation
public class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Edge> vertexList[];
    private ArrayList<String> cityNameMapping[];
    
    /*
     * Edge
     * int first vertex
     * int second vertex
     * int weight
     * 
     * LinkedList<Edge> adj[]
     * [0] -> [][][]
     * [1] -> [][][]
     * [2] -> [][][]
     * [3] -> [][][]
     * [4] -> [][][]
     * [5] -> [][][]
     * [6] -> [][][]
     * 
     * */
 
    // Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 

    void addVertex(String[] listOfCityNames) {
    	
    	for (String cityName : listOfCityNames) {
        	// Maps first_vertex to cityNameMapping such that cityNameMapping[index] returns the city name for that vertex
        	cityNameMapping.set(first_vertex, )
    	}

    }
    
    
    /*
     * @param first_vertex  : refers to the index of the element in cityNameMapping
     * @param second_vertex : refers to the index of the element in cityNameMapping
     * @param weight        : weight of the edge
     * 
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
        Graph g = new Graph(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}