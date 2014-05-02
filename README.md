Splay Tree
=========

A splay tree is a self-adjusting Binary Search Tree with the additional property that recently accessed elements are quick to access again. It performs basic operations such as insertion, look-up and removal in O(log n) amortized time. For many sequences of non-random operations, splay trees perform better than other search trees, even when the specific pattern of the sequence is unknown.

All normal operations on a binary search tree are combined with one basic operation, called splaying. Splaying the tree for a certain element rearranges the tree so that the element is placed at the root of the tree.

##Functionalities

Implementation of SplayTree in java. The library to implement SplayTree with the following operations,


    insert() - To add new (key, value) pair to the SplayTree
    find() - To get an element from the SplayTree by passing the key
    findMin() - To get the minimum element from the SplayTree
    findMax() - To get the maximum element from the SplayTree
    remove() - To remove a particular key from the SplayTree
    removeValue() - To remove all key matching the values from the list
    size() - To return the total size of the list
    

##Original release notes from 2014

SplayTree is a randomized data structure. The following code snippet shows how to run the library,


    	SplayTree<Long, Long> splayTree=new SplayTree<Long, Long>();
		
		splayTree.insert(10L, 25L);
		splayTree.insert(12L, 35L);
		splayTree.insert(13L, 45L);
		splayTree.insert(14L, 55L);
		System.out.println(splayTree.find(10L));
		System.out.println(splayTree.findMin());
		System.out.println(splayTree.findMax());
		splayTree.remove(13L);
		System.out.println(splayTree.size());
		
##Project Contributor

* Dinesh Appavoo ([@DineshAppavoo](https://twitter.com/DineshAppavoo))
