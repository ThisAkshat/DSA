/*
Intuition :-
Even difference = (LeftSum - RightSum) is even
Math trick: Difference even if both sums have same parity (both even or both odd)

Dry Run:-
Example: [10,10,3,7,6]

Total = 36

i	   leftSum	rightSum	     diff	        even?
0	    10	      26	         -16            	✓
1	    20	      16           	4	              ✓
2	    23	      13          	10	            ✓
3	    30	      6	            24             	✓
Count = 4 ✓

Why diff % 2 == 0 works?

leftSum - rightSum = even
This means:
- If leftSum even, rightSum must be even
- If leftSum odd, rightSum must be odd
So parity must match!
Time: O(n), Space: O(1)
*/

class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int count = 0;
        int total = 0;
        for (int num : nums)
            total += num;

        int leftSum = 0;
        for (int i = 0; i < n- 1; i++) {
            leftSum += nums[i];
            int rightSum = total - leftSum;
            int diff = leftSum - rightSum;
            if (diff % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
