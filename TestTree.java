public class TestTree{

        public static void main(String args[]){
                MyTree<Integer,Integer> tree1 = new MyTree<Integer,Integer>();
                tree1.new_node(10,45);
                tree1.new_node(9,653);
                tree1.new_node(8,41);
                tree1.new_node(12,90);
                tree1.new_node(11,900);
                tree1.new_node(16,7658);
                tree1.printTree();

                MyTree<Integer,Integer> tree2 = new MyTree<Integer,Integer>(tree1);
                tree2.printTree();

                System.out.println(tree2.get(16));
                System.out.println(tree2.get(17));


        }
}
