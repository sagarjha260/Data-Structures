//Find the max sum sub-array of a fixed size K(based on: first take sum till k then subtract left most and add next one and check if this is maximum )
package SlidingWindow;
public class MaxSumSubarray 
{
    public static int findMaxSumSubarray(int[] arr, int k)
    {
        int maxValue = Integer.MIN_VALUE;
        int currentRunningSum = 0;

        for (int i = 0; i < arr.length; i++)
        {
            currentRunningSum += arr[i];

            if (i >= k - 1)
            {
                maxValue = Math.max(maxValue, currentRunningSum);
                currentRunningSum -= arr[i - (k - 1)];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) 
    {
    	int[] arr = { 1, 4, 2, 10, 2, 3, 1, 0, 20};
        System.out.println(findMaxSumSubarray(arr, 4));
    }
}