class Solution {
    public int findFinalValue(int[] nums, int original) {
        int n = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i<n;i++){
            if(nums[i] == original){
                original = 2*nums[i];
            }
        }
        return original;
    }
}


/*
PROBLEM :-
Tumhe ek array nums diya hai aur ek number original diya hai.
Ab tumhe repeatedly check karna hai:
1. Agar original number array me milta hai → usko double kar do (original = original × 2).
2. Fir naye original ko again array me search karo.
3. Jab tak number milta rahe → double karte raho.
4. Jaise hi number array me milna band ho jaye → wahi final answer hai.

*******************************************************************************************************

Example 1:

Input: nums = [5,3,6,1,12], original = 3
Output: 24
Explanation: 
- 3 is found in nums. 3 is multiplied by 2 to obtain 6.
- 6 is found in nums. 6 is multiplied by 2 to obtain 12.
- 12 is found in nums. 12 is multiplied by 2 to obtain 24.
- 24 is not found in nums. Thus, 24 is returned.

*******************************************************************************************************
Dry run for example: nums = [5,3,6,1,12], original = 3

Step 0: sort array
nums = [1, 3, 5, 6, 12]
original = 3

Step 1: i = 0
nums[0] = 1
1 != 3 → nothing happens
original = 3

Step 2: i = 1
nums[1] = 3
3 == original → original = 2 × 3 = 6

Step 3: i = 2
nums[2] = 5
5 != 6 → nothing happens
original = 6

Step 4: i = 3
nums[3] = 6
6 == original → original = 2 × 6 = 12

Step 5: i = 4
nums[4] = 12
12 == original → original = 2 × 12 = 24

Loop ends

Final original = 24

Return 24

*/
