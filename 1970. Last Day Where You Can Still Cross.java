class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        /* 
        row = 3, col = 3
        cells = [[1,2], [2,1], [3,3], [2,2], [1,1], [1,3], [2,3], [3,2], [3,1]]
        */
        int left = 0; // left = 0
        int right = cells.length-1; // right = 9 - 1 = 8
        int ans = 0; // ans = 0
        while(left <= right){
            int mid = left + (right - left)/2; // 4 
            if(canCross(row, col, cells, mid)){
                ans = mid;
                left = mid+1;
            }else{
                right = mid - 1;
            }
        }
        return ans+1;
    }
    private boolean canCross(int row, int col, int[][] cells, int day){
        int[][] grid = new int[row][col];
        for(int i=0; i<=day; i++){
    // i=0: cells[0]=[1,2] → r=0, c=1 → grid[0][1]=1
    // i=1: cells[1]=[2,1] → r=1, c=0 → grid[1][0]=1
    // i=2: cells[2]=[3,3] → r=2, c=2 → grid[2][2]=1
    // i=3: cells[3]=[2,2] → r=1, c=1 → grid[1][1]=1
    // i=4: cells[4]=[1,1] → r=0, c=0 → grid[0][0]=1
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1;
            /*
            [1 1 0]
            [1 1 0]
            [0 0 1]
            */
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col]; // All False

        for(int c=0; c<col; c++){
            if(grid[0][c] == 0){ // Only grid[0][2]=0
                queue.offer(new int[]{0,c}); // Add (0,2)
                visited[0][c] = true;
            }
        }
        int[][] direction = {{-1, 0}, {1,0}, {0, -1}, {0,1}};
        while(!queue.isEmpty()){
            int[] current = queue.poll(); // (0,2)
            int r = current[0];
            int c = current[1];

            if(r == row-1){  // 0 == 2? false
                return true;
            }

            for(int[] dir : direction){
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nr < row && nc >= 0 && nc < col && grid[nr][nc] == 0 && !visited[nr][nc]) {
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return false;
    }
}



/*
1) Problem
Imagine a grid like a piece of land.
0 = land (you can walk)
1 = water (flooded, you can’t walk)
On day 0, everything is land.
Every day, one cell gets flooded according to the cells array.
Your goal:
You must walk from top row to bottom row, moving only through land, in 4 directions.
The question:
What is the last day when this is still possible?

******************************************************************************************************

2) Example (easy)
For this input:

row = 3, col = 3
cells = [
 [1,2], [2,1], [3,3], [2,2], [1,1],
 [1,3], [2,3], [3,2], [3,1]
]

Each day, one cell turns into water in this order.
At first, paths exist from top to bottom.
As more cells get flooded, paths start getting blocked.
Eventually, on Day 4, the path breaks.
So the last safe day is Day 3.

******************************************************************************************************

3) Short Dry Run (for day = 4)
The algorithm checks:
“Can I still cross on day 4?”
Flooded cells after day 4:
(1,2), (2,1), (3,3), (2,2), (1,1)
Grid becomes:
1 1 0
1 1 0
0 0 1

(1 = water, 0 = land)
Start from the top row:
[1 1 0]
Only (0,2) is land → start there.

Try to move:
From (0,2) → go down → (1,2) → ok
From (1,2) → go down → (2,2) → water ❌
Left → (1,1) → water ❌
Right / up → out of bounds ❌
No way to reach the bottom row.
So:
canCross(4) = false
That means day 4 is too late.
Binary search then tries earlier days and finally finds:

Answer = Day 3 ✅
*/
