/*Merge two arrays satisfying given constraints
int[] X = { 0, 2, 0, 3, 0, 5, 6, 0, 0};
int[] Y = { 1, 8, 9, 10, 15 };*/
import java.util.Arrays;

public class MergeAndArrange 
{
	public static void main(String[] args) 
	{
		int[] X = { 0, 2, 0, 3, 0, 5, 6, 0, 0};
		int[] Y = { 1, 8, 9, 10, 15 };
		
		int n1=X.length;
		int n2=Y.length;

		rearrange(X, Y, n1, n2);
		
		System.out.println(Arrays.toString(X));
	}

	private static void rearrange(int[] x, int[] y, int n1, int n2) 
	{
		int first_index=0;
		Arrays.sort(x);
		
		for(int k=0; k<n1; k++) 
		{
			if(x[k] != 0)
			{
				first_index=k;
				break;
			}
		}
			
		int i=0,j=0;
		while(first_index < n1 && j < n2) 
		{
			if(x[first_index] <= y[j]) 
			{
				x[i] = x[first_index];
				first_index++;	
			}
			else if(y[j] < x[first_index]) 
			{
				x[i] = y[j++];
			}	
			i++;
		}
		
		while(first_index < n1) {
			x[i++] = x[first_index++];
		}
		
		while(j < n2) {
			x[i++] = y[j++];
		}		
	}

}
