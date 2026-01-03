/*
We need to count valid colorings of an n×3 grid using 3 colors (Red, Yellow, Green)
where adjacent cells (horizontally or vertically) cannot have the same color. 
The key insight is that each row has only two types of valid color patterns,
and we can use dynamic programming to count valid transitions between consecutive rows.

Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown.

Example 2:

Input: n = 5000
Output: 30228214
  */



class Solution {
    int MOD = 1000000007;
    public int numOfWays(int n) {
        if(n == 0) return 0;
        long two = 6, three = 6;
        n--;
        while(n > 0){
            long nextTwo = (3*two + 2*three)%MOD;
            three = (2*two + 2*three) % MOD;
            two = nextTwo;
            n--;
        }
        return (int)(two + three) % MOD;
    }
}

/*
Dry Run :-
n = 5
Initialization:

int MOD = 1000000007;
if(n == 0) return 0; // n=5, so skip
long two = 6, three = 6; // For n=1 row
n--; // n = 5-1 = 4 (remaining rows to process)
Initial State: two = 6, three = 6, n = 4

Iteration 1: n = 4

while(n > 0) { // 4 > 0 → true
long nextTwo = (3two + 2three) % MOD;
// nextTwo = (3×6 + 2×6) % MOD = (18 + 12) = 30

three = (2*two + 2*three) % MOD;
// three = (2×6 + 2×6) % MOD = (12 + 12) = 24

two = nextTwo;  // two = 30

n--;  // n = 4-1 = 3
}
After iteration 1: two = 30, three = 24, n = 3

Iteration 2: n = 3

while(n > 0) { // 3 > 0 → true
long nextTwo = (3two + 2three) % MOD;
// nextTwo = (3×30 + 2×24) % MOD = (90 + 48) = 138

three = (2*two + 2*three) % MOD;
// three = (2×30 + 2×24) % MOD = (60 + 48) = 108

two = nextTwo;  // two = 138

n--;  // n = 3-1 = 2
}
After iteration 2: two = 138, three = 108, n = 2

Iteration 3: n = 2

while(n > 0) { // 2 > 0 → true
long nextTwo = (3two + 2three) % MOD;
// nextTwo = (3×138 + 2×108) % MOD = (414 + 216) = 630

three = (2*two + 2*three) % MOD;
// three = (2×138 + 2×108) % MOD = (276 + 216) = 492

two = nextTwo;  // two = 630

n--;  // n = 2-1 = 1
}
After iteration 3: two = 630, three = 492, n = 1

Iteration 4: n = 1

while(n > 0) { // 1 > 0 → true
long nextTwo = (3two + 2three) % MOD;
// nextTwo = (3×630 + 2×492) % MOD = (1890 + 984) = 2874

three = (2*two + 2*three) % MOD;
// three = (2×630 + 2×492) % MOD = (1260 + 984) = 2244

two = nextTwo;  // two = 2874

n--;  // n = 1-1 = 0
}
After iteration 4: two = 2874, three = 2244, n = 0

Final Result:

while(n > 0) { // 0 > 0 → false, exit loop
}

return (int)(two + three) % MOD;
// two + three = 2874 + 2244 = 5118
// 5118 % 1000000007 = 5118
// Return 5118
*/
