/**
 * 
 */
package splay;

/**
 * @author Dinesh Appavoo
 *
 */

	public class SplayTree<T extends Comparable<T>, U extends Comparable<U>>
	{
	    private BinaryNode<T,U> root;
	    private int removeCount=0;
	    private int sizeCount=0;
	    private long _size=1;


	    public SplayTree() {
	        root = null;
	    }

	    /**
	     * Insert into the tree.
	     * @param x the item to insert.
	     * @throws DuplicateItemException if x is already present.
	     */
	    public void insert(T key, U value) {
		BinaryNode n;
		int c;
		if (root == null) {
		    root = new BinaryNode(key, value);
		    return;
		}
		splay(key);
		if ((c = key.compareTo(root.key)) == 0) {
		    //	    throw new DuplicateItemException(x.toString());
			root.value=value;
		    return;
		}
		n = new BinaryNode(key, value);
		if (c < 0) {
		    n.left = root.left;
		    n.right = root;
		    root.left = null;
		} else {
		    n.right = root.right;
		    n.left = root;
		    root.right = null;
		}
		root = n;
		_size++;
	    }

	    /**
	     * Remove from the tree.
	     * @param x the item to remove.
	     * @throws ItemNotFoundException if x is not found.
	     */
	    public void remove(T key) {
		BinaryNode<T, U> x;
		splay(key);
		if (key.compareTo(root.key) != 0) {
		    //            throw new ItemNotFoundException(x.toString());
		    return;
		}
		// Now delete the root
		if (root.left == null) {
		    root = root.right;
		} else {
		    x = root.right;
		    root = root.left;
		    splay(key);
		    root.right = x;
		}
		_size--;
	    }
	    
	    /**
	     * Remove from the tree.
	     * @param x the item to remove.
	     * @throws ItemNotFoundException if x is not found.
	     */
	    public int removeValue(U value) {
		
	    	BinaryNode<T, U> x=root;
	    	int count=0;
	    	count=removeValuePreOrderTraverse(x, value);
	    	removeCount=0;
	    	//System.out.println("count in remove v: "+count);
	    	return count;
	    }
	    
	    /**
	     * Remove all matching values from the tree.
	     * @param  v the item to remove.
	     * @throws ItemNotFoundException if v is not found.
	     */
	    public int removeValuePreOrderTraverse(BinaryNode<T, U> head, U v) {

	        BinaryNode<T, U> x = head;
	    	if(x==null)
				return removeCount;
	    	if(x.value.compareTo(v)==0)
	    	{
	    		remove(x.key);
	    		removeCount++;
	    	}
	    	removeValuePreOrderTraverse(x.left, v);
			removeValuePreOrderTraverse(x.right, v);
			return removeCount;
	    }
	    
	    /**
	     * return the size of the tree.
	     */
	    public Long size()
	    {
	    	/*BinaryNode x=root;
	    	if(x==null)
	    		return sizeCount;
	    	int size=sizeHandler(x);
	    	sizeCount=0;
	    	return size;*/
	    	return _size;
	    }
	    /**
	     * return the size of the tree.
	     */
	    public int sizeHandler(BinaryNode<T, U> head) {

	    	BinaryNode<T, U> x = head;
	    	if(x==null)
				return sizeCount;
	    	sizeCount++;
	    	sizeHandler(head.left);
	    	sizeHandler(head.right);
			return sizeCount;
	    }
	    

	    /**
	     * Find the smallest item in the tree.
	     */
	    public U findMin() {
	        BinaryNode<T, U> x = root;
	        if(root == null) return null;
	        while(x.left != null) x = x.left;
	        splay(x.key);
	        return x.value; //Modified
	    }

	    /**
	     * Find the largest item in the tree.
	     */
	    public U findMax() {
	        BinaryNode<T, U> x = root;
	        if(root == null) return null;
	        while(x.right != null) x = x.right;
	        splay(x.key);
	        return x.value;		//Modified
	    }

	    /**
	     * Find an item in the tree.
	     */
	    public U find(T key) {
		if (root == null) return null;
		splay(key);
	        if(root.key.compareTo(key) != 0) return null;
	        return root.value;
	    }

	    /**
	     * Test if the tree is logically empty.
	     * @return true if empty, false otherwise.
	     */
	    public boolean isEmpty() {
	        return root == null;
	    }

	    /** this method just illustrates the top-down method of
	     * implementing the move-to-root operation 
	     */
	    private void moveToRoot(T key) {
		BinaryNode<T, U> l, r, t, y;
		l = r = header;
		t = root;
		header.left = header.right = null;
		for (;;) {
		    if (key.compareTo(t.key) < 0) {
			if (t.left == null) break;
			r.left = t;                                 /* link right */
			r = t;
			t = t.left;
		    } else if (key.compareTo(t.key) > 0) {
			if (t.right == null) break;
			l.right = t;                                /* link left */
			l = t;
			t = t.right;
		    } else {
			break;
		    }
		}
		l.right = t.left;                                   /* assemble */
		r.left = t.right;
		t.left = header.right;
		t.right = header.left;
		root = t;
	    }

	    private static BinaryNode header = new BinaryNode(null, null); // For splay //Modified
	    
	    /**
	     * Internal method to perform a top-down splay.
	     * 
	     *   splay(key) does the splay operation on the given key.
	     *   If key is in the tree, then the BinaryNode containing
	     *   that key becomes the root.  If key is not in the tree,
	     *   then after the splay, key.root is either the greatest key
	     *   < key in the tree, or the lest key > key in the tree.
	     *
	     *   This means, among other things, that if you splay with
	     *   a key that's larger than any in the tree, the rightmost
	     *   node of the tree becomes the root.  This property is used
	     *   in the delete() method.
	     */
	    
	    private void splay(T key) {
		BinaryNode<T, U> l, r, t, y;
		l = r = header;
		t = root;
		header.left = header.right = null;
		for (;;) {
		    if (key.compareTo(t.key) < 0) {
			if (t.left == null) break;
			if (key.compareTo(t.left.key) < 0) {
			    y = t.left;                            /* rotate right */
			    t.left = y.right;
			    y.right = t;
			    t = y;
			    if (t.left == null) break;
			}
			r.left = t;                                 /* link right */
			r = t;
			t = t.left;
		    } else if (key.compareTo(t.key) > 0) {
			if (t.right == null) break;
			if (key.compareTo(t.right.key) > 0) {
			    y = t.right;                            /* rotate left */
			    t.right = y.left;
			    y.left = t;
			    t = y;
			    if (t.right == null) break;
			}
			l.right = t;                                /* link left */
			l = t;
			t = t.right;
		    } else {
			break;
		    }
		}
		l.right = t.left;                                   /* assemble */
		r.left = t.right;
		t.left = header.right;
		t.right = header.left;
		root = t;
	    }

	    // test code 
	    public static void main(String [ ] args)
	    {
	        SplayTree t = new SplayTree();
	        final Long NUMS = 40000L;
	        final Long GAP  =   307L;

	        System.out.println("Checking... (no bad output means success)");

	        for(Long i = GAP; i != 0; i = (i + GAP) % NUMS)
	            t.insert(new Long(i), new Long(i));
	        System.out.println("Inserts complete");
	        
	        t.insert(14L, 1000000000L);

	        for(int i = 1; i < NUMS; i+= 2)
	            t.remove(new Long(i));
	        System.out.println("Removes complete");

	        if(((Long)(t.findMin())).intValue() != 2 ||
	            ((Long)(t.findMax())).intValue() != NUMS - 2)
	        {
	            System.out.println("FindMin or FindMax error!");
	        }else
	        {
	        	System.out.println("Minimum :"+t.findMin());
	        	System.out.println("Maximum :"+t.findMax());
	        }

	        for(int i = 2; i < NUMS; i+=2)
	            if(((Long)t.find(new Long(i))).intValue() != i)
	            {
	                System.out.println("Error: find fails for " + i);
	            }else
	            {
	            	;
	            }

        	System.out.println("Find Method : "+t.find(988L));
        	System.out.println("Find Method : "+t.find(14L));


	        for(int i = 1; i < NUMS; i+=2)
	            if(t.find(new Long(i))  != null)
	                System.out.println("Error: Found deleted item " + i);
	    }

	}

