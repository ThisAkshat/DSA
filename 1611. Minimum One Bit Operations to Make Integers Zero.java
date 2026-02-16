class Solution {
   public int minimumOneBitOperations(int n) {
    int result = 0;
    while (n > 0) {
        result ^= n;
        n >>= 1;
    }
    return result;
}
}

/*

* What the problem is :-

You’re given a number n.
You can flip bits only using very weird rules.
But LeetCode 1611 is secretly asking: “What is the minimum number of operations to convert n to 0 if the bits must be flipped in Gray Code order?”
This problem is exactly: Convert a Gray-code number to its normal binary index.
And that’s what this code does.

**********************************************************************************************************************

* Dry Run — n = 3
n = 3 = 11₂

Loop:

Step	     n     	result
start	     11	      0
1	         11     	0 ^ 11 = 11
2	         01	      11 ^ 01 = 10
3	         00	      stop
result = 10₂ = 2

Answer = 2 ✔

* Dry Run — n = 6
n = 6 = 110₂

Step	    n    	result
start	   110	   0
1	       110	  110
2	       011	  110 ^ 011 = 101
3	       001	  101 ^ 001 = 100
4	       000  	stop
100₂ = 4

Answer = 4 ✔

*************************************************************************************************************************

Example 1:

Input: n = 3
Output: 2
Explanation: The binary representation of 3 is "11".
"11" -> "01" with the 2nd operation since the 0th bit is 1.
"01" -> "00" with the 1st operation.

  */
