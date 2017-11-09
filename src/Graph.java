/*
 * CZ2001 Algorithms - Year 2017/2018 Semester 1
 * Lab 4 -  Application of BFS To Flight Scheduling
 * 
 * Group 1
 * 
 * */

import java.util.*;

public class Graph
{
    private int noOfVertices;
    private ArrayList<Integer>[] vertexList;
    
    // Names of Cities
	List<String> cityList = Arrays.asList("Singapore","Kuala Lumpur", "Jakarta","Bangkok","Hanoi","Manila","Beijing","Pyongyang","Seoul","Taipei","Hong Kong","Tokyo","Sydney","Perth","New Zealand","Washington D.C.","New York","Los Angeles","Chicago","Texas","Seattle","Boston","London","Amsterdam","Berlin","Copenhagen","Moscow","Luxembourg","Paris","Rome","Stockholm","Vatican City","Toronto","Shanghai","Cairo","Istanbul","Dubai","Macau","Las Vegas","Prague","Barcelona","Venice","Budapest","Munich","Zurich","Chiang Mai","Vancouver","Melbourne","Rio De Janeiro","Frankfurt");
	
    public Graph(int noOfCities)
    {
    	this.noOfVertices = noOfCities;
    	vertexList = new ArrayList[noOfCities];
        for (int i = 0; i < noOfVertices; i++) {
        	vertexList[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int firstVertex,int secondVertex)
    {
    	vertexList[firstVertex].add(secondVertex);
    }
    
    public void printGraph () {
    	System.out.println("\nNow printing Graph:\n");
    	for (int i = 0; i < vertexList.length; i++) {
    		System.out.format("[%2d]: %15s -->", i, cityList.get(i)); // Prints the city name of the vertex
    		for (Integer cityIndex : vertexList[i]) {
        		System.out.format(" [%2s - %s]", cityIndex, cityList.get(cityIndex)); // Prints adjacent cities
    		}
    		System.out.println();
    	}
    }
    
    public void printGraph (boolean printAsCondensed) {
    	if (printAsCondensed == true) {
        	System.out.println("\nNow printing Graph (Condensed Form):\n");
        	for (int i = 0; i < vertexList.length; i++) {
        		System.out.format("[%2d]: %15s -->", i, cityList.get(i)); // Prints the city name of the vertex
        		for (Integer cityIndex : vertexList[i]) {
            		System.out.format(" [%2s]", cityIndex, cityList.get(cityIndex)); // Prints adjacent cities
        		}
        		System.out.println();
        	}
    	}
    	else {
    		printGraph();
    	}
    }
    
    public void printCities () {
    	System.out.println("\nNow printing list of cities:\n");
    	for (int i = 0; i < cityList.size(); i++) {
    		System.out.format("[%2d]: %s\n", i, cityList.get(i));
    	}
    	System.out.println();
    }
    
    public void printCities (int sizeOfGraph) {
    	System.out.println("\nNow printing list of cities (reduced):\n");
    	for (int i = 0; i < sizeOfGraph; i++) {
    		System.out.format("[%2d]: %s\n", i, cityList.get(i));
    	}
    	System.out.println();
    }
    
    public int[][] generateMatrixOfEdges () {	
		int [][] matrixToBeGenerated = new int[noOfVertices][noOfVertices];
		Random rand = new Random();
		
		int a, b;
		
		/* This generates a random noOfVertices x noOfVertices matrix filled with 0 or 1
		 * 0 = edge is absent (there is no flight connecting the two cities)
		 * 1 = edge is present (there is a flight connecting the two cities)
		 */
		for(a = 0; a < noOfVertices; a++ ) {
			for(b = 0; b < noOfVertices; b++ ) {
				matrixToBeGenerated[a][b] = rand.nextInt(2);
			}
		}
		
		/*
		 * The graph we are simulating is an undirected graph, hence we take one half of the matrix and reflect that
		 * half across the diagonal to get a matrix that is symmetrical with respect to the main diagonal. In this
		 * graph, it also does not make sense to have a self-edge (i.e. an edge from one node back to itself) and hence
		 * all [a][a] vertices in the matrix are by default set to 0.
		 * 
		 * */
		for(a = 0; a < noOfVertices; a++) {
			for (b = 0; b < a; b++) {
				matrixToBeGenerated[b][a] = matrixToBeGenerated[a][b]; // Reflect matrix w.r.t. main diagonal
			}
			matrixToBeGenerated[a][a] = 0; // No self-edges
		}
		
		// For debugging purposes - prints a visual representation of the matrix
		System.out.println("\nNow printing Matrix:\n");
		for(a = 0; a < noOfVertices; a++ ) {
			for(b = 0; b < noOfVertices; b++ ) {
				System.out.format("[%d]", matrixToBeGenerated[a][b]);
			}
			System.out.println();
		}
		return matrixToBeGenerated;
    }
    
    public void BFS(int sourceVertex, int destinationVertex)
    {
    	// Flag to be set when a shortest path to destination is found
    	boolean shortestPathFound = false;
    	
    	// By default, all vertices start off as not visited (i.e. false)
        boolean visitedVertices[] = new boolean[noOfVertices];
 
        // Queue for BFS Algorithm
        LinkedList<Integer> queue = new LinkedList<Integer>();
        
        // Maps every new adjVertex to the one that was visited before it
        // Using this, we can trace back a path (shortest path) to the source
        // In this map: key is adjVertex (after) while currentVertex (before) is the value (see below)
        Map<Integer, Integer> previousVertices = new HashMap<Integer, Integer>();
        
        // Visit sourceVertex, mark it in visitedVertices as true, then add it to queue
        visitedVertices[sourceVertex]=true;
        queue.add(sourceVertex);
        
        // currentVertex is used to iterate through the while loop below
        int currentVertex = sourceVertex;
        
        while (queue.size() != 0)
        {
        	currentVertex = queue.poll();
        	
        	if (currentVertex == destinationVertex) {
        		shortestPathFound = true;
        		break;
        	}
        	
            // For all adjacent vertices (adjVertex) of the dequeued currentVertex:
            // If an adjVertex has not been visited, then mark it as visited and queue it
            Iterator<Integer> iter = vertexList[currentVertex].listIterator();
            while (iter.hasNext()) {
            	Integer adjVertex = iter.next();
            	if (visitedVertices[adjVertex] == false){
            		visitedVertices[adjVertex] = true;
            		queue.add(adjVertex);
            		previousVertices.put(adjVertex, currentVertex);
            		if (adjVertex == destinationVertex) {
            			shortestPathFound = true;
            			break;
            		}
                }
            }
        }
        
        if (shortestPathFound) {
        	ArrayList<Integer> pathToTrace = new ArrayList<Integer>();
        	Integer cityToTrace = currentVertex;
        	while (cityToTrace != null) {
        		pathToTrace.add(cityToTrace);
        		cityToTrace = previousVertices.get(cityToTrace);
        	}
        	Collections.reverse(pathToTrace);
        	System.out.println("\nNow printing trace of shortest path:\n");
        	for (Integer cityIndex : pathToTrace) {
        		System.out.format("%2d : %s\n", cityIndex, cityList.get(cityIndex));
        	}
        }
        else {
        	System.out.println("\nPath not found\n");
        }
    }


    public static void main(String args[])
    {
    	Scanner sc = new Scanner(System.in);
    	
    	int sizeOfGraph;
    	int source, destination;
    	
    	System.out.println("How many cities?");
    	
    	sizeOfGraph = sc.nextInt();
    	sc.nextLine(); // Consume \n
    	
    	// Create Graph of size sizeOfGraph
        Graph g = new Graph(sizeOfGraph);
        
        // Edges are represented in matrix form (i.e. matrix[a][b] where a and b are cities)
        // This is where we insert each of these edges into the Graph
        int[][] matrixOfEdges = g.generateMatrixOfEdges();
        int a, b;
        for (a = 0; a < sizeOfGraph; a++) {
        	for (b = 0; b < sizeOfGraph; b++) {
        		if (matrixOfEdges[a][b] == 1) {
            		g.addEdge(a, b);
        		}
        		else {
        			continue;
        		}
        	}
        }
        
        // For debug purposes. Used to display the graph visually. Comes in two varieties: with city names, and without
        // To include city names, do not pass in any arguments. To exclude city names, pass in true.
        g.printGraph(true);
        
        g.printCities(sizeOfGraph);
        System.out.println("\nWhich city (vertex) to select as source? (please input the number associated with the city)");
        source = sc.nextInt();
        sc.nextLine(); // Consume \n
        System.out.println("\nWhich city (vertex) to select as destination? (please input the number associated with the city)");
        destination = sc.nextInt();
        sc.nextLine(); // Consume \n
        
        g.BFS(source, destination);
    }
}