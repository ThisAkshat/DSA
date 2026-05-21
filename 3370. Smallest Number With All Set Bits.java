class Solution {
    public int smallestNumber(int n) {
        int result =1;
        while(result < n){
            result = 2*result+1;
        }
        return result;
    }
}

/*
problem - Smallest Number With All Set Bits
What is this problem saying?
You are given a positive integer n.
You need to find the smallest number x such that:
x >= n
and
the binary representation of x contains only set bits (only 1s).
Set bits means binary should contain only 1.

Examples of valid numbers:
1 → binary = 1
3 → binary = 11
7 → binary = 111
15 → binary = 1111
31 → binary = 11111

Examples of invalid numbers:
5 → binary = 101 (contains 0)
10 → binary = 1010
12 → binary = 1100
So basically, we need the smallest number greater than or equal to n whose binary representation has only 1s.
example - n = 10
We need the smallest number >= 10.

Check:
10 → 1010 → invalid
11 → 1011 → invalid
12 → 1100 → invalid
13 → 1101 → invalid
14 → 1110 → invalid
15 → 1111 → valid

Answer = 15
dry run - tough example
n = 26

Initially:
result = 1

Check:
result < n
1 < 26 → true

Step 1:
result = 2 * result + 1
result = 2 * 1 + 1
result = 3
Binary of 3 = 11

Check:
3 < 26 → true

Step 2:
result = 2 * 3 + 1
result = 7
Binary of 7 = 111

Check:
7 < 26 → true

Step 3:
result = 2 * 7 + 1
result = 15
Binary of 15 = 1111

Check:
15 < 26 → true

Step 4:
result = 2 * 15 + 1
result = 31
Binary of 31 = 11111

Check:
31 < 26 → false
Loop stops.
Return 31
Final answer = 31

How this code thinks:
Numbers having all set bits follow this pattern:
1
3
7
15
31
63
127

Pattern:
1
2 × 1 + 1 = 3
2 × 3 + 1 = 7
2 × 7 + 1 = 15
2 × 15 + 1 = 31

Why?
Because multiplying by 2 shifts binary left and adding 1 adds another set bit.

Example:
7 = 111
2 × 7 = 1110
1110 + 1 = 1111 = 15
So the code keeps generating numbers with all 1s in binary until it becomes greater than or equal to n.
Time Complexity: O(log n)
Space Complexity: O(1)


*/
