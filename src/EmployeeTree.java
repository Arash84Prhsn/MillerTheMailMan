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

    public void insert(String[] newChildren) {

        

    }




}

