class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            int mod = ((num%value)+value) % value;
            map.put(mod, map.getOrDefault(mod, 0)+1);
        }
        int i=0;
        while(true){
            int mod = i% value;
            if(!map.containsKey(mod) || map.get(mod) == 0)
            return i;
            map.put(mod, map.get(mod)-1);
            i++;
        }
    }
}
/*
1. PROBLEM :-
   You are given an array nums and an integer value.
Operation allowed:
You can add or subtract value any number of times to any element.

Goal:
After applying operations, maximize the MEX (Minimum Excluded Number).
Definition:
MEX = smallest non-negative integer not present in the array.
Key observation:
By adding or subtracting value, any number can be converted to another number with the same remainder modulo value.

So:
Only remainder (num % value) matters.

Approach:
1. Count how many numbers belong to each remainder class (mod value).
2. Try to build numbers from 0 upwards.
3. For each number i:
   * Required remainder = i % value
   * If we have a number with that remainder → use it
   * Otherwise → i is the MEX

Time Complexity: O(n)
Space Complexity: O(value)

2. EXAMPLE :-
Input
nums = [1,-10,7,13,6,8]
value = 5

Step 1: Convert all numbers to modulo form
1 % 5 = 1
-10 % 5 = 0
7 % 5 = 2
13 % 5 = 3
6 % 5 = 1
8 % 5 = 3

Frequency map:
0 → 1
1 → 2
2 → 1
3 → 2

Step 2: Build MEX
i = 0
need mod = 0
available → use it
map[0] = 0

i = 1
need mod = 1
available → use it
map[1] = 1

i = 2
need mod = 2
available → use it
map[2] = 0

i = 3
need mod = 3
available → use it
map[3] = 1
i = 4
need mod = 4
not available → stop
MEX = 4
Output = 4


3. DRY RUN :-
Example (custom case)
nums = [2,5,8,11,14]
value = 3

Step 1: Compute modulo
2 % 3 = 2
5 % 3 = 2
8 % 3 = 2
11 % 3 = 2
14 % 3 = 2

Map:
2 → 5
Step 2: Build MEX
i = 0
need mod = 0
not present → stop

MEX = 0
---

Another case:
nums = [0,3,6,1,4,7]
value = 3

Modulo:
0 → 3
1 → 2

Map:
0 → 3
1 → 2

Step-by-step:
i = 0 → mod 0 → use → left 2
i = 1 → mod 1 → use → left 1
i = 2 → mod 2 → not present → stop
MEX = 2
*/
