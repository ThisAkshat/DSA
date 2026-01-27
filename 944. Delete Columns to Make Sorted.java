class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int deleteCount = 0;
        for(int col = 0; col<m; col++){
            for(int row =1; row < n; row++){
                if(strs[row].charAt(col) < strs[row -1].charAt(col)){
                    deleteCount++;
                    break;
                }         
           }
        }
        return deleteCount;
    }
} /* strs = ["c b a",
             "d a f",
             "g h i"] */


/*
1) Problem Explanation
Yeh problem grid of strings ke columns delete karne ki hai:
Humare paas strs array hai jismein sab strings ki same length hai.
Inhe ek grid ki tarah imagine karo: har string ek row hai.
Har column check karna hai: agar wo column lexicographically sorted nahi hai (top to bottom), toh use delete karna hai.
Output: kitne columns delete karne padenge.
Lexicographically sorted ka matlab:
Column mein har agla character previous character se greater ya equal hona chahiye (ASCII value ke hisaab se).


2) Perfect Example
Example 1 se:

strs = ["cba", "daf", "ghi"]

Grid:

c b a
d a f
g h i
Columns:

Column 0: c, d, g → sorted? 'c' < 'd' < 'g' ✓

Column 1: b, a, h → sorted? 'b' < 'a'? NOPE! (delete)

Column 2: a, f, i → sorted? 'a' < 'f' < 'i' ✓

Delete columns = 1

3) Dry Run of Given Code on Example 1
Input: strs = ["cba", "daf", "ghi"]
n = 3 (rows)
m = 3 (columns)

deleteCount = 0

Column 0 check:

row=1: strs[1].charAt(0) = 'd'
strs[0].charAt(0) = 'c'
'd' < 'c'? NO (d > c) ✓ continue

row=2: strs[2].charAt(0) = 'g'
strs[1].charAt(0) = 'd'
'g' < 'd'? NO ✓
Column 0 sorted → no delete

Column 1 check:

row=1: strs[1].charAt(1) = 'a'
strs[0].charAt(1) = 'b'
'a' < 'b'? YES!
→ deleteCount++ (now 1), break loop
Column 1 not sorted → delete

Column 2 check:

row=1: strs[1].charAt(2) = 'f'
strs[0].charAt(2) = 'a'
'f' < 'a'? NO ✓

row=2: strs[2].charAt(2) = 'i'
strs[1].charAt(2) = 'f'
'i' < 'f'? NO ✓
Column 2 sorted → no delete

Final deleteCount = 1 ✅ matches example.

*/

