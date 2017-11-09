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
 
    public Graph(int noOfCities)
    {
    	this.noOfVertices = noOfCities;
    	vertexList = new ArrayList[noOfCities];
        for (int i = 0; i < noOfVertices; i++) {
        	vertexList[i] = new ArrayList<Integer>();
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
    	List<String> cityList = Arrays.asList("Singapore","Kuala Lumpur", "Jakarta","Bangkok","Hanoi","Manila","Beijing","Pyongyang","Seoul","Taipei","Hong Kong","Tokyo","Sydney","Perth","New Zealand","Washington D.C.","New York","Los Angeles","Chicago","Texas","Seattle","Boston","London","Amsterdam","Berlin","Copenhagen","Moscow","Luxembourg","Paris","Rome","Stockholm","Vatican City","Toronto","Shanghai","Cairo","Istanbul","Dubai","Macau","Las Vegas","Prague","Barcelona","Venice","Budapest","Munich","Zurich","Chiang Mai","Vancouver","Melbourne","Rio De Janeiro","Frankfurt"); //tbc, hardcoded
    	
    	Scanner sc = new Scanner(System.in);
    	Random rand = new Random();
    	System.out.println("How many cities?");
    	
    	//creat graph of size c
    	c = sc.nextInt();
        Graph g = new Graph(c);
        
        
        //init arraylist of pairs
        List<Pair> combinationList = new ArrayList<Pair>();
        
        //create list of all combinations for 0-c
        int i = 0;
        int j = i + 1;
        while(true) {
        	combinationList.add(new Pair(i,j));
        	j++;
        	if(j >= c) {
        		i++;
        		j = i + 1;
        		}
        	if(i >= c - 1) {
        		break;
        		}
        	}
        
        //combinationID list to check if that combination has been used before (no duplicates)
        int[] combinationID = new int[combinationList.size()];
    	for (int index = 0;index<combinationList.size();index++) {
    		combinationID[index]=1;
    	}
        
        //inserting edges into graph
        for (int index = 0;index<combinationID.length*2; index++) {
        	int param1 = -1;
        	int param2 = -1;
        	while (param1 == -1) {
        		if (combinationID[rand.nextInt(combinationID.length)] == 1) {
        			Pair temp = combinationList.get(i);
        			param1 = temp.x;
        			param2 = temp.y;
        			combinationID[i] = -1;
        		}
        	}
        	g.addEdge(param1, param2);
        }
        

 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}