class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;

        int left = 0;
        int right = 0;
        int sumCount = 0;
        int result = 0;
        int left2 = 0;
        int sumCount2 = 0;
        int count = 0;
        int prev = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num == prev) {
                count++;
            } else {
                prev = num;
                count = 1;
            }

            while (nums[left] < num - k) {
                sumCount--;
                left++;
            }

            while (right < n && nums[right] <= num + k) {
                sumCount++;
                right++;
            }
            result = Math.max(result, count + Math.min(sumCount - count, numOperations));

            sumCount2++;
            while (nums[left2] < num - 2L * k) {
                sumCount2--;
                left2++;
            }
            result = Math.max(result, Math.min(sumCount2, numOperations));
        }

        return result;
    }
}

/*
1. PROBLEM :-
   You are given:
* nums array
* k → maximum allowed change per operation
* numOperations → maximum operations allowed

Operation: Choose an unused index i and add any value in range: [-k, k]
to nums[i].

Goal: Maximize the frequency of any element after operations.
Difference from Part I: Here nums[i] can be as large as 10^9, so brute force frequency array is impossible.

---

Core Idea: Sort the array first.
We try making numbers equal to some target num.
For any target num: An element x can become num if:

num - k ≤ x ≤ num + k

Use Sliding Window to find:
How many numbers can be converted into num.
Two windows are maintained:

Window 1: [num-k , num+k]
Tracks elements that can become current num.

Window 2: [num-2k , num]
Used for cases where we create a completely new target value.

---

Variables: left, right → sliding window pointers
sumCount → count of numbers in range [num-k, num+k] 
count → frequency of current num
result → maximum frequency found

---

For each num:
Case 1:
Make elements equal to existing num
Possible frequency: count + min(convertible elements, numOperations)

Formula: count + min(sumCount - count, numOperations)
---

Case 2:
Create a new target value
If all elements lie within distance 2k,
they can meet at some common value.

Possible frequency: min(window size, numOperations)
Take maximum.
---

Time Complexity: O(n log n)
(Sorting dominates)

Space Complexity: O(1)

---

2. EXAMPLE :-
Input nums = [1,4,5]
k = 1
numOperations = 2
Sort: [1,4,5]

---

Target = 4
Range: [3,5]
Elements: 4,5

count = 1
sumCount = 2

Can convert: 5 → 4
Frequency: 1 + min(1,2) = 2
result = 2

---

Target = 5
Range: [4,6]
Elements: 4,5
Again: 1 + min(1,2) = 2
Maximum = 2
Output = 2
---

3. DRY RUN :-
Example (custom tough case)
nums = [2,4,5,8,10]
k = 2
numOperations = 2

Sorted: [2,4,5,8,10]
---

Target = 4
Range: [2,6]
Elements: 2,4,5

sumCount = 3
count = 1
Convert:
2 → 4
5 → 4

Possible: 1 + min(2,2) = 3
result = 3
---

Target = 5
Range:
[3,7]
Elements: 4,5
count = 1
Frequency: 1 + min(1,2) = 2
---

Target = 8
Range:
[6,10]
Elements: 8,10

count = 1
Frequency: 1 + min(1,2) = 2
---

Window 2 logic:
Range: [num-2k, num]
For num = 8: [4,8]

Elements:4,5,8

These can all meet at some value.
Possible: min(3,2) = 2
---

Maximum found = 3
Final Answer = 3

*/
