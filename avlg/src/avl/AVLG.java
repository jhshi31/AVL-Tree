package avl;

/*
 * AVLG extends a binary search tree superclass (AbstractBST) and
 * adds AVL tree functionality
 */

public class AVLG<T extends Comparable<T>> extends AbstractBST<T>{
	int g;	// max imbalance
	
	public AVLG() {
		g = 1;
	}
	
	public AVLG(int g) {
		this.g = Math.abs(g);
	}
	
	public void add(T value) {
		root = add_helper(root, value);
		size += 1;
	}
	
	// return subtree with value inserted
	private AVLNode<T> add_helper(AVLNode<T> cur, T value) {
		if (cur == null) {
			return new AVLNode<T>(value);
		}
		if (value.compareTo(cur.value) < 0) {
			cur.left_child = add_helper(cur.left_child, value);
		} else {
			cur.right_child = add_helper(cur.right_child, value);
		}
		AVLNode.updateHeight(cur);
		cur = AVLNode.fixImbalance(cur, g);
		return cur;
	}
	
	public void remove(T value) {
		if (this.exists(value) == false) {
			return;
		}
		root = remove_helper(root, value);
		size-=1;
	}
	
	// return subtree with value removed
	private AVLNode<T> remove_helper(AVLNode<T> cur, T value) {
		// cur is the node we want to remove
		if (value.compareTo(cur.value) == 0) {
			T p;
			T s;
			// inorder predecessor exists
			if ((p = AVLNode.getPredecessor(cur)) != null) {
				// remove the predecessor from cur's left subtree
				cur.left_child = rp_helper(cur.left_child);
				cur.value = p;
				AVLNode.updateHeight(cur);
				cur = AVLNode.fixImbalance(cur, g);
				return cur;
				
			// inorder successor exists	
			} else if ((s = AVLNode.getSuccessor(cur)) != null) {
				// remove the successor from cur's right subtree
				cur.right_child = rs_helper(cur.right_child);
				cur.value = s;
				AVLNode.updateHeight(cur);
				cur = AVLNode.fixImbalance(cur, g);
				return cur;
				
			// case of no predecessor and no successor 
			} else {
				return null;
			}
		
		// The value we want to remove is in cur's left subtree
		} else if (value.compareTo(cur.value) < 0) {
			cur.left_child = remove_helper(cur.left_child, value);
			AVLNode.updateHeight(cur);
			cur = AVLNode.fixImbalance(cur, g);
			return cur;
		
		// The value we want to remove is in cur's right subtree
		} else {
			cur.right_child = remove_helper(cur.right_child, value);
			AVLNode.updateHeight(cur);
			cur = AVLNode.fixImbalance(cur, g);
			return cur;
		}
	}
	
	// help remove the predecessor
	private AVLNode<T> rp_helper(AVLNode<T> cur) {
		if (cur.right_child == null) {
			return remove_helper(cur, cur.value);
		} else {
			cur.right_child = rp_helper(cur.right_child);
			AVLNode.updateHeight(cur);
			cur = AVLNode.fixImbalance(cur, g);
			return cur;
		}
	}
	
	// help remove the successor
	private AVLNode<T> rs_helper(AVLNode<T> cur) {
		if (cur.left_child == null) {
			return remove_helper(cur, cur.value);
		} else {
			cur.left_child = rs_helper(cur.left_child);
			AVLNode.updateHeight(cur);
			cur = AVLNode.fixImbalance(cur, g);
			return cur;
		}
	}	
}
