class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for(int x : nums) total += x;

        int need = (int)(total % p);
        if(need == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        long prefix = 0;
        int ans = nums.length;
        for(int i=0; i<nums.length; i++){
            prefix = (prefix + nums[i]) % p;
            int target = (int)((prefix - need + p) % p);
            
            if(map.containsKey(target)){
                ans = Math.min(ans, i-map.get(target));
            }
            map.put((int)prefix, i);
        }
        return ans == nums.length ? -1 : ans;
    }
}
/*
1. **PROBLEM :-**
   You are given an integer array `nums` and an integer `p`. The goal is to remove the **smallest possible contiguous subarray** so that the **sum of the remaining elements becomes divisible by `p`**.

Important conditions:

* You are allowed to remove **any contiguous subarray**.
* The removed subarray can also be empty.
* **Removing the entire array is not allowed.**
* If it is impossible to make the remaining sum divisible by `p`, return **-1**.

Core idea behind the solution:

First, calculate the **total sum of the array**.
If the total sum is already divisible by `p`, then no removal is needed.

Otherwise:
We need to remove a subarray whose sum has remainder **equal to the remainder of total sum when divided by `p`**.

Mathematically:
If
totalSum % p = `need`

Then we must remove a subarray whose sum % p = `need`.

To solve this efficiently:

* We use **prefix sum modulo p**.
* A **HashMap** stores the earliest index of each remainder.
* While iterating through the array, we try to find a previous prefix remainder that helps remove the required part.

This approach reduces the complexity to:

Time Complexity: **O(n)**
Space Complexity: **O(n)**

---

2. **EXAMPLE :-**

Input
nums = [3, 1, 4, 2]
p = 6

Step 1: Calculate total sum
sum = 3 + 1 + 4 + 2 = 10

Step 2: Check remainder
10 % 6 = 4

This means we must remove a subarray whose sum % 6 = 4.

Possible subarrays:
[3] → 3 % 6 = 3
[1] → 1 % 6 = 1
[4] → 4 % 6 = 4  ✓
[2] → 2 % 6 = 2
[3,1] → 4 % 6 = 4 ✓
[1,4] → 5 % 6 = 5
[4,2] → 6 % 6 = 0
[3,1,4] → 8 % 6 = 2

The smallest valid subarray is:
[4]

After removing:
Remaining array = [3,1,2]

New sum:
3 + 1 + 2 = 6

6 % 6 = 0

So the answer is:
Output = **1**

---

3. **DRY RUN :- (Detailed Example)**

nums = [8, 32, 15, 7, 10, 5]
p = 9

Step 1: Compute total sum

sum = 8 + 32 + 15 + 7 + 10 + 5
sum = 77

Step 2: Find remainder

77 % 9 = 5

So we must remove a subarray whose sum % 9 = 5

need = 5

Initialize:

map = {0 : -1}
prefix = 0
answer = n (6)

---

Iteration 1
i = 0
num = 8

prefix = (0 + 8) % 9
prefix = 8

target = (prefix - need + p) % p
target = (8 - 5 + 9) % 9
target = 12 % 9
target = 3

map does not contain 3

Update map:
map = {0:-1, 8:0}

---

Iteration 2
i = 1
num = 32

prefix = (8 + 32) % 9
prefix = 40 % 9
prefix = 4

target = (4 - 5 + 9) % 9
target = 8

map contains 8 at index 0

subarray length = i - index
length = 1 - 0 = 1

answer = min(6,1)
answer = 1

Update map:
map = {0:-1, 8:0, 4:1}

---

Iteration 3
i = 2
num = 15

prefix = (4 + 15) % 9
prefix = 19 % 9
prefix = 1

target = (1 - 5 + 9) % 9
target = 5

map does not contain 5

Update map:
map = {0:-1, 8:0, 4:1, 1:2}

---

Iteration 4
i = 3
num = 7

prefix = (1 + 7) % 9
prefix = 8

target = (8 - 5 + 9) % 9
target = 3

not found

Update map:
map = {0:-1, 8:3, 4:1, 1:2}

---

Iteration 5
i = 4
num = 10

prefix = (8 + 10) % 9
prefix = 18 % 9
prefix = 0

target = (0 - 5 + 9) % 9
target = 4

map contains 4 at index 1

length = 4 - 1 = 3

answer remains 1 (smaller already found)

---

Iteration 6
i = 5
num = 5

prefix = (0 + 5) % 9
prefix = 5

target = (5 - 5 + 9) % 9
target = 0

map contains 0 at index -1

length = 5 - (-1) = 6

Not better than current answer.

---

Final Answer:

Smallest subarray length = **1**

Subarray removed = **[32]**

Remaining sum becomes divisible by 9.


*/



