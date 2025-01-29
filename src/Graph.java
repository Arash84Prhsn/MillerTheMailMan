// Phase2:
import java.util.ArrayList;

public class Graph {

    private class Vertex {
        
        private int data;

        public Vertex(int data) {
            this.data = data;
        }
        public Vertex() {
            this(-1);
        }

        public int getData() {return this.data;}
        public void setData(int newData) {this.data = newData;}

        public boolean equals(Vertex vertex) {
            return this.data == vertex.data;
        }
    }

    private class Edge {
        
        private Vertex startingVertex;
        private Vertex endingVertex;
        private int weight;

        public Edge (Vertex startingVertex, Vertex endingVertex, int weight) {
            this.startingVertex = startingVertex;
            this.endingVertex = endingVertex;
            this.weight = weight;
        }

        public Edge() {
            this(null, null, 0);
        }

        //Getters:
        public Vertex getStartingVertex() {return this.startingVertex;}
        public Vertex getEndingVertex() {return this.endingVertex;}
        public int getWeight() {return this.weight;}
        
        //Setters:
        public void setStartingVertex(Vertex vertex) {
            this.startingVertex = vertex;
        }
        public void setEndingVertex(Vertex vertex) {
            this.endingVertex = vertex;
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }

        public boolean contains(Vertex vertex) {
            return this.startingVertex.equals(vertex) || this.endingVertex.equals(vertex);
        }
    }

    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    // Constructor initializes the graph from an adjacencyMatrix.
    public Graph(int[][] adjacencyMatrix) {

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            this.vertices.add(new Vertex(i));
        }

        for (int i = 0; i < adjacencyMatrix.length-1; i++) {
            for (int j = 1+i; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    this.edges.add(new Edge(this.vertices.get(i), this.vertices.get(j), adjacencyMatrix[i][j]));
                }
            }
        }
    }

    //Getters:
    public ArrayList<Vertex> getVertices() {return this.vertices;}
    public ArrayList<Edge> getEdges() {return this.edges;}

    //Setters:
    public void setVertices(ArrayList<Vertex> vertices) {this.vertices = vertices;}
    public void setEdges(ArrayList<Edge> edges) {this.edges = edges;}
    
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    public void addEdge(Vertex startingVertex, Vertex endingVertex, int weight) {
        this.edges.add(new Edge(startingVertex, endingVertex, weight));
    }

    // !!!this solution to TSP only works if an Edge exists from the final vertex to the starting vertex.
    // Nearest Neighbor Heuristic algorithm to solve the TSP problem
    public ArrayList<Vertex> tspPath() {
        ArrayList<Vertex> visited = new ArrayList<>();
        Vertex startingVertex = this.vertices.get(0);
        visited.add(startingVertex);

        while (visited.size() < this.vertices.size()) {
            
            Vertex current = visited.get(visited.size() - 1);// get last visited vertex
            Edge minEdge = new Edge(null, null, Integer.MAX_VALUE);
            
            for (Edge edge : this.edges) {
                if (edge.getStartingVertex().equals(current) && edge.getWeight() < minEdge.getWeight() && !visited.contains(edge.getEndingVertex())) {
                    minEdge = edge;
                    Vertex next = edge.getEndingVertex();
                }
                else if (edge.getEndingVertex().equals(current) && edge.getWeight() < minEdge.getWeight() && !visited.contains(edge.getStartingVertex())) {
                    minEdge = edge;
                    Vertex next = edge.getStartingVertex();
                }
            }
        }
        visited.add(startingVertex);
        return visited;
    }
    
    public int tspTotalWeight() {
        ArrayList<Vertex> path = tspPath();
        int totalWeight = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex start = path.get(i);
            Vertex end = path.get(i + 1);
            for (Edge edge : this.edges) {
                if (edge.contains(start) && edge.contains(end)) {
                    totalWeight += edge.getWeight();
                    break;
                }
            }
        }
        return totalWeight;
    }
    
    // Returns all edges in mst
    public ArrayList<Edge> primMST() {
        
        ArrayList<Edge> mst = new ArrayList<>();
        ArrayList<Vertex> visited = new ArrayList<>();// the vertices that have been visited and are in mst
        // Suppose the first vertex is where Miller starts from.                          
        Vertex startingVertex = this.vertices.get(0);
        visited.add(startingVertex);
    
        while (visited.size() < this.vertices.size()) {
            Edge newEdge = new Edge(null, null, -1);// initialize with negetive weight.
            for (Edge edge : this.edges) {
                if (visited.contains(edge.getStartingVertex()) && !visited.contains(edge.endingVertex) && edge.getWeight() < newEdge.getWeight()) {
                    newEdge = edge;
                }
            }// End for loop we have found our new edge for mst
    
            // Add the newly visited Vertex to the visiste list:
            if (visited.contains(newEdge.getStartingVertex())) {
                visited.add(newEdge.getEndingVertex());
            }
            else {
                visited.add(newEdge.getStartingVertex());
            }
        }// End while loop, all Vertices have been visited.
        
        return mst;
    }
    

    // Returns the vertices that were visisted while building mst.
    public ArrayList<Vertex> primVertices() {
        
        ArrayList<Edge> mst = new ArrayList<>();
        ArrayList<Vertex> visited = new ArrayList<>();// the vertices that have been visited and are in mst
        // Suppose the first vertex is where Miller starts from.                                 
        Vertex startingVertex = this.vertices.get(0);
        visited.add(startingVertex);
    
        while (visited.size() < this.vertices.size()) {
            Edge newEdge = new Edge(null, null, -1);// initialize with negetive weight.
            for (Edge edge : this.edges) {
                if (visited.contains(edge.getStartingVertex()) && !visited.contains(edge.endingVertex) && edge.getWeight() < newEdge.getWeight()) {
                    newEdge = edge;
                }
            }// End for loop we have found our new edge for mst
    
            // Add the newly visited Vertex to the visiste list:
            if (visited.contains(newEdge.getStartingVertex())) {
                visited.add(newEdge.getEndingVertex());
            }
            else {
                visited.add(newEdge.getStartingVertex());
            }
        }// End while loop, all Vertices have been visited.
        
        return visited;
    }
}
