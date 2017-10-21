package ordenamientos;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ordenamientos {

    static int n = 2000;
    static BigInteger V[] = new BigInteger[n];
    static int Iterations = 0;
    static long Time;

    public static void main(String[] args) {
        Fill();
        Scanner sc = new Scanner(System.in);
        ShowMenu();
        Switch(sc);
        Show();
        System.out.println("");
        System.out.println("Iteraciones: " + Iterations);
        Time = System.nanoTime() - Time;
        System.out.println("Tiempo de ejecución: " + Time);
    }

    static void Switch(Scanner sc) {
        int opc = sc.nextInt();
        switch (opc) {
            case 1:
                System.out.println("");
                System.out.println("Select Sort");
                Time = System.nanoTime();
                SelectSort();
                break;

            case 2:
                System.out.println("");
                System.out.println("Insert Sort");
                Time = System.nanoTime();
                InsertSort();
                break;

            case 3:
                System.out.println("");
                System.out.println("Shell Sort");
                Time = System.nanoTime();
                ShellSort();
                break;

            case 4:
                System.out.println("");
                System.out.println("Quick Sort");
                Time = System.nanoTime();
                QuickSort(0, n - 1);
                break;

            case 5:
                System.out.println("");
                System.out.println("Bubble Sort");
                Time = System.nanoTime();
                BubbleSort();
                break;

            case 6:
                System.out.println("");
                System.out.println("Heap Sort");
                Time = System.nanoTime();
                HeapSort();
                break;

            case 7:
                System.out.println("");
                System.out.println("Merge Sort");
                Time = System.nanoTime();
                MergeSort(0, n - 1);
                break;

            case 8:
                System.out.println("");
                System.out.println("Radix Sort");
                Time = System.nanoTime();
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

    static void AddIteration() {
        Iterations++;
    }

    static void ShowMenu() {
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

    static void Fill() {
        for (int i = 0; i < n; i++) {
            BigInteger a = new BigInteger(16, new Random()).subtract(new BigInteger("10000000000000"));
            V[i] = (a.multiply(BigInteger.TEN)).abs();
        }
    }

    static void SelectSort() {
        int menor;
        BigInteger tmp;
        for (int i = 0; i < n; i++) {
            menor = i;
            for (int j = i + 1; j < n; j++) {
                if (V[j].compareTo(V[menor]) == -1) {
                    menor = j;
                }
            }
            if (menor != i) {
                tmp = V[i];
                V[i] = V[menor];
                V[menor] = tmp;
            }
            AddIteration();
        }
    }

    static void InsertSort() {
        int j = 0;
        BigInteger temp = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            temp = V[i];
            j = i;
            while (j > 0 && temp.compareTo(V[j - 1]) == -1) {
                V[j] = V[j - 1];
                j = j - 1;
            }
            V[j] = temp;
            AddIteration();
        }
    }

    static void ShellSort() {
        int inc = V.length / 2, j, k;
        BigInteger temp;
        while (inc > 0) {
            for (int i = inc + 1; i < n; i++) {
                j = i - inc;
                while (j >= 0) {
                    k = j + inc;
                    if (V[j].compareTo(V[k]) == 1) {
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
            AddIteration();
        }
    }

    static int Partition(int left, int right) {
        int i = left, j = right;
        BigInteger pivot = V[(left + right) / 2], tmp;

        while (i <= j) {
            while (V[i].compareTo(pivot) == -1) {
                i++;
            }
            while (V[j].compareTo(pivot) == 1) {
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
        AddIteration();
    }

    static void HeapSort() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            Heap(n, i);
            AddIteration();
        }

        for (int i = n - 1; i >= 0; i--) {

            BigInteger temp = V[0];
            V[0] = V[i];
            V[i] = temp;

            Heap(i, 0);
            AddIteration();
        }
    }

    static void Heap(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && V[l].compareTo(V[largest]) == 1) {
            largest = l;
        }

        if (r < n && V[r].compareTo(V[largest]) == 1) {
            largest = r;
        }

        if (largest != i) {
            BigInteger swap = V[i];
            V[i] = V[largest];
            V[largest] = swap;
            Heap(n, largest);
        }
    }

    static void BubbleSort() {
        for (int i = 0; i < V.length - 1; i++) {
            for (int j = 0; j < V.length - 1; j++) {
                if (V[j].compareTo(V[j + 1]) == -1) {
                    BigInteger tmp = V[j + 1];
                    V[j + 1] = V[j];
                    V[j] = tmp;
                }
            }
            AddIteration();
        }
    }

    static void MergeSort(int lower, int higher) {
        if (lower < higher) {
            int middle = lower + (higher - lower) / 2;
            MergeSort(lower, middle);
            MergeSort(middle + 1, higher);
            Merge(lower, middle, higher);
        }
        AddIteration();
    }

    static void Merge(int lo, int mid, int hi) {
        BigInteger temp[] = new BigInteger[n];
        for (int i = lo; i <= hi; i++) {
            temp[i] = V[i];
        }
        int i = lo;
        int j = mid + 1;
        int k = lo;
        while (i <= mid && j <= hi) {
            if (temp[i].compareTo(temp[j]) <= -1) {
                V[k] = temp[i];
                i++;
            } else {
                V[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            V[k] = temp[i];
            k++;
            i++;
        }
    }

    static void RadixSort() {
        BigInteger m = getMax();
        int exp = 1;
        BigInteger w = m.divide(new BigInteger(exp + ""));
        while (w.compareTo(BigInteger.ZERO) > 1) {
            countSort(exp);
            exp *= 10;
        }
        AddIteration();

    }

    static void countSort(int exp) {
        BigInteger output[] = new BigInteger[n];
        int i;
        BigInteger count[] = new BigInteger[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++) {
            int w = Integer.valueOf((V[i].divide(new BigInteger(exp + ""))).mod(BigInteger.TEN) + "");
            count[w].add(BigInteger.ONE);
        }

        for (i = 1; i < 10; i++) {
            count[i] = count[i].add(count[i - 1]);
        }

        for (i = n - 1; i >= 0; i--) {
            int w = Integer.valueOf((V[i].mod(new BigInteger(exp + ""))).mod(BigInteger.TEN) + "");
            int y = Integer.valueOf(count[w].subtract(BigInteger.ONE) + "");
            output[y] = V[i];
            int z = Integer.valueOf((V[i].divide(new BigInteger(exp + ""))).mod(BigInteger.TEN) + "");
            count[z].subtract(BigInteger.ONE);
        }

        for (i = 0; i < n; i++) {
            V[i] = output[i];
        }
    }

    static BigInteger getMax() {
        BigInteger mx = V[0];
        for (int i = 1; i < n; i++) {
            if (V[i].compareTo(mx) == -1) {
                mx = V[i];
            }
        }
        return mx;
    }
}
