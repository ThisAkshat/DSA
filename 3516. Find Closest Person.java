class Solution {
    public int findClosest(int x, int y, int z) {
        int num1=Math.abs(x-z);
        int num2=Math.abs(y-z);
        if(num1<num2){
            return 1;
        }
        else if(num1>num2){
            return 2;
        }
        else{
            return 0;
        }
    }
}

/*
problem -
There are 3 people standing on a number line.
Person 1 is at position x.
Person 2 is at position y.
Person 3 is at position z.
Person 3 does not move.
Person 1 and Person 2 both move towards Person 3 with the same speed.
You have to find who reaches Person 3 first.
Since speed is the same, the person having the smaller distance from z reaches first.
Distance formula:
Person 1 distance = |x - z|
Person 2 distance = |y - z|

If Person 1 distance < Person 2 distance
return 1
If Person 2 distance < Person 1 distance
return 2
If both distances are equal
return 0
example -
x = 2
y = 7
z = 4
Person 1 distance
|2 - 4|
= 2
Person 2 distance
|7 - 4|
= 3
2 < 3
Person 1 reaches first.
Output = 1
dry run -
x = 10
y = 3
z = 7
Step 1
num1 = Math.abs(x - z)
= Math.abs(10 - 7)
= 3
num1 = 3
Step 2
num2 = Math.abs(y - z)
= Math.abs(3 - 7)
= 4
num2 = 4
Step 3
if(num1 < num2)
3 < 4
true
return 1
Output = 1
another dry run -
x = 1
y = 9
z = 5
num1
= |1 - 5|
= 4
num2
= |9 - 5|
= 4
if(num1 < num2)
4 < 4
false
else if(num1 > num2)
4 > 4
false
else
return 0
Output = 0
program logic -
int num1 = Math.abs(x - z);
Distance of Person 1 from Person 3.
int num2 = Math.abs(y - z);
Distance of Person 2 from Person 3.
if(num1 < num2)
Person 1 is closer.
return 1
else if(num1 > num2)
Person 2 is closer.
return 2
else
Both are equally far.
return 0
Time Complexity: O(1)
Space Complexity: O(1)


*/
