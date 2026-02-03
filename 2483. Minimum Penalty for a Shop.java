class Solution {
    public int bestClosingTime(String customers) {
        int penalty = 0;
        int best_penalty = 0;
        int ans = 0;
        char[] ch = customers.toCharArray();
        for(int i=0; i<customers.length(); i++){
            if(ch[i] == 'Y'){
                penalty--;
                if(penalty < best_penalty){
                    best_penalty = penalty;
                    ans = i+1;
                }
            }else penalty++;
        }
        return ans;
    }
} //// ch = ['Y','N','Y','N','Y','N','Y','N','Y','N','Y','Y','N','N','Y','Y','N','Y','N','Y']

/*
You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':

if the ith character is 'Y', it means that customers come at the ith hour
whereas 'N' indicates that no customers come at the ith hour.
If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:

For every hour when the shop is open and no customers come, the penalty increases by 1.
For every hour when the shop is closed and customers come, the penalty increases by 1.
Return the earliest hour at which the shop must be closed to incur a minimum penalty.

Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.


****************************************************************************************************************

Example 1:

Input: customers = "YYNY"
Output: 2
Explanation: 
- Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
- Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
- Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
- Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
- Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.

****************************************************************************************************************

Short dry run:-
Example: "YYNY"

Start: penalty = 0, best = 0, ans = 0

i=0 → Y
penalty = -1 → new best
ans = 1

i=1 → Y
penalty = -2 → new best
ans = 2

i=2 → N
penalty = -1 (worse, ignore)

i=3 → Y
penalty = -2 (equal best, but we keep earlier → ignore)

Final: ans = 2 Correct.
*/
