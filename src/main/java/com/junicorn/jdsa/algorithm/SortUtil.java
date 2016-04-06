package com.junicorn.jdsa.algorithm;

/**
 * Created by taoxiaoran on 16/3/31.
 */

/**
 * 排序算法总结
 */
public class SortUtil {

    public void insertionSort(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int temp = a[i];
            while (j >= 0 && temp < a[j]) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = temp;
        }
    }

    public void bubbleSort(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        boolean flag;
        for (int i = 0; i < a.length - 1; i++) {
            flag = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
     }

    public void selectSort(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            int k = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
     }

    // 快速排序
    public void quickSort(int[] a, int low, int high) {

        if (null == a || a.length < 2) {
            return;
        }

        if (low < high) {
            int mid = partition(a, low, high);
            quickSort(a, low, mid-1);
            quickSort(a, mid+1, high);
        }
    }

    private int partition(int[] a, int low, int high) {
        int pivot = a[low];

        while (low < high) {
            while (low < high && a[high] >= pivot) {
                high--;
            }
            a[low] = a[high];

            while (low < high && a[low] <= pivot) {
                low++;
            }
            a[high] = a[low];
        }

        a[low] = pivot;

        return low;
    }

    public void heapSort(int[] a) {
        if (null == a) {
            return;
        }
        buildMaxHeap(a);

        for (int i = a.length - 1; i >= 0; i--) {
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            adjustHeap(a, i, 0);
        }
    }

    private void buildMaxHeap(int[] a) {
        int mid = a.length / 2;
        for (int i = mid; i >=0; i--) {
            adjustHeap(a, a.length, i);
        }
    }

    private void adjustHeap(int[] a, int size, int parent) {
        int left = 2*parent + 1;
        int right = 2*parent + 2;
        int largest = parent;

        if (left < size && a[left] > a[parent]) {
            largest = left;
        }

        if (right < size && a[right] > a[largest]) {
            largest = right;
        }

        if (largest != parent) {
            int temp = a[largest];
            a[largest] = a[parent];
            a[parent] = temp;
            adjustHeap(a, size, largest);
        }
    }


    public void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid+1, high);
            merge(a, low, mid, high);
        }
    }

    private void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];

        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= high) {
            temp[k++] = a[j++];
        }

        for (int t = 0; t < temp.length; t++) {
            a[low+t] = temp[t];
        }
    }



    public static void main(String[] args) {
        int[] a = {1,3,2,5,4,8,9};
        SortUtil su = new SortUtil();
        su.quickSort(a, 0, a.length-1);
//        su.heapSort(a);
        for (int val : a) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
