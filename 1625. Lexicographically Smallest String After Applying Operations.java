class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        String ans = s;
        q.offer(s);
        vis.add(s);
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (cur.compareTo(ans) < 0) ans = cur;
            String added = add(cur, a);
            String rotated = rotate(cur, b);
            if (vis.add(added)) q.offer(added);
            if (vis.add(rotated)) q.offer(rotated);
        }
        return ans;
    }

    private String add(String s, int a) {
        char[] c = s.toCharArray();
        for (int i = 1; i < c.length; i += 2) {
            c[i] = (char) ('0' + ((c[i] - '0' + a) % 10));
        }
        return new String(c);
    }

    private String rotate(String s, int b) {
        int n = s.length();
        b %= n;
        return s.substring(n - b) + s.substring(0, n - b);
    }
}

/*
1. PROBLEM :-
   You are given:

* A numeric string s
* Integer a
* Integer b

You can perform these operations any number of times:

Operation 1:
Add a to digits at odd indices.
If digit exceeds 9, wrap around using modulo 10.

Example:
a = 5
"3456" → "3951"

---

Operation 2:
Rotate string right by b positions.

Example:
b = 1
"3456" → "6345"

---

Goal: Find the lexicographically smallest string possible after any sequence of operations.
---

Core Idea:
This is a graph/state-space search problem.
Each unique string is a state.

From every state:
* Apply Add operation
* Apply Rotate operation
This creates new states.

Use BFS:

* Start from original string
* Explore all reachable strings
* Store visited strings to avoid repetition
* Track smallest lexicographic string seen

Because all reachable states are explored, the smallest found is answer.

---

Why BFS works:
We are exploring all possible valid transformations.
State transitions:
Current string
→ Add operation
→ Rotate operation
Use:
HashSet → visited states
Queue → BFS traversal

---

Time Complexity:
At most finite reachable states, each processed once.
Approx:
O(number of reachable states × n)
Space:
O(number of reachable states)

---

2. EXAMPLE :-

Input
s = "74"
a = 5
b = 1
Start:
"74"
Current smallest:
"74"

---

Apply Add:
Only odd index changes
4 + 5 = 9
"79"

---

Apply Rotate:
"47"
Smaller than "74"
Current answer:
"47"

---

From "47"
Apply Add:
7 + 5 = 12 → 2
"42"
Smaller.
Answer:
"42"

---

Rotate:
"24"
Even smaller.
Answer:
"24"
No smaller possible.
Output = "24"

---

3. DRY RUN :-
Example (custom)
s = "1234"
a = 3
b = 2
Start:
1234
ans = 1234

---

Operation: Add
Odd positions:
2 and 4
2+3=5
4+3=7
1537
Compare:
1234 smaller
---

Operation: Rotate by 2
3412
---

Queue:
1537
3412

---

Process 1537

Add:
1 8 3 0
1830

Rotate: 3715
Current smallest still:
1234
---

Process 3412
Add: 3715

Rotate: 1234 (already visited)
---

Process 1830
Add:
1133

Now:
1133 < 1234

Update answer:
1133
---

Continue exploring more reachable states.
Suppose eventually we reach:
1031
Now:
1031 < 1133
Update answer:
1031
Continue until all states visited.
---

Final lexicographically smallest reachable string:
1031
Final Answer = "1031"
---

Key Insight:
Treat each transformed string as a graph node and use BFS to explore all reachable strings, keeping the smallest one.

*/
