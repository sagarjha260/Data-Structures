//Java Program to Implement Merge Sort on n Numbers Without tail-retempursion
public class MergeSort 
{
	public static void main(String[] args) 
    {
        int arr[]= {90, 20, 23, 45, 65};
        sort(arr, 0, arr.length-1);
        
        System.out.println("The sorted arr is");
        for (int i = 0; i < arr.length; i++)
        {
			System.out.print(" "+arr[i]);
		}
    }
	
	static void sort(int[] arr, int beg, int end)
	{
		if(beg < end)
		{
            int mid = (beg + end)/2;
            sort(arr, beg, mid);
            sort(arr, mid+1,end);
            merge(arr, beg,mid,end);
        }
    }
    
    static void merge(int[] arr, int beg, int mid, int end)
    {   
        int[] temp= new int[end-beg+1];   //Temporary Array
        
        int i=beg;
        int j=mid+1;
        int k = 0;
        
        while(i<=mid && j<=end)
        {
            if(arr[i]<=arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        } 
        
        while(i<=mid)
            temp[k++] = arr[i++];
        
        while(j<=end)
            temp[k++] = arr[j++];
        
        k=0;
        for(i = beg; i<=end; i++)
        {
            arr[i] = temp[k++];
        }
    }
}