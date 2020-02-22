package avl;

/*
 * Node define data structures and methods that support
 * AVL tree operations
 */

public class AVLNode<T extends Comparable<T>>{
		AVLNode<T> left_child;
		AVLNode<T> right_child;
		T value;
		int height = 0;
		
		public AVLNode(T value) {
			this.value = value;
		}
		
		public int getBalance(){
			int left_height = left_child == null ? -1 : left_child.height;
			int right_height = right_child == null ? -1 : right_child.height;
			return left_height - right_height;
		}
		
		/*
		 * Static Methods
		 */
		
		// re-calculate node height
		public static <T extends Comparable<T>> void updateHeight(AVLNode<T> a) {
			int left_height = a.left_child == null ? -1 : a.left_child.height;
			int right_height = a.right_child == null ? -1 : a.right_child.height;
			a.height = Math.max(left_height, right_height) + 1;
		}
		
		// return subtree (a and a's descendants) after a left rotation on a 
		public static <T extends Comparable<T>> AVLNode<T> leftR(AVLNode<T> a) {
			AVLNode<T> b = a.left_child;
			a.left_child = b.right_child;
			b.right_child = a;
			updateHeight(a);
			updateHeight(b);
			return b;
		}	
		
		// return subtree (a and a's descendants) after a right rotation on a
		public static <T extends Comparable<T>> AVLNode<T> rightR(AVLNode<T> a) {
			AVLNode<T> b = a.right_child;
			a.right_child = b.left_child;
			b.left_child = a;
			updateHeight(a);
			updateHeight(b);
			return b;
		}
		
		// return subtree after a left-right rotation on a
		public static <T extends Comparable<T>> AVLNode<T> left_rightR(AVLNode<T> a) {
			a.right_child = leftR(a.right_child);
			return rightR(a);
		}
		
		// return subtree after a right-left rotation on a
		public static <T extends Comparable<T>> AVLNode<T> right_leftR(AVLNode<T> a) {
			a.left_child = rightR(a.left_child);
			return leftR(a);
		}
		
		/*
		 *  fix imbalance in subtree if imbalance is detected,
		 *  returns corrected subtree 
		 */
		public static <T extends Comparable<T>> AVLNode<T> fixImbalance(AVLNode<T> cur, int g) {
			int b = cur.getBalance();
			// imbalance detected and Cur is left-heavy
			if (b > g) {
				int b1 = cur.left_child.getBalance();
				// case of left-heavy left subtree
				if (b1 > 0) {
					return AVLNode.leftR(cur);
				// case of right-heavy right subtree
				} else if (b1 < 0) {
					return AVLNode.right_leftR(cur);
				} else {
					return AVLNode.leftR(cur);
				}
				
			// imbalance detected and Cur is right-heavy
			} else if (b < -1 * g) {
				int b1 = cur.right_child.getBalance();
				// case of right-heavy right subtree
				if (b1 < 0) {
					return AVLNode.rightR(cur);
				// case of left_heavy right subtree
				} else if (b1 > 0) {
					return AVLNode.left_rightR(cur);
				} else {
					return AVLNode.rightR(cur);
				}
			
			// no imbalance	
			} else {
				return cur;
			}
		}
		
		/*
		 * returns the value of a's inorder predecessor,
		 * returns null if no predecessor exists
		 */
		public static <T extends Comparable<T>> T getPredecessor(AVLNode<T> a) {
			if (a.left_child == null) {
				return null;
			}
			AVLNode<T> cur = a.left_child;
			while (cur.right_child != null) {
				cur = cur.right_child;
			}
			return cur.value;
		}
		
		/*
		 * returns the value of a's inorder succesor,
		 * returns null if no successor exists
		 */
		public static <T extends Comparable<T>> T getSuccessor(AVLNode<T> a) {
			if (a.right_child == null) {
				return null;
			}
			AVLNode<T> cur = a.right_child;
			while (cur.left_child != null) {
				cur = cur.left_child;
			}
			return cur.value;
		}
}