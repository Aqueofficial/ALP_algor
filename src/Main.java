import java.util.Arrays;
import java.util.Random;


public class Main {


    //1.Heap sort n log n

    public static void heapify(int[] A, int i, int heapSize) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;
        if (left < heapSize && A[left] > A[largest]) {
            largest = left;
        }
        if (right < heapSize && A[right] > A[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            heapify(A, largest, heapSize);
        }
    }
    public static void heapSort(int[] A) { // teta(n*logn)
        int n = A.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(A, i, n);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heapify(A, 0, i);
        }
    }


    //2. Merge Sort n log n
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);

    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int l = 0; l < temp.length; l++) {
            arr[left + l] = temp[l];
        }
    }
    //3. Bubble sort     n

    // caly massiw sortujemy
    public static void bubbleSort(int[] A) {
        bubbleSort(A, 0, A.length - 1);
    }

    // nie caly [left; right] - dla Hybrid2
    private static void bubbleSort(int[] A, int left, int right) {
        int n = right - left + 1;
        for (int i = 0; i < n - 1; i++) {
            boolean bylaZmiana = false;
            for (int j = left; j < left + n - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int t = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = t;
                    bylaZmiana = true;
                }
            }
            if (!bylaZmiana) {
                break;
            }
        }
    }

    // help dla hybrydu - insertion sort
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static final int SMALL = 16;


    // 4. Hybrid sort: merge sort + insertion sort     n
    public static void hybridSort1(int[] arr) {
        hybridSort1(arr, 0, arr.length - 1);
    }

    private static void hybridSort1(int[] arr, int left, int right) {
        if (right - left < SMALL) {
            insertionSort(arr, left, right);
            return;
        }
        int mid = (left + right) / 2;
        hybridSort1(arr, left, mid);
        hybridSort1(arr, mid + 1, right);

        if (arr[mid] > arr[mid + 1]) {
            merge(arr, left, mid, right);
        }
    }

    //5.Hybryd sort: merge sort + bubble sort      n


    public static void hybridSort2(int[] arr) {
        hybridSort2(arr, 0, arr.length - 1);
    }

    private static void hybridSort2(int[] arr, int left, int right) {
        if (right - left < SMALL) {
            bubbleSort(arr, left, right);
            return;
        }
        int mid = (left + right) / 2;
        hybridSort2(arr, left, mid);
        hybridSort2(arr, mid + 1, right);

        if (arr[mid] > arr[mid + 1]) {
            merge(arr, left, mid, right);
        }
    }
    static final int N = 10000;

    // test 1
    static int[] test1() {
        Random rnd = new Random(1);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = rnd.nextInt(1000001);
        }
        return arr;
    }

    // test 2
    static int[] test2() {
        Random rnd = new Random(2);
        int[] arr = new int[N];
        for (int i = 0; i < 9000; i++) {
            arr[i] = i;
        }
        for (int i = 9000; i < N; i++) {
            arr[i] = rnd.nextInt(1000001);
        }
        return arr;
    }

    // test 3
    static int[] test3() {
        Random rnd = new Random(3);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = rnd.nextInt(10);
        }
        return arr;
    }

    // test 4
    static int[] test4() {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = N - i;
        }
        return arr;
    }

    // ---------- tests ----------

    static void sortBy(int algo, int[] arr) {
        if (algo == 0) heapSort(arr);
        else if (algo == 1) mergeSort(arr);
        else if (algo == 2) bubbleSort(arr);
        else if (algo == 3) hybridSort1(arr);
        else if (algo == 4) hybridSort2(arr);
        else Arrays.sort(arr);    // n
    }

    public static void main(String[] args) {
        String[] algoNames = {"Heapsort", "MergeSort", "BubbleSort",
                "Hybrid1 (M+Ins)", "Hybrid2 (M+Bub)", "Arrays.sort"};
        String[] testNames = {"Test 1 (random 0..1M)", "Test 2 (9000 sorted + 1000)",
                "Test 3 (0..9)", "Test 4 (reverse)"};
        int[][] tests = {test1(), test2(), test3(), test4()};

        // przegrew JIT
        for (int a = 0; a < algoNames.length; a++) {
            sortBy(a, tests[0].clone());
        }

        for (int t = 0; t < tests.length; t++) {
            System.out.println(testNames[t]);

            // prawidlowy wynik
            int[] expected = tests[t].clone();
            Arrays.sort(expected);

            for (int a = 0; a < algoNames.length; a++) {
                int[] copy = tests[t].clone(); // кsortujemy kopije

                long start = System.nanoTime();
                sortBy(a, copy);
                long end = System.nanoTime();

                double ms = (end - start) / 1000000.0;
                String result = Arrays.equals(copy, expected) ? "OK" : "WRONG";
                System.out.printf("  %-16s %8.3f ms  %s%n", algoNames[a], ms, result);
            }
            System.out.println();
        }
    }
}