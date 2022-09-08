package homework1.task1;

import java.security.InvalidParameterException;

public class Main {
    /**
     * fills two-dimensional array with random int
     * @param arr - two-dimensional array to fill
     * @param min - min generated int
     * @param max - max generated int
     */
    public static void fillArrayRandom(int[][] arr, int min, int max) {
        int count = (max-min);
        long last = (System.currentTimeMillis() % 31);

        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {
            int arriLength = arr[i].length;
            for (int j = 0; j < arriLength; j++) {
                last = last * 6364136223846793005L + 1442695040888963407L;
                if(last < 0)
                    last >>>= 1;

                arr[i][j] = (int)(last % (count+1) + min);
            }
        }
    }

    /**
     * finds min, max and average(rounded to int) of two-dimensional array
     * @param arr - two-dimensional array to fill
     * @return - array int[3] -> [min, max, avg]
     */
    public static int[] fillMinMaxAvg(int[][] arr) {
        int[] res = new int[3];
        res[0] = Integer.MAX_VALUE;
        res[1] = Integer.MIN_VALUE;
        long sum = 0;
        long count = 0;

        if(arr == null || arr.length == 0)
            throw new InvalidParameterException("Array should be initialized!");

        for (int[] ints : arr) {
            for (int anInt : ints) {
                sum += anInt;
                count++;

                if (anInt < res[0])
                    res[0] = anInt;

                if (anInt > res[1])
                    res[1] = anInt;
            }
        }

        if(count == 0)
            throw new InvalidParameterException("All lines are empty in array!");

        res[2] = (int)(sum/count);

        return res;
    }

    /**
     * prints array to console with colors
     * @param arr - two-dimensional array to print
     * @param arrayData - array int[3] -> [min, max, avg]
     */
    public static void printArray(int[][] arr, int[] arrayData) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED	= "\u001B[31m";
        String ANSI_GREEN =	"\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";
        String curColor;

        int maxLength = String.valueOf(arrayData[0]).length();
        if (String.valueOf(arrayData[1]).length() > maxLength)
            maxLength = String.valueOf(arrayData[1]).length();

        for (int[] ints : arr) {
            for (int anInt : ints) {

                if (anInt == arrayData[0])
                    curColor = ANSI_RED;
                else if (anInt == arrayData[1])
                    curColor = ANSI_GREEN;
                else if (anInt == arrayData[2])
                    curColor = ANSI_BLUE;
                else
                    curColor = ANSI_RESET;

                System.out.printf(curColor + " %" + maxLength + "d" + ANSI_RESET, anInt);
            }
            System.out.printf("%n");
        }
        System.out.println(" ------------------------------");

        System.out.printf(ANSI_RED + " [MIN: %d" + ANSI_BLUE + ", AVG: %d" + ANSI_GREEN + ", MAX: %d]%n" + ANSI_RESET
                , arrayData[0], arrayData[2], arrayData[1]);
    }

    /**
     * tests array with assert
     * @param arr - two-dimensional array to test
     * @param testdata - array int[3] -> [min, max, avg] to check
     */
    public static void test(int[][] arr, int[] testdata) {
        System.out.println("\nTesting...");
        int[] factdata = fillMinMaxAvg(arr);
        printArray(arr, factdata);
        assert testdata[0] == factdata[0] : String.format("MIN expected: %s, in fact: %s", testdata[0], factdata[0]);
        assert testdata[1] == factdata[1] : String.format("MAX expected: %s, in fact: %s", testdata[1], factdata[1]);
        assert testdata[2] == factdata[2] : String.format("AVG expected: %s, in fact: %s", testdata[2], factdata[2]);
        System.out.println("Test passed");
    }

    public static void main(String[] args) {
        int[][] arr = new int[5][10];
        fillArrayRandom(arr, -100, 100);
        int[] res = fillMinMaxAvg(arr);
        printArray(arr, res);

        int[][] testArr1 = new int[][]{
                {  72, -39, -52, -28, -84, -83, -61,  65, -45, -27},
                {  -4,  80, -30, -33, -41, -22,   3,  96, -19,   7},
                {  60,  60,   5, -28,  44, -59, -29, -18, -48, -13},
                {  72, 100, -38,  38,  48,  97,   0,  -2, -26,  81},
                {  86, -22, -88,  52, -50,  46, -88, -99, -51,  94}
        };
        int[] testData1 = new int[] {-99, 100, 0};

        int[][] testArr2 = new int[][]{
                {  1, 2, 3},
                {  4,  5, 6},
                {  7},
                {}};
        int[] testData2 = new int[] {1, 7, 4};

        int[][] testArr3 = new int[][]{
                {5},
        };
        int[] testData3 = new int[] {5, 5, 5};

        //don't forget add -ea to your VM options
        test(testArr1, testData1);
        test(testArr2, testData2);
        test(testArr3, testData3);
    }
}