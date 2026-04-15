class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        result.add(words[0]);

        for(int i = 1; i < words.length; i++){
            String prev = result.get(result.size()-1);
            if(!isAnagram(prev, words[i])){
                result.add(words[i]);
            }
        }
        return result;
    }
    private boolean isAnagram(String S1, String S2){
        if(S1.length() != S2.length()) return false;
        char[] a = S1.toCharArray();
        char[] b = S2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }
}

/*
1. PROBLEM :-
   You are given an array of strings words.
Operation allowed:
If two adjacent words are anagrams, you can delete the second one.
Repeat this process until no more adjacent anagrams exist.

Goal:
Return the final array after removing all possible adjacent anagrams.
Important idea:
Only compare each word with the last word that was kept.
If current word is an anagram of the previous kept word → skip it
Otherwise → keep it in result

Definition:
Two words are anagrams if they contain the same characters with same frequency (order doesn’t matter).

Approach:
1. Add the first word to result.
2. For each next word:
   * Compare with last word in result.
   * If not an anagram → add it.
3. Return result.

Time Complexity: O(n * k log k)
(k = length of each word, sorting for anagram check)
Space Complexity: O(n)


2. EXAMPLE :-
Input
words = ["abba","baba","bbaa","cd","cd"]
Step-by-step:
Start
result = ["abba"]
---

Check "baba"
"abba" and "baba" are anagrams
So skip "baba"
result = ["abba"]
---

Check "bbaa"
"abba" and "bbaa" are anagrams
Skip
result = ["abba"]
---

Check "cd"
"abba" and "cd" are NOT anagrams
Add
result = ["abba","cd"]
---

Check "cd"
"cd" and "cd" are anagrams
Skip
result = ["abba","cd"]
Final Output:
["abba","cd"]


3. DRY RUN :-

Example (custom case)
words = ["abc","bca","cab","xyz","zyx","pqr"]
Start
result = ["abc"]
---

i = 1 → "bca"
"abc" and "bca" are anagrams
Skip
result = ["abc"]
---

i = 2 → "cab"
"abc" and "cab" are anagrams
Skip
result = ["abc"]
---

i = 3 → "xyz"
"abc" and "xyz" are not anagrams
Add
result = ["abc","xyz"]
---

i = 4 → "zyx"
"xyz" and "zyx" are anagrams
Skip
result = ["abc","xyz"]
---

i = 5 → "pqr"
"xyz" and "pqr" are not anagrams
Add
result = ["abc","xyz","pqr"]
---

Final Output:
["abc","xyz","pqr"]

*/
