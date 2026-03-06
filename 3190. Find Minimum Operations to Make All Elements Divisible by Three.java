class Solution {
    public int minimumOperations(int[] nums) {
        int count = 0 ;
        for(int num : nums){
            if(num%3 != 0){
                count++;
            }
        }
        return count;
        }
    }

/*
PROBLEM :- You are given an integer array nums. In one operation, you can add or subtract 1 from any element of nums.
Return the minimum number of operations to make all elements of nums divisible by 3.

************************************************************************************************************************

Example 1:
Input: nums = [1,2,3,4]
Output: 3
Explanation:
All array elements can be made divisible by 3 using 3 operations:
Subtract 1 from 1.
Add 1 to 2.
Subtract 1 from 4.

Example 2:
Input: nums = [3,6,9]
Output: 0

************************************************************************************************************************

Dry run on a tougher example

Example:
nums = [7, 10, 12, 14, 15, 2]

Goal: count elements that are not divisible by 3.

Initial:
count = 0

---

Step 1
num = 7
7 % 3 = 1 → not divisible by 3

One operation needed (7 → 6 or 7 → 9)

count = 1

---

Step 2
num = 10
10 % 3 = 1 → not divisible by 3

One operation needed (10 → 9 or 10 → 12)

count = 2

---

Step 3
num = 12
12 % 3 = 0 → already divisible by 3

No operation

count = 2

---

Step 4
num = 14
14 % 3 = 2 → not divisible by 3

One operation needed (14 → 15 or 14 → 12)

count = 3

---

Step 5
num = 15
15 % 3 = 0 → already divisible by 3

No operation

count = 3

---

Step 6
num = 2
2 % 3 = 2 → not divisible by 3

One operation needed (2 → 3 or 2 → 0)

count = 4

---

Final

Total operations needed = 4
Return 4

*/
