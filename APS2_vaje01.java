import java.util.Random;

public class APS2_vaje01 {
    public static void main(String[] args) {
        // izpis prvih dveh vrstic
        System.out.println("   n     |    linearno  |   dvojisko   |");
        System.out.println("---------+--------------+---------------");
        // izpis ostalega
        for (int i = 1000; i < 100000; i += 1000) {
            long a = timeLinear(i);
            long b = timeBinary(i);
            System.out.printf("%8d +%13d +%13d  %n", i, a, b);
        }
    }
    
    public static int[] generateTable(int n) {
        int[] table = new int[n];
        for (int i = 0; i < n; i++)
            table[i] = i+1;
        return table;
    }
    
    public static int findLinear(int[] a, int v) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == v)
                return i;
        }
        return -1;
    }
    
    public static int findBinary(int[] a, int v) {
        return findBinary(a, 0, a.length, v);
    }
    
    public static int findBinary(int[] a, int l, int r, int v) {
        int n = (l + r) / 2;
        if (a[n] == v)
            return n;
        else if (a[n] > v)
            return findBinary(a, l, n, v);
        else
            return findBinary(a, n, r, v);
    }
    
    public static long timeLinear(int n) {
        int[] t = generateTable(n);
        Random rand = new Random();
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < 1000; i++) {
            int r = rand.nextInt(n) + 1;
            int index = findLinear(t, r);
        }
        
        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }
    
    public static long timeBinary(int n) {
        int[] t = generateTable(n);
        Random rand = new Random();
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < 1000; i++) {
            int r = rand.nextInt(n) + 1;
            int index = findBinary(t, r);
        }
        
        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }
}
