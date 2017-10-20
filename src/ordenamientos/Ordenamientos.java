package ordenamientos;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Ordenamientos {

    static BigInteger V[] = new BigInteger[2000];
    static int n = V.length;
    
    public static void main(String[] args) {
        Fill();
        Scanner sc = new Scanner(System.in);
        ShowMenu();
        int opc = sc.nextInt();
        switch (opc) {
            case 1:
                System.out.println("");
                System.out.println("Select Sort");
                SelectSort();
                break;

            case 2:
                System.out.println("");
                System.out.println("Insert Sort");
                InsertSort();
                break;

            case 3:
                System.out.println("");
                System.out.println("Shell Sort");
                ShellSort();
                break;

            case 4:
                System.out.println("");
                System.out.println("Quick Sort");
                QuickSort(0, V.length - 1);
                Show();
                break;

            case 5:
                System.out.println("");
                System.out.println("Bubble Sort");
                BubbleSort();
                break;

            case 6:
                System.out.println("");
                System.out.println("Heap Sort");
                HeapSort();
                Show();
                break;

            case 7:
                System.out.println("");
                System.out.println("Merge Sort");
                MergeSort();
                Show();
                break;

            case 8:
                System.out.println("");
                System.out.println("Radix Sort");
                RadixSort();
                break;
        }
    }

    static void Show() {
        String c = "";
        int i;
        for (i = 0; i < n; i++) {
            System.out.println(V[i]);;
        }
    }
    
    static void ShowMenu(){
        System.out.println("Seleccionar un ordenamiento para el vector: ");
        System.out.println("1. Select Sort");
        System.out.println("2. Insert Sort");
        System.out.println("3. Shell Sort");
        System.out.println("4. Quick Sort");
        System.out.println("5. Bubble Sort");
        System.out.println("6. Heap Sort");
        System.out.println("7. Merge Sort");
        System.out.println("8. Radix Sort");
    }
    
    static void Fill(){
        for (int i = 0; i < n; i++) {
            BigInteger a = new BigInteger(16, new Random()).subtract(new BigInteger("10000000000000"));
            V[i]=(a.multiply(BigInteger.TEN)).abs();
        }
    }

    static void SelectSort() {
        int menor;
        BigInteger tmp;
        for (int i = 0; i < n; i++) {
            menor = i;
            for (int j = i + 1; j < n; j++) {
                if (V[j].compareTo(V[menor])==-1) {
                    menor = j;
                }
            }
            if (menor != i) {
                tmp = V[i];
                V[i] = V[menor];
                V[menor] = tmp;
            }
        }
        Show();
    }

    static void InsertSort() {
        int j = 0;
        BigInteger temp = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            temp = V[i];
            j = i;
            while (j > 0 && temp.compareTo(V[j - 1])==-1) {
                V[j] = V[j - 1];
                j = j - 1;
            }
            V[j] = temp;
        }
        Show();
    }

    static void ShellSort() {
        int inc = V.length / 2, j, k;
        BigInteger temp;
        while (inc > 0) {
            for (int i = inc + 1; i < n; i++) {
                j = i - inc;
                while (j >= 0) {
                    k = j + inc;
                    if (V[j].compareTo(V[k])==1) {
                        temp = V[j];
                        V[j] = V[k];
                        V[k] = temp;
                    } else {
                        j = 0;
                    }
                    j = j - inc;
                }
            }
            inc = inc / 2;
        }
        Show();
    }

    static int Partition(int left, int right) {
        int i = left, j = right;
        BigInteger pivot = V[(left + right) / 2], tmp;

        while (i <= j) {
            while (V[i].compareTo(pivot)==-1) {
                i++;
            }
            while (V[j].compareTo(pivot)==1) {
                j--;
            }
            if (i <= j) {
                tmp = V[i];
                V[i] = V[j];
                V[j] = tmp;
                i++;
                j--;
            }
        };
        return i;
    }

    static void QuickSort(int left, int right) {
        int index = Partition(left, right);
        if (left < index - 1) {
            QuickSort(left, index - 1);
        }
        if (index < right) {
            QuickSort(index, right);
        }
    }

    static void HeapSort() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            Heapify(n, i);
        }

        for (int i = n - 1; i >= 0; i--) {

            BigInteger temp = V[0];
            V[0] = V[i];
            V[i] = temp;

            Heapify(i, 0);
        }
    }

    static void Heapify(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && V[l].compareTo(V[largest])==1) {
            largest = l;
        }

        if (r < n && V[r].compareTo(V[largest])==1) {
            largest = r;
        }

        if (largest != i) {
            BigInteger swap = V[i];
            V[i] = V[largest];
            V[largest] = swap;
            Heapify(n, largest);
        }
    }

    static void BubbleSort() {
        for (int i = 0; i < V.length - 1; i++) {
            for (int j = 0; j < V.length - 1; j++) {
                if (V[j] > V[j + 1]) {
                    int tmp = V[j + 1];
                    V[j + 1] = V[j];
                    V[j] = tmp;
                }
            }
        }
        Show();
    }

    static void MergeSort() {
        int i = 1;
        for (i = 1; i < V.length; i *= 2) {
            for (int j = 0; j < V.length; j += i) {
                int p = i >> 1;
                Merge(j, j + p - 1, j + p, j + p + p - 1);
            }
        }
        Merge(0, i / 2 - 1, i / 2, V.length);
    }

    static void Merge(int a, int b, int c, int d) {
        d = Math.min(d, V.length - 1);
        BigInteger mer[] = new BigInteger[d - a + 1];
        int idx = 0;
        int or = a;
        while (idx < mer.length) {
            if ((a > b ? false : (c > d ? true : V[a] <= V[c]))) {
                mer[idx++] = V[a++];
            } else {
                mer[idx++] = V[c++];
            }
        }

        for (int i = 0; i < mer.length; i++) {
            V[or + i] = mer[i];
        }
    }

    static void RadixSort() {
        if (V.length == 0) {
            return;
        }
        int[][] np = new int[V.length][2];
        int[] q = new int[0x100];
        int i, j, k, l, f = 0;
        for (k = 0; k < 4; k++) {
            for (i = 0; i < (np.length - 1); i++) {
                np[i][1] = i + 1;
            }
            np[i][1] = -1;
            for (i = 0; i < q.length; i++) {
                q[i] = -1;
            }
            for (f = i = 0; i < V.length; i++) {
                j = ((0xFF << (k << 3)) & V[i]) >> (k << 3);
                if (q[j] == -1) {
                    l = q[j] = f;
                } else {
                    l = q[j];
                    while (np[l][1] != -1) {
                        l = np[l][1];
                    }
                    np[l][1] = f;
                    l = np[l][1];
                }
                f = np[f][1];
                np[l][0] = V[i];
                np[l][1] = -1;
            }
            for (l = q[i = j = 0]; i < 0x100; i++) {
                for (l = q[i]; l != -1; l = np[l][1]) {
                    V[j++] = np[l][0];
                }
            }
        }
        Show();
    }
}
