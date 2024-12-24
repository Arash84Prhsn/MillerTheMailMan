public class AVLTree 
{

    private class Vertex {

        int data;
        Vertex left = null;
        Vertex right = null;
        Vertex parent = null;
        //int h;//the hieght difference between the left and right subtrees.
        
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

    }

    Vertex root;
    int size = 0;

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



}
