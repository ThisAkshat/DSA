class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int count = 0;
        int left =0;
        int right =0;
        for(int i :nums){
            right = right+i;
        }
        for(int i : nums){
            if(i==0){
                if(left >= right && left - right<=1){
                    count++;
                }
                if(right>=left && right -left <=1){
                    count++;
                }
            }
            left = left+i;
            right = right-i;
        }
        return count;
    }
}

/*
problem - Make Array Elements Equal to Zero
What is this problem saying?
You are given an array `nums`.
You can only start from an index where the value is `0`.
At the beginning, you must choose a direction:
* left
* right

After that, follow these rules repeatedly:
1. If the current index goes out of range (`< 0` or `>= n`)
   → process ends.
2. If `nums[curr] == 0`
   * move one step in the same direction.
3. If `nums[curr] > 0`
   * decrease that number by `1`
   * reverse direction
   * move one step.

Your task is to count how many starting choices are valid.

Valid means:
By the time the process ends, every element of the array must become `0`.

example - [1,0,2,0,3]
Possible starting positions are only zero indices:
* index 1
* index 3

For each index, 2 directions are possible:
* left
* right

Total 4 possibilities:
1. index = 1, left ❌
2. index = 1, right ❌
3. index = 3, left ✅
4. index = 3, right ✅

Answer = 2
dry run - tough example
nums = [2,1,0,3,1]

Step 1:
Find zero index
Only:
index = 2
Try both directions.

Case 1:
start = index 2, left
Initial:
[2,1,0,3,1]
curr = 2
direction = left
nums[2] = 0
Move left
curr = 1

Array:
[2,1,0,3,1]
nums[1] = 1 (>0)
Decrease by 1
[2,0,0,3,1]

Reverse direction
left → right
Move
curr = 2
nums[2] = 0
Move right
curr = 3
nums[3] = 3 (>0)
Decrease
[2,0,0,2,1]

Reverse direction
right → left
Move
curr = 2
nums[2] = 0
Move left
curr = 1
nums[1] = 0
Move left
curr = 0
nums[0] = 2 (>0)
Decrease
[1,0,0,2,1]

Reverse direction
left → right
Move
curr = 1
nums[1] = 0
Move right
curr = 2
nums[2] = 0
Move right
curr = 3
nums[3] = 2 (>0)
Decrease:
[1,0,0,1,1]

Reverse
Move left
curr = 2
Move left
curr = 1
Move left
curr = 0
nums[0] = 1 (>0)
Decrease
[0,0,0,1,1]

Reverse
Move right
curr = 1
Move right
curr = 2
Move right
curr = 3
nums[3] = 1 (>0)
Decrease
[0,0,0,0,1]

Reverse
Move left
curr = 2
Move left
curr = 1
Move left
curr = 0
nums[0] = 0
Move left
curr = -1
Out of bounds
End
Final array:
[0,0,0,0,1]

One element is still non-zero.
Invalid ❌

Case 2:
start = index 2, right
Initial:
[2,1,0,3,1]
curr = 2
direction = right
nums[2] = 0
Move right
curr = 3
nums[3] = 3
Decrease
[2,1,0,2,1]

Reverse
right → left
Move
curr = 2
Move left
curr = 1
nums[1] = 1
Decrease
[2,0,0,2,1]

Reverse
left → right
Move
curr = 2
Move right
curr = 3
nums[3] = 2

Decrease
[2,0,0,1,1]

Reverse
Move left
curr = 2
Move left
curr = 1
nums[1] = 0
Move left
curr = 0
nums[0] = 2
Decrease
[1,0,0,1,1]
Reverse
Move right
curr = 1
Move right
curr = 2
Move right
curr = 3
nums[3] = 1
Decrease
[1,0,0,0,1]

Reverse
Move left
curr = 2
Move left
curr = 1
Move left
curr = 0
nums[0] = 1

Decrease
[0,0,0,0,1]
Reverse
Move right
curr = 1
Move right
curr = 2

Move right
curr = 3
Move right
curr = 4
nums[4] = 1

Decrease
[0,0,0,0,0]
Reverse

Move left
curr = 3
curr = 2
curr = 1
curr = 0
curr = -1
Out of bounds
Final array:
[0,0,0,0,0]
Valid ✅

How this code thinks:
The code does not simulate the whole movement.
Instead, it uses a smart observation.

For every zero index:
`left = sum of left side`
`right = sum of right side`

If difference is:
* `0` → both directions valid
* `1` → one direction valid
* `>1` → invalid

Because movement becomes zig-zag after hitting non-zero elements, and in the end both sides must be consumed almost equally.

Condition:
```java
if(left >= right && left - right <= 1)
```
means: Left side is slightly larger or equal → one valid direction.

Condition:
```java
if(right >= left && right - left <= 1)
```
means:
Right side is slightly larger or equal → one valid direction.
Difference must be at most `1`, otherwise all elements cannot become `0`.



*/
