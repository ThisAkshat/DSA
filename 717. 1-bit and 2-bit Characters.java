class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int count1 = 0;
        for(int i=n-2;i>=0 && bits[i] == 1; i--){
            count1++;
        }
        return count1%2 == 0 ? true : false;
    }
}


/*
PROBLEM -> We have two special characters:

* The first character can be represented by one bit 0.
* The second character can be represented by two bits (10 or 11).

Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.

**************************************************************************************************************************

Example 1:

Input: bits = [1,0,0]
Output: true
Explanation: The only way to decode it is two-bit character and one-bit character.
So the last character is one-bit character.

Example 2:
Input: bits = [1,1,1,0]
Output: false
Explanation: The only way to decode it is two-bit character and two-bit character.
So the last character is not one-bit character.

**************************************************************************************************************************

 . Dry run for example: bits = [1,1,1,0]

n = 4
count1 = 0

loop starts from i = n-2 = 2

Step 1: i = 2
bits[2] = 1 → condition true
count1 = 1

Step 2: i = 1
bits[1] = 1 → condition true
count1 = 2

Step 3: i = 0
bits[0] = 1 → condition true
count1 = 3

Step 4: i = -1 → loop stops

now check
count1 % 2 == 0
3 % 2 = 1 → false

return false
*/
