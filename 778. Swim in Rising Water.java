class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = 0;
        int high = 0;
        for(int[] row : grid){
            for(int val : row) high = Math.max(high, val);
        }
        int result = high;

        while(low <= high){
            int mid = (low+high)/2;
            if(canReach(grid, mid)){
                result = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return result;
    }
    private boolean canReach(int[][] grid, int t){
        int n = grid.length;
        if(grid[0][0] > t) return false;
        boolean[][] visited = new boolean[n][n];
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            if(r==n-1 && c==n-1) return true;
            for(int[] d: dirs){
                int nr = r+d[0], nc = c+d[1];
                if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc] && grid[nr][nc] <= t){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}

/*
1. PROBLEM :-
   You are given a square grid where each cell contains an elevation value. Rain starts and water level increases over time.

At time t:
Water level = t
You can move from one cell to another (up, down, left, right) only if both cells have elevation less than or equal to t.
Start position:
top-left cell (0,0)

Target position:
bottom-right cell (n-1,n-1)
Goal:
Find the minimum time t at which it becomes possible to reach the bottom-right cell from the top-left cell.

Key idea used in the solution:
Binary Search + BFS

Explanation of the approach:

1. The answer lies between the minimum and maximum elevation values in the grid.
2. Use binary search on time t.
3. For each time t, check if it is possible to reach the destination using BFS.
4. If reachable, try smaller time.
5. If not reachable, increase time.

This works because if you can reach the destination at time t, you will also be able to reach it at any time greater than t.
Time Complexity:
O(n² log n²)
Space Complexity:
O(n²)

2. EXAMPLE :-
Input
grid =
[[0,2],
[1,3]]

Step-by-step idea:

At time t = 0
Water level = 0

You are at (0,0)
Elevation = 0

Neighbors:
(0,1) = 2
(1,0) = 1

Both are higher than water level 0
So you cannot move.
Not reachable.

At time t = 1
You can move to:
(1,0) because elevation = 1
But from there:
(1,1) = 3
Still not reachable.
At time t = 2
You can move further but still cannot reach the destination.
At time t = 3
Now all cells with elevation ≤ 3 become reachable.

Path becomes:
(0,0) → (1,0) → (1,1)
So minimum time required = 3
Output = 3


3. DRY RUN :-
Example (custom example)
grid =
[ [0,5,6],
[1,2,7],
[3,4,8] ]

Step 1
Find search range
low = 0
high = 8

Step 2
Binary Search
mid = (0 + 8) / 2
mid = 4
Check if we can reach destination at time = 4

Cells allowed (≤4):
0  X  X
1  2  X
3  4  X
Start BFS from (0,0)
Possible path:
(0,0) → (1,0) → (1,1) → (2,1)
But cannot reach (2,2)
Result: Not reachable

Update:
low = 5

Step 3
mid = (5 + 8) / 2
mid = 6

Cells allowed (≤6):
0  5  6
1  2  X
3  4  X

Try BFS
Reachable path:
(0,0) → (0,1) → (0,2) → (1,2)
But cannot go to destination yet.
Not reachable
Update:
low = 7

Step 4
mid = (7 + 8) / 2
mid = 7

Cells allowed:
0 5 6
1 2 7
3 4 X
Still destination blocked.

Update:
low = 8
Step 5
mid = 8
Now all cells accessible.

Path:
(0,0) → (1,0) → (2,0) → (2,1) → (2,2)
Reach destination.
Result = 8


Final Answer = 8

*/
