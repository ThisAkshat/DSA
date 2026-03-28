class Solution {
    int m, n; int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m=heights.length; n=heights[0].length;
        boolean[][] p= new boolean[m][n], A = new boolean[m][n];
        for(int i=0;i<m;i++){
            dfs(heights,p,i,0,heights[i][0]);
            dfs(heights,A,i,n-1,heights[i][n-1]);
        }
        for(int j=0;j<n;j++){
            dfs(heights,p,0,j,heights[0][j]);
            dfs(heights,A,m-1,j,heights[m-1][j]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<m;i++) for(int j=0;j<n;j++) if(p[i][j]&&A[i][j]) res.add(List.of(i,j));
        return res;
    }
    void dfs(int[][] heights, boolean[][] v, int i, int j, int prev){
        if(i<0 || j<0 || i>=m || j>=n || v[i][j] || heights[i][j]<prev) return;
        v[i][j]= true;
        for(int[] d:dirs) dfs(heights,v,i+d[0],j+d[1],heights[i][j]);
    }
}

/*
1. PROBLEM :-
   You are given a grid heights where each cell represents the height of land on an island. The island touches two oceans:

Pacific Ocean touches the top and left edges.
Atlantic Ocean touches the bottom and right edges.

Water flow rule:
Water can flow from a cell to its neighboring cells (up, down, left, right) if the next cell's height is less than or equal to the current cell's height.

Goal:
Find all cells from which water can reach both the Pacific Ocean and the Atlantic Ocean.

Important idea used in the solution:
Instead of checking from every cell toward the oceans (which would be very slow), we do the opposite:

Start from the oceans and move inward.

Steps of the algorithm:

1. Create two visited matrices:
   one for Pacific reach
   one for Atlantic reach

2. Start DFS from:
   Pacific borders (top row and left column)
   Atlantic borders (bottom row and right column)

3. While doing DFS:
   We only move to a neighbor if its height is greater than or equal to the previous height.
   This simulates reverse water flow.

4. After DFS finishes:
   Cells visited by both DFS traversals can reach both oceans.

Time Complexity: O(m × n)
Space Complexity: O(m × n)

2. EXAMPLE :-

Input
heights =

[ [1,2,2,3,5],
[3,2,3,4,4],
[2,4,5,3,1],
[6,7,1,4,5],
[5,1,1,2,4] ]

Pacific touches:
top row and left column

Atlantic touches:
bottom row and right column

After running DFS from both oceans, some cells can reach both oceans.

Cells that satisfy the condition:

[0,4]
[1,3]
[1,4]
[2,2]
[3,0]
[3,1]
[4,0]

Example explanation of one cell:
Cell (2,2)
Height = 5
Path to Pacific:
(2,2) → (1,2) → (0,2) → Pacific

Path to Atlantic:
(2,2) → (2,3) → (2,4) → Atlantic
So it is included in the result.

3. DRY RUN :-
Example (custom grid)

heights =
[ [3,3,3,3],
[3,1,2,3],
[3,2,3,3],
[3,3,3,3] ]

Grid
3 3 3 3
3 1 2 3
3 2 3 3
3 3 3 3

Step 1
Pacific DFS starts from:
top row and left column
Reachable Pacific cells expand inward if height condition is satisfied.
Most outer 3s get marked reachable.

Step 2
Atlantic DFS starts from:
bottom row and right column
These DFS calls also spread inward.

Step 3
Mark cells reachable by both oceans.
Cells that become common in both visited arrays:

(0,0)
(0,1)
(0,2)
(0,3)
(1,3)
(2,3)
(3,0)
(3,1)
(3,2)
(3,3)

These cells can flow to both oceans.

Final Output:
All coordinates that appear in both DFS visited matrices.



*/



