class Solution {
    public int minimumBoxes(int[] apple, int[] capacity){
        int totalApples = 0;
        for(int a: apple) totalApples += a;
        
        Arrays.sort(capacity);

        int count = 0;
        int currentCapacity = 0;

        for(int i = capacity.length - 1; i>=0; i--){
            currentCapacity += capacity[i];
            count++;

            if(currentCapacity >= totalApples){
                return count;
            }
        }
        return count;
    }
}
/*
What the problem really asks
You have:
some apple packs → apple[]
some boxes → capacity[]
You can split any pack into multiple boxes, so only this matters:
Do the selected boxes have total capacity ≥ total apples?
You want to pick the minimum number of boxes whose total capacity can hold all apples.

Step 1 — Count all apples
int totalApples = 0;
for(int a: apple) totalApples += a;
Because splitting is allowed, we don’t care about individual packs anymore — only the total number of apples.

Step 2 — Sort box capacities
Arrays.sort(capacity);

Now capacities are in ascending order.
We will take boxes from the largest capacity first.
Why?
Because big boxes fill faster → fewer boxes needed.
This is classic greedy.

Step 3 — Pick boxes from largest to smallest
for(int i = capacity.length - 1; i >= 0; i--){
    currentCapacity += capacity[i];
    count++;
    if(currentCapacity >= totalApples){
        return count;
    }
}
We keep adding the biggest available box until:
currentCapacity ≥ totalApples
The moment that happens, we stop — because using any smaller boxes would only increase the count.

*******************************************************************************************************************

Short dry run
Example:
apple = [1,3,2] → total = 6
capacity = [4,3,1,5,2]

Sort:
[1,2,3,4,5]

Take from end:
take 5 → sum = 5 (count = 1)
take 4 → sum = 9 (count = 2)  >= 6 → stop
Answer = 2
*/
