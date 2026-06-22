class Solution {
    public String sortVowels(String s) {
        ArrayList<Character>vowel = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            if(isVowel(s.charAt(i))) vowel.add(s.charAt(i));
        }
        Collections.sort(vowel);
        StringBuilder ans = new StringBuilder(s);
        int idx=0;
        for(int i=0;i<s.length();i++){
            if(isVowel(s.charAt(i))) ans.setCharAt(i,vowel.get(idx++));
        }
        return ans.toString();
    }
    public boolean isVowel(char ch){
        return (ch=='A'||ch=='I'||ch=='E'||ch=='O'||ch=='U'||ch=='a'||ch=='i'||ch=='o'||ch=='u'||ch=='e');
    }
}

/*
problem - 2785. Sort Vowels in a String
This problem says:
You are given a string.
You can only rearrange vowels.
Consonants must stay exactly at their original positions.
After collecting all vowels, sort them according to ASCII order and put them back into their vowel positions.

ASCII order:
A < E < I < O < U < a < e < i < o < u
Your task is to return the final string.
example - s = "lEetcOde"

Original string:
l E e t c O d e

Step 1: Collect vowels
E e O e

Step 2: Sort vowels according to ASCII
E O e e

Step 3: Put them back into vowel positions
Index : 0 1 2 3 4 5 6 7
Original : l E e t c O d e
Result : l E O t c e d e
Answer = "lEOtcede"
dry run - s = "ProgrammingIsAwesome"

String:
P r o g r a m m i n g I s A w e s o m e

Step 1: First loop
Collect all vowels.
o
a
i
I
A
e
o
e
vowel = [o,a,i,I,A,e,o,e]

Step 2:
Collections.sort(vowel)
ASCII order:
A
I
a
e
e
i
o
o
vowel = [A,I,a,e,e,i,o,o]

Step 3:
StringBuilder ans = new StringBuilder(s)
ans = ProgrammingIsAwesome
idx = 0
Traverse whole string.

i=0
P → consonant
No change
ans = ProgrammingIsAwesome

i=1
r → consonant
No change

i=2
o → vowel
Replace with vowel[0]
A
idx=1
ans = PrAgrammingIsAwesome

i=3
g → consonant
No change

i=4
r → consonant
No change

i=5
a → vowel
Replace with vowel[1]
I
idx=2
ans = PrAgrImmingIsAwesome

i=6
m → consonant
No change

i=7
m → consonant
No change

i=8
i → vowel
Replace with vowel[2]
a
idx=3
ans = PrAgrImmaingIsAwesome

i=9
n → consonant
No change

i=10
g → consonant
No change

i=11
I → vowel
Replace with vowel[3]
e

idx=4
ans = PrAgrImmaingesAwesome

i=12
s → consonant
No change

i=13
A → vowel
Replace with vowel[4]
e
idx=5
ans = PrAgrImmaingesewesome

i=14
w → consonant
No change

i=15
e → vowel
Replace with vowel[5]
i
idx=6
ans = PrAgrImmaingesewiseome
i=16
s → consonant
No change
i=17
o → vowel
Replace with vowel[6]
o
idx=7
No visible change
i=18
m → consonant
No change
i=19
e → vowel
Replace with vowel[7]
o
idx=8

Final answer:
PrAgrImmaingesewiseomo
Time Complexity = O(n log n)
n = string length
Sorting vowels dominates the complexity.
Space Complexity = O(n)
Because we store all vowels separately.
*/
