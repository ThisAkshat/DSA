class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for(int i=nums.length-1; i>=2; i--){
            int a = nums[i-2];
            int b = nums[i-1];
            int c = nums[i];

            if(a+b > c){
                return a+b+c;
            }
        }
        return 0;
    }
}
/*
Explanation:

This problem asks us to find the largest perimeter triangle we can form from any three numbers in the array. A valid triangle needs the sum of the two smaller sides to be greater than the largest side.

The key insight is that to maximize the perimeter, we should always try the three largest numbers first. If they form a valid triangle, that is our answer since no other combination can give a bigger perimeter. If they do not work, we slide the window one step left and try the next three largest numbers, and so on.

Sorting the array first makes this possible. After sorting, we always pick three consecutive elements from the right end. Since the array is sorted, nums[i-2] <= nums[i-1] <= nums[i], so nums[i] is always the largest side. We only need to check if nums[i-2] + nums[i-1] > nums[i]. If yes, valid triangle found, return the sum. If no, move left by one and try again. If no valid triangle exists after checking all triplets, return 0.

Dry Run (line by line on nums = [2,1,2]):

line: Arrays.sort(nums) -> nums = [1,2,2]
loop starts from i = nums.length-1 = 2, goes down to i >= 2

i = 2:
line: a = nums[i-2] = nums[0] = 1
line: b = nums[i-1] = nums[1] = 2
line: c = nums[i]   = nums[2] = 2
line: a+b > c -> 1+2 > 2 -> 3 > 2 -> true
line: return a+b+c -> return 1+2+2 = 5

Output: 5 (matches expected)

---

Dry Run (line by line on nums = [1,2,1,10]):

line: Arrays.sort(nums) -> nums = [1,1,2,10]
loop starts from i = 3, goes down to i >= 2

i = 3:
line: a = nums[1] = 1
line: b = nums[2] = 2
line: c = nums[3] = 10
line: a+b > c -> 1+2 > 10 -> 3 > 10 -> false, skip return

i = 2:
line: a = nums[0] = 1
line: b = nums[1] = 1
line: c = nums[2] = 2
line: a+b > c -> 1+1 > 2 -> 2 > 2 -> false, skip return

i = 1: i >= 2 -> 1 >= 2 -> false, exit loop

line: return 0

Output: 0 (matches expected, no valid triangle possible)
*/
