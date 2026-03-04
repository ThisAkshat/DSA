class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int result = 0;

        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        for(int i=0; i<n; i++){
            int char_index = s.charAt(i) - 'a';
            if(first[char_index] == -1){
                first[char_index] = i;
            }
            last[char_index] = i;
        }
        for(int i=0; i<26; i++){
            if(first[i] != -1 && first[i] < last[i]){
                boolean[] seen = new boolean[26];
                for(int j=first[i] + 1; j<last[i]; j++){
                    int middle_char = s.charAt(j) - 'a';
                    if(!seen[middle_char]){
                        seen[middle_char] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }
}

/*
PEOBLEM --
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
A palindrome is a string that reads the same forwards and backwards.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
* For example, "ace" is a subsequence of "abcde".

***************************************************************************************************************************

Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")

***************************************************************************************************************************

Dry run for example:

s = "aabca"
n = 5
result = 0

---

Step 1: Fill first and last arrays

Traverse string:

i = 0 → 'a'
first[a] = 0
last[a] = 0

i = 1 → 'a'
last[a] = 1

i = 2 → 'b'
first[b] = 2
last[b] = 2

i = 3 → 'c'
first[c] = 3
last[c] = 3

i = 4 → 'a'
last[a] = 4

Now:

first[a] = 0, last[a] = 4
first[b] = 2, last[b] = 2
first[c] = 3, last[c] = 3
others = -1

---

Step 2: Check each character from a to z

Character 'a':

first[a] = 0
last[a] = 4
0 < 4 → valid

Check middle part from index 1 to 3

Initialize seen[26] = false

j = 1 → 'a'
not seen → mark seen[a] = true
result = 1

j = 2 → 'b'
not seen → mark seen[b] = true
result = 2

j = 3 → 'c'
not seen → mark seen[c] = true
result = 3

---

Character 'b':

first[b] = 2
last[b] = 2
2 < 2 → false
skip

---

Character 'c':

first[c] = 3
last[c] = 3
3 < 3 → false
skip

---

All other characters first = -1 → skip

---

Final result = 3

Return 3

*/
