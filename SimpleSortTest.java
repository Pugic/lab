import java.util.Arrays;

class SimpleSortTest{
        public static  void main(String args[]){
                Integer[] test = {3,6,3,1,2,5,100,0,-8,54,7};
                SimpleSort.buble(test); 
                System.out.println(Arrays.toString(test));
                Integer[] test2 = {3,5,0,2,1,90,0,54,9};
                SimpleSort.insert(test2); 
                System.out.println(Arrays.toString(test2));
                Integer[] test3 = {5,80,0,2,70,90,0,54,4};
                SimpleSort.selection(test3); 
                System.out.println(Arrays.toString(test3));



        }

}
