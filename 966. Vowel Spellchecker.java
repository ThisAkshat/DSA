class Solution {
    private String devowel(String word) {
        char[] arr = word.toLowerCase().toCharArray();

        for (int i=0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                arr[i] = '*';
            }
        }

        return new String(arr);
    }
    public String[] spellchecker(String[] wordlist, String[] queries) {
        HashSet<String> exactSet =  new HashSet<>();
        HashMap<String, String> caseMap = new HashMap<>();
        HashMap<String, String> vowelMap = new HashMap<>();

        for(String i: wordlist){
            exactSet.add(i);
        }

        for(String i: wordlist){
            String val = i;
            String key = i.toLowerCase();
            caseMap.putIfAbsent(key, val);
        }

        for(String i: wordlist){
            String val = i;
            String key = devowel(i);
            vowelMap.putIfAbsent(key, val);
        }

        for(int i = 0; i<queries.length; i++){
            String word = queries[i];
            String wordLower = word.toLowerCase();
            if(exactSet.contains(word)){
                continue;
            }else if(caseMap.containsKey(wordLower)){
                queries[i] = caseMap.get(wordLower);
            }
            else if (vowelMap.containsKey(devowel(word))) {
                queries[i] = vowelMap.get(devowel(word));
            } 
            else {
                queries[i] = "";
            }
        }
        return queries;
        
    }
}
/*
problem - 966. Vowel Spellchecker
This problem asks us to correct every query word using the given wordlist.
There are 3 matching rules and they must be checked in order.

Rule 1: Exact Match
If query exactly exists in wordlist (same letters + same case), return it.

Example:
wordlist = ["KiTe"]
query = "KiTe"
Return:
"KiTe"
Rule 2: Case-Insensitive Match
If exact match fails, convert both words to lowercase and check.

Example:
wordlist = ["KiTe"]
query = "kite"

Both become:
kite
Return:
"KiTe"
Important:
If multiple matches exist, return the first one in wordlist.
Rule 3: Vowel Error Match
If case match fails, replace every vowel by '*' and compare.
Example:
wordlist = ["KiTe"]
query = "keti"
Lowercase:
kite
keti
Replace vowels with '*'
k*t*
k*t*
Match found.

Return:
"KiTe"
If nothing matches:
Return ""
example
wordlist = ["KiTe","kite","hare","Hare"]
queries = ["keti"]

Preprocessing:
exactSet
{
"KiTe",
"kite",
"hare",
"Hare"
}
caseMap
kite -> KiTe
hare -> hare

vowelMap
k*t* -> KiTe
h*r* -> hare

Query:
keti
Rule 1
exactSet contains keti ?
No
Rule 2
caseMap contains keti ?
No
Rule 3
devowel(keti)
k*t*
vowelMap contains k*t* ?
Yes
Return
KiTe

dry run - tough example
wordlist
["Yellow","YellOw","Apple","KiTe"]
queries
["yollow","YELLOW","AppLe","keti","xyz"]

Building exactSet
{
Yellow,
YellOw,
Apple,
KiTe
}
Building caseMap
yellow -> Yellow
yellow already exists
Ignore YellOw
apple -> Apple
kite -> KiTe

Final caseMap
yellow -> Yellow
apple -> Apple
kite -> KiTe

Building vowelMap
Yellow
lowercase
yellow
replace vowels
y*ll*w
y*ll*w -> Yellow
YellOw
same key
y*ll*w

Already exists
Ignore
Apple
*ppl*
*ppl* -> Apple
KiTe
k*t*
k*t* -> KiTe
Final vowelMap
y*ll*w -> Yellow
*ppl* -> Apple
k*t* -> KiTe
Processing query 1
yollow
Exact match?
No
Case match?
yollow not found
Vowel match?
devowel(yollow)
y*ll*w
Found
Return Yellow
Processing query 2
YELLOW
Exact match?
No
Case match?
yellow
Found
Return Yellow
Processing query 3
AppLe
Exact match?
No
Case match?
apple
Found
Return Apple
Processing query 4
keti
Exact match?
No
Case match?
No
devowel(keti)
k*t*
Found
Return KiTe
Processing query 5
xyz
Exact match?
No
Case match?
No
Vowel match?
xyz
Not found
Return ""

Final Answer
[
"Yellow",
"Yellow",
"Apple",
"KiTe",
""
]
Code understanding
devowel()
Convert word to lowercase.
Replace every vowel with '*'.

Example
KiTe
↓
kite
↓
k*t*
Used for vowel-error matching.
exactSet
Stores all original words.
Used for exact matching.
caseMap
key = lowercase word
value = first occurrence in wordlist

Example
KiTe
kite
Both generate
kite
Only first one is stored.
vowelMap
key = devowel(word)
value = first occurrence in wordlist
Example
KiTe
↓
k*t*
Stored as
k*t* -> KiTe
For every query
Check exactSet first.
If not found, check caseMap.
If not found, check vowelMap.
If not found, return empty string.

Why putIfAbsent?
Because problem says:
Return the first matching word from wordlist.
putIfAbsent ensures the first occurrence stays stored.

Time Complexity
Building maps:
O(N × L)

Processing queries:
O(Q × L)

Overall:
O((N + Q) × L)
where
N = wordlist length
Q = queries length
L = maximum word length (≤ 7)
Space Complexity
O(N)

*/
