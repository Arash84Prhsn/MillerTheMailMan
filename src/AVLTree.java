// Phase1:
import java.util.ArrayList;

public class AVLTree
{
//-------------------------------------------------------------------------------------------------------------------------------------------
private class Vertex {

        private int data;
        private Vertex left = null;
        private Vertex right = null;
        private Vertex parent = null;
        
        private int rightDescendants = 0;
        private int leftDescendants = 0;

        private int height = 1;

        // Vertex constructors:
        public Vertex(int data, Vertex left, Vertex right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        
        public Vertex(int data, Vertex left, Vertex right, Vertex parent) {
            this(data, left, right);
            this.parent = parent;
        }

        public Vertex(int data) {
            this.data = data;
        }

        public Vertex() {
            this.data = -1;
        }

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
        public int getRightDescendants() {
            return this.rightDescendants;
        }
        public int getLeftDescendants() {
            return this.leftDescendants;
        }

        public int getHeight() {
            return this.height;
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
            if (this.parent == null) return false;
            return this.data <= this.parent.getData();
        }
        
        public boolean isRightSubtree() {
            if (this.parent == null) return false;
            return this.data >= this.parent.getData();
        }

        public void incrementLeftDescendants() {this.leftDescendants++;}
        public void decrementLeftDescendants() {this.leftDescendants--;}
        public void incrementRightDescendants(){this.rightDescendants++;}
        public void decrementRightDescendants(){this.rightDescendants--;}
    
        public boolean VerifyAVL(){// Checks to see if the AVL property is preserved or not.
            if (Math.abs(leftDescendants - rightDescendants) > 1) return false;
            return true;
        }

        // Returns the maximum of left Vertexes and right Vertexex of this Vertex.
        public int getMaxdescendants() {
            return Math.max(this.leftDescendants, this.rightDescendants);
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
        Vertex x = y.getRight();
        Vertex z = y.getParent();
        Vertex t2 = y.getLeft();
        Vertex parent = z.getParent();

        y.setLeft(z);
        z.setRight(t2);
        z.setParent(y);

        if (parent == null) {
            this.setRoot(y);
            y.setParent(null);
        }
        
        else {
            if (parent.getLeft() == z) {
                parent.setLeft(y);
            }
            else {
                parent.setRight(y);
            }
            y.setParent(parent);
        }

        z.rightDescendants = y.leftDescendants;
        y.leftDescendants = z.getMaxdescendants() + 1;
        y.rightDescendants = x.getMaxdescendants() + 1;

        
        while (parent != null) {
            if (parent.left == y)
                parent.leftDescendants = y.getMaxdescendants()+1;
            else
                parent.rightDescendants = y.getMaxdescendants()+1;

            y = parent;    
            parent = parent.getParent();
        }

    }
    
    private void rightRotation(Vertex y) {
        Vertex x = y.getLeft();
        Vertex z = y.getParent();
        Vertex t3 = y.getRight();
        Vertex parent = z.getParent();
        
        y.setRight(z);
        z.setLeft(t3);
        
        if (parent == null) {
            this.setRoot(y);
            y.setParent(null);
            z.setParent(y);
        }
        
        else {
            if (parent.getLeft() == z) {
                parent.setLeft(y);
            }
            else {
                parent.setRight(y);
            }
            y.setParent(parent);
            z.setParent(y);
        }

        z.leftDescendants = y.rightDescendants;
        y.leftDescendants = x.getMaxdescendants() + 1;
        y.rightDescendants = z.getMaxdescendants() + 1;

        while (parent != null) {
            if (parent.left == y) {
                parent.leftDescendants = y.getMaxdescendants() + 1;
            }
            else {
                parent.rightDescendants = y.getMaxdescendants() + 1;
            }
        }
    }

    private void leftRightRotation(Vertex x) {
        Vertex y = x.getParent();
        Vertex z = y.getParent();
        Vertex t2 = x.getLeft();
        z.setLeft(x);
        x.setLeft(y);
        y.setRight(t2);
        y.setParent(x);
        x.setParent(z);
        
        y.rightDescendants = x.leftDescendants;
        x.leftDescendants = y.getMaxdescendants() + 1;
        z.leftDescendants = x.getMaxdescendants() + 1;
        
        this.rightRotation(x);
    }

    private void rightLeftRotaion(Vertex x) {
        Vertex y = x.getParent();
        Vertex z = y.getParent();
        Vertex t3 = x.getRight();
        z.setRight(x);
        x.setRight(y);
        y.setLeft(t3);
        y.setParent(x);
        x.setParent(z);

        y.leftDescendants = x.rightDescendants;
        x.rightDescendants = y.getMaxdescendants() + 1;
        z.rightDescendants = x.getMaxdescendants() + 1;        

        this.leftRotation(x);
    }

    private Vertex rr(Vertex z) {
        Vertex y = z.left; 
        Vertex T2 = y.right; 

        y.right = z; 
        z.left = T2; 

        z.height = Math.max(z.left.getHeight(), z.right.getHeight()) + 1; 
        y.height = Math.max(y.left.getHeight(), y.right.getHeight()) + 1; 

        return y;
    }

    private Vertex lr(Vertex z) {
        Vertex y = z.right; 
        Vertex T2 = y.left; 

        y.left = z; 
        z.right = T2; 

        z.height = Math.max(z.left.getHeight(), z.right.getHeight()) + 1;
        y.height = Math.max(y.left.getHeight(), y.right.getHeight()) + 1;

        return y; 
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

            if (newVertex.getData() > curr.getData() && curr.getRight() != null) {
                curr = curr.getRight();
            }
            else if (newVertex.getData() > curr.getData() && curr.getRight() == null) {
                curr.setRight(newVertex); 
                newVertex.setParent(curr);
                break;
            }
            else if (newVertex.getData() < curr.getData() && curr.getLeft() != null) {
                curr = curr.getLeft();
            }
            else if (newVertex.getData() < curr.getData() && curr.getLeft() == null) {
                curr.setLeft(newVertex); 
                newVertex.setParent(curr);
                break;
            }
        }// End while.

        this.size++;
        
        if (this.getSize() >= 3) {
            Vertex[] zyx = findUnbalanced(newVertex, "insert");
            if (zyx[0] != null) {
                this.fixTree(zyx);
            }
        }
                
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

            if (y != null && y.isLeftSubtree() && z.getLeftDescendants() == y.getMaxdescendants()) z.incrementLeftDescendants();
            else if (y != null && y.isRightSubtree() && z.getRightDescendants() == y.getMaxdescendants()) z.incrementRightDescendants();

            while (z!=null) {
                
                if (!z.VerifyAVL()) return zyx;

                x = y;
                y = z;
                z = z.getParent();
                
                if (y.isLeftSubtree() && z!=null && z.getLeftDescendants() == y.getMaxdescendants()) 
                    z.incrementLeftDescendants();
                else if (y.isRightSubtree() && z!= null && z.getRightDescendants() == y.getMaxdescendants()) 
                    z.incrementRightDescendants();
            
                zyx[0] = z; zyx[1] = y; zyx[2] = x;

                continue;
            }// End while.
        }// End if.

        else { 
            
            if (func.equals("removedleft")) x.decrementLeftDescendants();
            else x.decrementRightDescendants();
            
            Vertex unbalanced = x;
            
            while (unbalanced.getParent() != null) {
                //ensures that the parent should decrement the leftDescendants or rightDescendants.
                if (unbalanced.isLeftSubtree() && (Math.abs(unbalanced.getParent().getLeftDescendants() - unbalanced.getMaxdescendants())) > 1) {
                    unbalanced.getParent().decrementLeftDescendants();
                }
                else if (unbalanced.isRightSubtree() && (Math.abs(unbalanced.getParent().getRightDescendants() - unbalanced.getMaxdescendants())) > 1) {
                    unbalanced.getParent().decrementRightDescendants();
                }
                
                if (!unbalanced.VerifyAVL()) {
                    zyx[0] = unbalanced;
                    zyx[1] = unbalanced.getRightDescendants() > unbalanced.getLeftDescendants() ? unbalanced.getRight() : unbalanced.getLeft();
                    
                    if (zyx[1].getRightDescendants() > zyx[1].getLeftDescendants()) {
                        zyx[2] = zyx[1].getRight();
                    }
                    
                    else if (zyx[1].getRightDescendants() < zyx[1].leftDescendants) {
                        zyx[2] = zyx[1].getLeft();
                    }
                    // The height of the left and right subtrees of y are equal therefore:
                    else if (zyx[1].isLeftSubtree()) {
                        zyx[2] = zyx[1].getLeft();
                    }
                    
                    else {
                        zyx[2] = zyx[1].getRight();
                    }
                    
                    break;
                }
                
                unbalanced = unbalanced.getParent();
            }// End while.
        }// End else.

        return zyx;
    }// End findUnbalanced().

    // Helper method that searches the tree and returns the vertex of the given arguement. returns null if data does not exist in tree.
    private Vertex find(int data) {

        Vertex curr = this.getRoot();

        while (curr != null) {
            if (curr.getData() == data) return curr;
            if (data < curr.getData()) curr = curr.getLeft();
            if (data > curr.getData()) curr = curr.getRight();
        }

        return null;
    }

    // Helper method that nulls a certain vertex and removes refrence to the vertex from it's parent.
    // !!!This method does not decrement the size of the tree.
    private int removeRefrence(Vertex v) {

        int data = v.getData();

        if (v.getParent() == null) {
            return data;
        }

        if (v.isLeftSubtree()) {
            v.getParent().setLeft(null);
            return data;
        }

        if (v.isRightSubtree()) {
            v.getParent().setRight(null);
            return data;
        }

        return data;
    }

    public int remove(int data) {
        
        Vertex removedVertex = this.find(data);

        if (removedVertex == null) return -1;
        size--; // Since the removedVertex is not null we will decrement size as we now know for sure that a Vertex is getting removed.

        Vertex parentOfRemoved = null;
        String position = new String();

        if (removedVertex.getLeft() == null && removedVertex.getRight() == null) {
            parentOfRemoved = removedVertex.getParent();
            position = removedVertex.isLeftSubtree() ? "left" : "right";
            this.removeRefrence(removedVertex);
        }

        else if (removedVertex.hasRight()) {
            Vertex replacement = removedVertex.getRight();
            while (replacement.hasLeft()) {
                replacement = replacement.getLeft();
            }
            parentOfRemoved = replacement.getParent();
            position = replacement.isLeftSubtree() ? "left" : "right";
            removedVertex.setData(replacement.getData());
            this.removeRefrence(replacement);
        }

        else if (removedVertex.hasLeft()) {
            Vertex replacement = removedVertex.getLeft();
            while (replacement.hasRight()) {
                replacement = replacement.getRight();
            }
            parentOfRemoved = replacement.getParent();
            position = replacement.isLeftSubtree() ? "left" : "right";
            removedVertex.setData(replacement.getData());
            this.removeRefrence(replacement);
        }

        Vertex[] zyx = this.findUnbalanced(parentOfRemoved, "removed" + position);

        if (zyx[0] != null) this.fixTree(zyx);
            
        return data;
    }

    private void fixTree(Vertex[] zyx) {
        Vertex y = zyx[1];
        Vertex x = zyx[2];
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
    }

    // Returns an ArrayList containing all data in tree through levelOrder traversal of vertices.
    public ArrayList<Integer> levelOrder() {

        if (this.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Integer> data = new ArrayList<>();
        Queue<Vertex> queue = new Queue<>();

        queue.enqueue(this.getRoot());

        while (!queue.isEmpty()) {
            Vertex current = queue.dequeue();
            data.add(current.getData());

            if (current.hasLeft()) {
                queue.enqueue(current.getLeft());
            }
            if (current.hasRight()) {
                queue.enqueue(current.getRight());
            }
        }

        return data;
    }

    public ArrayList<Integer> preOrder() {
        ArrayList<Integer> data = new ArrayList<>();
        this.preOrderHelper(this.getRoot(), data);
        return data;
    }

    private void preOrderHelper(Vertex node, ArrayList<Integer> data) {
        if (node == null) return;
        data.add(node.getData());
        preOrderHelper(node.getLeft(), data);
        preOrderHelper(node.getRight(), data);
    }

    public ArrayList<Integer> inOrder() {
        ArrayList<Integer> data = new ArrayList<>();
        this.inOrderHelper(this.getRoot(), data);
        return data;
    }

    private void inOrderHelper(Vertex node, ArrayList<Integer> data) {
        if (node == null) return;
        inOrderHelper(node.getLeft(), data);
        data.add(node.getData());
        inOrderHelper(node.getRight(), data);
    }

    // //Method to graphically display the tree.
    // public void display() { 
    //     if (root == null) return;

    //     displayHelper(root, 0);
    //     }

    //     private void displayHelper(Vertex node, int level) {
    //         if (node == null) return;
            
    //         displayHelper(node.getRight(), level + 1);
            
    //         for (int i = 0; i < level; i++) {
    //             System.out.print("    ");
    //         }
    //         System.out.println(node.getData());
            
    //         displayHelper(node.getLeft(), level + 1);
        
    // }

}// End of AVL tree class