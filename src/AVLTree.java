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

        public int getBalance() {
            if (this.left == null && this.right == null)
                return 0;
            if (this.left == null)
                return -1*this.right.getHeight();
            if (this.right == null)
                return this.left.getHeight();
            else    
                return this.left.getHeight() - this.right.getHeight();
        }
    }//End of Vertex class.
//-------------------------------------------------------------------------------------------------------------------------------------------

    Vertex root = null;
    int size = 0;

    public AVLTree(){}

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
    private Vertex rightRotation(Vertex z) {
        Vertex y = z.left; 
        Vertex T2 = y.right; 

        y.right = z; 
        z.left = T2; 

        fixHeight(z); 
        fixHeight(y);

        return y;
    }

    private Vertex leftRotation(Vertex z) {
        Vertex y = z.right; 
        Vertex T2 = y.left; 

        y.left = z; 
        z.right = T2; 

        fixHeight(z);
        fixHeight(y);

        return y; 
    }


    // End of rotation methods.
//-------------------------------------------------------------------------------------------------------------------------------------------

    public boolean isEmpty() {return this.size == 0;}

    // Helper method to make sure we don't call on null.
    private static void fixHeight(Vertex vertex) {

        if (vertex.left == null && vertex.right == null) {
            vertex.height = 1;
            return;
        }
        if (vertex.left == null) {
            vertex.height = vertex.right.getHeight() + 1;
            return;
        }
        if (vertex.right == null) {
            vertex.height = vertex.left.getHeight() + 1;
            return; 
        }
        vertex.height = Math.max(vertex.left.getHeight(), vertex.right.getHeight()) + 1;
    }

    public void insert(int newData) {
        this.root = this.insert(root, newData);
    }

    private Vertex insert(Vertex vertex,int newData) {
        
        if (vertex == null) {
            this.root = new Vertex(newData);
            return this.root;
        }
        
        if (newData < vertex.getData()) {
            vertex.setLeft(this.insert(vertex.left, newData));
        }
        else if (newData > vertex.getData()) {
            vertex.setRight(this.insert(vertex.right, newData));
        }
        else {
            return vertex;
        }

        fixHeight(vertex);
        int balance = vertex.getBalance();

        //Check all four rotation cases
        //left left
        if (balance > 1 && newData < vertex.left.getData()) {
            return rightRotation(vertex);
        }
        //left right kink
        if (balance > 1 && newData > vertex.left.getData()) {
            vertex.setLeft(leftRotation(vertex.left));
            return rightRotation(vertex);
        }
        //right right
        if (balance < -1 && newData > vertex.right.getData()) {
            return leftRotation(vertex);
        }
        //right left kink
        if (balance < -1 && newData < vertex.right.getData()) {
            vertex.right = rightRotation(vertex.right);
            return leftRotation(vertex);
        }

        return vertex;
    }// End insert();

    private static Vertex getNextIninOrder(Vertex vertex) {
        Vertex curr = vertex;

        while (curr.left != null) {
            curr = curr.getLeft();
        }

        return curr;
    }

    public void remove(int newData) {
        this.root = this.remove(root, newData);
    }

    //returns root after it's done.
    private Vertex remove(Vertex root, int removedData) {
        if (root == null)
            return root;

        if (removedData < root.getData())
            root.left = remove(root.left, removedData);
        
        else if (removedData > root.getData())
            root.right = remove(root.right, removedData);

        
        else {
            // Vertex with only one child or no child
            if ((root.left == null) || 
                (root.right == null)) {
                Vertex temp = root.left != null ? 
                            root.left : root.right;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of 
                                 // the non-empty child
            } else {
                // Vertex with two children: Get the 
                // inorder successor (smallest in 
                // the right subtree)
                Vertex temp = getNextIninOrder(root.right);

                // Copy the inorder successor's 
                // data to this Vertex
                root.data = temp.data;

                // Delete the inorder successor
                root.right = remove(root.right, temp.data);
            }
        }

        // If the tree had only one Vertex then return
        if (root == null)
            return root;

        fixHeight(root);
        int balance = root.getBalance();

        // Left Left Case
        if (balance > 1 && root.left.getBalance() >= 0)
            return rightRotation(root);

        // Left Right Case
        if (balance > 1 && root.left.getBalance() < 0) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }

        // Right Right Case
        if (balance < -1 && root.right.getBalance() <= 0)
            return leftRotation(root);

        // Right Left Case
        if (balance < -1 && root.right.getBalance() > 0) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    // Helper method that searches the tree and returns the vertex of the given arguement. returns null if data does not exist in tree.
    public Vertex find(int data) {

        Vertex curr = this.getRoot();

        while (curr != null) {
            if (curr.getData() == data) return curr;
            if (data < curr.getData()) curr = curr.getLeft();
            if (data > curr.getData()) curr = curr.getRight();
        }

        return null;
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

    private void preOrderHelper(Vertex vertex, ArrayList<Integer> data) {
        if (vertex == null) return;
        data.add(vertex.getData());
        preOrderHelper(vertex.getLeft(), data);
        preOrderHelper(vertex.getRight(), data);
    }

    public ArrayList<Integer> inOrder() {
        ArrayList<Integer> data = new ArrayList<>();
        this.inOrderHelper(this.getRoot(), data);
        return data;
    }

    private void inOrderHelper(Vertex vertex, ArrayList<Integer> data) {
        if (vertex == null) return;
        inOrderHelper(vertex.getLeft(), data);
        data.add(vertex.getData());
        inOrderHelper(vertex.getRight(), data);
    }

    //Method to graphically display the tree.(Got it from AI)
    public void display() { 
        if (root == null) return;

        displayHelper(root, 0);
        }

        private void displayHelper(Vertex node, int level) {
            if (node == null) return;
            
            displayHelper(node.getRight(), level + 1);
            
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(node.getData());
            
            displayHelper(node.getLeft(), level + 1);
        
    }

}// End of AVL tree class