// Phase3:

import java.util.ArrayList;

public class EmployeeTree {

// Private Vertex class.----------------------------------------------------------
    private class Vertex {

        // Vertex attributes:
        String data = "";
        ArrayList<Vertex> children = null;
        Vertex parent = null;

        // Vertex constructors:
        public Vertex(){}
        public Vertex(String data) {
            this.data = data;
        }
        public Vertex(String data, Vertex parent) {
            this.data = data;
            this.parent = parent;
        }
        public Vertex(String data, ArrayList<Vertex> children, Vertex parent) {
            this.data = data;
            this.children = children;
            this.parent = parent;
        }       

        // Getter methods:
        public String getData() {return data;}
        public ArrayList<Vertex> getChildren() {return children;}
        public Vertex getParent() {return parent;}

        // Setter methods:
        public void setData(String data) {this.data = data;}
        public void setChildren(ArrayList<Vertex> children) {this.children = children;}
        public void setParent(Vertex parent) {this.parent = parent;}

        public void addChild (String newChild) {
            this.children.add(new Vertex(newChild, this));
        }

        public void addToChildren(ArrayList<Vertex> newChildren) {
            for (Vertex v : newChildren) {
                this.getChildren().add(v);
            }
        }
        
        public void addToChildren(String[] newChildren) {
            for (String s : newChildren) {
                this.getChildren().add(new Vertex(s, this));
            }
        }

        public boolean equals(Vertex vertex) {
            return this.data == vertex.data;
        }
    }
// End of Vertex class.------------------------------------------------------------

    private Vertex root = null;
    private int size = 0;

    public EmployeeTree(){}

    public Vertex getRootVertex(){return this.root;}
    public String getRootData() {return this.getRootVertex().getData();}
    public int getSize() {return this.size;}

    public void setRoot(Vertex newRoot) {this.root = newRoot;}

    public boolean isEmpty() {return size==0;}
    
    public void insert(String[] newInsert) throws IllegalArgumentException {

        if (this.isEmpty()) {
            this.root = new Vertex(newInsert[0]);
            size++;
        }
        
        String parentName = newInsert[0];

        // Search the tree for the parent and then add the children.
        // BFS search:
        Vertex parentVertex = this.bfs(parentName);
        
        if (parentVertex == null) {
            throw new IllegalArgumentException("Invalid input. Parent does not exist in tree");
        }

        for (int i = 1; i < newInsert.length; i++) {
            parentVertex.addChild(newInsert[i]);
            size++;
        }
    }// End of insert.

    public void remove(String removedItem) throws IllegalArgumentException {

        if (this.isEmpty()) {
            throw new IllegalArgumentException("Tree is empty. No items to remove");
        }

        // In the case that we are removing the root:
        if (removedItem.equals(this.getRootData())) {
            this.size = 0;
            this.root = null;
            return ;
        }
        
        Vertex removedVertex = bfs(removedItem);
        if (removedVertex == null) {
            throw new IllegalArgumentException("Item does not exist in tree");
        }

        Vertex parentVertex = removedVertex.getParent();
        parentVertex.getChildren().remove(removedVertex);
        size--;
    }
    
    private Vertex bfs(String item) {

        if (this.isEmpty()) return null;
        
        Queue<Vertex> queue = new Queue<>();
        queue.enqueue(this.getRootVertex());

        while (!queue.isEmpty()) {
            Vertex current = queue.dequeue();
            
            if (current.getData().equals(item)) {
                return current;
            }
            
            for (Vertex v : current.getChildren()) {
                queue.enqueue(v);
            }

        }

        return null;
    }


}// End of EmployeeTree class.

