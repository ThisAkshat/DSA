class Solution {
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length ==0) return 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];
        for(int i =0 ;i<m;i++){
            for(int j=0 ; j<n; j++){
                if(i == 0 || i == m-1 || j == 0 || j == n-1){
                    pq.offer(new int[]{heightMap[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }
        int water = 0;
        int maxheight = 0;
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        while(!pq.isEmpty()){
            int[] cell = pq.poll();
            int height = cell[0];
            int x = cell[1];
            int y = cell[2];
            maxheight = Math.max(maxheight, height);
            for(int[] dir: dirs){
                int nx = x+dir[0];
                int ny = y+dir[1];
                if(nx>=0 && nx<m && ny >=0 && ny<n && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if(heightMap[nx][ny] < maxheight){
                        water = water+maxheight - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{heightMap[nx][ny], nx, ny});
                }
            }
        }
        return water;
    }
}

/*
1. PROBLEM :-
   You are given a 2D grid (matrix) called heightMap where each cell represents the height of land at that position. After raining, water can get trapped between higher boundary cells.
Your task is to calculate the total amount of water that can be trapped in this 2D elevation map.
Important idea of the problem:
Water can only be trapped if it is surrounded by taller boundaries from all sides. In 2D, this becomes more complex than the 1D trapping rainwater problem because water can leak from any direction.
Core concept used in the solution:
We start from the boundary of the grid because water cannot be trapped on the edges. Then we move inward while maintaining the minimum boundary height using a Min Heap (Priority Queue).

Algorithm logic:

1. Add all boundary cells into a priority queue (min heap).
2. Mark them as visited.
3. Always process the smallest height cell first.
4. Track the current maximum boundary height.
5. Explore neighbors (up, down, left, right).
6. If a neighbor is lower than the current boundary height, water gets trapped.
7. Add that trapped water to the total.
8. Push the neighbor into the heap and continue.

Why this works:
The boundary acts like a wall. As we move inward, we simulate how water would fill the area.

Time Complexity: O(m × n log(m × n))
Space Complexity: O(m × n)

2. EXAMPLE :-

Input
heightMap =
[[1,4,3,1,3,2],
[3,2,1,3,2,4],
[2,3,3,2,3,1]]

Visual idea of heights

1 4 3 1 3 2
3 2 1 3 2 4
2 3 3 2 3 1

Water can only be trapped inside the boundary.

Trapped locations:

Cell (1,1) height = 2
Boundary around it is higher → can trap water

Cell (1,2) height = 1
Surrounded by taller cells → traps water

Small ponds form in the middle.

Water trapped:
1 unit at one place
3 units at another place

Total trapped water = 4

Output = 4

3. DRY RUN :-
Example (custom tough example)
heightMap =
[[5,5,5,5],
[5,1,2,5],
[5,2,1,5],
[5,5,5,5]]

Grid:

5 5 5 5
5 1 2 5
5 2 1 5
5 5 5 5

Step 1
Add all boundary cells into the priority queue.
Boundary cells:
All outer 5s are inserted into the heap.
Visited cells marked.
Current maxHeight = 0

Step 2
Process smallest boundary cell from heap.
height = 5
maxHeight = 5
Now check neighbors.

Step 3
Move to inner cell (1,1)
heightMap[1][1] = 1
Since
1 < maxHeight (5)
Water trapped =
5 - 1 = 4
Total water = 4
Add this cell to heap.

Step 4
Next inner cell (1,2)
height = 2
Water trapped =
5 - 2 = 3
Total water =
4 + 3 = 7

Step 5
Next inner cell (2,1)
height = 2
Water trapped =
5 - 2 = 3
Total water =
7 + 3 = 10

Step 6
Next inner cell (2,2)
height = 1
Water trapped =
5 - 1 = 4
Total water =
10 + 4 = 14
All cells processed.

Final trapped water = 14
Output = 14



*/



