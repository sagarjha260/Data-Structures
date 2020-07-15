import java.util.HashSet;
import java.util.Set;

public class AllSubString 
{
	public static void findSubstring(char[] ch, int n)
	{
        for (int i=0; i < n ; i++)
        {
        	
            StringBuilder sb = new StringBuilder();
            sb.append(ch[i]);
            for (int j=i+1; j <= n ; j++)
            {
                System.out.println(sb);
                
                if (j < n)
                {
                    sb.append(ch[j]);
                }
            }
        }
    }    
    public static void main(String args[]) 
    {
      String str = "abaa";
      int n = str.length();
      
      int total_substring=n*(n+1)/2;
      System.out.println("Total substring is: "+total_substring);
      
      findSubstring(str.toCharArray(), n);    
    }
}
/*
 * Note : number of substring formula is n*(n+1)/2
 */
