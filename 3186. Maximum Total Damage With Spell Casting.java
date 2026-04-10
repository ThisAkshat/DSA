class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Long> map =new HashMap<>();
        for(int p: power) map.put(p, map.getOrDefault(p, 0L)+p);
        List<Integer> vals = new ArrayList<>(map.keySet());
        Collections.sort(vals);
        int n = vals.size();
        long[] dp = new long[n];
        dp[0] = map.get(vals.get(0));
        for(int i = 1;i<n;i++){
            long take = map.get(vals.get(i));
            int j = i-1;
            while(j>=0 && vals.get(i) - vals.get(j)<= 2) j--;
            if(j>=0) take = take+dp[j];
            dp[i] = Math.max(dp[i-1], take);
        }
        return dp[n-1];
    }
}

/*
1. PROBLEM :-
   You are given an array power where each value represents the damage of a spell. You can pick spells to maximize total damage.
Restriction:
If you pick a spell with damage x, then you cannot pick any spell with damage:
x - 2, x - 1, x + 1, x + 2

Also:

* You can use each spell only once.
* Multiple spells can have the same damage.

Goal:
Find the maximum total damage you can achieve.

Core idea:
This is similar to a variation of the “House Robber” problem.

Steps:-
1. First, group same values together.
   For example:
   power = [1,1,3,4] → convert to:
   1 → 2 (1+1)
   3 → 3
   4 → 4

2. Sort unique values.

3. Use DP:
   For each value:
   * Either skip it
   * Or take it and add best previous valid value (difference > 2)

4. Store result in dp array.
Time Complexity: O(n log n)
Space Complexity: O(n)

2. EXAMPLE :-
Input
power = [1,1,3,4]
Step 1: Group values
1 → 2
3 → 3
4 → 4
Sorted values:
[1,3,4]

Step 2: DP
i = 0
dp[0] = 2

---

i = 1 (value = 3)
Check previous values:
3 - 1 = 2 → not allowed
So:
take = 3
skip = dp[0] = 2
dp[1] = max(2,3) = 3

---

i = 2 (value = 4)
Check previous:
4 - 3 = 1 → not allowed
4 - 1 = 3 → allowed

So:
take = 4 + dp[0] = 4 + 2 = 6
skip = dp[1] = 3

dp[2] = 6
Final Answer = 6

---

3. DRY RUN :-
Example (custom tough case)
power = [2,2,3,3,3,6,8,9]

Step 1: Group values
2 → 4
3 → 9
6 → 6
8 → 8
9 → 9
Sorted values:
[2,3,6,8,9]

---

i = 0
dp[0] = 4

---

i = 1 (value = 3)
3 - 2 = 1 → not allowed
take = 9
skip = 4
dp[1] = 9

---

i = 2 (value = 6)
6 - 3 = 3 → allowed
take = 6 + dp[1] = 6 + 9 = 15
skip = 9
dp[2] = 15

---

i = 3 (value = 8)
8 - 6 = 2 → not allowed
8 - 3 = 5 → allowed
take = 8 + dp[1] = 8 + 9 = 17
skip = 15
dp[3] = 17

---

i = 4 (value = 9)
9 - 8 = 1 → not allowed
9 - 6 = 3 → allowed

take = 9 + dp[2] = 9 + 15 = 24
skip = 17
dp[4] = 24
---
Final Answer = 24


*/
