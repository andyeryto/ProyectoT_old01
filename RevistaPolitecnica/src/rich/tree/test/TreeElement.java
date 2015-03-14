package rich.tree.test;

import org.richfaces.model.TreeNodeImpl;

public class TreeElement<T> extends TreeNodeImpl {

	 private T data;
     private String type;

     public TreeElement() {
               super();
     }

     public TreeElement(boolean leaf, T data) {
               super(leaf);
               this.data = data;
               this.type = leaf ? "leaf" : "branch";
     }

     public T getData() {
               return data;
     }

     public void setData(T data) {
               this.data = data;
     }

     public String getType() {
               return type;
     }

     public void setType(String type) {
               this.type = type;
     }

     @Override
     public String toString() {
               return this.data.toString();
     }
}
