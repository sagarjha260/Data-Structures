//Longest substring without repeating characters
import java.util.*;
public class LongestSubString
{
	static void longestSubstring(String str)
	{     
        char ch[] = str.toCharArray();
        String longestSubstring = null;
        int longestSubstringLength = 0;

        Map<Character, Integer> hm = new HashMap<Character, Integer>();
   
        for (int i = 0; i < ch.length; i++) 
        {
            if(!hm.containsKey(ch[i]))
                hm.put(ch[i], i);
            else
            {   
                i = hm.get(ch[i]);
                hm.clear();
            }
             
            if(hm.size() > longestSubstringLength)
            {
                longestSubstringLength = hm.size();
                longestSubstring = hm.keySet().toString();
            }
        }
        System.out.println("The longest substring : "+longestSubstring); 
        System.out.println("The longest Substring Length : "+longestSubstringLength);
    }
	
    public static void main(String[] args) 
    {   
        longestSubstring("aababcdbcabcdefghhijk");
    }  
    
}
	
 