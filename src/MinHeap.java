public class MinHeap {

    private class Vertex {
        
        private int data;
        private Vertex left;
        private Vertex right;
        private Vertex parent;

        public Vertex(int data, Vertex left, Vertex right, Vertex parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Vertex(int data) {
            this(data, null, null, null);
        }

        public Vertex() {
            this(-1);
        }

        public int getData() {return this.data;}
        public Vertex getLeft() {return this.left;}
        public Vertex getRight() {return this.right;}
        public Vertex getParent() {return this.parent;}

        public void setData(int newData) {this.data = newData;}
        public void setLeft(Vertex newLeft) {this.left = newLeft;}
        public void setRight(Vertex newRight) {this.right = newRight;}
        public void setParent(Vertex newParent) {this.parent = newParent;}

        public boolean hasLeft() {return this.left != null;}
        public boolean hasRight() {return this.right != null;}

        public Vertex getMinChild() {
            if (this.hasLeft() && this.hasRight()) 
                return this.left.getData() <= this.right.getData() ? this.left : this.right;
             
            if (this.hasLeft()) 
                return this.left;
             
            if (this.hasRight()) 
                return this.right;
             
            return null;
        }



        public boolean equals(Vertex v) {
            return this.data == v.data;
        }
    }// End Vertex class


    private Vertex root = null;
    private int size = 0;

    public MinHeap(){}
    public MinHeap(int[] array) {
        for (int i : array) {
            this.insert(i);
        }
    }

    public int getSize() {return this.size;}
    public Vertex getRoot() {return this.root;}
    public int getRootData() {return this.root.getData();}

    public boolean isEmpty() {return size==0;}

    public static void heapSort(int[] array) {
        MinHeap heap = new MinHeap(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.removeMin();
        }
    }

    public void insert(int data) {
        
        if (this.isEmpty()) {
            this.root = new Vertex(data);
            size++;
            return;
        }

        Vertex insertedVertex = null;
        Queue<Vertex> queue = new Queue<>();
        Vertex current = this.root;
        queue.enqueue(current);

        while (!queue.isEmpty()) {
            current = queue.dequeue();

            if (!current.hasLeft()) {
                insertedVertex = new Vertex(data, null, null, current);
                current.setLeft(insertedVertex);
                size++;
                break;
            } 
            else {
                queue.enqueue(current.getLeft());
            }

            if (!current.hasRight()) {
                insertedVertex = new Vertex(data, null, null, current);
                current.setRight(insertedVertex);
                size++;
                break;
            } 
            else {
                queue.enqueue(current.getRight());
            }
        }

        this.bubbleUp(insertedVertex);

    }// End insert

    public int removeMin() {
        
        if (this.isEmpty()) return -1;

        int min = this.getRootData();

        Queue<Vertex> queue = new Queue<>();
        Vertex current = this.root;
        queue.enqueue(current);

        while (!queue.isEmpty()) {
            current = queue.dequeue();

            if (current.hasLeft()) {
                queue.enqueue(current.getLeft());
            }
            if (current.hasRight()) {
                queue.enqueue(current.getRight());
            }
        }
        // Now current is the last vertex of the heap.

        Vertex parentVertex = current.getParent();
        // Edge case where current was parent and now parent is null:
        if (parentVertex == null || this.getSize() == 1) {
            this.root = null;
            size--;
            return min;
        }
        // Otherwise continue:
        this.root.setData(current.getData());

        if (parentVertex.hasRight()) {
            parentVertex.setRight(null);
        }
        else {
            parentVertex.setLeft(null);
        }
        
        this.size--;
        this.bubbleDown(this.root);
        return min;
    }

    private void bubbleDown(Vertex root) {
        
        if (root.hasLeft()) {
            
            Vertex smaller = root.getMinChild();
            if (smaller.getData() >= root.getData()) return;
            
            if (root.getLeft().equals(smaller)) {
                this.swap(root, root.getLeft());
                this.bubbleDown(root.getLeft());
            }
            else {
                this.swap(root, root.getRight());
                this.bubbleDown(root.getRight());
            }
        }
    }

    private void swap(Vertex parent, Vertex child) {
        int temp = parent.getData();
        parent.setData(child.getData());
        child.setData(temp);
    }

    private void bubbleUp(Vertex current) {
        
        if (current.getParent() == null) return;

        if (current.getData() < current.getParent().getData()) {
            this.swap(current, current.getParent());
            this.bubbleUp(current.getParent());
        }
    }

    public void levelOrderPrint() {
        
        if (this.isEmpty()) return;

        Queue<Vertex> queue = new Queue<>();
        Vertex current = this.root;
        queue.enqueue(current);

        while (!queue.isEmpty()) {
            current = queue.dequeue();
            System.out.print(current.getData() + " ");

            if (current.hasLeft()) {
                queue.enqueue(current.getLeft());
            }
            if (current.hasRight()) {
                queue.enqueue(current.getRight());
            }
        }
        System.out.println();
    }

}
