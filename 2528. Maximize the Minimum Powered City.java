class Solution {
    public long maxPower(int[] nums, int r, int k) {
        int n = nums.length;
        long[] prefix = new long[n+1];
        for(int i=0;i<n;i++){
            int left = Math.max(0, i-r);
            int right = Math.min(n-1, i+r);
            prefix[left] += nums[i];
            if(right + 1 < n)
            prefix[right+1] -= nums[i];
        }
        for(int i=1; i<n; i++){
            prefix[i] += prefix[i-1];
        }
        long maxP = k;
        for(int num : nums) maxP += num;

        long left =0, right = maxP;
        while(left<right){
            long mid = (left+right)/2;
            if(isPossible(prefix, mid+1, k, r))
            left = mid+1;
            else
            right = mid;
        }
        return left;
    }
    boolean isPossible(long[] prefix, long minPower, long k, int r){
        int n = prefix.length;
        long[] diff = new long[n+1];
        for(int i=0;i<n-1;i++){
            if(i>0) diff[i] += diff[i-1];
            long current = prefix[i] + diff[i];
            long need = Math.max(0, minPower - current);
            if(need == 0) continue;
            if(need > k) return false;
            k = k -need;
            diff[i] = diff[i] + need;
            int end = Math.min(n-1, i+2*r+1);
            diff[end] = diff[end] - need;
        }
        return true;
    }
}



/*

* What this problem really asks

You have cities on a line.
Each city already has some power stations.
Each station covers range r left & right.
So each city’s power = sum of stations in [i - r … i + r]
You can add k new stations anywhere.
Your goal is: Make the weakest city as strong as possible.
This is a classic: “maximize the minimum” → Binary Search.


**********************************************************************************************************

Example 1:

Input: stations = [1,2,4,5,0], r = 1, k = 2
Output: 5
Explanation: 
One of the optimal ways is to install both the power stations at city 1. 
So stations will become [1,4,4,5,0].
- City 0 is provided by 1 + 4 = 5 power stations.
- City 1 is provided by 1 + 4 + 4 = 9 power stations.
- City 2 is provided by 4 + 4 + 5 = 13 power stations.
- City 3 is provided by 5 + 4 = 9 power stations.
- City 4 is provided by 5 + 0 = 5 power stations.
So the minimum power of a city is 5.
Since it is not possible to obtain a larger power, we return 5.

**********************************************************************************************************

* Dry run :-
prefix = [3,7,11,9,5]
k = 2
Try minPower = 5

City 0
current = 3
need = 5 - 3 = 2
k = 2 → 0
We add 2 stations at city 1
They affect [0 … 2]
diff[0] += 2
diff[3] -= 2

Now: power becomes [5, 9, 13, 9, 5]
All cities ≥ 5 ✔
So 5 is possible.

Try 6
City 0:
current = 3
need = 3
k = 2 ❌
Not possible.
So max = 5

*/
