class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for(String str : strs){
            int zeros = 0, ones = 0;
            for(char c : str.toCharArray()){
                if(c == '0') zeros++;
                else ones++;
            }
            for(int i = m; i>= zeros; i--){
                for(int j=n; j>=ones; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeros][j - ones]+1); // knapsack formula 
                }
            }
        }
        return dp[m][n];
    }
}

/*
The problem is :-

You are given a list of binary strings (strings made of only 0 and 1).

You are also given:

* m → maximum number of 0s you are allowed to use
* n → maximum number of 1s you are allowed to use

Now you must:

👉 Choose some strings from the list (a subset).
👉 When you combine all chosen strings together,
the total number of 0s ≤ m
and the total number of 1s ≤ n

Among all possible valid subsets, you want:

> The subset that contains the **maximum number of strings**.

That’s it.

It is NOT asking you to maximize length of strings.
It is NOT asking you to use all capacity.
It is asking:

> “How many strings can I pick at most without exceeding m zeros and n ones?”

******************************************************************************************************************

Dry run for:

strs = ["10","0001","111001","1","0"]
m = 5
n = 3

dp is a 2D array of size 6 x 4
dp[i][j] means: maximum number of strings we can take using at most i zeros and j ones

Initially all values are 0.

---

String 1: "10"
zeros = 1
ones = 1

Update dp from bottom to top.

After processing "10":

For every i >= 1 and j >= 1:
dp[i][j] = 1

So dp now has 1 in all cells where i >= 1 and j >= 1.

---

String 2: "0001"
zeros = 3
ones = 1

Now update from bottom:

For i from 5 down to 3
For j from 3 down to 1

Example important updates:

dp[5][3] = max(dp[5][3], dp[2][2] + 1)
dp[2][2] was 1
so dp[5][3] becomes 2

Similarly:
dp[4][3] becomes 2
dp[3][3] becomes 2
dp[3][2] becomes 2
dp[3][1] becomes 1 (since dp[0][0] + 1)

Now maximum value in table is 2.

---

String 3: "111001"
zeros = 2
ones = 4

Since ones = 4 > n = 3
This string cannot fit in any valid state.

So dp remains unchanged.

---

String 4: "1"
zeros = 0
ones = 1

Update:

For i from 5 down to 0
For j from 3 down to 1

Example:

dp[5][3] = max(dp[5][3], dp[5][2] + 1)

dp[5][2] was 2
So dp[5][3] becomes 3

Many states increase by 1 if they had space for one more 1.

Now maximum becomes 3.

---

String 5: "0"
zeros = 1
ones = 0

Update:

For i from 5 down to 1
For j from 3 down to 0

Example:

dp[5][3] = max(dp[5][3], dp[4][3] + 1)

dp[4][3] was 3
So dp[5][3] becomes 4

Now dp[5][3] = 4

---

Final answer:

dp[5][3] = 4

******************************************************************************************************************

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.

Example 2:
Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.

*/
