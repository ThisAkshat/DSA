class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for(int num: nums){
            int[] next = dp.clone();
            for(int i=0; i<3; i++){
                if(dp[i] != Integer.MIN_VALUE){
                    int sum = dp[i] + num;
                    next[sum%3] = Math.max(next[sum % 3], sum);
                }
            }
            dp = next;
        }
        return dp[0];
    }
}
/*
PROBLEM :-
Think of it like this:
You have a list of numbers and you can pick any of them. Your goal is to collect numbers so that the total sum is as large as possible, but the sum must be divisible by 3.
If adding a number makes the total not divisible by 3, you may skip that number.
In the end, you return the biggest possible sum that can be perfectly divided by 3.

**************************************************************************************************************************************

Example 1:
Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

Example 2:
Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.

Example 3:
Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).

**************************************************************************************************************************************

Dry run for example:

nums = [3, 6, 5, 1, 8]

Initial
dp = [0, -inf, -inf]
meaning
dp[0] = best sum with remainder 0 when divided by 3
dp[1] = best sum with remainder 1
dp[2] = best sum with remainder 2

Step 1: num = 3

current dp = [0, -inf, -inf]

i = 0
sum = 0 + 3 = 3
3 % 3 = 0

next[0] = max(0,3) = 3

dp becomes
[3, -inf, -inf]

Step 2: num = 6

current dp = [3, -inf, -inf]

i = 0
sum = 3 + 6 = 9
9 % 3 = 0

next[0] = max(3,9) = 9

dp becomes
[9, -inf, -inf]

Step 3: num = 5

current dp = [9, -inf, -inf]

i = 0
sum = 9 + 5 = 14
14 % 3 = 2

next[2] = 14

dp becomes
[9, -inf, 14]

Step 4: num = 1

current dp = [9, -inf, 14]

i = 0
sum = 9 + 1 = 10
10 % 3 = 1

next[1] = 10

i = 2
sum = 14 + 1 = 15
15 % 3 = 0

next[0] = max(9,15) = 15

dp becomes
[15, 10, 14]

Step 5: num = 8

current dp = [15, 10, 14]

i = 0
sum = 15 + 8 = 23
23 % 3 = 2
next[2] = 23

i = 1
sum = 10 + 8 = 18
18 % 3 = 0
next[0] = 18

i = 2
sum = 14 + 8 = 22
22 % 3 = 1
next[1] = 22

dp becomes
[18, 22, 23]

Final answer

dp[0] = 18

Return 18

*/
