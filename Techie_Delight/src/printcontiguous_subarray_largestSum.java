//By Kadane's Algorithm. Time complexity o(n) and space complexity o(1)
class printcontiguous_subarray_largestSum
{
	public static void kadane(int arr[], int n)
	{
		int start = 0, end = 0,beg = 0;
		int maxSoFar = 0;
		int maxEndingHere = 0;
		
		for (int i = 0; i < n; i++)
		{
			maxEndingHere = maxEndingHere + arr[i];
			
			if (maxEndingHere < 0)
			{
				maxEndingHere = 0;
				beg = i + 1;
			}
			
			if (maxSoFar < maxEndingHere)
			{
				maxSoFar = maxEndingHere;
				start = beg;
				end = i;
			}
		}

		System.out.println("The sum of contiguous sub-array with the largest sum is " +	maxSoFar);
		System.out.print("The contiguous sub-array with the largest sum is ");
		for (int i = start; i <= end; i++) 
		{
			System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args)
	{
		int[] arr = { 3,4,-7,3,1,3,1,-4,-2,-2};
		kadane(arr, arr.length);
	}
}