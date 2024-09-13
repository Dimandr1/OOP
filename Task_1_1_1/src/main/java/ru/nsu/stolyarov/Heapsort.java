package ru.nsu.stolyarov;

//эти импорты для замеров времени работы алгоритмов
//import java.util.ArrayList;
//import java.util.Random;

/**
 * @author Dmitry Stolyarov
   * Реализация алгоритма пирамидальной сортировки
 */

public class Heapsort {
    /**
     * функция опускает элемент вниз по куче, пока он не займет правильное место, т.е. его значение
     * будет не меньше его детей. До тех пор она меняет его местами с наибольшим из детей
     * и снова рекурсивно запускается.
     *
     * @param arr - массив, на котором находится куча
     * @param n   - размер массива
     * @param i   - элемент, который нужно поставить на место
     */
    public static void checkHeap(int[] arr, int n, int i) {
        if (i * 2 + 2 < n && arr[i] < arr[i * 2 + 2] && arr[i * 2 + 1] < arr[i * 2 + 2]) {
            int t = arr[i];
            arr[i] = arr[i * 2 + 2];
            arr[i * 2 + 2] = t;
            checkHeap(arr, n, i * 2 + 2);
        } else if (i * 2 + 1 < n && arr[i] < arr[i * 2 + 1]) {
            int t = arr[i];
            arr[i] = arr[i * 2 + 1];
            arr[i * 2 + 1] = t;
            checkHeap(arr, n, i * 2 + 1);
        }
    }

    /**
     * функция сортирует массив с помощью пирамидальной сортировки.
     *
     * @param arr - исходный массив
     * @return - возвращает отсортированный исходный массив
     */
    public static int[] heapsort(int[] arr) {
        //Превращаем массив в max-кучу
        for (int i = arr.length - 1; i >= 0; i--) {
            checkHeap(arr, arr.length, i);
        }
        /*
        Вынимаем из кучи максимальный элемент и ставим его на правильное место
         восстанавливаем кучу
         */
        for (int i = arr.length - 1; i >= 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            checkHeap(arr, i, 0);
        }
        return arr;
    }

    //замеры времени выполнения
    /*public static void main(String[] args) {
        Random num = new Random();
        int iterations = 10;
        int n = 8192;
        for (int k = 0; k < iterations; k++) {
            n *= 2;
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = num.nextInt(100000000);
            }
            double t = System.currentTimeMillis();
            heapsort(ar);
            System.out.println(n + " numbers - " + (System.currentTimeMillis() - t));
        }
    }*/
}