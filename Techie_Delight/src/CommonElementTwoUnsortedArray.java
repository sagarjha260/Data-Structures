import java.util.Arrays;

/*
larger array equals a
other array equals b
sort a
iterate through b
binary search b at iterated index
  I would throw (last index - index) logic in binary search to exit out of that even faster by returning "NOT FOUND" 
  as soon as that is hit.if found && (last index - index) is less than or equal store last index print value
*/
public class CommonElementTwoUnsortedArray 
{
	public static void main(String[] args) 
	{
		int arr1[] = { 1, 4, 5, 2, 7, 3, 9 };
		int arr2[] = { 5,6,10 };
		
		Arrays.sort(arr1);  //1,2,3,4,5,7,9
		
		for (int i = 0; i < arr2.length; i++) 
		{
			int result= search(arr1, arr2[i]);
			if(result != -1)
				System.out.println(result+ "");
		}
	}

	private static int search(int[] arr1, int key) 
	{
		int first=0, last=arr1.length-1;
		
		while(first <= last)
		{
			int middle=(first+last)/2;
			
			if(arr1[middle]< key)
				first = middle+1;
			
			else if(arr1[middle] == key)
			{
				return arr1[middle];
			}
			
			else
				last = middle-1;
		}
		return -1;
	}
}
