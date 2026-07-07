class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[][] dp = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        return f(triangle,n,0,0,dp,visited);
    }
    public int f(List<List<Integer>> triangle, int n, int i, int j, int[][] dp, boolean[][] visited){
        if(i==n-1) return triangle.get(i).get(j);
        if(visited[i][j]) {
            return dp[i][j];
        }
        int d = triangle.get(i).get(j) + f(triangle,n,i+1,j,dp,visited);
        int dg = triangle.get(i).get(j) + f(triangle,n,i+1,j+1,dp,visited);
        visited[i][j] = true;
        return dp[i][j] = Math.min(d,dg);
    }

}
/*
Explanation:
This problem asks us to find the minimum sum path from the top of the triangle to the bottom, where at each cell (i,j) we can only move to (i+1,j) or (i+1,j+1) in the next row.
The approach is top down recursion with memoization. Starting from cell (0,0), at every cell we have two choices, go straight down to (i+1,j) or go diagonally to (i+1,j+1). We take the minimum of both paths and add the current cell's value. A visited array and dp array together act as the memo table so we never recompute the same cell twice.
Base case: when we reach the last row (i == n-1), we just return that cell's value directly since there is nowhere further to go.
Memo check: if visited[i][j] is true, it means we already computed the best answer for this cell before, so we return dp[i][j] directly without recursing again.
Otherwise we recurse into both directions, compute d (going down) and dg (going diagonal), store the minimum in dp[i][j], mark visited[i][j] as true, and return that minimum.




Dry Run (line by line on triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]):

line: n = triangle.size() -> n = 4
line: dp = new int[4][4], visited = new boolean[4][4], all false/0
line: return f(triangle, 4, 0, 0, dp, visited)

f(i=0, j=0):
line: i == n-1 -> 0 == 3 -> false
line: visited[0][0] -> false, skip memo
line: d = triangle.get(0).get(0) + f(triangle,4,1,0,dp,visited) -> 2 + f(1,0)

  f(i=1, j=0):
  line: i == n-1 -> 1 == 3 -> false
  line: visited[1][0] -> false, skip memo
  line: d = triangle.get(1).get(0) + f(triangle,4,2,0,dp,visited) -> 3 + f(2,0)

    f(i=2, j=0):
    line: i == n-1 -> 2 == 3 -> false
    line: visited[2][0] -> false, skip memo
    line: d = triangle.get(2).get(0) + f(triangle,4,3,0,dp,visited) -> 6 + f(3,0)

      f(i=3, j=0):
      line: i == n-1 -> 3 == 3 -> true
      line: return triangle.get(3).get(0) -> return 4

    back in f(2,0): d = 6 + 4 = 10
    line: dg = triangle.get(2).get(0) + f(triangle,4,3,1,dp,visited) -> 6 + f(3,1)

      f(i=3, j=1):
      line: i == n-1 -> true
      line: return triangle.get(3).get(1) -> return 1

    back in f(2,0): dg = 6 + 1 = 7
    line: visited[2][0] = true
    line: dp[2][0] = Math.min(10, 7) = 7
    return 7

  back in f(1,0): d = 3 + 7 = 10
  line: dg = triangle.get(1).get(0) + f(triangle,4,2,1,dp,visited) -> 3 + f(2,1)

    f(i=2, j=1):
    line: i == n-1 -> false
    line: visited[2][1] -> false, skip memo
    line: d = triangle.get(2).get(1) + f(triangle,4,3,1,dp,visited) -> 5 + f(3,1)

      f(i=3, j=1):
      line: i == n-1 -> true
      line: return triangle.get(3).get(1) -> return 1

    back in f(2,1): d = 5 + 1 = 6
    line: dg = triangle.get(2).get(1) + f(triangle,4,3,2,dp,visited) -> 5 + f(3,2)

      f(i=3, j=2):
      line: i == n-1 -> true
      line: return triangle.get(3).get(2) -> return 8

    back in f(2,1): dg = 5 + 8 = 13
    line: visited[2][1] = true
    line: dp[2][1] = Math.min(6, 13) = 6
    return 6

  back in f(1,0): dg = 3 + 6 = 9
  line: visited[1][0] = true
  line: dp[1][0] = Math.min(10, 9) = 9
  return 9

back in f(0,0): d = 2 + 9 = 11
line: dg = triangle.get(0).get(0) + f(triangle,4,1,1,dp,visited) -> 2 + f(1,1)

  f(i=1, j=1):
  line: i == n-1 -> false
  line: visited[1][1] -> false, skip memo
  line: d = triangle.get(1).get(1) + f(triangle,4,2,1,dp,visited) -> 4 + f(2,1)

    f(i=2, j=1):
    line: visited[2][1] -> true, memo hit
    line: return dp[2][1] -> return 6

  back in f(1,1): d = 4 + 6 = 10
  line: dg = triangle.get(1).get(1) + f(triangle,4,2,2,dp,visited) -> 4 + f(2,2)

    f(i=2, j=2):
    line: i == n-1 -> false
    line: visited[2][2] -> false, skip memo
    line: d = triangle.get(2).get(2) + f(triangle,4,3,2,dp,visited) -> 7 + f(3,2)

      f(i=3, j=2):
      line: i == n-1 -> true
      line: return triangle.get(3).get(2) -> return 8

    back in f(2,2): d = 7 + 8 = 15
    line: dg = triangle.get(2).get(2) + f(triangle,4,3,3,dp,visited) -> 7 + f(3,3)

      f(i=3, j=3):
      line: i == n-1 -> true
      line: return triangle.get(3).get(3) -> return 3

    back in f(2,2): dg = 7 + 3 = 10
    line: visited[2][2] = true
    line: dp[2][2] = Math.min(15, 10) = 10
    return 10

  back in f(1,1): dg = 4 + 10 = 14
  line: visited[1][1] = true
  line: dp[1][1] = Math.min(10, 14) = 10
  return 10

back in f(0,0): dg = 2 + 10 = 12
line: visited[0][0] = true
line: dp[0][0] = Math.min(11, 12) = 11
return 11

Output: 11 (matches expected, path is 2 -> 3 -> 5 -> 1 = 11)
*/
