package avl;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * abstract class defining instance variables and methods used
 * by binary search trees
 * 
 * NOTE: AVLNode is a tree node designed for AVL operations,
 * 		 it supersedes generic node operations
 */

public abstract class AbstractBST<T extends Comparable<T>> implements Iterable<T>{
	AVLNode<T> root = null;
	int size = 0;
	
	public boolean exists(T value) {
		return exists_helper(root, value);
	}
	
	private boolean exists_helper(AVLNode<T> cur, T value) {
		if (cur == null) {
			return false;
		} else if (value.compareTo(cur.value) == 0) {
			return true;
		} else if (value.compareTo(cur.value) < 0) {
			return exists_helper(cur.left_child, value); 
		} else {
			return exists_helper(cur.right_child, value);
		}
	}
	
	public abstract void add(T value);
	
	public abstract void remove(T value);
	
	public Iterator<T> iterator(){
		ArrayList<T> l = new ArrayList<T>();
		inorderTraversal(root, l);
		return l.iterator();
	}
	
	private void inorderTraversal(AVLNode<T> cur, ArrayList<T> l) {
		if (cur != null) {
			inorderTraversal(cur.left_child, l);
			l.add(cur.value);
			inorderTraversal(cur.right_child, l);
		}
	}
}
