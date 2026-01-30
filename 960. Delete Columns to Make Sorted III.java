class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int[] dp = new int[m];
        Arrays.fill(dp,1);
        int maxKeep = 1;
        for(int i =0; i<m; i++){
            for(int j=0; j<i; j++){
                boolean valid = true;
                for(int row=0; row<n; row++){
                    if(strs[row].charAt(i)<strs[row].charAt(j)){
                        valid = false;
                        break;
                    }
                }
                if(valid){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxKeep = Math.max(maxKeep, dp[i]);
        }
        return m - maxKeep;
    }
}
/*
Row 0: a b g
Row 1: b a f  
Row 2: c a e 
*/

/*
What this problem really wants
You are NOT trying to sort the rows relative to each other.
You only care that inside each row, characters go left → right in non-decreasing order.
So after deleting some columns, every row must look like:
a ≤ b ≤ c ≤ d …
You want to delete minimum columns.

Equivalently:
Keep the largest set of columns that keeps every row sorted.
That is a Longest Increasing Subsequence (LIS) problem — but on columns, not numbers.
Key idea
Each column is a “vector”:
column i = [strs[0][i], strs[1][i], strs[2][i], ...]

You are allowed to keep columns j -> i in this order only if
for every row:
strs[row][j] ≤ strs[row][i]
So column i can come after column j only if all rows stay sorted.
That’s exactly what your valid check does.
What dp[i] means
dp[i] = length of the longest valid column sequence ending at column i

Basically:
If I keep column i as the last column, what is the maximum number of columns I can keep?
How the DP works
For every column i, you try all earlier columns j < i.

If for all rows:
strs[row][j] ≤ strs[row][i]
then column i can come after j.
So:
dp[i] = max(dp[i], dp[j] + 1)
That is classic LIS logic.
Why answer is m - maxKeep
m = total columns
maxKeep = maximum columns you can keep in valid order
So:
minimum deletions = total - kept
                  = m - maxKeep
Exactly what your code returns.

Short dry run (from your comment)
Row 0: a b g
Row 1: b a f
Row 2: c a e

Columns:
Col	Vector
0	[a, b, c]
1	[b, a, a]
2	[g, f, e]

Now test transitions:
Can 1 come after 0?
Row1: a < b ❌ → No

Can 2 come after 0?
a≤g, b≤f, c≤e → all true → Yes

Can 2 come after 1?
b≤g, a≤f, a≤e → all true → Yes
So LIS length = 2 (0 → 2 or 1 → 2)
Total columns = 3
Answer = 3 - 2 = 1

Why this is a Hard problem
Because:
It’s not row-wise LIS
It’s not column-wise sorting
It’s vector LIS with a per-row constraint
That’s why greedy doesn’t work here — DP is mandatory.
  */
