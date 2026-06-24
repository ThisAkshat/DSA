class Solution {
    public int maxFreqSum(String s) {
        int i, max_vowel=0, max_cons=0;
        int freq[]=new int[26];
        for(char c: s.toCharArray())
        {
            i=c-'a';
            freq[i]++;
            if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u'){
                max_vowel=Math.max(max_vowel, freq[i]);
            }
            else{
                max_cons=Math.max(max_cons, freq[i]);
            }
        }
        return max_vowel+max_cons;
    }
}
/*
problem - 3541. Find Most Frequent Vowel and Consonant
This problem says:
You are given a lowercase string.
You have to do two things.
1. Find the vowel that appears the most.
Vowels are:
a, e, i, o, u
2. Find the consonant that appears the most.
Consonants are all remaining letters.
Finally:
answer = (highest vowel frequency) + (highest consonant frequency)
If there are no vowels, vowel frequency = 0.
If there are no consonants, consonant frequency = 0.
example - s = "successes"
String:
s u c c e s s e s

Count frequencies.
s = 4
u = 1
c = 2
e = 2

Vowels:
u = 1
e = 2
Maximum vowel frequency = 2
Consonants:
s = 4
c = 2
Maximum consonant frequency = 4
Answer:
2 + 4 = 6

dry run - tough example
s = "programmingisawesome"

Code:
int i,max_vowel=0,max_cons=0;
int freq[]=new int[26];
Initial:
max_vowel=0
max_cons=0
freq=[0,0,0,0....0]
Character 1
p
i=15
freq[15]=1
Consonant
max_cons=max(0,1)=1
Character 2
r
i=17
freq[17]=1
max_cons=1
Character 3
o
i=14
freq[14]=1
Vowel
max_vowel=1
Character 4
g
i=6
freq[6]=1
max_cons=1
Character 5
r
freq[17]=2
max_cons=2
Character 6
a
freq[0]=1
max_vowel=1
Character 7
m
freq[12]=1
max_cons=2
Character 8
m
freq[12]=2
max_cons=2
Character 9
i
freq[8]=1
max_vowel=1
Character 10
n
freq[13]=1
max_cons=2
Character 11
g
freq[6]=2
max_cons=2
Character 12
i
freq[8]=2
max_vowel=2
Character 13
s
freq[18]=1
max_cons=2
Character 14
a
freq[0]=2
max_vowel=2
Character 15
w
freq[22]=1
max_cons=2
Character 16
e
freq[4]=1
max_vowel=2
Character 17
s
freq[18]=2
max_cons=2
Character 18
o
freq[14]=2
max_vowel=2
Character 19
m
freq[12]=3
max_cons=3
Character 20
e
freq[4]=2
max_vowel=2

Final frequencies:
a=2
e=2
i=2
o=2
g=2
m=3
r=2
s=2
Others smaller
Maximum vowel frequency = 2
Maximum consonant frequency = 3

Answer:
2+3=5
Code understanding:
for(char c:s.toCharArray())
Traverse every character.

i=c-'a'
Convert character into array index.
freq[i]++
Increase its frequency.
if vowel
Update max_vowel.
else
Update max_cons.

Finally
return max_vowel+max_cons
Time Complexity = O(n)
Space Complexity = O(1)
Because frequency array size is always 26.
*/
