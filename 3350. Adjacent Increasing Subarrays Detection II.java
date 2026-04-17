class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int up = 1, preUp = 0, res = 0;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                up++;
            } else {
                preUp = up;
                up = 1;
            }
            int half = up >> 1;
            int min = preUp < up ? preUp : up;
            int candidate = half > min ? half : min;
            if (candidate > res) res = candidate;
        }
        return res;
    }
}

/*
1. PROBLEM :-
   You are given an array nums.

You need to find the maximum value of k such that:

* There exist two adjacent subarrays of length k
* Both subarrays are strictly increasing
* If first starts at index a, second starts at a + k

Strictly increasing means:
nums[i] < nums[i+1]

Goal:
Return the maximum possible k.

---

Core idea:
Instead of checking all subarrays, we track lengths of increasing sequences.
Variables used:
up → current increasing sequence length
preUp → previous increasing sequence length
res → answer

At each step:
1. If current element > previous → increase up
2. Otherwise:

   * store previous sequence in preUp
   * reset up = 1

Now for each position:
We calculate possible k using:

Option 1:
Split current increasing sequence into two parts
k = up / 2

Option 2:
Use previous and current sequences
k = min(preUp, up)

Take max of both.

---

Time Complexity: O(n)
Space Complexity: O(1)

---

2. EXAMPLE :-
Input
nums = [2,5,7,8,9,2,3,4,3,1]
Increasing segments:
[2,5,7,8,9] → length = 5
[2,3,4] → length = 3
Now possible k:
From first segment:
k = 5 / 2 = 2
From adjacent segments:
k = min(5,3) = 3
Maximum = 3
Output = 3

---

3. DRY RUN :-
Example (custom case)
nums = [1,2,3,4,5,1,2,3,4]
Step 1: Identify increasing segments
[1,2,3,4,5] → length = 5
[1,2,3,4] → length = 4

---
Iteration:
i = 0 → up = 1

i = 1 → 2 > 1 → up = 2
candidate = max(2/2=1, min(0,2)=0) → 1

i = 2 → 3 > 2 → up = 3
candidate = max(3/2=1, min(0,3)=0) → 1

i = 3 → 4 > 3 → up = 4
candidate = max(4/2=2, 0) → 2

i = 4 → 5 > 4 → up = 5
candidate = max(5/2=2, 0) → 2

---
i = 5 → 1 < 5
preUp = 5
up = 1
---

i = 6 → 2 > 1 → up = 2
candidate = max(2/2=1, min(5,2)=2) → 2

i = 7 → 3 > 2 → up = 3
candidate = max(3/2=1, min(5,3)=3) → 3

i = 8 → 4 > 3 → up = 4
candidate = max(4/2=2, min(5,4)=4) → 4
---

Final Answer = 4

*/
