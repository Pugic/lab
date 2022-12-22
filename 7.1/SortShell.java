public class SortShell {
    public static void sort_shell(Integer[] a){
        Integer i, j, k, h, m=0, b=a.length;
        Integer[] d = { 1, 4, 10, 23, 57, 145, 356, 911, 1968, 4711, 11969, 27901,
                84801, 213331, 543749, 1355339, 3501671, 8810089, 21521774,
                58548857, 157840433, 410151271, 1131376761, 2147483647 };
        while (d[m] < b) ++m;
        while (--m >= 0){
            k = d[m];
            for (i = k; i < b; i++){     // k-сортировка
                j=i;
                h=a[i];
                while ((j >= k) && (a[j-k] > h)){
                    a[j]=a[j-k];
                    j -= k;
                }
                a[j] = h;
            }
        }
    }
}
