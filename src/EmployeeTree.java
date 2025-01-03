// Phase3:
public class EmployeeTree {

// Private Vertex class.---------------------------------------------------------- 
    private class Vertex {

        // Vertex attributes:
        String data = "";
        Vertex[] children = null;
        Vertex parent = null;

        // Vertex constructors:
        public Vertex(){}
        public Vertex(String data) {this.data = data;}
        public Vertex(String data, Vertex parent) {this.data = data; this.parent = parent;}
        public Vertex(String data, Vertex[] children, Vertex parent) {
            this.data = data;
            this.children = children;
            this.parent = parent;
        }

        // Getter methods:
        public String getData() {return data;}
        public Vertex[] getChildren() {return children;}
        public Vertex getParent() {return parent;}

        // Setter methods:
        public void setData(String data) {this.data = data;}
        public void setChildren(Vertex[] children) {this.children = children;}
        public void setParent(Vertex parent) {this.parent = parent;}

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
    
    public void insert(String[] newInsert) {

        String[] childern = new String[newInsert.length-1];

        for (int i = 1; i < newInsert.length; i++)
            childern[i-1] = newInsert[i];

        // In the case that the tree is empty just add the new items normally.
        if (this.isEmpty()) {
            
            Vertex newRoot = new Vertex(newInsert[0]);
            
            Vertex[] childrenVertexes = new Vertex[childern.length];

            for (int j = 0; j < childern.length; j++) {
                childrenVertexes[j] = new Vertex(childern[j], newRoot);
                size++;
            }

            newRoot.setChildren(childrenVertexes);
            this.setRoot(newRoot);
            size++;
            return;
        }

        // If the tree is not empty, Go through the tree in an breadth first manner find the parent and then add the children.
        Queue<Vertex> queue = new Queue();



    }




}

