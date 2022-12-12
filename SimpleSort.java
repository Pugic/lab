public class SimpleSort{

        public static <T extends Comparable<T>> void buble (T mas[]){ 
                for(int i = 0; i < (mas.length - 1 ); i++){
                        for(int j = 0; j < mas.length - i-1; j++ ){
                                if(mas[j].compareTo(mas[j+1]) >  0){
                                        T k = mas[j];
                                        mas[j]=mas[j+1];
                                        mas[j+1]=k;
                                }

                        }
                }
        }

        public static <T extends Comparable<T>> void insert (T mas[]){
                for(int i=1; i<mas.length;i++){
                        T x = mas[i];
                        int j = i;
                        while(j>0 && mas[j-1].compareTo(x) > 0){
                                mas[j] = mas[j-1];
                                j--;
                        }
                        mas[j] = x;
                }

        }

        public static <T extends Comparable<T>> void selection (T mas[]){
                for(int i=0; i < mas.length; i++){
                        int min = i;
                        for (int j = i; j<mas.length; j++){
                                if (mas[j].compareTo(mas[min]) < 0 ){
                                        min = j;
                                }
                        }
                        T k = mas[min];
                        mas[min] = mas[i];
                        mas[i] = k;
                }
        }
}
