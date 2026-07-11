class Solution {
    int[][] dp;
    public int minScoreTriangulation(int[] values) {
        dp=new int[values.length][values.length];
        for(int i=0;i<values.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return solve(0,values.length-1,values);
    }
    int solve(int i,int j,int[] nums){
        if(j-i<2) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int min=Integer.MAX_VALUE;
        for(int ind=i+1;ind<j;ind++){
            int dup=nums[ind]*nums[i]*nums[j]+
            solve(i,ind,nums)+
            solve(ind,j,nums);
            min=Math.min(min,dup);
        }
        dp[i][j]=min;
        return min;
    }
}
/*
Explanation:

This problem asks us to divide a convex polygon into triangles using only its own vertices, and find the way to do it such that the sum of all triangle scores (product of three vertex values) is minimized.

The approach is top down recursion with memoization. The idea is to fix the two endpoints i and j as the "base" edge of the current sub-polygon. Then we try every possible third vertex ind between i and j to form a triangle with vertices i, ind, j. That one triangle costs values[i]*values[ind]*values[j]. The remaining polygon is now split into two smaller sub-polygons: (i to ind) and (ind to j). We recursively solve both and add them. We pick the ind that gives the minimum total score.

Base case: if j - i < 2, there are fewer than 3 vertices between i and j so no triangle can be formed, return 0.

Memo check: if dp[i][j] is not -1, we already solved this sub-problem, return it directly.

The outer call is always solve(0, n-1) which covers the full polygon.

Dry Run (line by line on values = [3,7,4,5]):

line: dp = new int[4][4], all filled with -1
line: return solve(0, 3, values)

solve(i=0, j=3):
line: j-i = 3-0 = 3, 3 < 2 -> false, continue
line: dp[0][3] != -1 -> -1 != -1 -> false, continue
line: min = Integer.MAX_VALUE

ind = 1:
line: dup = values[1]*values[0]*values[3] + solve(0,1) + solve(1,3)
          = 7*3*5 + solve(0,1) + solve(1,3)
          = 105 + solve(0,1) + solve(1,3)

  solve(i=0, j=1):
  line: j-i = 1-0 = 1, 1 < 2 -> true
  line: return 0

  solve(i=1, j=3):
  line: j-i = 3-1 = 2, 2 < 2 -> false, continue
  line: dp[1][3] != -1 -> false, continue
  line: min = Integer.MAX_VALUE

    ind = 2:
    line: dup = values[2]*values[1]*values[3] + solve(1,2) + solve(2,3)
              = 4*7*5 + solve(1,2) + solve(2,3)
              = 140 + solve(1,2) + solve(2,3)

      solve(i=1, j=2):
      line: j-i = 1 < 2 -> true
      line: return 0

      solve(i=2, j=3):
      line: j-i = 1 < 2 -> true
      line: return 0

    back in solve(1,3), ind=2:
    line: dup = 140 + 0 + 0 = 140
    line: min = Math.min(MAX_VALUE, 140) = 140

  ind loop ends (ind only goes from 2 to 2 here)
  line: dp[1][3] = 140
  line: return 140

back in solve(0,3), ind=1:
line: dup = 105 + 0 + 140 = 245
line: min = Math.min(MAX_VALUE, 245) = 245

ind = 2:
line: dup = values[2]*values[0]*values[3] + solve(0,2) + solve(2,3)
          = 4*3*5 + solve(0,2) + solve(2,3)
          = 60 + solve(0,2) + solve(2,3)

  solve(i=0, j=2):
  line: j-i = 2, 2 < 2 -> false, continue
  line: dp[0][2] != -1 -> false, continue
  line: min = Integer.MAX_VALUE

    ind = 1:
    line: dup = values[1]*values[0]*values[2] + solve(0,1) + solve(1,2)
              = 7*3*4 + solve(0,1) + solve(1,2)
              = 84 + 0 + 0 = 84
    line: min = Math.min(MAX_VALUE, 84) = 84

  ind loop ends
  line: dp[0][2] = 84
  line: return 84

  solve(i=2, j=3):
  line: j-i = 1 < 2 -> true
  line: return 0

back in solve(0,3), ind=2:
line: dup = 60 + 84 + 0 = 144
line: min = Math.min(245, 144) = 144

ind loop ends
line: dp[0][3] = 144
line: return 144

Output: 144 (matches expected, triangles are (3,4,5) scoring 60 and (3,7,4) scoring 84, total = 144)
*/
