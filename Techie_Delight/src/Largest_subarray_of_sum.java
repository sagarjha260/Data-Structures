import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Largest_subarray_of_sum 
{
	private static void printAllSubArray(int[] arr, int sum) 
	{
		Map<Integer, ArrayList<Integer>> hm= new HashMap<>();
		insert(hm,0,-1);
		
		int sum_so_far = 0;
		int index_sum = 0;
		int max_size_array = 0;
		int subarray_start=0, subarray_end=0;
		for (int i = 0; i < arr.length; i++) 
		{
			sum_so_far += arr[i];
			
			if(hm.containsKey(sum_so_far-sum))
			{
				ArrayList<Integer> list = hm.get(sum_so_far-sum);
				for(Integer value : list) 
				{
					if(value == -1) {
						index_sum = i-(value+1);
					}
					else {
						index_sum = i-value;
					}
					if(index_sum >max_size_array) {
						max_size_array= index_sum;
						subarray_start=value+1;
						subarray_end=i;
					}
				}
			}
			insert(hm,sum_so_far,i);	
		}
		System.out.println("Sub array ["+ subarray_start + "..."+ subarray_end +"]"+ " has Max size array with size "+max_size_array);
		printSubArray(arr, subarray_start, subarray_end);
	}

	private static void insert(Map<Integer, ArrayList<Integer>> hm, int key, int value) 
	{
		if(!hm.containsKey(key))
			hm.put(key, new ArrayList<>());
		hm.get(key).add(value);
	}
	
	private static void printSubArray(int[] arr, int subarray_start, int subarray_end) 
	{
		System.out.println(IntStream.range(subarray_start, subarray_end+1).mapToObj(k->arr[k]).collect(Collectors.toList()));
	}

	public static void main(String[] args) 
	{
		int arr[]= {3,4,-7,3,1,3,1,-4,-2,-2};
		int sum=7;
		
		printAllSubArray(arr, sum);
		int maxSubArray=maxSubArraySum(arr);//This is to get max subarray without any sum
		System.out.println(maxSubArray);
	}
	
	public static int maxSubArraySum(int[] arr) {
		  
	    int size = arr.length;
	    int start = 0;
	    int end = 0;
	  
	    int maxSoFar = 0;
	    int maxEndingHere = 0;
	  
	    for (int i = 0; i < size; i++) 
	    {
	        if (arr[i] > maxEndingHere + arr[i]) 
	        {
	            start = i;
	            maxEndingHere = arr[i];
	        } 
	        else
	            maxEndingHere = maxEndingHere + arr[i];
	  
	        if (maxSoFar < maxEndingHere) 
	        {
	            maxSoFar = maxEndingHere;
	            end = i;
	        }
	    }
	    System.out.println(start+" "+end);
	    return maxSoFar;
	}
}
