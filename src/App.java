public class App {
    public static void main(String[] args) {
        
        // Phase 1 Test:
        AVLTree avlTree = new AVLTree();
        avlTree.insert(22);
        avlTree.insert(36);
        avlTree.insert(19);
        avlTree.insert(37);
        avlTree.insert(44);
        avlTree.insert(14);
        avlTree.insert(17);
        avlTree.insert(10);
        System.out.println(avlTree.levelOrder());
        avlTree.insert(99);
        avlTree.insert(58);
        System.out.println(avlTree.levelOrder());
        avlTree.remove(99);
        avlTree.remove(58);
        System.out.println(avlTree.levelOrder());
        System.out.println("----------------------------------------------------------------");
//------------------------------------------------------------------------------------------
        // Phase 2 Test:
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
//------------------------------------------------------------------------------------------
        // Phase 3 Test:
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
//------------------------------------------------------------------------------------------
        // Phase 4 Test:
        int[] arr = {4, 2, 5, 1, 3, 7, 6};
        MinHeap heap = new MinHeap(arr);
        
        MinHeap.heapSort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        heap.levelOrderPrint();
//------------------------------------------------------------------------------------------
    }
}
