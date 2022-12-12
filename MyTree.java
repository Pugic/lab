class TreeNode<K extends Comparable<K>, D>{

        public K key;
        public D data;
        TreeNode<K,D> left, right;
        public TreeNode(){}

        public TreeNode(K k, D d){
                this.key = k;
                this.data = d;
                this.left = null;
                this.right = null;
        }

}

public class MyTree<K extends Comparable<K>,D>{

        TreeNode<K,D> head;
        int len;

        public MyTree(){
                this.head = null;
                this.len = 0;
        }

        public MyTree(MyTree<K,D> another_tree){
                this.head = another_tree.head;
                this.len = another_tree.len;
        }

        public D get(K key){
                TreeNode<K,D> node = this.head;

                while (node != null){
                        int comp = key.compareTo(node.key);
                        if (comp == 0){
                                return node.data;
                        }
                        if (comp > 0 ){
                                node = node.right;
                        }
                        else{
                                node = node.left;
                        }


                }
                return null;
        }

        public void new_node(K new_key, D new_data){
                if (this.head == null){
                        this.head = new TreeNode<K,D>(new_key,new_data);
                        this.len++;
                        return;
                }
                TreeNode<K,D> node = this.head;
                while(node != null){
                        int comp = new_key.compareTo(node.key);
                        if (comp > 0){
                                if (node.right != null){
                                        node = node.right;
                                }
                                else{
                                        node.right = new TreeNode<K,D>(new_key,new_data);
                                        this.len++;
                                        return;
                                }

                        }
                        else if(comp == 0){
                                node.data = new_data;
                                return;
                        }
                        else{
                                if (node.left != null){
                                        node = node.left;
                                }
                                else{
                                        node.left = new TreeNode<K,D>(new_key,new_data);
                                        this.len++;
                                        return;
                                }

                        }

                }


        }


        public void delete(){
                this.head = null;
                this.len = 0;
        }

        private static String StrMul(String s, int n){
                StringBuilder buff = new StringBuilder();
                for(int i = 0; i < n; i++){
                        buff.append(s);
                }
                return buff.toString();
        }

        public void print(TreeNode<K,D> node, int level, String type){
                String s; s = StrMul(" ",level*4);
                System.out.println(s + type +  node.key + " : " + node.data);
                if (node.right!=null){
                        print(node.right,level+1, "right ");
                }
                if (node.left!=null){
                        print(node.left, level+1, "left ");
                }
        }

        public void printTree(){
                print(this.head, 0, "head  ");
        }



}
