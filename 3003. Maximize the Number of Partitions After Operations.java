class Solution {
    public int maxPartitionsAfterOperations(String S, int k) {
        if(k == 26) return 1;
        char[] s = S.toCharArray();
        int n = s.length;
        int[] left1 = new int[n], left2 = new int[n];
        int[] partitions = new int[n];
        int mask1 = 0, mask2 = 0, count = 1;
        for(int i = 0; i < n; i++) {
            int filter = 1 << (s[i] - 'a');
            mask2 |= mask1 & filter;
            mask1 |= filter;
            if(Integer.bitCount(mask1) > k) {
                mask1 = filter;
                mask2 = 0;
                count++;
            }
            left1[i] = mask1;
            left2[i] = mask2;
            partitions[i] = count;
        }
        int ans = count;
        mask1 = mask2 = count = 0;
        for(int i = n - 1; i >= 0; i--) {
            int filter = 1 << (s[i] - 'a');
            mask2 |= mask1 & filter;
            mask1 |= filter;
            if(Integer.bitCount(mask1) > k) {
                mask1 = filter;
                mask2 = 0;
                count++;
            }
            if(Integer.bitCount(mask1) == k) {
                if((filter & mask2) != 0 && (filter & left2[i]) != 0 && Integer.bitCount(left1[i]) == k && (left1[i] | mask1) != (1 << 26) - 1) ans = Math.max(ans, count + partitions[i] + 2);
                else if(mask2 != 0) ans = Math.max(ans, count + partitions[i] + 1);
            }
        }
        return ans;
    }
}


/*
1. PROBLEM :-
   You are given:
* A string s
* An integer k
You may change at most one character in the string to any lowercase letter.
After that, repeatedly:
* Take the longest prefix containing at most k distinct characters
* Remove it
* Count one partition
Continue until string becomes empty.
Goal:
Maximize the number of partitions.

---

Core Idea:
Without any change:
Greedily split whenever adding a new character makes distinct count exceed k.
But we can change one character to force extra splits.

Observation:
Changing one character may:

1. Create one extra partition
2. Sometimes create two extra partitions

The code uses:

* Left-to-right preprocessing
* Right-to-left preprocessing
* Bitmasks to track distinct characters
* Evaluate effect of changing one character

Arrays:
left1[i] → distinct character mask in current left partition
left2[i] → repeated overlap information
partitions[i] → number of partitions till i
Then reverse traversal checks whether modifying one character can increase partition count.
Special case:
If k = 26
All letters fit in one partition
Answer always 1

---

Time Complexity: O(n)
Space Complexity: O(n)

---

2. EXAMPLE :-
Input
s = "accca"
k = 2

Without change:
Whole string has only:
a,c
Only 2 distinct characters
Entire string forms one partition
Partitions = 1

---

Use one change:
Change middle c → b
String becomes:
"acbca"

Now partition greedily:
Prefix "ac"
(2 distinct)
Remaining:
"bca"

---

Prefix "bc"
Remaining:
"a"

---

Prefix "a"
Total partitions:
["ac"]
["bc"]
["a"]
Answer = 3
Output = 3

---

3. DRY RUN :-
Example (custom tough case)
s = "abccde"
k = 2

---

Without modification:
Start:
"a b c c d e"

Build longest prefix with at most 2 distinct:
"a b"
(2 distinct)

Adding c gives 3 distinct
Stop.
Partition 1:
"ab"

---

Remaining:
"ccde"

Take longest prefix:
"cc d"
(2 distinct)
Adding e gives 3 distinct
Stop
Partition 2:
"ccd"

---

Remaining:
"e"
Partition 3:
"e"
Total = 3

---

Now use one change
Change:
s[2] = c → x

String:
abxcde

Process:
"ab" → partition 1
"xc" → partition 2
"de" → partition 3
Still 3

---

Better change:
Change s[1] = b → x

String:
axccde

Now:
"ax" → partition 1
"cc" can extend with d
"ccd" → partition 2
"e" → partition 3
Still 3
---

Try stronger split:
Change s[3] = c → y
abcyde

Partitions:
"ab"
"cy"
"de"

3 partitions
No better possible.
Maximum = 3
Final Answer = 3

---
Key Insight:
One carefully chosen character change can force additional split boundaries, which is what the bitmask optimization checks efficiently.

*/
