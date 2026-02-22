class Solution {
    public int maxOperations(String s) {
        int count = 0;
        int prefix = 0;
        int n = s.length();
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '0'){
                if(i==n-1 || s.charAt(i+1)=='1'){ // 01010
                    count = count+prefix;
                }
            }else{
                prefix++;
            }
        }
    return count;
    }
}

/*
The problem is saying this:
You are given a binary string made of 0s and 1s.
You are allowed to repeatedly do this operation:
Pick an index i such that:
* s[i] is '1'
* s[i + 1] is '0'
So basically you find a pattern "10".
When you choose that index:
You take that '1' and move it to the right,
until it either:
* reaches the end of the string, or
* hits another '1'
While moving, it passes over zeros and those zeros shift left.
So effectively, you are pushing a '1' right across consecutive zeros.
You can keep doing this as long as there exists a "10" pattern somewhere.

Your goal is:
Find the maximum number of operations you can perform.
In simple words:
How many times can you move a '1' to the right by choosing a valid "10" position before no more moves are possible?


***********************************************************************************************************************

Example 1:
Input: s = "1001101"
Output: 4

Explanation:
We can perform the following operations:

Choose index i = 0. The resulting string is s = "0011101".
Choose index i = 4. The resulting string is s = "0011011".
Choose index i = 3. The resulting string is s = "0010111".
Choose index i = 2. The resulting string is s = "0001111".

Example 2:
Input: s = "00111"
Output: 0

***********************************************************************************************************************

Dry run for:

s = "1001101"

Initialize:
count = 0
prefix = 0
n = 7

---

i = 0
s[0] = '1'
prefix = 1

---

i = 1
s[1] = '0'
next is '0'
condition fails
nothing happens

---

i = 2
s[2] = '0'
next is '1'
condition true

count = count + prefix
count = 0 + 1 = 1

---

i = 3
s[3] = '1'
prefix = 2

---

i = 4
s[4] = '1'
prefix = 3

---

i = 5
s[5] = '0'
next is '1'
condition true

count = count + prefix
count = 1 + 3 = 4

---

i = 6
s[6] = '1'
prefix = 4

---

End loop

Return count = 4


*/
