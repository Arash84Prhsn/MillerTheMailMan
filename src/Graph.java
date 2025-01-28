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
        public void setData(int newData) {
            this.data = newData;
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
    }

    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public Graph(int[][] adjacencyMatrix)

    
    


}
