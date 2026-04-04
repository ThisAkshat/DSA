class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        long[] finishPrev = new long[n];
        long[] timeCol = new long[n];
        finishPrev[0] = (long) skill[0] * mana[0];
        for(int i = 1;i<n;i++)
            finishPrev[i] = finishPrev[i-1]+(long) skill[i]*mana[0];
            for(int j =1;j<m;j++){
                for(int i=0;i<n;i++)
                timeCol[i] = (long) skill[i] * mana[j];

                long prefix = 0, need =0;
                for(int i=0;i<n;i++){
                    need = Math.max(need, finishPrev[i] - prefix);
                    prefix += timeCol[i];
            }
            long sum =0;
            for(int i =0;i<n;i++){
                sum += timeCol[i];
                finishPrev[i] = need+sum;
            }
        }
        return finishPrev[n-1];
    }
}

/*
1. PROBLEM :-
   You are given two arrays:

skill → represents efficiency of each wizard
mana → represents requirement of each potion

There are:
n wizards
m potions

Each potion must pass through all wizards in order (0 → n-1).

Time taken by wizard i on potion j:
time[i][j] = skill[i] * mana[j]

Important constraint:
A potion must move to the next wizard immediately after finishing the current one. So timing must be perfectly synchronized.

Goal:
Find the minimum total time required to complete all potions.

Core idea:
This is a scheduling problem similar to flow of jobs in sequence.

We maintain:
finishPrev[i] → time when wizard i finishes previous potion

For each new potion:
We calculate the earliest time we can start so that:

* No wizard is idle waiting
* No potion is delayed

We compute a "need" value to shift the start time of the current potion so all constraints are satisfied.

Time Complexity: O(n * m)
Space Complexity: O(n)

2. EXAMPLE :-

Input
skill = [1,1,1]
mana = [1,1,1]

Step-by-step:

Potion 0

Wizard 0 → 1
Wizard 1 → 2
Wizard 2 → 3

finishPrev = [1,2,3]

---

Potion 1
Each wizard takes 1 time

We try to start immediately, but must sync with previous finish times.

Final schedule becomes:

Wizard 0 → starts at 1, ends at 2
Wizard 1 → starts at 2, ends at 3
Wizard 2 → starts at 3, ends at 4

finishPrev = [2,3,4]

---

Potion 2

Wizard 0 → starts at 2, ends at 3
Wizard 1 → starts at 3, ends at 4
Wizard 2 → starts at 4, ends at 5

finishPrev = [3,4,5]
Final Answer = 5



3. DRY RUN :-
Example (custom tough case)
skill = [2,3,1]
mana = [3,2,4]

n = 3, m = 3

---

Potion 0

Time taken:
Wizard 0 → 2*3 = 6
Wizard 1 → 3*3 = 9
Wizard 2 → 1*3 = 3

Cumulative finish:

finishPrev[0] = 6
finishPrev[1] = 6 + 9 = 15
finishPrev[2] = 15 + 3 = 18

---

Potion 1

TimeCol:
Wizard 0 → 2*2 = 4
Wizard 1 → 3*2 = 6
Wizard 2 → 1*2 = 2

Now compute need:
prefix = 0

i = 0
need = max(0, 6 - 0) = 6
prefix = 4

i = 1
need = max(6, 15 - 4 = 11) = 11
prefix = 10

i = 2
need = max(11, 18 - 10 = 8) = 11
prefix = 12

So need = 11

Now update finishPrev:
i = 0 → 11 + 4 = 15
i = 1 → 15 + 6 = 21
i = 2 → 21 + 2 = 23
finishPrev = [15,21,23]

---

Potion 2
TimeCol:
Wizard 0 → 2*4 = 8
Wizard 1 → 3*4 = 12
Wizard 2 → 1*4 = 4

Compute need:
prefix = 0

i = 0
need = max(0, 15 - 0) = 15
prefix = 8

i = 1
need = max(15, 21 - 8 = 13) = 15
prefix = 20

i = 2
need = max(15, 23 - 20 = 3) = 15
prefix = 24

So need = 15
Update finishPrev:

i = 0 → 15 + 8 = 23
i = 1 → 23 + 12 = 35
i = 2 → 35 + 4 = 39
---

Final Answer:
39


*/

