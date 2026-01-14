/*
Computer 0 is already unlocked. To unlock computer i, you need some j < i with lower complexity.
So each computer must have lower complexity than ALL computers after it!

Approach
1. Check if valid: complexity must be strictly increasing from computer 1 onwards
2. If valid: Count permutations = (n-1)!
Because computer 0 fixed at start, rest (n-1) can be in any order

Dry Run :- 
  
Example: [1,2,3]
n=3, min=1
Check i=1: 2 > 1 ✓
Check i=2: 3 > 1 ✓
Valid! Compute (3-1)! = 2! = 2 ✓

Example: [3,3,3,4,4,4]
n=6, min=3
Check i=1: 3 <= 3 ❌ → return 0
  */
class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length, min = complexity[0];
        int MOD = (int) 1e9 + 7;

        for (int i = 1; i < n; i++) {
            if (complexity[i] <= min) return 0;
        }

        long ft = 1;
        for (int i = 2; i < n; i++) {
            ft = (ft * i) % MOD;
        }

        return (int) ft;
    }
}
