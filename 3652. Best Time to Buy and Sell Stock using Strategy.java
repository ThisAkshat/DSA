class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        int half_k = k/2;
        long originalProfit = 0;
        for(int i=0; i<n; i++){
            originalProfit = originalProfit + (long)strategy[i] * prices[i];
        }

        long currentChange = 0;
        for(int i=0; i<k; i++){
            int newVal = (i < half_k) ? 0 : 1;
            currentChange = currentChange + (long)(newVal - strategy[i]) * prices[i];
        }

        long maxChange = currentChange;
        for(int i=k; i<n; i++){
            int leaving_Idx = i - k;
            int leavingPosInWindow = 0;
            int leavingNewVal = (leavingPosInWindow < half_k) ? 0 : 1;
            currentChange = currentChange - (long)(leavingNewVal - strategy[leaving_Idx]) * prices[leaving_Idx];
            int enteringPosInWindow = k -1;
            int enteringNewVal = (enteringPosInWindow < half_k) ? 0 : 1;
            currentChange = currentChange + (long)(enteringNewVal - strategy[i]) * prices[i];
        }

        long[] A = new long[n];
        long[] B = new long[n];

        for(int i=0; i<n; i++){
            A[i] = (long)strategy[i] * prices[i];
            B[i] = prices[i];
        }

        long[] prefixA = new long[n+1];
        long[] prefixB = new long[n+1];

        for(int i=0; i<n; i++){
            prefixA[i+1] = prefixA[i] + A[i];
            prefixB[i+1] = prefixB[i] + B[i];
        }

        maxChange = 0;
        for(int start = 0; start <= n-k; start++){
            int end = start + k - 1;
            int firstHalfEnd = start + half_k - 1;
            long changeFirstHalf = 0;
            if(firstHalfEnd >= start){
                changeFirstHalf = -(prefixA[firstHalfEnd + 1] - prefixA[start]);
            }

            int secondHalfStart = start + half_k;
            long changeSecondHalf = 0;
            if(secondHalfStart <= end){
                long sumB = prefixB[end + 1] - prefixB[secondHalfStart];
                long sumA = prefixA[end + 1] - prefixA[secondHalfStart];
                changeSecondHalf = sumB - sumA;
            }

            long totalChange = changeFirstHalf + changeSecondHalf;
            maxChange = Math.max(maxChange, totalChange);
        }
        return (long)(originalProfit + maxChange);
       }
}

/*
1) Problem Explanation in Hindi
Yeh problem stock trading strategy modification ki hai:
Hume prices array aur strategy array diye gaye hain.
Strategy[i] = -1 → buy, 0 → hold, 1 → sell.
Profit = sum(strategy[i] × prices[i]) over all days.
Hume max ek modification karne ka mauka mil raha hai:
Kisi bhi k consecutive days choose karo,
Unmein pehle k/2 days ko 0 (hold) set karo,
Aur baaki k/2 days ko 1 (sell) set karo.
Goal: Max possible profit nikalo, chahe modification karo ya na karo.

Important note:
Budget ya stock ownership ka koi constraint nahi hai, isliye har trade feasible hai.
Modification optional hai (zero days bhi modify kar sakte ho, lekin k length fix hai).

2) Perfect Example
Let’s take a custom example to understand:

prices = [3, 5, 2, 7, 4, 6],
strategy = [1, -1, 0, 1, -1, 0],
k = 4

Original profit = 3×1 + 5×(-1) + 2×0 + 7×1 + 4×(-1) + 6×0
= 3 - 5 + 0 + 7 - 4 + 0 = 1

Now possible modifications (k=4, so first 2 days → 0, last 2 days → 1):

Window 0..3 (indices 0 to 3):
Original: [1, -1, 0, 1] → Modified: [0, 0, 1, 1]
New strategy = [0, 0, 1, 1, -1, 0]
Profit = 0×3 + 0×5 + 1×2 + 1×7 + (-1)×4 + 0×6
= 0 + 0 + 2 + 7 - 4 + 0 = 5

Window 1..4 (indices 1 to 4):
Original: [-1, 0, 1, -1] → Modified: [0, 0, 1, 1]
New strategy = [1, 0, 0, 1, 1, 0]
Profit = 1×3 + 0×5 + 0×2 + 1×7 + 1×4 + 0×6
= 3 + 0 + 0 + 7 + 4 + 0 = 14

Window 2..5 (indices 2 to 5):
Original: [0, 1, -1, 0] → Modified: [0, 0, 1, 1]
New strategy = [1, -1, 0, 0, 1, 1]
Profit = 1×3 + (-1)×5 + 0×2 + 0×7 + 1×4 + 1×6
= 3 - 5 + 0 + 0 + 4 + 6 = 8

Max profit = max(original=1, 5, 14, 8) = 14

3) Dry Run of Given Code on Example 1
Example 1 from problem:
prices = [4, 2, 8], strategy = [-1, 0, 1], k = 2

Initial steps:
n = 3
half_k = k/2 = 1

originalProfit:
= (-1)×4 + 0×2 + 1×8 = -4 + 0 + 8 = 4

First sliding window approach in code (seems buggy/partial, but let's trace):

currentChange calculation for first window (indices 0..k-1 = 0..1):
newVal for i=0 (pos 0 in window < half_k? yes) → 0
newVal for i=1 (pos 1 in window < half_k? no) → 1

currentChange = Σ (newVal - strategy[i]) × prices[i] for i=0 to 1
= (0 - (-1))×4 + (1 - 0)×2
= (1)×4 + (1)×2 = 4 + 2 = 6

maxChange = 6

Sliding window loop i=k to n-1 (i=2 to 2):

i=2:
leaving_Idx = 2-2 = 0
leavingPosInWindow = 0 (always set to 0? bug)
leavingNewVal = (0 < half_k? yes) → 0
currentChange -= (0 - strategy[0]) × prices[0]
= (0 - (-1))×4 = 1×4 = 4
So currentChange = 6 - 4 = 2

enteringPosInWindow = k-1 = 1
enteringNewVal = (1 < half_k? no) → 1
currentChange += (1 - strategy[2]) × prices[2]
= (1 - 1)×8 = 0×8 = 0
So currentChange = 2 + 0 = 2

maxChange = max(6, 2) = 6

But code resets maxChange to 0 after this loop! Bug:
maxChange = 0; overwrites previous value.

Second part (prefix sums):

A[i] = strategy[i] × prices[i]
A = [-4, 0, 8]
B[i] = prices[i]
B = [4, 2, 8]

prefixA: [0, -4, -4, 4]
prefixB: [0, 4, 6, 14]

maxChange = 0
Loop start from 0 to n-k = 0 to 1:

start=0:
end = 0+2-1=1
firstHalfEnd = 0+1-1=0
changeFirstHalf = -(prefixA[1] - prefixA[0])
= -( -4 - 0 ) = 4

secondHalfStart = 0+1=1
changeSecondHalf:
sumB = prefixB[2] - prefixB[1] = 6 - 4 = 2
sumA = prefixA[2] - prefixA[1] = (-4) - (-4) = 0
changeSecondHalf = sumB - sumA = 2 - 0 = 2

totalChange = 4 + 2 = 6
maxChange = max(0, 6) = 6

start=1:
end = 2
firstHalfEnd = 1+1-1=1
changeFirstHalf = -(prefixA[2] - prefixA[1])
= -( (-4) - (-4) ) = 0

secondHalfStart = 1+1=2
sumB = prefixB[3] - prefixB[2] = 14 - 6 = 8
sumA = prefixA[3] - prefixA[2] = 4 - (-4) = 8
changeSecondHalf = 8 - 8 = 0

totalChange = 0 + 0 = 0
maxChange stays 6

Final profit:
originalProfit + maxChange = 4 + 6 = 10 ✅ matches example.

  */








