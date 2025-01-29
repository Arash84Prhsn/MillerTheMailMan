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

Miller can view the edges of this graph at anytime.
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