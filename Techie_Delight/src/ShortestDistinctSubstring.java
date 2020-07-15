//Java program to find the smallest window containing all characters of a pattern. (Repeating allowed)
import java.util.Arrays; 
public class ShortestDistinctSubstring
{ 
	static String findSubString(String str) 
	{ 
		int n = str.length(); 
		int distinctCharCount = 0; 
		
		boolean[] visited = new boolean[256]; 
		Arrays.fill(visited, false);
		for (int i = 0; i < n; i++) 
		{ 
			if (visited[str.charAt(i)] == false) 
			{ 
				visited[str.charAt(i)] = true; 
				distinctCharCount++; 
			} 
		} 

		int[] currentCount = new int[256]; 
		int start = 0;
		int startIndex = -1; 
		int count = 0; 
		int minLength = Integer.MAX_VALUE; 
		
		for (int j = 0; j < n; j++) 
		{ 
			currentCount[str.charAt(j)]++; 

			if (currentCount[str.charAt(j)] == 1) 
				count++; 

			if (count == distinctCharCount) 
			{  
				while (currentCount[str.charAt(start)] > 1) 
				{ 
					if (currentCount[str.charAt(start)] > 1) 
						currentCount[str.charAt(start)]--; 
					start++; 
				} 

				int windowLength = j - start + 1; 
				if (minLength > windowLength) 
				{ 
					minLength = windowLength; 
					startIndex = start; 
				} 
			} 
		} 
		return str.substring(startIndex, startIndex + minLength); 
	} 

	public static void main(String args[]) 
	{ 
		String str = "aabcddee"; 
		System.out.println("Smallest window containing all distinct characters is: " + findSubString(str)); 
	} 
} 