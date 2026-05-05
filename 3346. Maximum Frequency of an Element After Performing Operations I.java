class Solution {
    public int maxFrequency(int[] nums, int k, int ops) {
        int max = Arrays.stream(nums).max().getAsInt();
        int n = max + k + 2;
        int[] f = new int[n];
        for (int x : nums) f[x]++;

        int[] pre = new int[n];
        pre[0] = f[0];
        for (int i = 1; i < n; i++) pre[i] = pre[i - 1] + f[i];

        int ans = 0;
        for (int t = 0; t < n; t++) {
            if (f[t] == 0 && ops == 0) continue;
            int l = Math.max(0, t - k), r = Math.min(n - 1, t + k);
            int tot = pre[r] - (l > 0 ? pre[l - 1] : 0);
            int adj = tot - f[t];
            int val = f[t] + Math.min(ops, adj);
            ans = Math.max(ans, val);
        }
        return ans;
    }
}

/*
1. PROBLEM :-
   You are given:

* nums array
* k → allowed change range
* ops → number of operations

Operation:
Pick an unused index i and add any value in [-k, k] to nums[i].

Goal:
Maximize the frequency of any single value after at most ops operations.
---

Core Idea: Each number x can be changed to any value in:
[x - k , x + k]

We try to make as many elements equal to some target value t.

For each possible target t:

1. Count how many elements already equal to t → f[t]
2. Count how many elements can be converted to t
   (i.e., elements in range [t-k, t+k])
Let:
tot = total elements in [t-k, t+k]
adj = tot - f[t] → elements we can convert

We can only use at most ops operations:

So final frequency:
f[t] + min(ops, adj)
Take maximum over all t.
---

Prefix sum is used to quickly count elements in range.

---

Time Complexity: O(n + max_value)
Space Complexity: O(max_value)
---

2. EXAMPLE :-
Input
nums = [1,4,5]
k = 1
ops = 2
Step 1: Frequency
1 → 1
4 → 1
5 → 1
---

Try target t = 4
Range:
[3,5]
Elements in range:
4,5 → total = 2
f[4] = 1
adj = 2 - 1 = 1
We can convert 1 element
Final = 1 + 1 = 2

---

Try target t = 5
Range:
[4,6]

Elements:
4,5 → total = 2
f[5] = 1
adj = 1
Final = 2
---

Maximum = 2
Output = 2
---

3. DRY RUN :-
Example (custom)
nums = [2,3,7,8,9]
k = 2
ops = 2
---

Step 1: Frequency
2 → 1
3 → 1
7 → 1
8 → 1
9 → 1
---

Try t = 3
Range:
[1,5]

Elements:
2,3 → total = 2
f[3] = 1
adj = 1
Final = 1 + 1 = 2
---

Try t = 7
Range:
[5,9]

Elements:
7,8,9 → total = 3
f[7] = 1
adj = 2
We can use ops = 2
Final = 1 + 2 = 3
---

Try t = 8
Range:
[6,10]

Elements:
7,8,9 → total = 3
f[8] = 1
adj = 2
Final = 3
---

Maximum = 3
Final Answer = 3


*/
