class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] answer = new int[n];

        for(int i = n-1; i>=0; i--){
            int ni = i+k;
            if(ni>=n){
                answer[i] = energy[i];
            }
            else{
                answer[i] = energy[i] + answer[ni];
            }
        }
        int maxEnergy = Integer.MIN_VALUE;
        for(int val: answer){
            maxEnergy = Math.max(maxEnergy, val);
        }
        return maxEnergy;
    }
}
/*
1. PROBLEM :-
   You are given an array energy where each element represents the energy of a magician.

Rules:
* You can start from any index i.
* After taking energy from index i, you must jump to i + k.
* Continue jumping by k steps until you go out of the array.
* You must take energy at every visited index (even if negative).

Goal:
Find the maximum total energy you can collect by choosing the best starting index.
Core idea:
This is a dynamic programming problem.

Instead of starting from every index and calculating forward, we calculate from the end backward.
For each index i:
answer[i] = energy[i] + answer[i + k] (if exists)
Otherwise:
answer[i] = energy[i]

Finally, take the maximum value from the answer array.

Time Complexity: O(n)
Space Complexity: O(n)

2. EXAMPLE :-
Input
energy = [5,2,-10,-5,1]
k = 3

Step-by-step:

Start from index 0:
5 → jump to 3 → -5
Total = 5 + (-5) = 0

Start from index 1:
2 → jump to 4 → 1
Total = 2 + 1 = 3

Start from index 2:
-10 → jump to 5 (out of bounds)
Total = -10

Start from index 3:
-5 → jump to 6 (out of bounds)
Total = -5

Start from index 4:
1 → end
Total = 1
Maximum = 3
Output = 3


3. DRY RUN :-
Example (custom tough case)
energy = [4, -2, 3, -1, 6, -5, 2]
k = 2
n = 7

Initialize answer array from back:
i = 6
answer[6] = 2

---

i = 5
answer[5] = -5

---

i = 4
answer[4] = 6 + answer[6]
= 6 + 2 = 8

---

i = 3
answer[3] = -1 + answer[5]
= -1 + (-5) = -6

---

i = 2
answer[2] = 3 + answer[4]
= 3 + 8 = 11

---

i = 1
answer[1] = -2 + answer[3]
= -2 + (-6) = -8

---

i = 0
answer[0] = 4 + answer[2]
= 4 + 11 = 15

---

Final answer array:
[15, -8, 11, -6, 8, -5, 2]

Maximum value = 15
Best path:
Start at index 0 → 4 → 3 → 6 → 2
Total = 4 + 3 + 6 + 2 = 15

Final Answer = 15
*/
