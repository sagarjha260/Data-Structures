public class KthMaxElement 
{
	public static void main(String[] args) {
		int arr[]= {12, 11, 13, 5, 6, 7};
		int k= 3;
		sort(arr, k);
	}

	private static void sort(int[] arr, int k) 
	{
		int n=arr.length;
		if(k > n) {
            System.out.println("Invalid k size");
            return;
        }
		
		for (int i = n/2-1; i >=0; i--) 
			heapify(arr, n, i);
		
		for (int i = n-1; i >= n-k; i--)
		{
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			
			if(i == n-k)
				System.out.println(arr[i]);
			
			heapify(arr, i, 0);
		}
	}

	private static void heapify(int[] arr, int n, int i) 
	{
		int largest=i;
		int l=2*i + 1;
		int r=2*i + 2;
		
		if(l<n && arr[l] > arr[largest])
			largest = l;
		
		if(r<n && arr[r] > arr[largest])
			largest = r;
		
		if(largest != i)
		{
			int temp=arr[i];
			arr[i]=arr[largest];
			arr[largest]=temp;
			
			heapify(arr, n, largest);
		}
	}
}
