class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] minPrefix = new long[k];
        long[] prefix = new long[n+1];
        for(int i = 0; i<k; i++) minPrefix[i] = Long.MAX_VALUE;
        minPrefix[0]= 0;
        long ans = Long.MIN_VALUE;
        for(int i=1; i<=n; i++){
            prefix[i] = prefix[i-1] + nums[i-1];
            int mod = i%k;
            if(minPrefix[mod] != Long.MAX_VALUE){
                ans = Math.max(ans, prefix[i] - minPrefix[mod]);
            }
            minPrefix[mod] = Math.min(minPrefix[mod], prefix[i]);
        }
        return ans;
    }
}

/*
Problem :- You are given an array of integers nums and an integer k.
Return the maximum sum of a subarray of nums, such that the size of the subarray is divisible by k.

*********************************************************************************************************************************

Example 1:
Input: nums = [1,2], k = 1
Output: 3
Explanation: The subarray [1, 2] with sum 3 has length equal to 2 which is divisible by 1.

Example 2:
Input: nums = [-1,-2,-3,-4,-5], k = 4
Output: -10
Explanation: The maximum sum subarray is [-1, -2, -3, -4] which has length equal to 4 which is divisible by 4.

*********************************************************************************************************************************

nums = [-5,1,2,-3,4], k = 2

Initial
minPrefix = [0, ∞]
prefix = [0,...]
ans = -∞

i = 1
prefix[1] = -5

mod = 1
minPrefix[1] = ∞ → skip

update → minPrefix[1] = -5

i = 2
prefix[2] = -4
mod = 0

ans = max(-∞, -4 - 0) = -4
update → minPrefix[0] = -4

i = 3
prefix[3] = -2
mod = 1

ans = max(-4, -2 - (-5)) = 3
update → minPrefix[1] = -5

i = 4
prefix[4] = -5
mod = 0
ans = max(3, -5 - (-4)) = 3
update → minPrefix[0] = -5

i = 5
prefix[5] = -1
mod = 1
ans = max(3, -1 - (-5)) = 4

Final Answer
4
*/
