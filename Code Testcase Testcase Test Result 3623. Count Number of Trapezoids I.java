/*
Intuition
Horizontal trapezoid = Two parallel horizontal sides
Parallel to x-axis = Same y-coordinate
So we need two pairs of points with same y-values (top side pair and bottom side pair)

Dry Run
Example: [[1,0],[2,0],[3,0],[2,2],[3,2]]

Step 1: Count frequency by y

y=0: 3 points → C(3,2)=3

y=2: 2 points → C(2,2)=1

Step 2: Calculate

text
total = 3 + 1 = 4
pairs = 3×3 + 1×1 = 9 + 1 = 10
Step 3: Formula

text
(total² - pairs) / 2 = (16 - 10)/2 = 6/2 = 3 ✓


Example 1:
Input: points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
Output: 3

Explanation:
There are three distinct ways to pick four points that form a horizontal trapezoid:
Using points [1,0], [2,0], [3,2], and [2,2].
Using points [2,0], [3,0], [3,2], and [2,2].
Using points [1,0], [3,0], [3,2], and [2,2].

Example 2:
Input: points = [[0,0],[1,0],[0,1],[2,1]]
Output: 1
Explanation: There is only one horizontal trapezoid that can be formed.
*/


class Solution {
    public int countTrapezoids(int[][] points) {
        final long MOD = 1000000007;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int[] p : points) {
            freq.put(p[1], freq.getOrDefault(p[1], 0) + 1);
        } // freq = {0:3, 2:2}
        long total = 0, pairs = 0;
        for (int f : freq.values()) {
            if (f <= 1) {
                continue;
            }
            long pair = (long) f * (f - 1) / 2; //Explanation: C(3,2) = 3 (3 points mein se 2 choose karne ke 3 ways)
            //Explanation: C(2,2) = 1 (2 points mein se 2 choose karne ka 1 way)
            total += pair;
            pairs += pair * pair;
        }
        return (int) ((total * total - pairs) / 2 % MOD);
        /*
        Calculate step-by-step:
        
        total * total = 4 * 4 = 16
        
        total * total - pairs = 16 - 10 = 6
        
        / 2 = 6 / 2 = 3
        
        % MOD = 3 % 1000000007 = 3
        */
    }
}
