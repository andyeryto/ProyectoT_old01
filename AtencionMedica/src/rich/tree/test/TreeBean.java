package rich.tree.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;

@ManagedBean(name = "treeBean")
@ViewScoped
public class TreeBean implements Serializable{

	private static final long serialVersionUID = 8249542819459244128L;

	private TreeElement<String> root;

	private TreeElement<String> selected;

	public TreeBean() {
		if (root == null) {
			TreeElement<String> branch = new TreeElement<String>(false,
					"First branch");

			for (int i = 0; i < 5; i++) {
				TreeElement<String> leaf = new TreeElement<String>(true,
						"leaf " + (i + 1));
				branch.addChild(i, leaf);
			}

			root = new TreeElement<String>();
			root.addChild(0, branch);
			root.addChild(1, new TreeElement<String>(false, "Second branch"));
			root.addChild(2, new TreeElement<String>(true,
					"Leaf directly linked to the root"));
		}
	}

	public TreeElement<String> getRoot() {
		return root;
	}

	public TreeElement<String> getSelected() {
		return selected;
	}

	public void setSelected(TreeElement<String> selected) {
		this.selected = selected;
	}

	public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) {
		System.out.println("some node selected!!");
		List<Object> selection = new ArrayList<Object>(
				selectionChangeEvent.getNewSelection());
		Object currentSelectionKey = selection.get(0);
		String s = currentSelectionKey.getClass().getCanonicalName();
		System.out.println(s);
		UITree tree = (UITree) selectionChangeEvent.getSource();
		tree.setRowKey(currentSelectionKey);
	}

	// private TreeNodeCustom rootNodes = new TreeNodeCustom();
	//
	// private void loadTree(){
	// System.out.println("CARGANDO ARBOL");
	//
	// TreeNode tree1 = new TreeNodeCustom("tree","Arbol1");
	// TreeNode tree1Child = new TreeNodeCustom(true,"leaf","Hoja1");
	// tree1.addChild(0, tree1Child);
	//
	// TreeNode tree2 = new TreeNodeCustom("tree","Arbol2");
	// TreeNode tree2Child = new TreeNodeCustom(true,"leaf","Hoja2");
	// tree2.addChild(0, tree2Child);
	//
	// rootNodes.addChild(0, tree1);
	// rootNodes.addChild(1, tree2);
	// }
	//
	// public TreeNodeCustom getRootNodes() {
	// loadTree();
	// return rootNodes;
	// }
	//
	// public void setRootNodes(TreeNodeCustom rootNodes) {
	// this.rootNodes = rootNodes;
	// }

}
