class Solution {
    public int totalMoney(int n) {
        int result = 0;
        int M_m = 1;
        while(n>0){
            int money = M_m;
            for(int i=1;i<=Math.min(n,7);i++){
                result = result + money;
                money++;
            }
            n = n-7;
            M_m++;
        }
        return result;
    }
}
/*
1. PROBLEM :-
   Hercy saves money every day following a pattern:

Week 1:
Monday = 1
Tuesday = 2
Wednesday = 3
Thursday = 4
Friday = 5
Saturday = 6
Sunday = 7

Week 2:
Monday starts with one extra dollar than previous Monday.

Monday = 2
Tuesday = 3
Wednesday = 4
Thursday = 5
Friday = 6
Saturday = 7
Sunday = 8

Week 3:
Monday = 3
Tuesday = 4
Wednesday = 5
...

Goal:
Find the total money saved after n days.

---

Core Idea:
Simulate week by week.
For each week:
* Monday amount starts from M_m
* Every next day increases by 1
* Add money for maximum 7 days

If fewer than 7 days remain:
Only process remaining days.

After finishing one week:
* Reduce n by 7
* Increase Monday money by 1

---

How the Code Works:
Variable:
result → stores total money
M_m → Monday money for current week
Initially: M_m = 1

---

While days remain: while(n > 0)

---

Start week: money = M_m
For each day: Add current money
Increase money by 1
Loop only for: min(n, 7)
Because last week may not be complete.

---

After week ends: n = n - 7
Move to next Monday: M_m++

---

Time Complexity: O(n)
At most all days are visited once.
Space Complexity: O(1)
Only variables are used.

---

2. EXAMPLE :-
Input: n = 10

---

Week 1:
1 + 2 + 3 + 4 + 5 + 6 + 7 = 28
Remaining: 10 - 7 = 3 days

---

Week 2:
Monday = 2
Only 3 days left:
2 + 3 + 4 = 9

---

Total:
28 + 9 = 37
Output: 37

---

3. DRY RUN :-
Example (custom tough case)

Input: n = 15

Initial: result = 0
M_m = 1

---

Week 1: money = 1

Add: 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28
result = 28
Remaining: n = 15 - 7 = 8
Next Monday:
M_m = 2

---

Week 2:
money = 2

Add: 2 + 3 + 4 + 5 + 6 + 7 + 8 = 35

result = 28 + 35 = 63

Remaining:n = 8 - 7 = 1
Next Monday: M_m = 3

---

Week 3: Only 1 day left
Add: 3
result = 63 + 3 = 66

Remaining: n = 0
---

Final Answer: 66




*/
