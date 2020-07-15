public class LongestPalindromicSubString 
{
	private static int expand(String s, int left, int right) 
	{
	    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) 
	    {
	        left--;
	        right++;
	    }
	    return right - left - 1;
	}
	
	public static String longestPalindrome(String str) 
	{
	    int start = 0, end = 0;
	    for (int i = 0; i < str.length(); i++) 
	    {
	        int len1 = expand(str, i, i);
	        int len2 = expand(str, i, i + 1);
	        int maxlength = Math.max(len1, len2);
	        
	        if (maxlength > end - start) 
	        {
	            start = i - (maxlength - 1) / 2;
	            end = i + maxlength / 2;
	        }
	    }
	    return str.substring(start, end + 1);
	}
	public static void main(String[] args) 
	{
		String str="google";
		System.out.println(longestPalindrome(str));
	}
}