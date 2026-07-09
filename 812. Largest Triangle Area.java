class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0.0;
        int n = points.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int x1 = points[i][0];
                    int x2 = points[j][0];
                    int y1 = points[i][1];
                    int y2 = points[j][1];
                    int x3 = points[k][0];
                    int y3 = points[k][1];
                    double area = 0.5 * Math.abs(
                            x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)
                        );
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
/*
Explanation:

This problem asks us to find the largest area triangle that can be formed using any three points from the given array. Since the number of points is at most 50, trying every possible combination of three points is perfectly fine (at most 50*49*48/6 = 19600 combinations).

For each triplet of points, we calculate the triangle area using the shoelace formula. Given three points (x1,y1), (x2,y2), (x3,y3), the area is:

0.5 * |x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)|

The Math.abs is needed because depending on the order of points (clockwise or counterclockwise), the formula can give a negative value, but area is always positive. We keep track of the maximum area seen across all triplets and return it.

Three nested loops pick every unique combination of i, j, k where i < j < k, so no triplet is counted twice.

Dry Run (line by line on points = [[0,0],[0,1],[1,0],[0,2],[2,0]]):

line: maxArea = 0.0
line: n = points.length -> n = 5

Outer loop i = 0:
  Middle loop j = 1:
    Inner loop k = 2:
    line: x1=points[0][0]=0, x2=points[1][0]=0, y1=points[0][1]=0, y2=points[1][1]=1
    line: x3=points[2][0]=1, y3=points[2][1]=0
    line: area = 0.5 * |0*(1-0) + 0*(0-0) + 1*(0-1)|
               = 0.5 * |0 + 0 + (-1)|
               = 0.5 * 1 = 0.5
    line: maxArea = Math.max(0.0, 0.5) = 0.5

    Inner loop k = 3:
    line: x1=0, x2=0, y1=0, y2=1, x3=points[3][0]=0, y3=points[3][1]=2
    line: area = 0.5 * |0*(1-2) + 0*(2-0) + 0*(0-1)|
               = 0.5 * |0 + 0 + 0| = 0.0
    line: maxArea = Math.max(0.5, 0.0) = 0.5

    Inner loop k = 4:
    line: x1=0, x2=0, y1=0, y2=1, x3=points[4][0]=2, y3=points[4][1]=0
    line: area = 0.5 * |0*(1-0) + 0*(0-0) + 2*(0-1)|
               = 0.5 * |0 + 0 + (-2)|
               = 0.5 * 2 = 1.0
    line: maxArea = Math.max(0.5, 1.0) = 1.0

  Middle loop j = 2:
    Inner loop k = 3:
    line: x1=0, x2=points[2][0]=1, y1=0, y2=points[2][1]=0, x3=0, y3=2
    line: area = 0.5 * |0*(0-2) + 1*(2-0) + 0*(0-0)|
               = 0.5 * |0 + 2 + 0| = 1.0
    line: maxArea = Math.max(1.0, 1.0) = 1.0

    Inner loop k = 4:
    line: x1=0, x2=1, y1=0, y2=0, x3=2, y3=0
    line: area = 0.5 * |0*(0-0) + 1*(0-0) + 2*(0-0)|
               = 0.5 * |0 + 0 + 0| = 0.0
    line: maxArea = Math.max(1.0, 0.0) = 1.0

  Middle loop j = 3:
    Inner loop k = 4:
    line: x1=0, x2=points[3][0]=0, y1=0, y2=points[3][1]=2, x3=2, y3=0
    line: area = 0.5 * |0*(2-0) + 0*(0-0) + 2*(0-2)|
               = 0.5 * |0 + 0 + (-4)|
               = 0.5 * 4 = 2.0
    line: maxArea = Math.max(1.0, 2.0) = 2.0

Outer loop i = 1:
  Middle loop j = 2:
    Inner loop k = 3:
    line: x1=points[1][0]=0, x2=points[2][0]=1, y1=points[1][1]=1, y2=points[2][1]=0, x3=0, y3=2
    line: area = 0.5 * |0*(0-2) + 1*(2-1) + 0*(1-0)|
               = 0.5 * |0 + 1 + 0| = 0.5
    line: maxArea = Math.max(2.0, 0.5) = 2.0

    Inner loop k = 4:
    line: x1=0, x2=1, y1=1, y2=0, x3=2, y3=0
    line: area = 0.5 * |0*(0-0) + 1*(0-1) + 2*(1-0)|
               = 0.5 * |0 + (-1) + 2|
               = 0.5 * 1 = 0.5
    line: maxArea = Math.max(2.0, 0.5) = 2.0

  Middle loop j = 3:
    Inner loop k = 4:
    line: x1=0, x2=0, y1=1, y2=2, x3=2, y3=0
    line: area = 0.5 * |0*(2-0) + 0*(0-1) + 2*(1-2)|
               = 0.5 * |0 + 0 + (-2)|
               = 0.5 * 2 = 1.0
    line: maxArea = Math.max(2.0, 1.0) = 2.0

Outer loop i = 2:
  Middle loop j = 3:
    Inner loop k = 4:
    line: x1=points[2][0]=1, x2=points[3][0]=0, y1=points[2][1]=0, y2=points[3][1]=2, x3=2, y3=0
    line: area = 0.5 * |1*(2-0) + 0*(0-0) + 2*(0-2)|
               = 0.5 * |2 + 0 + (-4)|
               = 0.5 * 2 = 1.0
    line: maxArea = Math.max(2.0, 1.0) = 2.0

All loops done.
line: return maxArea -> return 2.0

Output: 2.00000 (matches expected, largest triangle is formed by points (0,0), (0,2), (2,0))
*/
