class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        Arrays.sort(nums);
        int i=0;
        int[] arr = new int[2];
        int a = 0;
        while(i!=(nums.length-1)){
            if(nums[i]==nums[i+1]) {
                arr[a] = nums[i];
                a++;
            } 
            i++;   
        }return arr;
    }
}
/*

problem - The Two Sneaky Numbers of Digitville
What is this problem saying?
You are given an array `nums`.
Originally, the array should contain all numbers from `0` to `n-1` exactly once.

Example:
For n = 4

Expected array:
[0,1,2,3]

But two numbers appear one extra time.
So the array size becomes `n + 2`.
Your task is to find those two repeated numbers.

example - nums = [0,3,2,1,3,2]
Numbers from 0 to 3 should appear once.

Let's count:
0 → 1 time
1 → 1 time
2 → 2 times
3 → 2 times

Repeated numbers are:
[2,3]
Answer = [2,3]
dry run - tough example
nums = [7,1,5,4,3,4,6,0,9,5,8,2]

Step 1:
Sort the array

After sorting:
[0,1,2,3,4,4,5,5,6,7,8,9]

Create:
arr = [0,0]
a = 0
i = 0

Step 2:
i = 0
nums[0] = 0
nums[1] = 1
0 == 1 ?
No
i++
i = 1

Step 3:
nums[1] = 1
nums[2] = 2
1 == 2 ?
No
i++
i = 2

Step 4:
nums[2] = 2
nums[3] = 3
2 == 3 ?
No
i++
i = 3

Step 5:
nums[3] = 3
nums[4] = 4
3 == 4 ?
No
i++
i = 4

Step 6:
nums[4] = 4
nums[5] = 4
4 == 4 ?
Yes
arr[a] = 4
arr[0] = 4
a++
a = 1

Current:
arr = [4,0]
i++
i = 5

Step 7:
nums[5] = 4
nums[6] = 5
4 == 5 ?
No
i++
i = 6

Step 8:
nums[6] = 5
nums[7] = 5
5 == 5 ?
Yes
arr[a] = 5
arr[1] = 5
a++
a = 2

Current:
arr = [4,5]
Continue loop until end.

Finally:
arr = [4,5]

Return:
[4,5]
How this code thinks:
After sorting, duplicate numbers always become adjacent.

Example:
Before sorting:
[5,1,4,5,2,4]

After sorting:
[1,2,4,4,5,5]
Now duplicates can be found by checking:
nums[i] == nums[i+1]
Whenever two adjacent numbers are equal, that number is repeated.

Code logic:
Sort array
Scan from left to right

If:
nums[i] == nums[i+1]
Store that number in answer array
Since the problem guarantees exactly two repeated numbers, we will find exactly two duplicates.

Time Complexity:
Sorting = O(n log n)
Traversal = O(n)
Overall = O(n log n)

Space Complexity:
O(1)
(ignoring the output array)




*/
