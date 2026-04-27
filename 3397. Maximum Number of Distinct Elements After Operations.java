class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        long lastUsed = Long.MIN_VALUE;
        int count = 0;
        for(int num : nums){
            long candidate = Math.max((long) num-k, lastUsed + 1);
            if(candidate <= (long) num+k){
                count++;
                lastUsed = candidate;
            }
        }
        return count;
    }
}

/*
1. PROBLEM :-
   You are given:
* An array nums
* An integer k

For each element nums[i], at most once you may add any value in range:
[-k, k]
So each number can be changed into any value between:
nums[i] - k  to  nums[i] + k
Goal:
Make the maximum possible number of distinct elements.

---

Core Idea:
Each number gives an interval:
[num-k , num+k]
We want assign one unique value from each interval.
Greedy strategy:

* Sort nums
* For each number, assign the smallest possible unused value.

Why smallest?
Leaving larger values available helps future elements.

For each num:
candidate = max(num-k, lastUsed+1)
Meaning:
* Try smallest value in its range
* But it must be greater than previous used value
If candidate <= num+k
we can assign it uniquely.

Increase count.

---

Time Complexity: O(n log n)
Space Complexity: O(1) extra (ignoring sorting)

---

2. EXAMPLE :-

Input
nums = [4,4,4,4]
k = 1

Each 4 can become:
[3,4,5]

Sorted:
[4,4,4,4]
Start:
lastUsed = -∞

---

First 4
candidate = max(3,-∞)=3
Use 3
count=1
lastUsed=3

---

Second 4
candidate=max(3,4)=4
Use 4
count=2
lastUsed=4

---

Third 4
candidate=max(3,5)=5
Use 5
count=3
lastUsed=5

---

Fourth 4
candidate=max(3,6)=6
6 > 5 (outside range)
Cannot use.
Final distinct elements:
3,4,5
Answer = 3

---

3. DRY RUN :-
Example (custom tough case)
nums = [2,2,3,5,5]
k = 2

Ranges:
2 → [0,4]
2 → [0,4]
3 → [1,5]
5 → [3,7]
5 → [3,7]

Sorted already.
---

lastUsed = -∞
Element 2:
candidate=max(0,-∞)=0
Use 0
count=1
lastUsed=0
---

Element 2:
candidate=max(0,1)=1
Use 1
count=2
lastUsed=1
---

Element 3:
candidate=max(1,2)=2
Use 2
count=3
lastUsed=2
---

Element 5:
candidate=max(3,3)=3
Use 3
count=4
lastUsed=3
---

Element 5:
candidate=max(3,4)=4
Use 4
count=5
lastUsed=4
---

Final assigned values:
[0,1,2,3,4]
All distinct.
Maximum distinct elements = 5
Final Answer = 5


*/
