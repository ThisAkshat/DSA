class Solution {
    public int[] getNoZeroIntegers(int n) {
        int a = 1;
        int b = n-1;
        while(foundZero(a) || foundZero(b)){
            a++;
            b=n-a;
        }
        return new int[]{a,b};
    }
    private boolean foundZero(int num){
        while(num > 0){
            if(num % 10 == 0) return true;
            num /= 10;
        }
        return false;
    }
}

/*
problem - You are given an integer n.
You have to find two positive integers a and b such that:

1. a + b = n
2. a should not contain digit 0.
3. b should not contain digit 0.
Return any valid pair.
A no-zero integer means its decimal representation should not contain even a single 0.

Examples:
12 → valid
345 → valid
101 → invalid
20 → invalid

Idea:
Start with:
a = 1
b = n-1

Check whether both numbers contain 0.
If yes, increase a by 1 and recalculate b.
Keep doing this until both numbers become no-zero integers.
Then return them.

Code understanding:
int a = 1;
Start first number from 1.
int b = n-1;
Second number is whatever remains.
while(foundZero(a) || foundZero(b))
Keep looping until both numbers are no-zero integers.
a++;
Increase a.
b = n-a;

Update b so that:
a+b=n
return new int[]{a,b};
Return answer.
foundZero() function understanding:
while(num > 0)
Check every digit.
if(num % 10 == 0)
If digit is 0.
return true;
num /= 10;
Remove last digit.

If loop finishes:
return false;
No zero digit exists.
example - n = 11

Initially:
a=1
b=10
foundZero(1)=false
foundZero(10)=true
Loop runs.
a=2
b=9
foundZero(2)=false
foundZero(9)=false
Loop stops.

Return:
[2,9]
dry run - n = 101

Initial:
a=1
b=100
foundZero(1)=false
foundZero(100)=true
Loop
a=2
b=99
foundZero(2)=false
foundZero(99)=false
Loop stops.

Return:
[2,99]

Check:
2+99=101
2 has no zero
99 has no zero
Answer = [2,99]
*/
