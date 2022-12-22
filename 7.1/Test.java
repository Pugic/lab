

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class Test{

    public static void main(String args[]) {
            
    long  end;
    long now = System.nanoTime();
    if(TestLSDSort()){
    	end = System.nanoTime();
	System.out.println(String.format("LSD time:  %.2f sec ",((float)(end - now)/1_000_000_000.0)));

    }
    now = System.nanoTime();
    if(TestMSDSort()){
    	end = System.nanoTime();
	System.out.println(String.format("MSD time:  %.2f sec ",((float)(end - now)/1_000_000_000.0)));

    }

    now = System.nanoTime();
    if(TestCountingSort()){
    	end = System.nanoTime();
	System.out.println(String.format("Counting time:  %.2f sec ",((float)(end - now)/1_000_000_000.0)));

    }

    now = System.nanoTime();
    if(TestShell()){
    	end = System.nanoTime();
	System.out.println(String.format("Shell time:  %.2f sec",((float)(end - now)/1_000_000_000.0)));

    }




    }
    private static <T extends Comparable<T>> boolean check_order(T arr[]){

        for (int i = 1; i < arr.length; i++){
            if (arr[i-1].compareTo(arr[i]) > 0){
                return false;
            }
        }

        return true;
    }

    private static Integer[] toIntArr(ArrayList<Integer> arrlist){
        Integer[] res = new Integer[arrlist.size()];
        for (int i = 0; i < res.length; i++){
            res[i] = arrlist.get(i);
        }
        return res;
    }

    private static Integer[] genIntegerArr(int len){
        Random rnd = new Random();
        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++)
            arr[i] = rnd.nextInt(10000);
        return arr;
    }
 
    private static ArrayList<Integer> genArrList(int len){
        Random rnd = new Random();
        ArrayList<Integer> arr = new ArrayList<>(len);
        for (int i = 0; i < len; i++)
            arr.add(rnd.nextInt(1_000_000));
        return arr;
    }

    public static boolean TestLSDSort(){
        Integer[] arr = genIntegerArr(1000000);
        RadixSorts.sort_lsd(arr);
        return check_order(arr);
    }

    public static boolean TestMSDSort(){
        ArrayList<Integer> arr = genArrList(1000000);
        RadixSorts.sort_msd(arr);
        return check_order(toIntArr(arr));
    }

    public static boolean TestCountingSort(){
        ArrayList<Integer> arr = genArrList(1000000);
        RadixSorts.sort_counting(arr);
        return check_order(toIntArr(arr));
    }
    public static boolean TestShell(){
    	Integer[] arr = genIntegerArr(1000000);
	SortShell.sort_shell(arr);
	return check_order(arr);
    }

}
