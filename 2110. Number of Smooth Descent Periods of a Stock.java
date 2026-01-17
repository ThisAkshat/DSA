class Solution {
    public long getDescentPeriods(int[] prices) {
     long result = 1;
     long count = 1;

     for(int i=1; i<prices.length;i++)   { // prices = [3,2,1,4]
        if(prices[i-1] - prices[i] == 1){
            count++;
        }else{
            count = 1;
        }
        result += count;
     }
     return result;
    }
}

/*
Intuition :-
Smooth descent = Price drops by exactly 1 each day. Count all contiguous subarrays where difference between consecutive elements is 1.

Approach :-
Use sliding window: Track length of current descent sequence. If difference ≠ 1, reset sequence length. Add sequence length to result each step.
Key formula: If sequence length = k, it contributes k new subarrays ending at current position.

Example 1:

Input: prices = [3,2,1,4]
Output: 7
Explanation: There are 7 smooth descent periods:
[3], [2], [1], [4], [3,2], [2,1], and [3,2,1]
Note that a period with one day is a smooth descent period by the definition.

Example 2:
Input: prices = [8,6,7,7]
Output: 4
Explanation: There are 4 smooth descent periods: [8], [6], [7], and [7]
Note that [8,6] is not a smooth descent period as 8 - 6 ≠ 1.

Example 3:
Input: prices = [1]
Output: 1
Explanation: There is 1 smooth descent period: [1]
  */

/*

Dry Run :-
Example: prices = [3,2,1,4]

Step-by-step:

result = 1, count = 1 (single element [3])

i=1: prices[0]-prices[1] = 3-2 = 1 ✓
    count++ = 2
    result += count = 1+2 = 3
    Subarrays ending at i=1: [2], [3,2]

i=2: prices[1]-prices[2] = 2-1 = 1 ✓
    count++ = 3
    result += count = 3+3 = 6
    Subarrays ending at i=2: [1], [2,1], [3,2,1]

i=3: prices[2]-prices[3] = 1-4 = -3 ≠ 1 ❌
    count = 1 (reset)
    result += count = 6+1 = 7
    Subarrays ending at i=3: [4]

Total: 7 ✓
*/
