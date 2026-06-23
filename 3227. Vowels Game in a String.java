class Solution {
    public boolean doesAliceWin(String s) {
        for(char c:s.toCharArray()){
            if(c=='a' || c=='e' || c=='i' || c=='o'|| c=='u'){
                return true;
            }
        }
        return false;
    }
}

/*
problem - 3227. Vowels Game in a String
This problem is actually much simpler than its statement makes it look.
You are given a string.
Two players play alternately.
Alice starts first.
Rules:
Alice can remove any non-empty substring having an odd number of vowels.
Bob can remove any non-empty substring having an even number of vowels.
Whoever cannot make a move loses.
Both players play optimally.

You have to return:
true → Alice wins
false → Alice loses
The trick of this problem:
We do not need to simulate the game.
We only need one observation.
If the string contains at least one vowel, Alice always wins.
If the string contains no vowels, Alice loses immediately.
Why?
Suppose there is at least one vowel.
Alice can always remove a substring containing exactly one vowel.
After that, whatever Bob does, Alice can still force a win.
Mathematically, this game has been proven that:
At least one vowel present → Alice always wins
No vowels present → Alice always loses
That's why the code is simply checking whether any vowel exists.
example - s = "leetcoder"

String:
l e e t c o d e r
Check every character.
l → not vowel
e → vowel found
Immediately return true
Alice wins.
example - s = "bbcd"

String:
b b c d
Check every character.
b → not vowel
b → not vowel
c → not vowel
d → not vowel
No vowel found.
Return false
Alice loses.

dry run - tough example
s = "programming"

Code:
for(char c:s.toCharArray()){
if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u'){
return true;
}
}
return false;

Iteration 1
c='p'
Not vowel
Continue

Iteration 2
c='r'
Not vowel
Continue

Iteration 3
c='o'
Vowel found
return true
Program stops here.
No need to check remaining characters.
Answer = true
Another tough example
s = "rhythms"
Characters:

r

h

y

t

h

m

s
r → not vowel
h → not vowel
y → not vowel
t → not vowel
h → not vowel
m → not vowel
s → not vowel
Loop ends.
return false
Answer = false
Code understanding:
public boolean doesAliceWin(String s) {
Loop through every character.
for(char c:s.toCharArray()){
If any vowel exists.
if(c=='a' || c=='e' || c=='i' || c=='o'|| c=='u'){
Alice wins.
return true;
}
}
No vowel exists.
return false;
}

Time Complexity = O(n)
Space Complexity = O(1)
*/
