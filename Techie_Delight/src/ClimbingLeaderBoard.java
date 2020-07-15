public class ClimbingLeaderBoard
{
	public static void main(String[] args) 
	{	
		int scores[] = {100, 90 , 90,  80, 75, 60}; 
		int alice[]= {50, 65, 77, 90, 102};
		
        int a[]=climbingLeaderboard(scores, alice); 
        for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	static int[] climbingLeaderboard(int[] scores, int[] alice) 
	{
		int n = scores.length;
		int m = alice.length;

		int res[] = new int[m];
		int[] rank = new int[n];

		rank[0] = 1;

		for (int i = 1; i < n; i++) 
		{
			if (scores[i] == scores[i - 1]) 
			{
				rank[i] = rank[i - 1];
		    }
			else 
			{ 
				rank[i] = rank[i - 1] + 1;
		    }
		}
		
		for (int i = 0; i < m; i++)
		{
			int aliceScore = alice[i];
			if (aliceScore > scores[0]) 
			{
				res[i] = 1;
			} 
			else if (aliceScore < scores[n - 1]) 
			{
				res[i] = rank[n - 1] + 1;
			} 
			else
			{
				int index = binarySearch(scores, aliceScore);
				res[i] = rank[index];
			}
		 }
		 return res;
	}
	
	private static int binarySearch(int[] arr, int key) 
	{
		int start = 0;
		int end = arr.length - 1;

		while (start <= end) 
		{
			int mid = (start + end)/ 2;
			
			if (arr[mid] == key) 
				return mid;
	
			else if (arr[mid] < key && key < arr[mid - 1]) 
				return mid;
			
			else if (arr[mid] > key && key >= arr[mid + 1]) 
				return mid + 1;
			
			else if (arr[mid] < key)
				end = mid - 1;
			
			else if (arr[mid] > key) 
				start = mid + 1;
		 }
		 return -1;
	}
}