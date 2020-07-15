package do_later;
public class LongestPalindromeManachersAlgo 
{
	/**
     * Linear time Manacher's algorithm to find longest palindromic substring.
     * There are 4 cases to handle
     * Case 1 : Right side palindrome is totally contained under current palindrome. In this case do not consider this as center.
     * Case 2 : Current palindrome is proper suffix of input. Terminate the loop in this case. No better palindrom will be found on right.
     * Case 3 : Right side palindrome is proper suffix and its corresponding left side palindrome is proper prefix of current palindrome. Make largest such point as
     * next center.
     * Case 4 : Right side palindrome is proper suffix but its left corresponding palindrome is be beyond current palindrome. Do not consider this
     * as center because it will not extend at all.
     *
     * To handle even size palindromes replace input string with one containing $ between every input character and in start and end.
     */
    public static int longestPalindromicSubstringLinear(char input[]) 
    {
        int index = 0;
        //pre process the input to convert it into type abc -> $a$b$c$ to handle even length case. Total size will be 2*n + 1 of this new array.
        char newInput[] = new char[2*input.length + 1];
        for(int i=0; i < newInput.length; i++) 
        {
            if(i % 2 != 0) 
                newInput[i] = input[index++];
            else
                newInput[i] = '$';
        }

        //create temporary array for holding largest palindrome at every point. There are 2*n + 1 such points.
        int T[] = new int[newInput.length];
        int start = 0;
        int end = 0;
        //here i is the center.
        for(int i=0; i < newInput.length; ) 
        {
            while(start >0 && end < newInput.length-1 && newInput[start-1] == newInput[end+1])
            {
                start--;
                end++;
            }
            T[i] = end - start + 1; //set the longest value of palindrome around center i at T[i]

            if(end == T.length -1)  //This is case 2. Current palindrome is proper suffix of input. No need to proceed. Just break out of loop.
            {
                break;
            }
            
            int newCenter = end + (i%2 ==0 ? 1 : 0); //Mark newCenter to be either end or end + 1 depending on if we dealing with even or old number input.

            for(int j = i + 1; j <= end; j++) 
            {
            	//i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome. So take minimum
                //of either left side palindrome or distance of j to end.
                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                //Only proceed if we get case 3. This check is to make sure we do not pick j as new center for case 1 or case 4
                //As soon as we find a center lets break out of this inner while loop.
                if(j + T[i - (j - i)]/2 == end) 
                {
                    newCenter = j;
                    break;
                }
            }
            //make i as newCenter. Set right and left to atleast the value we already know should be matching based of left side palindrome.
            i = newCenter;
            end = i + T[i]/2;
            start = i - T[i]/2;
        }
       
        //find the max palindrome in T and return it.
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < T.length; i++) 
        {
            int val;
            val = T[i]/2;
            if(max < val)
                max = val;
        }
        return max;
    }

    public static void main(String[] args) {
    	System.out.println(longestPalindromicSubstringLinear("google".toCharArray()));
	}
}

