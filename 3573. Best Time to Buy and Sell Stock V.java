class Solution{
    int n,k;
    int[] prices;
    Long[][][] dp;
    final long NEG = (long)-1e18;

    long dfs(int i, int t, int state){
        if(i == n){
            return state == 0 ? 0 : NEG;
        }
        if(dp[i][t][state] != null)  return dp[i][t][state];

        long ans = dfs(i+1, t, state);
        if(state == 0){
            ans = Math.max(ans, dfs(i+1, t, 1) - prices[i]);
            ans = Math.max(ans, dfs(i+1, t, 2) + prices[i]);
        }
        else if(state == 1 && t < k){
            ans = Math.max(ans, dfs(i+1, t+1, 0) + prices[i]);
        }
        else if(state == 2 && t < k){
            ans = Math.max(ans, dfs(i+1, t+1, 0) - prices[i]);
        }
        return dp[i][t][state] = ans;
    }
    public long maximumProfit(int[] prices, int k ){
        this.prices = prices;
        this.n = prices.length;
        this.k = k;
        dp = new Long[n+1][k+1][3];
        return dfs(0, 0, 0);
    }
}


/*
1) Problem Explanation in Hindi
Yeh problem stock trading ki hai, lekin do type ke transactions allowed hain:

Normal transaction:
Pehle kharido (buy), phir baad mein becho (sell).
Profit = sell_price - buy_price.

Short selling transaction:
Pehle becho (sell), phir baad mein kharido (buy).
Profit = sell_price - buy_price (yahan sell_price pehle, buy_price baad mein).

Rules:
Max k transactions kar sakte ho.
Ek transaction pehle complete karo tabhi dusra shuru kar sakte ho.
Ek din mein do operations nahi kar sakte ek hi transaction ke liye (i.e., buy aur sell same day nahi ho sakte).
Goal: Max total profit nikalo at most k transactions mein.
  
Example 1:
prices = [1,7,9,8,2], k=2
Normal: buy day0(1), sell day2(9) → profit 8
Short sell: sell day3(8), buy day4(2) → profit 6
Total = 14.

2) Perfect Example
Let’s take a custom example to see all possibilities:
prices = [10, 5, 8, 12, 7, 15], k=2
Possible trades:
Normal: buy@5, sell@12 → profit 7
Short sell: sell@10, buy@5 → profit 5
Normal: buy@7, sell@15 → profit 8
Combinations with k=2:
Normal(5→12) + Normal(7→15) = 7+8=15
Short(10→5) + Normal(7→15) = 5+8=13
Short(10→5) + Short(12→7) = 5+5=10
Normal(5→12) + Short(12→7) → possible? Sequence matters:
Buy@5, Sell@12 (normal done), then Sell@12 again? No, because after selling@12, you can't immediately sell again same day for short — you must buy first in short transaction, so invalid.
Actually, short transaction needs sell first then buy later, so after normal sell@12, you can't sell again same day.
Better to visualize states in DP.

3) Dry Run:-
prices = [1,7,9,8,2], k=2
Initialization:
n=5, k=2
dp[6][3][3] (i, t, state)
state: 0=no active position, 1=holding after buy (normal started), 2=holding after sell (short started)

NEG = -1e18 (acts as -inf)

dfs(0, 0, 0):

At i=0, t=0, state=0:
Option 1: skip → dfs(1,0,0)
Option 2: state=0 → can start normal buy → dfs(1,0,1) - price[0]
Option 3: state=0 → can start short sell → dfs(1,0,2) + price[0]

Let’s compute bottom-up conceptually (but recursion goes deep first).

We’ll trace manually key calls:

Base case: i=5 (end), state must be 0 to get 0, else NEG.

Let’s fill dp from i=4 backwards:

i=4, price=2

t=0, state=0:
skip→i=5,state=0→0
start normal buy: dfs(5,0,1)-2 = NEG-2 = NEG
start short sell: dfs(5,0,2)+2 = NEG+2 = NEG
max=0 → dp[4][0][0]=0

t=0, state=1 (holding after buy):
skip→ dfs(5,0,1)=NEG
if t<k: close normal → dfs(5,1,0)+2 = 0+2=2
max=2 → dp[4][0][1]=2

t=0, state=2 (holding after short sell):
skip→ dfs(5,0,2)=NEG
if t<k: close short → dfs(5,1,0)-2 = 0-2=-2
max=-2 → dp[4][0][2]=-2

t=1, state=0:
skip→0
start buy: dfs(5,1,1)-2=NEG
start short: dfs(5,1,2)+2=NEG
max=0 → dp[4][1][0]=0

t=1, state=1:
skip→NEG
if t<k: close normal→ dfs(5,2,0)+2=0+2=2
max=2 → dp[4][1][1]=2

t=1, state=2:
skip→NEG
if t<k: close short→ dfs(5,2,0)-2=0-2=-2
max=-2 → dp[4][1][2]=-2

t=2, state=0:
skip→0, others NEG → dp[4][2][0]=0

t=2, state=1:
skip→NEG, t<k false → NEG

t=2, state=2:
skip→NEG, t<k false → NEG

i=3, price=8:

t=0, state=0:
skip→dp[4][0][0]=0
start buy→dp[4][0][1]-8=2-8=-6
start short→dp[4][0][2]+8=-2+8=6
max= max(0,-6,6)=6 → dp[3][0][0]=6

t=0, state=1:
skip→dp[4][0][1]=2
if t<k: close normal→dp[4][1][0]+8=0+8=8
max(2,8)=8 → dp[3][0][1]=8

t=0, state=2:
skip→dp[4][0][2]=-2
if t<k: close short→dp[4][1][0]-8=0-8=-8
max(-2,-8)=-2 → dp[3][0][2]=-2

t=1, state=0:
skip→dp[4][1][0]=0
start buy→dp[4][1][1]-8=2-8=-6
start short→dp[4][1][2]+8=-2+8=6
max(0,-6,6)=6 → dp[3][1][0]=6

t=1, state=1:
skip→dp[4][1][1]=2
if t<k: close normal→dp[4][2][0]+8=0+8=8
max(2,8)=8 → dp[3][1][1]=8

t=1, state=2:
skip→dp[4][1][2]=-2
if t<k: close short→dp[4][2][0]-8=0-8=-8
max(-2,-8)=-2 → dp[3][1][2]=-2

t=2, state=0:
skip→dp[4][2][0]=0
start buy→dp[4][2][1]-8=NEG
start short→dp[4][2][2]+8=NEG
max=0 → dp[3][2][0]=0

others t=2, state=1/2 → NEG.

i=2, price=9:

t=0, state=0:
skip→dp[3][0][0]=6
start buy→dp[3][0][1]-9=8-9=-1
start short→dp[3][0][2]+9=-2+9=7
max(6,-1,7)=7 → dp[2][0][0]=7

t=0, state=1:
skip→dp[3][0][1]=8
if t<k: close normal→dp[3][1][0]+9=6+9=15
max(8,15)=15 → dp[2][0][1]=15

t=0, state=2:
skip→dp[3][0][2]=-2
if t<k: close short→dp[3][1][0]-9=6-9=-3
max(-2,-3)=-2 → dp[2][0][2]=-2

t=1, state=0:
skip→dp[3][1][0]=6
start buy→dp[3][1][1]-9=8-9=-1
start short→dp[3][1][2]+9=-2+9=7
max(6,-1,7)=7 → dp[2][1][0]=7

t=1, state=1:
skip→dp[3][1][1]=8
if t<k: close normal→dp[3][2][0]+9=0+9=9
max(8,9)=9 → dp[2][1][1]=9

t=1, state=2:
skip→dp[3][1][2]=-2
if t<k: close short→dp[3][2][0]-9=0-9=-9
max(-2,-9)=-2 → dp[2][1][2]=-2

t=2, state=0:
skip→dp[3][2][0]=0
start buy→NEG, start short→NEG → dp[2][2][0]=0

i=1, price=7:

t=0, state=0:
skip→dp[2][0][0]=7
start buy→dp[2][0][1]-7=15-7=8
start short→dp[2][0][2]+7=-2+7=5
max(7,8,5)=8 → dp[1][0][0]=8

t=0, state=1:
skip→dp[2][0][1]=15
if t<k: close normal→dp[2][1][0]+7=7+7=14
max(15,14)=15 → dp[1][0][1]=15

t=0, state=2:
skip→dp[2][0][2]=-2
if t<k: close short→dp[2][1][0]-7=7-7=0
max(-2,0)=0 → dp[1][0][2]=0

t=1, state=0:
skip→dp[2][1][0]=7
start buy→dp[2][1][1]-7=9-7=2
start short→dp[2][1][2]+7=-2+7=5
max(7,2,5)=7 → dp[1][1][0]=7

t=1, state=1:
skip→dp[2][1][1]=9
if t<k: close normal→dp[2][2][0]+7=0+7=7
max(9,7)=9 → dp[1][1][1]=9

t=1, state=2:
skip→dp[2][1][2]=-2
if t<k: close short→dp[2][2][0]-7=0-7=-7
max(-2,-7)=-2 → dp[1][1][2]=-2

t=2, state=0:
skip→dp[2][2][0]=0 → dp[1][2][0]=0

i=0, price=1:

t=0, state=0:
skip→dp[1][0][0]=8
start buy→dp[1][0][1]-1=15-1=14
start short→dp[1][0][2]+1=0+1=1
max(8,14,1)=14 → dp[0][0][0]=14

Answer = dfs(0,0,0) = 14 ✅
*/








