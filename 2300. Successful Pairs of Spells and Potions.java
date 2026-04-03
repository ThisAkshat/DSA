class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int result[] = new int[n];
        for(int i=0;i<n;i++){
            long minPotion = (success + spells[i]-1) / spells[i];
            int index = lowerBound(potions, minPotion);
            result[i] = m - index;
        }
        return result;
    }
    private int lowerBound(int[] arr, long target){
        int left = 0, right = arr.length;
        while(left < right){
            int mid = left + (right-left)/2;
            if(arr[mid]<target) left = mid+1;
            else right = mid;
        }
        return left;
    }
}

/*
1. PROBLEM :-
   You are given two arrays:

spells → represents strength of spells
potions → represents strength of potions

Also given a value success.

A pair (spell, potion) is successful if:
spell * potion >= success

Goal: For each spell, count how many potions can form a successful pair.
Return an array where:
result[i] = number of potions that can form a successful pair with spells[i]

Core idea:
Instead of checking all pairs (which is O(n*m)), we optimize using sorting + binary search.

Key observation:
For a spell s, we need:
s * potion >= success

So:
potion >= ceil(success / s)
We calculate the minimum potion needed for each spell and then find how many potions satisfy this using binary search.

Steps:
1. Sort the potions array.
2. For each spell:

   * Calculate minimum required potion.
   * Use lower bound (binary search) to find first valid potion.
   * Count remaining potions.

Time Complexity: O(n log m + m log m)
Space Complexity: O(1)

2. EXAMPLE :-
Input
spells = [5,1,3]
potions = [1,2,3,4,5]
success = 7

Step 1
Sort potions

[1,2,3,4,5]
Step 2
For each spell

Spell = 5
Required:
potion >= ceil(7 / 5) = 2
Valid potions:
[2,3,4,5]
Count = 4

---

Spell = 1
Required:
potion >= ceil(7 / 1) = 7
No potion ≥ 7
Count = 0
---

Spell = 3
Required:
potion >= ceil(7 / 3) = 3

Valid potions:
[3,4,5]
Count = 3
Final Output:
[4,0,3]

3. DRY RUN :-
Example (custom tough case)
spells = [4,2,8]
potions = [1,3,5,7,9]
success = 20
Step 1
Sort potions
[1,3,5,7,9]
m = 5

---

Spell = 4
Required:
potion >= ceil(20 / 4) = 5
Binary search lower bound for 5
Index = 2
Valid potions:
[5,7,9]
Count = 5 - 2 = 3
---

Spell = 2
Required:
potion >= ceil(20 / 2) = 10
No such potion exists
Index = 5
Count = 5 - 5 = 0
---

Spell = 8
Required:
potion >= ceil(20 / 8) = 3
Binary search lower bound for 3
Index = 1
Valid potions:
[3,5,7,9]
Count = 5 - 1 = 4
---

Final Answer:
[3,0,4]
*/
