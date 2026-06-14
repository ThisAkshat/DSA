class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for(int k=1;k<=60;k++){
            long val = (long) num1-(long) k*num2;
            if(val<k) continue;
            if(Long.bitCount(val) <= k) return k;
        }
        return -1;
    }
}

/*
problem - You are given num1 and num2.
In one operation, choose any i from 0 to 60 and subtract:
2^i + num2
from num1.
You need to make num1 exactly 0 using the minimum number of operations.
Return minimum operations, otherwise return -1.
Key observation:
Suppose we perform exactly k operations.
Each operation subtracts:

(2^i1 + num2)
+
(2^i2 + num2)
+
...
+
(2^ik + num2)
Total subtraction becomes:
(2^i1 + 2^i2 + ... + 2^ik) + k × num2
To make num1 become 0:
num1 = (sum of powers of 2) + k × num2

Rearrange:
val = num1 - k × num2
Now val must be representable as a sum of exactly k powers of 2.
BitCount tells minimum powers of 2 needed.
Example:
13 = 1101₂
BitCount = 3
Meaning:
13 = 8 + 4 + 1
Needs at least 3 powers of 2.
Also:
13 = 4 + 4 + 2 + 2 + 1
Can be split into more than 3 powers.
Therefore for k operations:

bitCount(val) ≤ k ≤ val
If true, answer = k.
Code meaning:
for(int k=1;k<=60;k++)
Try every possible number of operations.
long val = (long) num1-(long) k*num2;
Compute:
val = num1 − k×num2
if(val<k) continue;
Need at least k value because smallest possible sum of k powers is:
1+1+1+...+1 = k
if(Long.bitCount(val) <= k)
Check whether val can be split into exactly k powers of 2.

If yes:
return k;
First valid k is minimum answer.
example - num1 = 3, num2 = -2
k = 1
val = 3 - (1×-2)
val = 5
bitCount(5)
101₂
= 2
2 ≤ 1 ?
No
k = 2
val = 3 - (2×-2)
val = 7
bitCount(7)
111₂
= 3
3 ≤ 2 ?
No
k = 3
val = 3 - (3×-2)
val = 9
1001₂
bitCount = 2
2 ≤ 3
and
9 ≥ 3
Valid
Answer = 3
dry run - num1 = 20, num2 = 1
k = 1
val = 20 - 1
= 19
bitCount(19)
10011₂
= 3
3 ≤ 1 ?
No
k = 2
val = 20 - 2
= 18
10010₂
bitCount = 2
2 ≤ 2
18 ≥ 2
Valid
Return 2
Why?
val = 18
18 = 16 + 2
= 2^4 + 2^1
Exactly 2 powers of 2.
Operation 1:
subtract (16 + 1) = 17
Operation 2:
subtract (2 + 1) = 3
Total subtraction:
17 + 3 = 20
num1 becomes 0.
Answer = 2.


*/
