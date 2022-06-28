package com.company;

public class Main {

    // 4
    public static int oddOrPos(int[] x)
    {
        int count = 0;
        for (int i = 0; i < x.length; i++)
        {
            if (x[i]%2 != 0 || x[i] > 0)
            {
                count++;
            }
        }
        return count;
    }

    // 3
    public static int countPositive(int[] x)
    {
        int count = 0;
        for (int i=0; i < x.length; i++)
        {
            if (x[i] > 0)
            {
                count++;
            }
        }
        return count;
    }


    // 2
    public static int lastZero (int[] x)
    {
        for (int i = x.length-1; i >=0; i--)
        {
            if (x[i] == 0)
            {
                return i;
            }
        }
        return -1;
    }

    // 1. find last
    public static int findLast(int[] x, int y)
    {
        for (int i=x.length-1; i >= 0; i--)
        {
            if (x[i] == y)
            {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
	// write your code here
        // 1. find last
        int x[] = {2,3,5};
        int y = 2;
        int result = findLast(x, y);
        System.out.println(result);

        // 2. last zero
        int x2[] = {0,1,0};
        int result2 = lastZero(x2);
        System.out.println(result2);

        // 3 countPositive
        int x3[] = {-4, 2, 0, 2};
        int result3 = countPositive(x3);
        System.out.println(result3);

        // 4 oddOrPos
        int x4[] = {-3, -2, 0, 1, 4};
        int result4 = oddOrPos(x4);
        System.out.println(result4);
    }

}
