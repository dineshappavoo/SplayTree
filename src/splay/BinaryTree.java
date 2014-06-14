/**
 * 
 */
package splay;

/**
 * @author Dinesh Appavoo
 *
 */
class BinaryNode<T extends Comparable<T>, U extends Comparable<U>>
{
    BinaryNode(T theKey, U theValue) {
        key = theKey;
        value=theValue;
        left = right = null;
    }

    T key;          // The key in the node
    U value;			//The value associated for the key
    BinaryNode<T, U> left;         // Left child
    BinaryNode<T, U> right;        // Right child
}