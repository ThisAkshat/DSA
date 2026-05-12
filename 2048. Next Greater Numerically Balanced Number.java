class Solution {
    public int nextBeautifulNumber(int n) {
        for(int num = n+1; num<=1224444;num++) {
            if(balanced(num)){
                return num;
            }
        }
        return -1;
    }
    private boolean balanced (int num){
        int[] freq = new int[10];
        while(num>0){
            int digit = num%10;
            freq[digit]++;
            num = num/10;   
        }
        for(int d=0;d<10;d++){
            if(freq[d] != 0 && freq[d] != d)
            return false;
        }
        return true;
    }
}

/*
1. PROBLEM :-
   A number is called numerically balanced if every digit appears exactly the same number of times as its value.
Example:
22 → valid
Digit 2 appears exactly 2 times.

1333 → valid
Digit 1 appears 1 time
Digit 3 appears 3 times

122 → invalid
Digit 1 appears 1 time ✔
Digit 2 appears 2 times ✔
Actually valid.

1022 → invalid
Digit 0 appears 1 time, but digit 0 should appear 0 times.

Goal:
Find the smallest numerically balanced number strictly greater than n.

---

Core Idea:
Start checking from:
n + 1
For every number:
* Count frequency of every digit
* Verify if every appearing digit follows:

frequency == digit value
If yes:
Return that number immediately.
Since the maximum possible answer is limited, we only search till:
1224444
This is the largest numerically balanced number within constraints.

---

How the Code Works:
Step 1:
Loop from:
n + 1
to
1224444

---

Step 2:
For each number call:
balanced(num)

---

Step 3:
Count frequency of digits.
Example: 1333

Frequency array:
1 → 1 time
3 → 3 times

---

Step 4:
Validate condition.
Check: if(freq[d] != 0 && freq[d] != d)

Meaning: If digit exists but its count is not equal to digit value → invalid

Example:
333
Digit 3 appears 3 times ✔
Example:
331
Digit 3 appears 2 times ❌
So invalid.

---

Time Complexity: O(M × D)
Where:
M = numbers checked
D = number of digits (maximum ~7)
Worst case is still efficient because upper bound is small.
Space Complexity: O(1)
Only frequency array of size 10 is used.

---

2. EXAMPLE :-
Input:
n = 1000
We start checking from:
1001

---

1001
Digit frequency:
1 → 2 times ❌
Not balanced.

---

1022
1 → 1 time ✔
0 → 1 time ❌
2 → 2 times ✔
Invalid.

---

1333
1 → 1 time ✔
3 → 3 times ✔
Balanced.
Output:
1333

---

3. DRY RUN :-

Example (custom tough case)

Input:

n = 2500
Start checking:
2501, 2502, 2503...

---

3000
3 → 1 time ❌
Invalid.

---

3133
Digit frequency:
1 → 1 time ✔
3 → 3 times ✔
Balanced.
Since:
3133 > 2500
Return:
3133

---

Internal Working of balanced(3133)
Initial:
num = 3133
freq = [0,0,0,0,0,0,0,0,0,0]

---

Step 1:
digit = 3
freq[3]++
freq[3] = 1
num = 313

---

Step 2:
digit = 3
freq[3] = 2
num = 31

---

Step 3:
digit = 1
freq[1] = 1
num = 3

---

Step 4:
digit = 3
freq[3] = 3
num = 0

---

Final frequency:
1 → 1
3 → 3

Validation passes.
Return:true

*/
