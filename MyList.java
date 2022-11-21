class Node<E>{
    public E data;
    public Node<E> next;

    public Node(){}
}
    

public class MyList<E>{

    private Node<E> head;
    private Node<E> tail;
    private int len;

    public MyList(){};


    public void init_head(E head_data){
        Node<E> head_node = new Node<>();
        head_node.data = head_data;
        head_node.next = this.head;
        this.head = head_node;
        len ++;
    }

    public MyList(MyList<E> old_list){
        Node <E> old_node = new Node<>();
        this.init_head(old_list.head.data);
        old_node = old_list.head.next;
        while (old_node != null){
                this.add_tail(old_node.data);
                old_node=old_node.next; 
        }
        len = old_list.getlen();
    }

    public void add_tail(E data){
        Node<E> node_tail = head;
        Node<E> new_tail = new Node<>();
        while (node_tail.next != null){
                node_tail = node_tail.next;
        }
        node_tail.next = new_tail; 
        new_tail.data = data;
        new_tail.next = null;
        this.tail = new_tail;
        len ++;
    }

    public void del_first(){
        try{
                if (len == 0){
                        throw new Exception("Empty head");
                }

                this.head = this.head.next;
                len --;

                if (len == 0){
                        tail = null;
                }
        }
        catch (Exception null_head){
                System.out.println(null_head.getMessage());
        }
    }


    public void del_last(){
        Node<E> node = head;
        while (node.next.next  != null){
            node = node.next;
        }
        node.next = null;
        tail = node;
        len --;
    }

    public void del_by_val(E value){
        Node<E> node = head;
        Node<E> node_bef = null;
        
        while (node != null){
            if (node.data == value){

                if (node == this.head){
                    this.del_first();
                } else if (node == this.tail){
                    this.del_last();
                } else {
                    node_bef.next = node.next;
                    node.next = null; 
                }
                len--;
                return;
            }
            node_bef = node;
            node = node.next;
        }
        System.out.println("No such element");
    }

    public int getlen(){
        return this.len;
    }

    public void del_list(){
        head = null;
        tail = null;
        len = 0;
    }


    public E gethead(){
        return this.head.data;
    }

    public E gettail(){
        return this.tail.data;
    }

    public E getdata(int num){
        if (num > len){
            System.out.println("position > len");
            return null;

        } else{
                Node<E> node = head;
                for (int i = 0; i < num-1; i++){
                node = node.next;
                }
                return node.data;
        }
    }

    public void  print_list(){
        Node<E> node = this.head;
        while (node != null){
                System.out.println(node.data);
                node = node.next;
        }


    }

}

