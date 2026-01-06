/*
We can think of this as:
"Can we run all n computers for at least X minutes?"

Binary Search on answer (minutes)

Check function: For a given time mid, can batteries support n computers?

Key insight: Any battery can supply at most mid minutes (even if it has more)

Reason: A battery can't be in two computers at same time

DRY RUN for Example 1: n = 2, batteries = [3,3,3]
Step-by-Step:
Initial: sum = 3+3+3 = 9
left = 0, right = sum/n = 9/2 = 4

Iteration 1:
left=0, right=4
mid = (0+4+1)>>1 = (5)>>1 = 2
need = mid*n = 2*2 = 4
have = min(3,2) + min(3,2) + min(3,2) = 2+2+2 = 6
have(6) >= need(4) → True → left = mid = 2

Iteration 2:
left=2, right=4
mid = (2+4+1)>>1 = (7)>>1 = 3
need = 3*2 = 6
have = min(3,3)+min(3,3)+min(3,3) = 3+3+3 = 9
have(9) >= need(6) → True → left = 3

Iteration 3:
left=3, right=4
mid = (3+4+1)>>1 = (8)>>1 = 4
need = 4*2 = 8
have = min(3,4)+min(3,4)+min(3,4) = 3+3+3 = 9
have(9) >= need(8) → True → left = 4

Iteration 4:
left=4, right=4 → Loop ends

Answer: left = 4 ✅

WHY IT WORKS:
Check Function Logic:
For mid = 4 minutes:

Each battery contributes min(battery, 4) minutes

Battery [3,3,3] → 3+3+3 = 9 total minutes available

We need n*mid = 2*4 = 8 minutes

9 >= 8 → Possible!

Binary Search Bounds:
Lower bound (left): 0 minutes

Upper bound (right): sum/n (if all batteries distributed equally)

Mid calculation: (left+right+1)>>1 = ceiling of average

Time Complexity: O(m * log(S/n))
m = batteries.length

S = sum of batteries

Binary search: log(S/n) iterations

Each check: O(m)

*/



class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for(int b : batteries) sum += b;
        long left = 0, right = sum/n;

        while(left<right){
            long mid = (left + right + 1) >> 1;
            long need = mid*n, have = 0;

            for(int b : batteries)
            have += Math.min(b, mid);

            if(have >= need) left = mid;
            else right = mid-1;
        }
        return left;
    }
}
