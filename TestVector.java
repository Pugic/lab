class TestVector{

        public static void main(String[] args){
                MyVector<Integer> vec = new MyVector<Integer>();
                vec.append(1);
                vec.append(2);
                vec.append(3);
                vec.append(4);
                System.out.println(vec);

                vec.del_last();
                System.out.println(vec);

                vec.del_index(0);
                System.out.println(vec);
                vec.del_index(-12);

                vec.add_index(0,34);
                System.out.println(vec);

                vec.del_val(3);
                System.out.println(vec);

                System.out.println("max: " + vec.getmax() + " len:  " + vec.getlen() + " zero el " + vec.getByIndex(0));

                MyVector<Integer> vec2 = new MyVector<Integer>(vec);
                System.out.println(vec2);

                MyVector<Integer> vec3 = new MyVector<Integer>(15);
                System.out.println(vec3.getmax() + " " + vec3.getlen()); 


        }
}
