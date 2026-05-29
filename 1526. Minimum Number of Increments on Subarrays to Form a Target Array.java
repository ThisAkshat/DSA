class Solution {
    public int minNumberOperations(int[] target) {
        int ops = target[0];
        for(int i= 1;i<target.length;i++){
            if(target[i] > target[i-1]){
                ops += target[i] - target[i-1];
            }
        }
        return ops;
    }
}

/*
problem - Minimum Number of Increments on Subarrays to Form a Target Array
What is this problem saying?
You are given a target array.

Initially, there is another array called initial having all values 0.

Example:

target = [1,2,3,2,1]
initial = [0,0,0,0,0]

In one operation:
You can choose any subarray and increase all elements of that subarray by 1.

Example:
[0,0,0,0,0]
Choose subarray from index 1 to 3

After one operation:
[0,1,1,1,0]

Goal:
Make the initial array exactly equal to target using minimum operations.

Important thing:
In one operation, you can increment many adjacent elements together.

example - target = [1,2,3,2,1]

Start:
[0,0,0,0,0]

Operation 1:
Increase whole array

[1,1,1,1,1]

Operation 2:
Increase index 1 to 3
[1,2,2,2,1]

Operation 3
Increase index 2
[1,2,3,2,1]
Target achieved.
Answer = 3
dry run - tough example
target = [3,1,5,4,2]

Code:
ops = target[0]
ops = 3
Why?

Because to make first element = 3, we need minimum 3 operations.
Current:
ops = 3
Loop starts from i = 1
i = 1
target[1] = 1
target[0] = 3

Check:
target[i] > target[i-1]
1 > 3 → false
No operation added.
ops = 3

Why?
Because previous operations already covered this height.
Visual:
3
1

No extra height needed.
i = 2
target[2] = 5
target[1] = 1
Check:
5 > 1 → true
Extra needed:
5 - 1 = 4
ops += 4
ops = 7

Why?

Previous height = 1
Current height = 5

Need 4 extra layers.

Visual:
Already covered:
1

Need extra:
2
3
4
5

So +4 operations.

i = 3

target[3] = 4
target[2] = 5

Check:
4 > 5 → false
No extra operation.
ops = 7
Why?
Because previous operations already covered enough height.

i = 4

target[4] = 2
target[3] = 4

Check:
2 > 4 → false
No extra operation.

ops = 7
Loop ends.
Return:7

Final answer = 7
How this code thinks:
Key observation:
Whenever current number becomes greater than previous number, we need extra operations.
If current is smaller or equal:
No extra operations are needed because previous operations already covered it.

Example:
target = [1,2,3]
Visual height:
1 2 3

Index 0:
Need 1 operation.

Index 1:
Already have height 1.
Need only +1 extra.

Index 2:
Already have height 2.
Need only +1 extra.

Total:
1 + 1 + 1 = 3
Another example:
target = [5,4,3]

Index 0:
Need 5 operations.

Next elements are smaller.
No extra operations.
Answer = 5
That is why code says:
If current > previous
Add only difference:
ops += target[i] - target[i-1]
Formula:
Answer = target[0] + sum of positive increases

Example:
[3,1,5,4,2]
= 3 + (5 - 1)
= 3 + 4
= 7

Time Complexity: O(n)
Space Complexity: O(1)

*/
