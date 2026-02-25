class Solution {
    public int numSub(String s) {
        long mod = 1000000007;
        long count = 0, ans = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                ans = (ans + (count * (count + 1) / 2)) % mod;
                count = 0;
            }
        }
        
        ans = (ans + (count * (count + 1) / 2)) % mod;
        return (int) ans;
    }
}

/*
PROBLEM - Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.

---

EXAMPLE'S - 
Example 1:

Input: s = "0110111"
Output: 9
Explanation: There are 9 substring in total with only 1's characters.
"1" -> 5 times.
"11" -> 3 times.
"111" -> 1 time.
Example 2:

Input: s = "101"
Output: 2
Explanation: Substring "1" is shown 2 times in s.

---

Dry run for s = "0110111"

Initialize
mod = 1000000007
count = 0
ans = 0

---

i = 0
s.charAt(0) = '0'

else part runs
ans = ans + count*(count+1)/2
ans = 0 + 0*(1)/2 = 0
count = 0

---

i = 1
s.charAt(1) = '1'

if part runs
count = count + 1
count = 1

---

i = 2
s.charAt(2) = '1'

count = 2

---

i = 3
s.charAt(3) = '0'

else part runs
ans = ans + count*(count+1)/2
ans = 0 + (2*3)/2
ans = 3

count = 0

---

i = 4
s.charAt(4) = '1'

count = 1

---

i = 5
s.charAt(5) = '1'

count = 2

---

i = 6
s.charAt(6) = '1'

count = 3

---

loop ends

final addition
ans = ans + count*(count+1)/2
ans = 3 + (3*4)/2
ans = 3 + 6
ans = 9

---

return 9


*/
