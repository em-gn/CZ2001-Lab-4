import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Graph
{
    private int noOfVertices;
    private ArrayList<Integer>[] vertexList;
 
    Graph(int noOfCities)
    {
    	noOfVertices = noOfCities;
    	ArrayList<Integer> listOfConnectedVertices = new ArrayList<Integer>();
        for (int i = 0; i < noOfCities; ++i) {
        	vertexList[i] = listOfConnectedVertices;
        }
    }

    void addEdge(int firstVertex,int secondVertex)
    {
    	vertexList[firstVertex].add(secondVertex);
    }
 
    /*
     * BFS Algorithm
     * */
    void BFS(int sourceVertex)
    {
        /*
         * By default, all vertices start off as not visited (i.e. false)
         * */
        boolean visited[] = new boolean[noOfVertices];
 
        // Queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Visit sourceVertex and mark it in visited as true
        visited[sourceVertex]=true;
        queue.add(sourceVertex);
 
        while (queue.size() != 0)
        {
        	/* Dequeue a vertex from the queue to check
        	 * */
        	sourceVertex = queue.poll();
            System.out.print(sourceVertex + " ");
 
            /* For all adjacent vertices (adjVertex) of the dequeued sourceVertex:
             * If an adjVertex has not been visited, then mark it as visited and queue it
             */ 
            Iterator<Integer> iter = vertexList[sourceVertex].listIterator();
            while (iter.hasNext()) {
            	Integer adjVertex = iter.next();
            	if (!visited[adjVertex]){
            		visited[adjVertex] = true;
            		queue.add(adjVertex);
                }
            }
        }
    }

    // Driver method to
    public static void main(String args[])
    {
    	int c;
    	int numFlights;
    	List<String> cityList = Arrays.asList("city1", "city2", "city3"); //tbc, hardcoded
    	
    	Scanner sc = new Scanner(System.in);
    	Random rand = new Random();
    	System.out.println("How many cities?");
    	
    	c = sc.nextInt();
        Graph g = new Graph(c);
        
        System.out.println("How many flights");
        
        
        int[] combinationID = new int[c*(c^2-1)/2];
        
    	for (int i = 0;i<c*(c^2-1)/2;i++) {
    		combinationID[i]=1;
    	}
        
        //create list of cities used in this iteration
        List<String> edgeList = new ArrayList<String>() {
            {
            	for (int i=0;i<c;i++) add(cityList.get(i));
            }
        };
        
        //create list of all combinations
        List<String[]> pairs = new ArrayList<>();
        for (int i = 0; i < edgeList.size(); ++i) {
            for (int j = i + 1; j < edgeList.size(); ++j) {
                pairs.add(new String[]{edgeList.get(i), edgeList.get(j)});
            }
        }
        
        //inserting edges into graph
        for (int i = c*2;i<combinationID.length; i++) {
        	String param1 = null;
        	String param2 = null;
        	while (param1 == null) {
        		if (combinationID[rand.nextInt(combinationID.length)] == 1) {
        			String[] temp = pairs.get(i);
        			param1 = temp[0];
        			param2 = temp[1];
        			combinationID[i] = -1;
        		}
        	}
        	g.addEdge(param1, param2, rand.nextInt());
        }
        

 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}