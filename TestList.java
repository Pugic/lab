class TestList{

        public static void main(String[] args){

                MyList<Integer> lst = new MyList<Integer>();

                lst.init_head(3);
                lst.add_tail(5);
                lst.add_tail(9);
                lst.add_tail(0);
                lst.add_tail(888);
                System.out.println("1 test");
                lst.print_list();
                System.out.println("2 test");
                System.out.println( lst.gethead());
                System.out.println( lst.getlen());
                System.out.println( lst.getdata(3));
                System.out.println( lst.gettail());
                System.out.println("3 test");
                lst.del_first();
                lst.print_list();
                System.out.println("4 test");
                lst.del_last();
                lst.print_list();
                System.out.println("5 test");
                lst.del_by_val(9);
                lst.print_list();
                System.out.println("6 test");
                MyList<Integer> lst2 = new MyList<Integer>(lst);
                lst2.print_list();
                System.out.println("7 test");
                lst2.del_list();
                lst2.print_list();
                System.out.println("8 test");
                lst.del_by_val(99);
                lst.getdata(800);
                lst2.del_first();



        }





}
