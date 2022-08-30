package homework1.task2;

import java.util.Arrays;

public class Main {
    /**
     * counting sort or bubble sort implementation
     * @param arr - one-dimensional array to sort
     * @param minBubbleSize - min difference between min and max element for bubble sort mode activation
     */
    public static void sort(int[] arr, int minBubbleSize) {
        if(arr == null)
            throw new IllegalArgumentException("Array cannot be null");

        if(arr.length < 2)
            return;

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        long minMaxDiff = max - min;
        if(minMaxDiff<minBubbleSize) {
            //countingSort
            int[] helpArray = new int[max - min + 1];
            for (int k : arr) {
                helpArray[(k - min)]++;
            }
            //System.out.println(min + " - " +  max);
            //System.out.println(Arrays.toString(helpArray));

            int resIndex = 0;
            for (int i = 0; i < helpArray.length; i++) {
                for (int j = 0; j < helpArray[i]; j++) {
                    arr[resIndex++] = min + i;
                }
            }
        }
        else {
            //bubbleSort
            int n = arr.length;
            while (n != 0) {
                int swap = 0;
                for (int i = 1; i < n; i++) {
                    if (arr[i - 1] > arr[i]) {
                        int temp = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = temp;

                        swap = i;
                    }
                }
                n = swap;
            }
        }

    }

    /**
     * tests array with assert
     * @param arrToTest - one-dimensional array to test
     * @param expectedArr - expected one-dimensional array
     */
    public static void test(int[] arrToTest, int[] expectedArr, int minBubbleSize) {
        System.out.println();
        System.out.println("Testing...");
        sort(arrToTest, minBubbleSize);
        System.out.println(Arrays.toString(expectedArr) + " -> " + Arrays.toString(arrToTest));
        assert expectedArr != null : "Null not allowed here";
        assert arrToTest.length == expectedArr.length : String.format("Different size, expected %d in fact %d", expectedArr.length, arrToTest.length);
        for (int i = 0; i < arrToTest.length; i++) {
            assert arrToTest[i] == expectedArr[i] : String.format("Different elements, expected %d in fact %d", expectedArr[i], arrToTest[i]);
        }
        System.out.println("Test passed");
    }

    public static void main(String[] args) {
        final int MIN_BUBBLE_SIZE = 10;
        int[] testArr1 = {1,2,3,4,5,5,6,9};
        int[] testArr2 = {1,2,3,4,5,6,9};

        int[] arr = {5,6,3,2,5,1,4,9};
        System.out.print(Arrays.toString(arr) + " -> ");
        sort(arr, MIN_BUBBLE_SIZE);
        System.out.println(Arrays.toString(arr));

        //don't forget add -ea to your VM options
        test(arr, testArr1, MIN_BUBBLE_SIZE);
        test(arr, testArr2, MIN_BUBBLE_SIZE);
    }
}
