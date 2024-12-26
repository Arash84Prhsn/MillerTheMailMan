public class AVLTree
{
//-------------------------------------------------------------------------------------------------------------------------------------------
private class Vertex {

        private int data;
        private Vertex left = null;
        private Vertex right = null;
        private Vertex parent = null;
        
        int rightDecendents = 0;
        int leftDecendents = 0;
        
        //Vertex constructors:
        public Vertex(int data, Vertex left, Vertex right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        
        public Vertex(int data, Vertex left, Vertex right, Vertex parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Vertex(int data) {
            this.data = data;
        }

        public Vertex(){this.data = -1;}

        //Vertex getter methods:
        public int getData() {
            return this.data;
        }
        public Vertex getLeft() {
            return this.left;
        }
        public Vertex getRight() {
            return this.right;
        }
        public Vertex getParent() {
            return this.parent;
        }

        //Vertex setter methods:
        public void setData(int data) {this.data = data;}
        public void setLeft(Vertex left) {this.left = left;}
        public void setRight(Vertex right) {this.right = right;}
        public void setParent(Vertex parent) {this.parent = parent;}

        //Additional Vertex methods:
        public boolean hasLeft() {
            return this.left!=null;
        }
        
        public boolean hasRight() {
            return this.right!=null;
        }

        public boolean isLeftSubtree() {
            return this.parent.getLeft() == this;
        }
        
        public boolean isRightSubtree() {
            return this.parent.getRight() == this;
        }

        public void incrementLeftDescendents(){leftDecendents++;}
        public void incrementRightDescendents(){rightDecendents++;}

        public boolean VerifyAVL(){
            int dif = Math.abs(leftDecendents - rightDecendents);
            if (dif > 1) return false;
            return true;
        }


    }//End of Vertex class.
//-------------------------------------------------------------------------------------------------------------------------------------------

    Vertex root;
    int size = 0;

    //getter methods:
    public Vertex getRoot() {return this.root;}
    public int getSize() {return this.size;}
    public int getRootData() {return this.root.getData();}

    //Setter methods:
    public void setRoot(Vertex root) {this.root = root;}
    public void setSize(int size) {this.size = size;}
    public void setRootData(int data) {this.getRoot().setData(data);}
//-------------------------------------------------------------------------------------------------------------------------------------------
    //AVL tree rotation methods:
    private void leftRotation(Vertex y) {
        Vertex t1 = y.getParent().getLeft();
        Vertex t2 = y.getLeft();
        y.setLeft(y.getParent());
        
        if (y.getParent().getParent() != null)
            y.getParent().getParent().setRight(y);
        
        y.setParent(y.getParent().getParent());

        y.getLeft().setParent(y);
        
        y.getLeft().setLeft(t1);
        y.getLeft().setRight(t2);
        if (t2 != null)
            t2.setParent(y.getLeft());
    }
    
    private void rightRotation(Vertex y) {
        Vertex t1 = y.getRight();
        Vertex t2 = y.getParent().getRight();
        y.setRight(y.getParent());
        
        if (y.getParent().getParent() != null)
            y.getParent().getParent().setLeft(y);
        
        y.setParent(y.getParent().getParent());

        y.getRight().setParent(y);
        
        y.getRight().setLeft(t1);
        y.getRight().setRight(t2);
        
        if (t1 != null)
            t1.setParent(y.getLeft());
    }

    private void leftRightRotation(Vertex z) {
        leftRotation(z);
        rightRotation(z);
    }

    private void rightLeftRotaion(Vertex z) {
        rightRotation(z);
        leftRotation(z);
    }
    //End of rotation methods.
//-------------------------------------------------------------------------------------------------------------------------------------------

    public boolean isEmpty() {return this.size == 0;}

    public void insert(int newData) {
        
        Vertex newVertex = new Vertex(newData);

        if (this.isEmpty()) {
            this.setRoot(newVertex);
            size++;
            return;
        }

        
        Vertex curr = this.root;

        while (true) {
            if (newVertex.getData() > curr.getData() && curr.getRight() != null) curr = curr.getRight();

            else if (newVertex.getData() > curr.getData() && curr.getRight() == null) {curr.setRight(newVertex); break;}

            else if (newVertex.getData() < curr.getData() && curr.getLeft() != null) curr = curr.getLeft();

            else if (newVertex.getData() < curr.getData() && curr.getLeft() == null) {curr.setLeft(newVertex); break;}
        }

        this.size++;
        Vertex z = findUnbalanced(newVertex);
        
        if (z != null) {
            



        }

    }

    private Vertex findUnbalanced(Vertex vert) {
        
        Vertex unbalanced = null;

        while (vert.getParent() != null) {
            

            if (vert.isLeftSubtree()) {
                
                vert = vert.getParent();
                vert.incrementLeftDescendents();
                
                if (!vert.VerifyAVL()) return vert;

            }

            else {

                vert = vert.getParent();
                vert.incrementRightDescendents();
                
                if (!vert.VerifyAVL()) return vert;
            }
        }//End while

        return vert;
    
    }

}
