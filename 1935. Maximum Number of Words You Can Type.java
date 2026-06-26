class Solution {
    public int canBeTypedWords(String text, String str) {
        String[] arr = text.split(" ");
        int ans = arr.length;
        int[] count = new int[26];
        for(char ch: str.toCharArray())
            count[ch-'a']++;
        for(String s: arr){
            for(char ch: s.toCharArray()){
                if(count[ch-'a']>0){
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }
}

/*
problem - 1935. Maximum Number of Words You Can Type
This problem says:
You are given a sentence containing multiple words.
Some keyboard keys are broken.
A word can be typed only if none of its characters are broken.
Your task is to count how many words can be typed completely.

example
text = "hello world"
brokenLetters = "ad"

Words:
hello
world
Broken letters:
a
d
Check each word.
hello
Contains a?
No
Contains d?
No
Can be typed.
world
Contains d?
Yes
Cannot be typed.
Answer = 1
dry run - tough example
text = "i love programming in java"
brokenLetters = "aeg"

Step 1
Split the sentence.
arr =
[
"i",
"love",
"programming",
"in",
"java"
]
ans = arr.length = 5

Step 2
Create frequency array.
count[26]
Initially
All values are 0.
Process broken letters.
'a'
count['a'-'a']=count[0]=1
'e'
count['e'-'a']=count[4]=1
'g'
count['g'-'a']=count[6]=1
Only these three positions contain 1.
Step 3
Check every word.
Word = "i"
Characters
i
count['i'-'a']
0
No broken character.
ans remains 5
Word = "love"
Characters
l
count=0
o
count=0
v
count=0
e
count=1
Broken character found.
ans--
ans=4
Stop checking this word.
Word = "programming"

Characters
p
0
r
0
o
0
g
1

Broken character found.
ans--
ans=3
Stop checking this word.
Word = "in"

Characters
i
0
n
0

No broken character.
ans remains 3
Word = "java"

Characters
j
0
a
1
Broken character found.
ans--
ans=2
Stop checking this word.

Final Answer
2
Only these words can be typed.
i
in
Code understanding
String[] arr = text.split(" ");
Split the sentence into individual words.
int ans = arr.length;
Initially assume every word can be typed.
int[] count = new int[26];
Frequency array for broken letters.
for(char ch : str.toCharArray())
Visit every broken letter.
count[ch-'a']++;
Mark that letter as broken.
for(String s : arr)
Check every word.
for(char ch : s.toCharArray())
Visit every character of the current word.
if(count[ch-'a'] > 0)
Broken letter found.
ans--
Reduce answer because this word cannot be typed.
break;
No need to check remaining characters of this word.

Finally
return ans;
Time Complexity = O(n)
where n = total number of characters in text + brokenLetters.
Space Complexity = O(1)
Because the frequency array always has size 26.
*/
