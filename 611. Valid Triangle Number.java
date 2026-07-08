class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for(int k = n-1; k>=2; k--){
            int i =0;
            int j = k-1;
            while(i<j){
                if(nums[i] + nums[j] > nums[k]){
                    count = count + (j-i);
                    j--;
                }else{
                    i++;
                }
            }
        }
        return count;
    }
}

/*

Explanation:

A valid triangle needs all three conditions: a+b > c, a+c > b, b+c > a. But if we sort the array, we only need to check the smallest two sides sum greater than the largest side, because the other two conditions are automatically satisfied when the array is sorted.

The approach sorts the array and fixes the largest side k starting from the end. For the remaining elements to the left of k, we use two pointers i (starting from 0) and j (starting at k-1) to count how many pairs (i,j) satisfy nums[i] + nums[j] > nums[k].

If nums[i] + nums[j] > nums[k] is true, then every index between i and j also works with j as the right pointer, because the array is sorted so anything larger than nums[i] paired with nums[j] will also satisfy the condition. So we add j-i to count and move j left. If the condition is false, nums[i] is too small, so we move i right to try a bigger value.

Dry Run (line by line on nums = [2,2,3,4]):

line: Arrays.sort(nums) -> nums = [2,2,3,4] (already sorted)
line: n = nums.length -> n = 4
line: count = 0

Outer loop k = 3 (nums[k] = 4):
line: i = 0, j = k-1 = 2

  while i < j -> 0 < 2 -> true
  line: nums[i] + nums[j] > nums[k] -> nums[0]+nums[2] > nums[3] -> 2+3 > 4 -> 5 > 4 -> true
  line: count = count + (j-i) -> count = 0 + (2-0) = 2
  line: j-- -> j = 1

  while i < j -> 0 < 1 -> true
  line: nums[i] + nums[j] > nums[k] -> nums[0]+nums[1] > nums[3] -> 2+2 > 4 -> 4 > 4 -> false
  line: i++ -> i = 1

  while i < j -> 1 < 1 -> false, exit while

Outer loop k = 2 (nums[k] = 3):
line: i = 0, j = k-1 = 1

  while i < j -> 0 < 1 -> true
  line: nums[i] + nums[j] > nums[k] -> nums[0]+nums[1] > nums[2] -> 2+2 > 3 -> 4 > 3 -> true
  line: count = count + (j-i) -> count = 2 + (1-0) = 3
  line: j-- -> j = 0

  while i < j -> 0 < 0 -> false, exit while

Outer loop k = 1 -> k >= 2 -> 1 >= 2 -> false, exit outer loop

line: return count -> return 3

Output: 3 (matches expected, valid triplets are (2,3,4), (2,3,4), (2,2,3))
*/
