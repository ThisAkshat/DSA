class Solution {
    public long minOperations(int[][] queries) {
        long total = 0;
        long[] pow4 = new long[20];
        pow4[0] = 1;
        for (int t = 1; t < 20; t++) pow4[t] = pow4[t - 1] * 4;

        for (int[] q : queries) {
            long l = q[0], r = q[1];
            long sumLives = 0;
            for (int k = 1; k < 20; k++) {
                long start = pow4[k - 1];
                long end = pow4[k] - 1;
                if (start > r) break;
                long lo = Math.max(l, start);
                long hi = Math.min(r, end);
                if (hi >= lo) sumLives += (hi - lo + 1) * k;
            }
            total += (sumLives + 1) / 2;
        }
        return total;
    }
}

/*
problem - For every query [l,r], create an array:
[l,l+1,l+2,...,r]
In one operation you can choose any two numbers.

Both selected numbers become:
floor(number/4)
You have to make every element become 0.
For every query, find the minimum operations.
Finally return the sum of all queries.
Important observation:
Every number has its own "life".

Life = how many times we must divide by 4 until it becomes 0.
Example:
1 → 0
Life = 1
2 → 0
Life = 1
3 → 0
Life = 1
4 → 1 → 0
Life = 2
5 → 1 → 0
Life = 2
16 → 4 → 1 → 0
Life = 3
If a number has life = 3, it must participate in 3 operations.

Key pattern:
[1,3] need 1 division
[4,15] need 2 divisions
[16,63] need 3 divisions
[64,255] need 4 divisions

In general:
[4^(k-1),4^k-1]
all numbers need k divisions.
Now suppose total lives needed for a query is:
sumLives
One operation reduces life of 2 numbers together.
So:
operations = ceil(sumLives/2)
Formula:
(sumLives+1)/2
This is exactly what the code does.
Code understanding:
long[] pow4 = new long[20];

Stores:
1
4
16
64
256
...

for (int t = 1; t < 20; t++)
pow4[t] = pow4[t - 1] * 4;
Generate powers of 4.

For every query:
long l = q[0], r = q[1];
Take range.
long sumLives = 0;
Total lives needed.
for (int k = 1; k < 20; k++)
Check every life level.
long start = pow4[k - 1];
Beginning of this group.
long end = pow4[k] - 1;
Ending of this group.
For example:
k=1
1 to 3
k=2
4 to 15

k=3
16 to 63
long lo = Math.max(l,start);
long hi = Math.min(r,end);
Take overlap between query and current group.
if(hi>=lo)
There is overlap.
sumLives += (hi-lo+1)*k;
Number of elements × their life.

Finally:
total += (sumLives+1)/2;
Add operations.

example - queries=[[1,2],[2,4]]
Query 1:
[1,2]

Array:
[1,2]
1 needs 1 life
2 needs 1 life
sumLives=2
operations=(2+1)/2
=1

Query 2:
[2,4]

Array:
[2,3,4]
2 →1 life
3 →1 life
4 →2 lives
sumLives=4
operations=(4+1)/2
=2
Total:
1+2
=3
dry run - queries=[[2,6]]

Array:
[2,3,4,5,6]

Step 1:
Find lives.
2 →1
3 →1
4 →2
5 →2
6 →2

sumLives:
1+1+2+2+2 =8

operations:
(8+1)/2
=4
Now dry run exactly like operations:

Initial:
[2,3,4,5,6]

Operation 1:
Choose 2 and 5
[0,3,4,1,6]

Operation 2:
Choose 4 and 6
[0,3,1,1,1]

Operation 3:
Choose 3 and 1
[0,0,0,1,1]
Operation 4:
Choose 1 and 1
[0,0,0,0,0]
Total operations = 4

Answer = 4.
*/
