class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int count1 = 0;
        for(int i = 0; i<n; i++){
            if(nums[i] == 1)
            count1++;
        }
        if(count1 > 0){
            return n - count1;
        }
        int minSetsTol = Integer.MAX_VALUE;
        for(int i =0; i<n; i++){
            int GCD = nums[i];
            for(int j = i+1; j<n; j++){
                GCD = gcd(GCD, nums[j]);

                if(GCD == 1){
                    minSetsTol = Math.min(minSetsTol, j-i);
                    break;
                }
            }
        }
        if(minSetsTol == Integer.MAX_VALUE){
            return -1;
        }
        return minSetsTol + (n-1);
    }
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

/*
Problem - 
You are given a 0-indexed array nums consisting of positive integers. You can do the following operation on the array any number of times:

Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.

The gcd of two integers is the greatest common divisor of the two integers.

***********************************************************************************************************************************************

Example 1:
Input: nums = [2,6,3,4]
Output: 4
Explanation: We can do the following operations:
- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].

Example 2:
Input: nums = [2,10,6,14]
Output: -1
Explanation: It can be shown that it is impossible to make all the elements equal to 1.

***********************************************************************************************************************************************

Dry run for:

nums = [2,6,3,4]

n = 4

---

Step 1: Count how many 1s are already present

count1 = 0
Array has no 1

So move to next part.

---

Step 2: Find smallest subarray whose gcd becomes 1

Initialize:
minSetsTol = infinity

---

i = 0
GCD = nums[0] = 2

j = 1
GCD = gcd(2,6) = 2
Not 1, continue

j = 2
GCD = gcd(2,3) = 1
Now GCD becomes 1

subarray length difference = j - i = 2 - 0 = 2
minSetsTol = 2
Break inner loop

---

i = 1
GCD = nums[1] = 6

j = 2
GCD = gcd(6,3) = 3
Not 1

j = 3
GCD = gcd(3,4) = 1
Now GCD becomes 1

j - i = 3 - 1 = 2
minSetsTol = min(2,2) = 2
Break

---

i = 2
GCD = nums[2] = 3

j = 3
GCD = gcd(3,4) = 1

j - i = 3 - 2 = 1
minSetsTol = min(2,1) = 1
Break

---

i = 3
No j possible

---

After loops:

minSetsTol = 1

---

Final calculation:

Return minSetsTol + (n - 1)

= 1 + (4 - 1)
= 1 + 3
= 4

---

Final answer: 4

*/

