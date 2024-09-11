package ru.nsu.stolyarov;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void CheckHeap(int[] arr, int n, int i) {
        if (i * 2 + 2 < n && arr[i] < arr[i * 2 + 2] && arr[i * 2 + 1] < arr[i * 2 + 2]) {
            int t = arr[i];
            arr[i] = arr[i * 2 + 2];
            arr[i * 2 + 2] = t;
            CheckHeap(arr, n, i * 2 + 2);
        } else if (i * 2 + 1 < n && arr[i] < arr[i * 2 + 1]) {
            int t = arr[i];
            arr[i] = arr[i * 2 + 1];
            arr[i * 2 + 1] = t;
            CheckHeap(arr, n, i * 2 + 1);
        }
    }

    public static int[] Heapsort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            CheckHeap(arr, arr.length, i);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            CheckHeap(arr, i, 0);
        }
        return arr;
    }
}