class Solution {
    public int minOperations(int[] nums) {
        int[] stack = new int[nums.length + 1];
        int top = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            while (stack[top] > nums[i]) {
                top--;
                ans++;
            }
            if (stack[top] != nums[i])
                stack[++top] = nums[i];
        }
        return ans + top;
    }
}


/*
* Problem :-
The problem is simply saying this:
You have an array of non-negative numbers.
You are allowed to repeatedly:
1. Choose any subarray
2. Find the smallest number inside that subarray
3. Set all occurrences of that smallest number (within that chosen subarray) to 0
Your goal:
- Make every element in the entire array equal to 0
- Using the minimum number of operations
That’s it. Nothing more.

*************************************************************************************************

Input: nums = [0,2]

Output: 1

Explanation:

Select the subarray [1,1] (which is [2]), where the minimum non-negative integer is 2. Setting all occurrences of 2 to 0 results in [0,0].
Thus, the minimum number of operations required is 1.

*************************************************************************************************
Dry Run :-

Example: nums = [3,1,2,1]

Initial:

stack = [0]
top = 0
ans = 0

i = 0 → 3
stack[top] = 0
0 > 3 ? No
0 != 3 → push

stack = [0,3]
top = 1
ans = 0

i = 1 → 1
stack[top] = 3
3 > 1 ? Yes → pop

top = 0
ans = 1

stack[top] = 0
0 > 1 ? No
0 != 1 → push

stack = [0,1]
top = 1
ans = 1

i = 2 → 2
stack[top] = 1
1 > 2 ? No
1 != 2 → push

stack = [0,1,2]
top = 2
ans = 1

i = 3 → 1
stack[top] = 2
2 > 1 ? Yes → pop

top = 1
ans = 2

stack[top] = 1
1 > 1 ? No
1 != 1 ? No → don’t push

stack = [0,1]
top = 1
ans = 2

End of loop
Return: ans + top = 2 + 1 = 3
*/
