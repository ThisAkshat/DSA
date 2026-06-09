class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for(int i=0;i<n;i++){;
            int aliceX = points[i][0] ,aliceY = points[i][1];
            for(int j=0; j<n; j++){
                if( i==j ) continue;
                int bobX = points[j][0], bobY = points[j][1];

                if(aliceX <= bobX && aliceY >= bobY){
                    boolean valid = true;
                    for(int k = 0; k<n; k++){
                        if(k==i || k==j) continue;
                        int x = points[k][0], y = points[k][1];
                        if(aliceX <= x && x <= bobX && bobY <= y && y <= aliceY){
                            valid = false;
                            break;
                        }
                    }
                    if(valid) ans++;
                }
            }
        }
        return ans;
    }
}

/*
problem -
This is exactly the same logic as Problem 3025.
The only difference is constraints.

3025:
n ≤ 50
3027:
n ≤ 1000

Your code is still the brute-force solution from 3025.
For every Alice point:
For every Bob point:
For every third point:
Check whether that point lies inside the rectangle.
So complexity becomes O(n³).

With n = 1000:
1000 × 1000 × 1000
= 10⁹ operations
Too large.
That's why 3027 is Hard.
The question is still asking:
Count all pairs (Alice, Bob) such that:
Alice is upper-left of Bob
aliceX ≤ bobX
aliceY ≥ bobY
and no third point lies inside or on the border of the rectangle.

example -
points = [[6,2],[4,4],[2,6]]
Let's name them:
P0 = (6,2)
P1 = (4,4)
P2 = (2,6)
Possible pairs:
P2 → P1
2 ≤ 4 ✓
6 ≥ 4 ✓
Rectangle:

(2,6) ---- (4,6)
|           |
|           |
(2,4) ---- (4,4)

No point inside.

Valid.

count = 1
P1 → P0
4 ≤ 6 ✓
4 ≥ 2 ✓

Rectangle:
(4,4) ---- (6,4)
|           |
|           |
(4,2) ---- (6,2)
No point inside.
Valid.
count = 2
P2 → P0
2 ≤ 6 ✓
6 ≥ 2 ✓
Rectangle:

(2,6) ------------ (6,6)
|                    |
|      (4,4)         |
|                    |
(2,2) ------------ (6,2)

P1 lies inside.
Invalid.
Answer = 2

dry run -
points = [[1,8],[2,6],[3,7],[5,3],[7,1]]

Names:
P0=(1,8)
P1=(2,6)
P2=(3,7)
P3=(5,3)
P4=(7,1)

ans = 0
i = P0
j = P1
1≤2 ✓
8≥6 ✓

Check others:
P2=(3,7)
x=3 not in [1,2]
P3=(5,3)
outside
P4=(7,1)
outside
Valid.
ans=1
j = P2
1≤3 ✓
8≥7 ✓

Check others:
P1=(2,6)
x=2 in [1,3] ✓
y=6 in [7,8] ✗
outside
P3 outside
P4 outside
Valid.
ans=2
j = P3
1≤5 ✓
8≥3 ✓

Check others:
P1=(2,6)
2 in [1,5] ✓
6 in [3,8] ✓

Inside rectangle.
Invalid.
ans=2
j = P4
1≤7 ✓
8≥1 ✓

Check others:
P1=(2,6)
Inside.
Invalid.
ans=2
i = P1
j = P3
2≤5 ✓
6≥3 ✓
Check:
P2=(3,7)
7 not in [3,6]
Outside.
P4=(7,1)
Outside.

Valid.
ans=3
j = P4
2≤7 ✓
6≥1 ✓

Check:
P3=(5,3)
Inside rectangle.
Invalid.
ans=3
i = P2
j = P3
3≤5 ✓
7≥3 ✓
Check:

No point inside.
Valid.
ans=4
j = P4
3≤7 ✓
7≥1 ✓
P3=(5,3)
Inside rectangle.
Invalid.
ans=4
i = P3
j = P4
5≤7 ✓
3≥1 ✓

No point inside.
Valid.

ans=5
Final answer = 5
program logic -
for every point i:

```
treat it as Alice
for every point j:
    treat it as Bob
    if Alice is upper-left of Bob:
        valid = true
        for every point k:
            if k lies inside rectangle:
                valid = false
                break
        if valid:

            ans++
```
Inside rectangle check:
aliceX <= x <= bobX
and
bobY <= y <= aliceY
If both are true, then point lies inside or on border of rectangle.
So pair becomes invalid.

Time Complexity:
O(n³)
Space Complexity:

O(1)
Why this code passes 3025 but not 3027?

3025:
n ≤ 50
50³ = 125000
Easy.
3027:
n ≤ 1000
1000³ = 1,000,000,000
Too many operations → TLE.

*/
