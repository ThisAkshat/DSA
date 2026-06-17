class Solution {
    public int[] sumZero(int n) {
        int[] Result = new int[n];
        int num = 1;

        for(int i=0;i<n/2;i++){
            Result[i] = -num;
            Result[n-1-i] = num;
            num++;
        }
        return Result;
    }
}

/*
problem - You are given an integer n.
You have to create an array of size n containing unique integers.

Conditions:
1. Every integer must be unique.
2. Sum of all integers must be 0.
3. Any valid answer is accepted.
The question does not ask for a specific array. It only asks for any array satisfying the conditions.

Idea:
Take numbers in pairs.
If we choose:
-1 and 1
Their sum becomes 0.

Similarly:
-2 and 2
Their sum also becomes 0.
-3 and 3
Their sum also becomes 0.
Keep creating such pairs.
If n is odd, one position will remain unfilled.
Default value of Java arrays is 0, so that remaining position automatically becomes 0.

Code understanding:
int[] Result = new int[n];
Create answer array.
int num = 1;
Start number from 1.
for(int i=0;i<n/2;i++)
Run only for half of the array because one iteration fills two positions.

Result[i] = -num;
Put negative number at front.
Result[n-1-i] = num;
Put positive number at back.
num++;
Move to next pair.
Finally return array.
example - n = 5

Initially:
[0,0,0,0,0]

i=0
Result[0]=-1
Result[4]=1
[-1,0,0,0,1]

i=1
Result[1]=-2
Result[3]=2
[-1,-2,0,2,1]

Return:
[-1,-2,0,2,1]

Sum:
-1-2+0+2+1
=0
dry run - n = 8
Initial:
[0,0,0,0,0,0,0,0]
num=1
i=0
Result[0]=-1
Result[7]=1
[-1,0,0,0,0,0,0,1]
num=2

i=1
Result[1]=-2
Result[6]=2
[-1,-2,0,0,0,0,2,1]
num=3

i=2
Result[2]=-3
Result[5]=3
[-1,-2,-3,0,0,3,2,1]
num=4
i=3
Result[3]=-4
Result[4]=4
[-1,-2,-3,-4,4,3,2,1]

Final array:
[-1,-2,-3,-4,4,3,2,1]

Sum: -1-2-3-4+4+3+2+1=0

*/
