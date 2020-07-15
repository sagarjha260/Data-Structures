package do_later;
//Median of stream of integers running integers : O(N log N)
import java.util.Comparator;
import java.util.PriorityQueue;

public class RunningMedian
{
	public static double[] getMedians(int arr[] )
	{
		PriorityQueue<Integer> lowers= new PriorityQueue<>(new Comparator<Integer>() {
			
			public int compare(Integer a, Integer b)
			{
				return -1*a.compareTo(b);
			}
		});
		PriorityQueue<Integer> highers= new PriorityQueue<>();
		
		double medians[]= new double[arr.length];
		for (int i = 0; i < medians.length; i++) 
		{
			int number= arr[i];
			addNumber(number, lowers, highers);
			rebalance(lowers, highers);
			medians[i]= getMedians(lowers, highers);
			
		}
		return medians;			
	}
	
	private static void addNumber(int number, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) 
	{
		if(lowers.size() == 0 || number< lowers.peek())
		{
			lowers.add(number);
		}
		else
		{
			highers.add(number);
		}
	}
	
	private static void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) 
	{
		PriorityQueue<Integer> biggerHeap =   lowers.size() > highers.size() ? lowers : highers;
		PriorityQueue<Integer> smallerHeap =  lowers.size() > highers.size() ? highers : lowers ;
		
		if(biggerHeap.size()-smallerHeap.size() >= 2)
		{
			smallerHeap.add(biggerHeap.poll());
		}
	}
	
	private static double getMedians(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) 
	{
		PriorityQueue<Integer> biggerHeap =   lowers.size() > highers.size() ? lowers : highers;
		PriorityQueue<Integer> smallerHeap =  lowers.size() > highers.size() ? highers : lowers ;
		
		if(biggerHeap.size() == smallerHeap.size())
		{
			return ((double)biggerHeap.peek() + smallerHeap.peek())/2; 
		}
		else
		{
			return biggerHeap.peek();
		}
	}

	public static void main(String[] args) 
	{
		/*int arr[]= {5};
		int arr[]= {5, 15};
		int arr[]= {5, 15, 1};*/
		int arr[]= {5, 15, 1, 3};
		
		double returnedArray[] = getMedians(arr);
		for (int i = 0; i < returnedArray.length; i++) 
		{
			System.out.println(returnedArray[i]);
		}
	}

}
