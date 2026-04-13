class Solution {
    public int magicalSum(int m, int k, int[] nums) {
        int MOD = (int) (1e9 + 7);
        int n = nums.length;

        int[][] C = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j < i; j++) {
                C[i][j] = (C[i-1][j-1] + C[i-1][j]) % MOD;
            }
        }

        int[][] pow = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            pow[i][0] = 1;
            for (int cnt = 1; cnt <= m; cnt++) {
                pow[i][cnt] = (int)((long)pow[i][cnt-1] * nums[i] % MOD);
            }
        }

        int[][][][] dp = new int[n + 1][k + 1][m + 1][m + 1];
        dp[0][0][0][0] = 1;

        for (int pos = 0; pos < n; pos++) {
            for (int bits = 0; bits <= k; bits++) {
                for (int carry = 0; carry <= m; carry++) {
                    for (int chosen = 0; chosen <= m; chosen++) {
                        if (dp[pos][bits][carry][chosen] == 0) continue;
                        
                        int remaining = m - chosen;
                        for (int cnt = 0; cnt <= remaining; cnt++) {
                            int total = carry + cnt;
                            int new_bits = bits + (total & 1);
                            int new_carry = total >> 1;
                            
                            if (new_bits <= k && new_carry <= m) {
                                long add = (long)dp[pos][bits][carry][chosen] 
                                    * C[remaining][cnt] % MOD 
                                    * pow[pos][cnt] % MOD;
                                
                                dp[pos+1][new_bits][new_carry][chosen+cnt] = 
                                    (dp[pos+1][new_bits][new_carry][chosen+cnt] + (int)add) % MOD;
                            }
                        }
                    }
                }
            }
        }


        int res = 0;
        for (int carry = 0; carry <= m; carry++) {
            int final_bits = k;

            int carry_bits = Integer.bitCount(carry);
            if (carry_bits <= k) {
                res = (res + dp[n][k - carry_bits][carry][m]) % MOD;
            }
        }

        return res;
    }
}

/*
1. PROBLEM :-
   You are given:
   m → length of sequence
   k → number of set bits required
   nums → array of values

You need to form sequences seq of length m such that:

* Each element in seq is an index in nums
* Product = nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m-1]]

Condition:
Compute value:
2^(seq[0]) + 2^(seq[1]) + ... + 2^(seq[m-1])

The binary representation of this sum must have exactly k set bits.
Goal:
Return the sum of products of all such valid sequences (mod 1e9+7)

---

Core idea: Each seq[i] contributes a power of 2. When multiple same indices appear, their powers add and create carries in binary.

So the problem becomes:
Simulate binary addition with carry and count how many set bits appear.

DP is used with state:
dp[pos][bits][carry][chosen]

Where:
pos → current index in nums
bits → number of set bits formed so far
carry → carry value
chosen → how many elements selected so far

At each position:
* Choose cnt elements from current index
* Update carry and bits
* Multiply contribution using combinations and power

Finally:
Check if total set bits (including carry bits) = k

---

Time Complexity:
O(n * k * m^2)

Space Complexity:
O(n * k * m^2)

---

2. EXAMPLE :-
Input
m = 2
k = 2
nums = [5,4,3,2,1]

We need sequences of length 2
Possible sequences:
[0,1], [1,0], [0,2], [2,0], etc.

Check condition:
Example:
seq = [0,1]

Value:
2^0 + 2^1 = 1 + 2 = 3

Binary:
11 → 2 set bits → valid
Product:
5 * 4 = 20
Similarly compute for all valid sequences.
Total sum = 170
Output = 170

---

3. DRY RUN :-
Example (custom small case)
m = 3
k = 2
nums = [2,3]
We need sequences of length 3 using indices {0,1}

---

Step 1: Precompute
Combinations C(n, r)

Powers:
nums[0] → [1,2,4,8]
nums[1] → [1,3,9,27]

---

Step 2: DP start
dp[0][0][0][0] = 1

---

At pos = 0 (value = 2)
We can pick cnt = 0 to 3
Case cnt = 1:
total = 0 + 1 = 1
bits += 1
carry = 0

Contribution:
C(3,1) * 2^1 = 3 * 2 = 6

---

Case cnt = 2:
total = 2
bits += 0
carry = 1

Contribution:
C(3,2) * 2^2 = 3 * 4 = 12

---

Case cnt = 3:
total = 3
bits += 1
carry = 1

Contribution:
C(3,3) * 2^3 = 1 * 8 = 8
---

Step 3: pos = 1 (value = 3)
Repeat similar transitions
---

Step 4: Final step
At end:
We check remaining carry

Example:
carry = 1 → binary = 1 → 1 bit
If previous bits = 1
total bits = 2 → valid
---

Step 5: Sum all valid contributions
Final Answer = sum of all valid sequence products (mod 1e9+7)

---
Final Output depends on full DP computation.


*/
