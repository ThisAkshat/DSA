class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n + 1][n + 1];

        // Step 1: Apply difference array updates
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];

            diff[r1][c1] += 1;
            diff[r1][c2 + 1] -= 1;
            diff[r2 + 1][c1] -= 1;
            diff[r2 + 1][c2 + 1] += 1;
        }

        // Step 2: Prefix sum horizontally
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // Step 3: Prefix sum vertically
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        // Step 4: Build the final matrix (trim extra row/col)
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = diff[i][j];
            }
        }

        return result;
    }
}

/*
Problem :-
Tumhe ek integer n diya gaya hai. Iska matlab hai ki starting me ek n x n matrix hai jo completely zero se filled hai.
Matlab agar n = 3 hai, to shuru me matrix kuch aisa hoga:
0 0 0
0 0 0
0 0 0
Ab tumhe ek 2D array queries diya gaya hai.
Har query ka format hai:
[row1, col1, row2, col2]
Iska matlab:
* (row1, col1) top-left corner hai
* (row2, col2) bottom-right corner hai
Aur tumhe kya karna hai:
Is rectangle (submatrix) ke andar jitne bhi cells aate hain,
un sab me 1 add karna hai.

Matlab:
Har x aur y ke liye jahan
row1 <= x <= row2
aur
col1 <= y <= col2

wahan mat[x][y] me +1 karna hai.

Ye process har query ke liye karna hai, ek ke baad ek.

Agar koi cell multiple queries me aata hai,
to usme multiple baar +1 hoga.

Finally tumhe pura matrix return karna hai
after all queries apply ho chuki ho.

Simple words me:

Start with zero matrix.
Har query ek rectangle batati hai.
Us rectangle ke andar ke sab elements me 1 add karo.
Saari queries apply karne ke baad final matrix return karo.

*************************************************************************************************

Example:

n = 2
queries = [[0,0,1,1]]

---

Step 0: Initial matrix (2 x 2)

0 0
0 0

---

Query: [0,0,1,1]

Top-left = (0,0)
Bottom-right = (1,1)

Condition:

0 <= row <= 1
0 <= col <= 1

So pura matrix cover ho raha hai.

Affected cells:

(0,0)
(0,1)
(1,0)
(1,1)

Sab me +1 hoga.

---

After applying query:

1 1
1 1

---

Final output:

[[1,1],[1,1]]


*************************************************************************************************

Dry run for:

n = 3
queries = [[1,1,2,2], [0,0,1,1]]

---

Step 0: Initial matrix (3 x 3)

0 0 0
0 0 0
0 0 0

---

Query 1: [1,1,2,2]

Top-left = (1,1)
Bottom-right = (2,2)

So we add 1 to all cells where:

1 <= row <= 2
1 <= col <= 2

Affected cells:

(1,1)
(1,2)
(2,1)
(2,2)

Matrix after Query 1:

0 0 0
0 1 1
0 1 1

---

Query 2: [0,0,1,1]

Top-left = (0,0)
Bottom-right = (1,1)

Add 1 to all cells where:

0 <= row <= 1
0 <= col <= 1

Affected cells:

(0,0)
(0,1)
(1,0)
(1,1)

Now update one by one:

(0,0): 0 → 1
(0,1): 0 → 1
(1,0): 0 → 1
(1,1): 1 → 2

---

Final matrix:

1 1 0
1 2 1
0 1 1

---

Return:

[[1,1,0],[1,2,1],[0,1,1]]




*/

