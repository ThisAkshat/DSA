class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int X=0;
        for(String op : operations){
            X+=(op.charAt(1) == '+') ? 1 :-1;
        }
        return X;
    }
}
/*
1. PROBLEM :-
   You are given an array operations containing strings.

Each operation is one of:
"++X" or "X++" → increment X by 1
"--X" or "X--" → decrement X by 1

Initial value: X = 0

Goal:
Return the final value of X after performing all operations.
---

Core Idea:
Check the middle character of each string:
* If it is '+' → increment
* If it is '-' → decrement

Because:
"++X" → op.charAt(1) = '+'
"X++" → op.charAt(1) = '+'
"--X" → op.charAt(1) = '-'
"X--" → op.charAt(1) = '-'

So:
X += (op.charAt(1) == '+') ? 1 : -1
---

Time Complexity: O(n)
Space Complexity: O(1)
---

2. EXAMPLE :-
Input
operations = ["--X","X++","X++"]

Start:
X = 0
---

"--X" → decrement
X = -1
---

"X++" → increment
X = 0
---

"X++" → increment
X = 1

Final Output = 1
---

3. DRY RUN :-
Example (custom)
operations = ["X++","--X","++X","X--","++X"]
Start:
X = 0
---

"X++" → +1
X = 1
---

"--X" → -1
X = 0
---

"++X" → +1
X = 1
---

"X--" → -1
X = 0
---

"++X" → +1
X = 1
---

Final Answer = 1


*/
