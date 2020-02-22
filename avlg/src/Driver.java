import avl.AVLG;

public class Driver {
	public static void main(String[] args) {
		AVLG<Integer> avl = new AVLG<Integer>();
		avl.add(2);
		avl.add(1);
		avl.add(3);
		for (Integer e : avl) {
			System.out.println(e);
		}
	}
}
