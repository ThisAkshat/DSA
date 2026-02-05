class Solution {
    public int countNegatives(int[][] grid) {
        int count = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length;j++){
                if(grid[i][j] < 0){
                    count += grid[i].length-j;
                    break;
                }
            }
        }
        return count;
    }
}

/*
Problem in simple words :-
You are given a matrix where:
* Each row is sorted from left to right in decreasing order
* Each column is also sorted from top to bottom in decreasing order

So big numbers are on the top-left,
small (negative) numbers are pushed to the bottom-right.
You just need to count how many numbers are negative.

******************************************************************************************

🧪 Example
grid =
[ 4   3   2  -1 ]
[ 3   2   1  -1 ]
[ 1   1  -1  -2 ]
[ -1 -1  -2  -3 ]

Look row by row:
Row 0 → first negative at index 3 → 1 negative
Row 1 → first negative at index 3 → 1 negative
Row 2 → first negative at index 2 → 2 negatives
Row 3 → first negative at index 0 → 4 negatives

Total = 1 + 1 + 2 + 4 = 8

******************************************************************************************

🧾 Short dry run
Row = [1, 1, -1, -2]


Scan from left:
* 1 → not negative
* 1 → not negative
* -1 → first negative at index 2

Row length = 4
Negatives = 4 - 2 = 2
(−1, −2)

Stop scanning this row.

*/
