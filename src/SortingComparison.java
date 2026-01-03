import java.util.*;

public class SortingComparison {

    public static void main(String[] args) {
        int[] arr = new int[1000];
        Random rand = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();

        // Populate the array with unique random integers
        int i = 0;
        while (i < arr.length) {
            int randomNum = rand.nextInt(10000);
            if (uniqueNumbers.add(randomNum)) {
                arr[i++] = randomNum;
            }
        }

        // Create copies of the array for each sorting algorithm
        int[] bubbleSortArr = Arrays.copyOf(arr, arr.length);
        int[] selectionSortArr = Arrays.copyOf(arr, arr.length);
        int[] insertionSortArr = Arrays.copyOf(arr, arr.length);
        int[] mergeSortArr = Arrays.copyOf(arr, arr.length);
        int[] quickSortArr = Arrays.copyOf(arr, arr.length);

        // Print the original array
        System.out.println("Original Array:");
        printArray(arr);

        // Bubble Sort
        System.out.println("\nBubble Sort Input Array:");
        printArray(bubbleSortArr);
        int[] bubbleSortCounts = bubbleSort(bubbleSortArr);
        System.out.println("Bubble Sort Comparisons: " + bubbleSortCounts[0]);
        System.out.println("Bubble Sort Exchanges: " + bubbleSortCounts[1]);

        // Selection Sort
        System.out.println("\nSelection Sort Input Array:");
        printArray(selectionSortArr);
        int[] selectionSortCounts = selectionSort(selectionSortArr);
        System.out.println("Selection Sort Comparisons: " + selectionSortCounts[0]);
        System.out.println("Selection Sort Exchanges: " + selectionSortCounts[1]);

        // Insertion Sort
        System.out.println("\nInsertion Sort Input Array:");
        printArray(insertionSortArr);
        int[] insertionSortCounts = insertionSort(insertionSortArr);
        System.out.println("Insertion Sort Comparisons: " + insertionSortCounts[0]);
        System.out.println("Insertion Sort Exchanges: " + insertionSortCounts[1]);

        // Merge Sort
        System.out.println("\nMerge Sort Input Array:");
        printArray(mergeSortArr);
        int[] mergeSortAux = new int[arr.length];
        int mergeSortComparisons = mergeSort(mergeSortArr, mergeSortAux, 0, arr.length - 1);
        System.out.println("Merge Sort Comparisons: " + mergeSortComparisons);
        // Note: Merge Sort doesn't explicitly count exchanges, as it doesn't involve direct swaps.

        // Quick Sort
        System.out.println("\nQuick Sort Input Array:");
        printArray(quickSortArr);
        int quickSortComparisons = quickSort(quickSortArr, 0, arr.length - 1);
        System.out.println("Quick Sort Comparisons: " + quickSortComparisons);
        // Note: Quick Sort doesn't explicitly count exchanges, as it doesn't involve direct swaps.
    }

    // Helper function to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Bubble Sort
    public static int[] bubbleSort(int[] arr) {
        int comparisons = 0, exchanges = 0;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                comparisons++;
                if (arr[i] > arr[i + 1]) {
                    exchanges++;
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        return new int[]{comparisons, exchanges};
    }

    // Selection Sort
    public static int[] selectionSort(int[] arr) {
        int comparisons = 0, exchanges = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                exchanges++;
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return new int[]{comparisons, exchanges};
    }

    // Insertion Sort
    public static int[] insertionSort(int[] arr) {
        int comparisons = 0, exchanges = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            comparisons++;
            while (j >= 0 && arr[j] > key) {
                exchanges++;
                arr[j + 1] = arr[j];
                j--;
                comparisons++;
            }
            arr[j + 1] = key;
        }
        return new int[]{comparisons, exchanges};
    }

    // Merge Sort
    public static int mergeSort(int[] arr, int[] aux, int lo, int hi) {
        int comparisons = 0;
        if (hi <= lo) {
            return comparisons;
        }
        int mid = lo + (hi - lo) / 2;
        comparisons += mergeSort(arr, aux, lo, mid);
        comparisons += mergeSort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
        return comparisons;
    }

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                aux[k] = arr[j++];
            } else if (j > hi) {
                aux[k] = arr[i++];
            } else if (arr[i] < arr[j]) {
                aux[k] = arr[i++];
            } else {
                aux[k] = arr[j++];
            }
        }
        System.arraycopy(aux, lo, arr, lo, hi - lo + 1);
    }

    // Quick Sort
    public static int quickSort(int[] arr, int lo, int hi) {
        int comparisons = 0;
        if (hi <= lo) {
            return comparisons;
        }
        int j = partition(arr, lo, hi);
        comparisons += (hi - lo); // Count comparisons for partition
        comparisons += quickSort(arr, lo, j - 1);
        comparisons += quickSort(arr, j + 1, hi);
        return comparisons;
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int i = lo;
        for (int j = lo + 1; j <= hi; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[lo];
        arr[lo] = arr[i];
        arr[i] = temp;
        return i;
    }
}
