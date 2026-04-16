class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        boolean[] inc = new boolean[n - k + 1];
        
        for (int i = 0; i <= n - k; i++) {
            boolean ok = true;
            for (int j = i; j < i + k - 1; j++) {
                if (nums.get(j) >= nums.get(j + 1)) {
                    ok = false;
                    break;
                }
            }
            inc[i] = ok;
        }
        
        for (int i = 0; i + k < inc.length; i++) {
            if (inc[i] && inc[i + k]) return true;
        }
        
        return false;
    }
}


/*
1. PROBLEM :-
   You are given:
   nums → list of integers
   k → size of subarray
You need to check if there exist two adjacent subarrays of length k such that:
1. Both subarrays are strictly increasing
2. They are adjacent → second starts right after first
   (if first starts at index a, second must start at a + k)
Strictly increasing means:
nums[i] < nums[i+1] for all elements in the subarray
Goal:
Return true if such two adjacent increasing subarrays exist, otherwise false.

Core idea:
1. First check every subarray of length k and mark whether it is strictly increasing.
2. Store results in a boolean array inc[].
3. Then check if there exist two positions i and i+k such that:
   inc[i] == true and inc[i+k] == true

Time Complexity: O(n * k)
Space Complexity: O(n)

---

2. EXAMPLE :-
Input
nums = [2,5,7,8,9,2,3,4,3,1]
k = 3
Check subarrays of size 3:

Index 0 → [2,5,7] → increasing → true
Index 1 → [5,7,8] → increasing → true
Index 2 → [7,8,9] → increasing → true
Index 3 → [8,9,2] → not increasing → false
Index 4 → [9,2,3] → not increasing → false
Index 5 → [2,3,4] → increasing → true
Index 6 → [3,4,3] → not increasing → false
Index 7 → [4,3,1] → not increasing → false
inc = [T, T, T, F, F, T, F, F]
Now check adjacent pairs:
Check i = 2
inc[2] = true
inc[2+3] = inc[5] = true
Condition satisfied
Output = true

---

3. DRY RUN :-
Example (custom case)
nums = [1,3,5,7,2,4,6,8]
k = 2

Step 1: Check subarrays of size 2
Index 0 → [1,3] → true
Index 1 → [3,5] → true
Index 2 → [5,7] → true
Index 3 → [7,2] → false
Index 4 → [2,4] → true
Index 5 → [4,6] → true
Index 6 → [6,8] → true

inc = [T, T, T, F, T, T, T]

Step 2: Check adjacent subarrays
i = 0
inc[0] = true
inc[0+2] = inc[2] = true

Condition satisfied
So two subarrays:
[1,3] and [5,7]

Both increasing and adjacent
Final Answer = true



*/
