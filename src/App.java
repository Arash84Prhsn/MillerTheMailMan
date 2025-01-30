public class App {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Phase1:");
        // Phase 1 Test:
        AVLTree avlTree = new AVLTree();
        avlTree.insert(22);     
        avlTree.insert(36);
        avlTree.insert(19);
        avlTree.insert(37);
        avlTree.insert(69);
        avlTree.insert(44);
        avlTree.insert(37);
        avlTree.insert(14);
        avlTree.insert(51);
        avlTree.insert(7);
        avlTree.insert(1);
        System.out.println("Preorder: " + avlTree.preOrder());
        System.out.println("Inorder: " + avlTree.inOrder());
        avlTree.display();
        System.out.println("----------------------------------------------------------------");
        
        avlTree.insert(99);
        avlTree.insert(58);
        System.out.println("Preorder: " + avlTree.preOrder());
        System.out.println("Inorder: " + avlTree.inOrder());
        avlTree.display();
        System.out.println("----------------------------------------------------------------");

        avlTree.remove(99);
        avlTree.remove(58);
        System.out.println("Preorder: " + avlTree.preOrder());
        System.out.println("Inorder: " + avlTree.inOrder());
        avlTree.display();
        
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
//------------------------------------------------------------------------------------------
        // Phase 2 Test:
        System.out.println("Phase2:");
        int[][] adjacencyMatrix = {
            {0, 12, 34, 30},
            {12, 0, 35, 42},
            {34, 35, 0, 20},
            {30, 42, 20, 0}
        };
        Graph graph = new Graph(adjacencyMatrix);
        graph.printGraphEdges();
        System.out.println(graph.tspPath());
        System.out.println(graph.tspTotalWeight());
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
//------------------------------------------------------------------------------------------
        // Phase 3 Test:
        System.out.println("Phase3:");
        EmployeeTree employeeTree = new EmployeeTree();
        employeeTree.insert(new String[] {"Zuck", "Brad", "Sara"});
        employeeTree.insert(new String[] {"Brad", "Jack", "Ellie", "Tim"});
        employeeTree.insert(new String[] {"Sara", "Sam", "Elon"});
        System.out.println(employeeTree.levelOrderTraversal());
        employeeTree.remove("Jack");
        System.out.println(employeeTree.levelOrderTraversal());
        employeeTree.insert(new String[] {"Sam", "Bill"});
        System.out.println(employeeTree.levelOrderTraversal());
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
//------------------------------------------------------------------------------------------
        // Phase 4 Test:
        System.out.println("Phase4:");
        int[] arr = {4, 2, 5, 1, 3, 7, 6};
        MinHeap heap = new MinHeap(arr);
        
        System.out.print("Unsorted array: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        MinHeap.heapSort(arr);
        System.out.print("Sorted array: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("MinHeap of the original array(Level-order of heap) :");
        heap.levelOrderPrint();
        System.out.println("----------------------------------------------------------------");
//------------------------------------------------------------------------------------------
    }
}
