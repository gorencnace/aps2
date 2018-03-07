import java.lang.Math;

// casovne primerjave razlicnih tehnik sortiranja tabel

public class Sort {
    public static void main(String[] args) {
        
        // stevilo n predstavlja velikost tabele
        
        System.out.println("   n  | selectionSort | insertionSort |    heapSort   ");
        System.out.println("------+---------------+---------------+---------------");
        
        for (int i = 10; i <= 1000; i += 10) {
            long a = timeSelection(i);
            long b = timeInsertion(i);
            long c = timeHeap(i);
            System.out.printf("%5d |%14d |%14d |%14d %n", i, a, b, c);
        }
        
        
    }
    
    /* POVPRECNI CASI UREJANJA
     * v vsaki iteraciji zanke:
     *   - generira novo tabelo
     *   - meri cas urejanja
     *   - doda dobljeni cas k skupnemu casu
     * nato dobljeni skupni cas delji s stevilom ponovitev zanke
     */
    
    public static long timeSelection(int n) {
        
        long executionTime = 0;
        
        for (int i = 0; i < 1000; i++) {
            Comparable[] a = generateTable(n);
            
            long startTime = System.nanoTime();
            selectionSort(a);
            executionTime =  executionTime + (System.nanoTime() - startTime);
        }
        
        
        return executionTime / 1000;
    }
    
    public static long timeInsertion(int n) {
        
        long executionTime = 0;
        
        for (int i = 0; i < 1000; i++) {
            Comparable[] a = generateTable(n);
            
            long startTime = System.nanoTime();
            insertionSort(a);
            executionTime =  executionTime + (System.nanoTime() - startTime);
        }
        
        
        return executionTime / 1000;
    }
    
    public static long timeHeap(int n) {
        
        long executionTime = 0;
        
        for (int i = 0; i < 1000; i++) {
            Comparable[] a = generateTable(n);
            
            long startTime = System.nanoTime();
            heapsort(a);
            executionTime =  executionTime + (System.nanoTime() - startTime);
        }
        
        
        return executionTime / 1000;
    }
    
    /* GENERIRANJE TABELE
     * generira tabelo dolzine n
     * v kateri so nakljucni elementi od 
     * Integer.MAX_VALUE do Integer.MIN_VALUE
     */
    
    public static Comparable[] generateTable(int n) {
        Comparable[] a = new Comparable[n];
        int seed = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            a[i] = (Comparable) (int) ((Math.random() * seed) + Integer.MIN_VALUE);
        }
        return a;
    }
    
    /*
     * HEAPSORT (casovna zahtevnost O(n * log n))
     * metoda narejena po smernicah iz predavanja
     * Zamisel:
     * 1. neurejeni del tabele uredimo v kopico
     *    (implementacija s tabelo)
     * 2. zamenjamo prvi element tabele (v korenu
     *    kopice je najvecji element) z zadnjim 
     *    elementom neurejenega dela tabele
     * 3. uredimo neurejeni del tabele v kopico,
     *    da bo ta veljavna
     * 4. ponavljamo postopek od tocke 1. do 3.
     *    dokler ni neurejeni del tabele (kopica)
     *    prazna
     */
    
    public static void heapsort(Comparable[] t) {
        int n = t.length;
        int r = n - 1;
        createHeap(t, n);
        while (r > 0) {
            swap(t, 0, r);
            r = r - 1;
            fixHeap(t, 0, r);
        }
    }
    
    // kreiranje kopice
    public static void createHeap(Comparable[] t, int n) {
        for (int i = n - 1; i >= 0; i--) {
            fixHeap(t, i, n-1);
        }
    }
    
    // urejanje neurejenega dela tabele v kopico
    public static void fixHeap(Comparable[] t, int i, int j) {
        i = i+1;
        j = j+1;
        int s;
        if (i <= (j / 2)) {
            s = 2 * i;
            if ((s + 1) <= j) {
                if (t[s-1].compareTo(t[s]) < 0) {
                    s = s + 1;
                }
            }
            if (t[i-1].compareTo(t[s-1]) < 0) {
                swap(t, i-1, s-1);
                fixHeap(t, s-1, j-1);
            }
        }
    }
    
    /*
     * SELECTION SORT (casovna zahtevnost O(n²))
     * Tabelo razdelimo na urejeni del in neurejeni del.
     * V neurejenem delu  tabele najdemo najmanjsi element
     * in ga vstavimo v urejeni del tabele.
     */
    
    public static void selectionSort(Comparable[] t) {
        for(int i = 0; i < t.length; i++) {
            int min = i;
            for (int j = i+1; j < t.length; j++) {
                if (t[min].compareTo(t[j]) > 0)
                    min = j;
            }
            swap(t, i, min);
        }
    }
    
    /*
     * INSERTION SORT (casovna zahtevnost od O(n) do O(n²))
     * Algoritem se iterativno pomika po tabeli in linearno
     * po vrsti vstavlja elemente iz neurejenega dela tabele
     * v urejeni del.
     * Ta algoritem bi lahko se dodatno izboljsal z bisekcijo
     */
    
    public static void insertionSort(Comparable[] t) {
        int n = t.length;
        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0 && t[j-1].compareTo(t[j]) > 0) {
                swap(t, j, j-1);
                j--;
            }
        }
    }
    
    // metoda za zamenjavo elementov v tabeli
    private static void swap(Comparable[] t, int i, int j) {
        Comparable c = t[i];
        t[i] = t[j];
        t[j] = c;
    }
}
