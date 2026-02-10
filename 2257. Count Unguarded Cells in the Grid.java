class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] wall : walls) { // marks walls as 1
            grid[wall[0]][wall[1]] = 1;
        }
        for (int[] guard : guards) { // marks guard as 1
            grid[guard[0]][guard[1]] = 1;
        }
        for (int[] guard : guards) { // for each guard, mark visible cells
            int x, y;
            //left direction
            x = guard[0];
            y = guard[1] - 1;
            while (y >= 0 && grid[x][y] != 1) {
                grid[x][y] = 2;
                y--;
            }
            // right direction
            x = guard[0];
            y = guard[1] + 1;
            while (y < n && grid[x][y] != 1) {
                grid[x][y] = 2;
                y++;
            }
            // up direction
            x = guard[0] - 1;
            y = guard[1];
            while (x >= 0 && grid[x][y] != 1) {
                grid[x][y] = 2;
                x--;
            }
            // down direction
            x = guard[0] + 1;
            y = guard[1];
            while (x < m && grid[x][y] != 1) {
                grid[x][y] = 2;
                x++;
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}




/*
1) Problem :-
You have a grid (like a chessboard).

Some cells have:
Guards
Walls
Empty spaces

Rules:
A guard can see in 4 directions → up, down, left, right
A guard keeps seeing until a wall or another guard blocks the view
Any cell that a guard can see is called guarded

Your job:
Count how many empty cells are NOT guarded by any guard

************************************************************************************

2) Simple example

Let’s say:
m = 3, n = 3
guards = [[1,1]]
walls = [[0,1],[1,0],[2,1],[1,2]]
Grid (G = guard, W = wall, . = empty)

.  W  .
W  G  W
.  W  .

The guard is totally surrounded by walls, so it can’t see anything.
So all empty cells stay unguarded.
There are 4 dots → answer = 4

************************************************************************************

3) Short Dry Run

Let’s use this input:
m = 4, n = 6
guards = [[0,0],[1,1],[2,3]]
walls = [[0,1],[2,2],[1,4]]

First, build the grid:
0 = empty
1 = wall or guard
2 = guarded area

Place walls and guards:

G W . . . .
. G . . W .
. . W G . .
. . . . . .


Now every guard starts “looking” 
Guard at (0,0)
Looks right → stops at wall (0,1)
Looks down → marks cells until something blocks
Guard at (1,1)
Looks all 4 directions until wall or guard
Guard at (2,3)
Same thing
They mark all visible cells as 2 (guarded).
After all guards finish looking, the grid is full of:
1 → wall or guard
2 → guarded cells
0 → unguarded empty cells
Finally we count how many 0s are left.

Those 0s are the answer → 7

*/



