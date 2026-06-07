class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) continue;
                int x1 = points[i][0] ,y1 = points[i][1];
                int x2 = points[j][0] ,y2 = points[j][1];
                if(x1 <= x2 && y1 >= y2){
                    boolean valid = true;
                    for(int k=0; k<n; k++){
                        if(k == i || k == j) continue;
                        int x = points[k][0] ,y = points[k][1];
                        if(x >= x1 && x <= x2 && y >= y2 && y <= y1){
                            valid = false;
                            break;
                        }
                    }
                    if( valid ){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
/*
problem -
You are given n points on a 2D plane.

You need to count pairs (A, B) such that:
1. A is on the upper-left side of B.
2. No other point lies inside the rectangle formed by A and B.
3. No other point lies on the border of that rectangle either.
4. Only A and B are allowed inside/on the rectangle.
For A(x1,y1) and B(x2,y2):

A must be upper-left of B:
x1 ≤ x2
y1 ≥ y2
Why?
Upper-left means:
* A should be left of B → x1 ≤ x2
* A should be above B → y1 ≥ y2
If these conditions hold, then A and B form a rectangle.
Now check whether any third point exists inside or on the border of this rectangle.
If yes → invalid pair.
If no → valid pair.
example -
points = [[6,2],[4,4],[2,6]]

Let's name them:
P0 = (6,2)
P1 = (4,4)
P2 = (2,6)
Possible valid upper-left pairs:
P2 → P1
x = 2 ≤ 4 ✓
y = 6 ≥ 4 ✓

Rectangle:

(2,6) -------- (4,6)
|               |
|               |
(2,4) -------- (4,4)

No point inside.
Valid pair.
P1 → P0
x = 4 ≤ 6 ✓
y = 4 ≥ 2 ✓

Rectangle:

(4,4) -------- (6,4)
|               |
|               |
(4,2) -------- (6,2)

No point inside.
Valid pair.
P2 → P0

x = 2 ≤ 6 ✓
y = 6 ≥ 2 ✓
Rectangle:

(2,6) ---------------- (6,6)
|                       |
|       (4,4)           |
|                       |
(2,2) ---------------- (6,2)

P1=(4,4) lies inside rectangle.
Invalid pair.
Answer = 2

dry run -
points = [[1,5],[3,4],[5,2],[2,1],[4,3]]
Let's name them:
P0=(1,5)
P1=(3,4)
P2=(5,2)
P3=(2,1)
P4=(4,3)

count = 0
i = P0
j = P1
x1=1 y1=5
x2=3 y2=4
1≤3 ✓
5≥4 ✓

Check all other points.
P2=(5,2)
5 between [1,3] ? No
P3=(2,1)
y=1 not between [4,5]
P4=(4,3)
x=4 not between [1,3]
No point found.
Valid.
count = 1
j = P2
1≤5 ✓
5≥2 ✓

Check others.
P1=(3,4)
3 between [1,5] ✓
4 between [2,5] ✓
Point exists inside rectangle.
Invalid.
count = 1
j = P3
1≤2 ✓
5≥1 ✓

Check others.
P1=(3,4)
x=3 between [1,2] ?
No
P2=(5,2)
No
P4=(4,3)
No
Valid.
count = 2
j = P4

1≤4 ✓
5≥3 ✓

Check others.
P1=(3,4)
3 between [1,4] ✓
4 between [3,5] ✓

Inside rectangle.
Invalid.
count = 2
i = P1
j = P2
3≤5 ✓
4≥2 ✓
Check others.

P4=(4,3)
4 between [3,5] ✓
3 between [2,4] ✓
Inside rectangle.
Invalid.
count = 2
j = P3
3≤2 ✗
Not upper-left.
Skip.
j = P4
3≤4 ✓
4≥3 ✓

Check others.
No point inside.
Valid.
count = 3
i = P2
j = P3
5≤2 ✗

Skip.

j = P4
5≤4 ✗

Skip.
i = P3
j = P4
2≤4 ✓
1≥3 ✗

Skip.
Final answer = 3
program logic -
for every point i:
for every point j:

```
    if i and j can form upper-left pair:
        assume valid = true
        check every third point k
        if point k lies inside rectangle:
            valid = false
        if valid:
            count++
```

How does code check inside rectangle?
if(
x >= x1 &&
x <= x2 &&
y >= y2 &&
y <= y1
)

This means:
x is horizontally inside rectangle
and
y is vertically inside rectangle
So point lies inside or on border.

Time Complexity = O(n³)
Outer loop = n
Second loop = n
Third loop = n

Total = O(n³)
Space Complexity = O(1)


*/
