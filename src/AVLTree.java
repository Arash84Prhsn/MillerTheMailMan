public class AVLTree
{
//-------------------------------------------------------------------------------------------------------------------------------------------
private class Vertex {

        private int data;
        private Vertex left = null;
        private Vertex right = null;
        private Vertex parent = null;
        
        int rightDecendants = 0;
        int leftDecendants = 0;
        
        // Vertex constructors:
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

        // Vertex getter methods:
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

        // Vertex setter methods:
        public void setData(int data) {this.data = data;}
        public void setLeft(Vertex left) {this.left = left;}
        public void setRight(Vertex right) {this.right = right;}
        public void setParent(Vertex parent) {this.parent = parent;}

        // Additional Vertex methods:
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

        public void incrementLeftDescendants(){this.leftDecendants++;}
        public void incrementRightDescendants(){this.rightDecendants++;}
        public void decrementLeftDescendant(){this.leftDecendants--;}
        public void decrementRightDescendant(){this.rightDecendants--;}
    
        public boolean VerifyAVL(){
            int dif = Math.abs(leftDecendants - rightDecendants);
            if (dif > 1) return false;
            return true;
        }


    }//End of Vertex class.
//-------------------------------------------------------------------------------------------------------------------------------------------

    Vertex root;
    int size = 0;

    // getter methods:
    public Vertex getRoot() {return this.root;}
    public int getSize() {return this.size;}
    public int getRootData() {return this.root.getData();}

    // Setter methods:
    public void setRoot(Vertex root) {this.root = root;}
    public void setSize(int size) {this.size = size;}
    public void setRootData(int data) {this.getRoot().setData(data);}
//-------------------------------------------------------------------------------------------------------------------------------------------
    // AVL tree rotation methods:
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

    private void leftRightRotation(Vertex x) {
        leftRotation(x);
        rightRotation(x);
    }

    private void rightLeftRotaion(Vertex x) {
        rightRotation(x);
        leftRotation(x);
    }
    // End of rotation methods.
//-------------------------------------------------------------------------------------------------------------------------------------------

    public boolean isEmpty() {return this.size == 0;}

    public void insert(int newData) {
        
        Vertex newVertex = new Vertex(newData);

        if (this.isEmpty()) {
            this.setRoot(newVertex);
            this.size++;
            return;
        }

        Vertex curr = this.root;

        while (true) {

                 if (newVertex.getData() > curr.getData() && curr.getRight() != null) curr = curr.getRight();

            else if (newVertex.getData() > curr.getData() && curr.getRight() == null) {curr.setRight(newVertex); break;}

            else if (newVertex.getData() < curr.getData() && curr.getLeft() != null) curr = curr.getLeft();

            else if (newVertex.getData() < curr.getData() && curr.getLeft() == null) {curr.setLeft(newVertex); break;}
        }// End while.

        this.size++;
        
        if (this.getSize() >= 3) {
        
            Vertex[] zyx = findUnbalanced(newVertex, "insert");
            Vertex z = zyx[0];
            Vertex y = zyx[1];
            Vertex x = zyx[2];
            
            if (z != null) {
                
                if (y.isLeftSubtree() && x.isLeftSubtree()) {
                    this.rightRotation(y);
                    return;
                }

                if (y.isRightSubtree() && x.isRightSubtree()) {
                    this.leftRotation(y);
                    return;
                }

                if (y.isLeftSubtree() && x.isRightSubtree()) {
                    this.leftRightRotation(x);
                    return;
                }

                if (y.isRightSubtree() && x.isLeftSubtree()) {
                    this.rightLeftRotaion(x);
                    return;
                }
            }// End inner if
        }// End if
    }// End insert();
    
    // A helper method to find the unbalanced Vertex.
    private Vertex[] findUnbalanced(Vertex x, String func) {
        
        Vertex[] zyx = new Vertex[3];
        
        if (func.equals("insert")) {
            zyx[2] = x;
            
            Vertex y = x.getParent();
            Vertex z = y.getParent();

            if (x.isLeftSubtree()) y.incrementLeftDescendants();
            else if (x.isRightSubtree()) y.incrementRightDescendants();

            if (y.isLeftSubtree()) z.incrementLeftDescendants();
            else if (y.isRightSubtree()) z.incrementRightDescendants();

            while (z!=null) {
                
                if (!z.VerifyAVL()) return zyx;

                x = y;
                y = z;
                z = z.getParent();
                
                if (y.isLeftSubtree() && z!=null) z.incrementLeftDescendants();
                else if (y.isRightSubtree() && z!= null) z.incrementRightDescendants();
            
                zyx[0] = z; zyx[1] = y; zyx[2] = x;

                continue;
            }// End while.
        }// End if.

        else if (func.equals("removedLeft")) {

            Vertex y = x.getParent();
            Vertex z = y.getParent();
            
            x.decrementLeftDescendant();

            if (x.isLeftSubtree()) y.decrementLeftDescendant();
            else if (x.isRightSubtree()) y.decrementRightDescendant();

            if (y.isLeftSubtree()) z.decrementLeftDescendant();
            else if (y.isRightSubtree()) z.decrementRightDescendant();
            

        }

        return zyx;
    }// End findUnbalanced().

    // Helper method that searches the tree and returns the vertex of the given arguement. returns null if data does not exist in tree.
    private Vertex find(int data) {

        Vertex curr = this.getRoot();

        while (curr != null) {
            if (curr.getData() == data) return curr;
            if (curr.getData() > data) curr = curr.getLeft();
            if (curr.getData() < data) curr = curr.getRight();
        }

        return null;
    }

    // Helper method that removes that nulls a certain vertex and removes refrence to the vertex from it's parent.
    // !!!This method does not decrement the size of the tree.
    private int nullVertex(Vertex v) {

        int data = v.getData();

        if (v.getParent() == null) {
            return data;
        }

        if (v.isLeftSubtree()) {
            v.getParent().setLeft(null);
        }

        if (v.isRightSubtree()) {
            v.getParent().setRight(null);
        }

        return data;
    }

    public int remove(int data) {
        
        Vertex removedVertex = this.find(data);

        if (removedVertex == null) return -1;
        size--; // Since the removedVertex is not null we will decrement size as we now know for sure that a Vertex is getting removed.

        Vertex parentOfRemoved = null;

        if (removedVertex.getLeft() == null && removedVertex.getRight() == null) {
            parentOfRemoved = removedVertex.getParent();
            this.nullVertex(removedVertex);
        }

        else if (removedVertex.hasRight()) {
            Vertex replacement = removedVertex.getRight();
            while (replacement.hasLeft()) {
                replacement = replacement.getLeft();
            }
            parentOfRemoved = replacement.getParent();
            removedVertex.setData(replacement.getData());
            this.nullVertex(replacement);
        }

        else if (removedVertex.hasLeft()) {
            Vertex replacement = removedVertex.getLeft();
            while (replacement.hasRight()) {
                replacement = replacement.getRight();
            }
            parentOfRemoved = replacement.getParent();
            removedVertex.setData(replacement.getData());
            this.nullVertex(replacement);
        }

        findUnbalanced(parentOfRemoved, "remove");


        return data;
    }



}// End of AVL tree class
