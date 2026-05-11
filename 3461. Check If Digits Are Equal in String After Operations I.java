class Solution {
    public boolean hasSameDigits(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        while(n > 2) {
            for(int i = 0; i < n - 1; i++) {
                arr[i] = (char)(((arr[i]-'0' + arr[i+1]-'0') % 10) + '0');
            }
            n--;
        }
        
        return arr[0] == arr[1];
    }
}

/*
1. PROBLEM :-
   You are given a string s containing only digits.
You must repeatedly perform the following operation until only 2 digits remain:
For every adjacent pair of digits:
newDigit = (currentDigit + nextDigit) % 10
Replace the string with these newly formed digits.

Goal: Check whether the final remaining two digits are equal.
Return:
* true → if both digits are same
* false → otherwise

---

Core Idea:
Convert string into a character array.
Keep reducing the array size.
At every step:
* Take adjacent digits
* Add them
* Take modulo 10
* Replace current position with new digit
Continue until only 2 digits remain.
Finally compare:
arr[0] == arr[1]
If equal → return true
Else → return false

---

How the Code Works:
Step 1:
Convert string to char array.
Example: "3902"
becomes: ['3','9','0','2']

---

Step 2:
Run loop until only 2 digits remain.
Condition: while(n > 2)

---

Step 3:
Generate new digits.

Formula:

(arr[i]-'0' + arr[i+1]-'0') % 10
Convert digit character into integer.
Example: '3' → 3
Then convert back to character.

---

Step 4:
Reduce length by 1.
Because after every operation:
new length = old length - 1

---
Time Complexity: O(n²)
Because for every reduction we traverse the string again.
Space Complexity: O(1)
Only same array is reused.
---

2. EXAMPLE :-
Input: s = "3902"
Initial: 3902

---

Step 1:
(3+9)%10 = 2
(9+0)%10 = 9
(0+2)%10 = 2

New string: 292
---

Step 2:

(2+9)%10 = 1
(9+2)%10 = 1
New string: 11

---

Final digits:
1 and 1
Both are equal.

Output: true

---

3. DRY RUN :-
Example (custom tough case)
Input:
s = "583741"

---

Initial: 583741
n = 6

---

Iteration 1:
(5+8)%10 = 3
(8+3)%10 = 1
(3+7)%10 = 0
(7+4)%10 = 1
(4+1)%10 = 5

New array: 31015
n = 5

---

Iteration 2:
(3+1)%10 = 4
(1+0)%10 = 1
(0+1)%10 = 1
(1+5)%10 = 6

New array: 4116
n = 4

---

Iteration 3:
(4+1)%10 = 5
(1+1)%10 = 2
(1+6)%10 = 7

New array: 527
n = 3
---

Iteration 4:
(5+2)%10 = 7
(2+7)%10 = 9

New array: 79
n = 2
---

Final digits:
7 and 9
7 ≠ 9
Output: false

*/
