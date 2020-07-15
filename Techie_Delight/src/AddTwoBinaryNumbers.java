public class AddTwoBinaryNumbers 
{ 
	static String addBinary(String a, String b) 
	{
		String result = ""; 
		int sum = 0;		 

		//Traverse both strings starting from last characters 
		int i = a.length() - 1;
		int j = b.length() - 1; 
		while (i >= 0 || j >= 0 || sum == 1) 
		{ 
			// Comput sum of last digits and carry 
			sum += ((i >= 0)? a.charAt(i) - '0': 0); 
			sum += ((j >= 0)? b.charAt(j) - '0': 0); 

			result = (char)( sum % 2 + '0') + result;  // If current digit sum is 1 or 3, add 1 to result

			sum /= 2;  // Compute carry 
			
			i--; 
			j--; 
		} 
		
	    return result; 
	} 
	
	public static void main(String args[]) { 
		String a = "10101", b="10001"; 
		System.out.print(addBinary(a, b)); 
	} 
} 
