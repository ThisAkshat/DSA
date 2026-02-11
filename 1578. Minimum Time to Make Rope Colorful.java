class Solution {
    public int minCost(String colors, int[] neededTime) {
        int ans = 0;
        for (int i = 0; i < colors.length() - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i + 1)) {
                ans += Math.min(neededTime[i], neededTime[i + 1]);
                if (neededTime[i] >= neededTime[i + 1])
                    neededTime[i + 1] = neededTime[i];
            }
        }
        return ans;

    }
}


/*
1) Problem
You have a rope of balloons.
Each balloon has:
* a color
* a time needed to remove it
Alice doesn’t want two same-colored balloons next to each other.
Bob can delete balloons, but each deletion costs time.
Your goal is: Remove the minimum total time worth of balloons so that no two adjacent balloons have the same color.

************************************************************************************************************************

2) Simple example
colors = "aabaa"
neededTime = [1,2,3,4,1]

Rope:
a  a  b  a  a
1  2  3  4  1

There are two bad places:
first two a a
last two a a
We must remove one from each pair
First pair:
Remove cheaper one → min(1,2) = 1
Last pair:
Remove cheaper one → min(4,1) = 1
Total time = 1 + 1 = 2

************************************************************************************************************************

3) Short Dry Run
Example:
colors = "abaac"
neededTime = [1,2,3,4,5]

Compare neighbors:
i = 0
a vs b → different → do nothing
i = 1
b vs a → different → do nothing
i = 2

a vs a → same color ⚠
Remove cheaper:
min(3,4) = 3
ans = 3

Keep the bigger one (4) because it’s better to delete expensive ones later.
i = 3
a vs c → different → stop
Final answer = 3
*/
