class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1000000007;
        
        int[][][] dp = new int[m][n][k];
        
        dp[0][0][grid[0][0] % k] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int rem = 0; rem < k; rem++) {
                    if (dp[i][j][rem] > 0) {
                        if (i + 1 < m) {
                            int newRem = (rem + grid[i + 1][j]) % k;
                            dp[i + 1][j][newRem] = (dp[i + 1][j][newRem] + dp[i][j][rem]) % MOD;
                        }
                        if (j + 1 < n) {
                            int newRem = (rem + grid[i][j + 1]) % k;
                            dp[i][j + 1][newRem] = (dp[i][j + 1][newRem] + dp[i][j][rem]) % MOD;
                        }
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1][0];
    }
}


/*
1. Tumhe kya diya gaya hai?

Tumhe ek matrix (grid) diya hai.
Example:
5 2 4
3 0 5
0 7 2

Aur ek number diya hai **k**.

Example:
k = 3

---

2. Tum kaha se start karte ho?

Tum top-left corner se start karte ho.
(0,0)

Example me ye 5 hai.
[5] 2 4
 3  0 5
 0  7 2

---

3. Tumhe kaha pahuchna hai?

Tumhe bottom-right corner tak jana hai.

(m-1 , n-1)

Example me:

5 2 4
3 0 5
0 7 [2]

---

4. Tum kaise move kar sakte ho?
Tum sirf 2 direction me move kar sakte ho:
1️⃣ Right →
2️⃣ Down ↓

Tum left ya up nahi ja sakte

Example path:
5 → 2 → 4
        ↓
        5
        ↓
        2

---

5. Path ka sum kya hota hai?

Jis path se tum jaate ho uske sab numbers ko add karte hain

Example:
5 → 2 → 4 → 5 → 2

Sum:
5 + 2 + 4 + 5 + 2 = 18

---
6. Condition kya hai?

Path ka total sum hona chahiye:
sum % k == 0

Matlab:
sum k se perfectly divide ho jaye
koi remainder na aaye.

Example:
18 % 3 = 0
Isliye ye valid path hai
---

7. Tumhe kya find karna hai?

Tumhe count karna hai:
kitne different paths hai

jahan:
* start → (0,0)
* end → (m-1,n-1)
* sirf right / down
* aur path ka sum k se divisible ho

8. Example samjho

Grid:
5 2 4
3 0 5
0 7 2

k = 3

Kuch paths:
Path 1
5 → 2 → 4
        ↓
        5
        ↓
        2

Sum:
18

18 % 3 = 0 
Valid.

Path 2

5
↓
3
↓
0
→
5
→
2

Sum:
15

15 % 3 = 0 
Valid
---

Total valid paths:
2

Isliye output:
2
---

9. Simple sentence me problem
Tumhe grid me top-left se bottom-right tak jaana hai,
sirf right ya down move karte hue,
aur aise paths count karne hain jinka total sum k se divide ho jaye.


*/
