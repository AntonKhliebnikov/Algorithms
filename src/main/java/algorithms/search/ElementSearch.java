package algorithms.search;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ElementSearch {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            System.out.println("Введите размер массива:");
            int capacity = scanner.nextInt();
            int[] array = new int[capacity];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(101);
            }

            Arrays.sort(array);
            System.out.println(Arrays.toString(array));

            System.out.println("Введите число - программа выведет искомый индекс:");
            int element = scanner.nextInt();
            binarySearch(array, element);
        }
    }

    private static void binarySearch(int[] array, int element) {
        int foundIndex = 0;
        int steps = 0;
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            steps++;
            int mid = (left + right) / 2;
            if (array[mid] == element) {
                System.out.println("Число находится под индексом: " + mid);
                foundIndex++;
                break;
            }

            if (array[mid] < element) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Для поиска индекса числа, было совершено: " + steps + " шага/ов!");
        if (foundIndex < 1) {
            System.out.println("Указанного числа нет в массиве!");
        }
    }
}