# Miller The Mail Man
Miller the mail man has faced four problems in his great endeavors of bringing mail to companies. We face these four problems in the four phases below and provide an appropriote solution
and data structure to face them.

>Each phase is tested in the app.java file, starting from phase 1 to phase 4.
## Phase 1
The first problem involves Miller having an easy way to store and a quick way to access company postal codes since his memory has been failling him.

For this we have provided an AVLtree data structure so Miller has easy storage, easy insertion of new companies or romoval of a company, and due to
the self balancing properties of the AVLtree, Miller has quick access to the company postal codes and complete control over them now.

The AVL tree has a linked structure with vertices that store data and height. The insert and remove method are both implemented with the help
of recursive and encapsulated versions of them.

Miller can also get the level-order, in-order and the pre-order of the tree to view it's components.
## Phase 2
The second problem that Miller is facing is the TSP(traveling salesman problem).

The Heuristic algorith has been implemeted on a graph structure as a solution.
Miller can see the path that has to take for the day and the distance that he will need to travel in order to complete the path.

Miller can also view the edges of this graph at anytime.
## Phase 3
In this phase we have to create a way for Miller to easily search for the reciever of a letter in the company.

For this we have implemented a special tree structure such that the children of each vertex are the employees that 
directly work under that vertex and the descendants are employees that are indirectly under the vertex.

this allows Miller to easily navigate the company starting from the root of the tree and find the person he is looking for.
And each company can easily insert a new or delete an old employee.

Miller can have a level-order permutation of the employees in the tree to see it's overall employees.
## Phase 4
In the final phase Miller needs to somehow sort the letters that he has based on time (1 <= dayOfLetter <= 365).

for this we have implemented a linked MinHeap tree structure in order to perform heapSort
on the letters and quickly sort the letters and solve Miller's dillema.

Miller can view the level-order permutation of the tree so he knows exactly what the tree looks like.
> this is due to the complete property of heaps.

> Check the app file for testing of the files.
# Implementations
Let's go over some of the detatils of the solutions and data structures deployed in each phase:

## AVLtree
In order to implement the AVLtree a private Vertex class was created that holds the following properties:

`Data`, `Left`, `Right`, `Height`, `Parent`
>The parent property was used in the initial versions of the tree implementation and is no longer used. Maybe it'll be removed later.

`Data` holds the data of each Vertex, `Left` holds refrence to left child, `Right` to right child, `Height` is used to make sure that
in the case that the avl rule has been broken, do the proper rotations to restore the avl property of the tree.

> fixHeight(vertex) : this method fixes the height of certain vertex and ensures that the height is what it is supposed to be.
    
> leftRotation(z) : performs left rotation on subtree rooted z.
 
> rightRotation(z) : performs right rotation on subtree rooted z.
 
> insertHelper(root, newData) recursive method that takes root and newData as it's initial parameters and inserts new data and ensures that the avl property is preserved.
 
> removeHelper(root, removedData) recursive method that takes root and removedData as it's initial parameters and removes the removedData from 

> the tree and ensures that the avl property is preserved.
## Graph
In order to use the adjacency matrix that Miller has for solving the TSP, a Graph structure has been implemented.

The Graph holds two ArrayLists, one for the Vertices and one for the Edges. The Vertices simply hold data, and Edges hold a starting vertex, an ending vertex
and an integer representing the weight of the edge. 

The constructor of the Graph initializes the Vertices and the Edges of the graph from Miller's adjacency matrix.
> only the upper triangle of matrix is scanned for edges.

The `tspPath()` method deploys the heuristic algorithm to tell us the order of the vertices that must be visisted for Miller to travel
to all companies(vertices) and return to his starting point.

The `tspWeight()` method takes the path that the previous mathod gave us and returns the total weight of the edges that are on this path and Miller
must traverse to complete the path.
## EmployeeTree
The Employee tree is a non-binary tree that holds Strings in each of the vertices and each vertex also holds a refrence to an ArrayList of
vertices that are the children of that vertex.

Tree traversal is implemented using the BFS algorithm

insertion is done by recieving an array of Strings where the first string of the array tells us the parent and the rest of the array gives
us the string names of the newly inserted employees that work under the parent. Parent is found using the bfs traversal of the tree.

removal is done by removing the employee from the parent vertexe's list of children. In the case that we wanted to remove the root we simply null it and set size to 0.
and after each removal the size is reset using the `countSize()` method.
## MinHeap
The MinHeap has been implemented for the purpose of implementing the heapSort algorithm and sorting Miller's letters.

The MinHeap as a linked structure and is a complete tree, Therefore insertion is done by performing level-order traversal on the tree and inserting at the
first empty spot and bubbling up and heapifying the tree and ensure the minheap property is preserved.

You are only allowed to remove the root of the heap. after removal of the root the last vertex in heap is found by level-order traversal and placed at the root.
After that we bubble down to heapify the tree and ensure the minheap property is preserved.
