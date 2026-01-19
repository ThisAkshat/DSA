class Solution {

    int B;
    List<Integer>[] tree;
    int[] present, future;
    int[][][] dp; // dp[node][parentBought][budget]

    public int maxProfit(int n, int[] present, int[] future,
            int[][] hierarchy, int budget) {

        this.B = budget;
        this.present = present;
        this.future = future;

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();

        for (int[] e : hierarchy) {
            tree[e[0] - 1].add(e[1] - 1);
        }

        dp = new int[n][2][B + 1];

        dfs(0);

        int ans = 0;
        for (int b = 0; b <= B; b++) {
            ans = Math.max(ans, dp[0][0][b]);
        }
        return ans;
    }

    // Merge two knapsack arrays
    private int[] merge(int[] A, int[] B2) {
        int[] C = new int[B + 1];
        Arrays.fill(C, Integer.MIN_VALUE / 2);

        for (int i = 0; i <= B; i++) {
            if (A[i] < 0)
                continue;
            for (int j = 0; i + j <= B; j++) {
                C[i + j] = Math.max(C[i + j], A[i] + B2[j]);
            }
        }
        return C;
    }

    private void dfs(int u) {
        for (int v : tree[u]) {
            dfs(v);
        }

        for (int parentBought = 0; parentBought <= 1; parentBought++) {

            int price = parentBought == 1 ? present[u] / 2 : present[u];
            int profit = future[u] - price;

            // Option 1: skip buying u
            int[] skip = new int[B + 1];
            for (int v : tree[u]) {
                skip = merge(skip, dp[v][0]);
            }

            // Option 2: buy u
            int[] take = new int[B + 1];
            Arrays.fill(take, Integer.MIN_VALUE / 2);

            if (price <= B) {
                int[] base = new int[B + 1];
                for (int v : tree[u]) {
                    base = merge(base, dp[v][1]);
                }
                for (int b = price; b <= B; b++) {
                    take[b] = base[b - price] + profit;
                }
            }

            // Best of take / skip
            for (int b = 0; b <= B; b++) {
                dp[u][parentBought][b] = Math.max(skip[b], take[b]);
            }
        }
    }
}



/*
1) Problem Explanation
Yeh problem company hierarchy ke employees par stock trading ki hai:
Har employee ek stock khareed sakta hai (max ek baar).
present[i] → aaj ki price.
future[i] → kal ki expected price.
hierarchy → boss-subordinate ka relation (tree format, CEO = employee 1).
budget → kitna paisa total invest kar sakte ho.
Discount rule → Agar kisi employee ka direct boss stock khareedta hai, toh us employee ko apna stock aadhe price (floor(present[i]/2)) par milta hai.
Goal → Budget se zyada na kharch karte hue max profit (future - cost) nikalo.


Constraints:
n ≤ 160, budget ≤ 160, prices ≤ 50 → DP feasible hai.
Tree hai, cycles nahi.


2) Perfect Example (Detailed)
Let’s take Example 4 from problem:

Input:
n = 3
present = [5, 2, 3]
future = [8, 5, 6]
hierarchy = [[1,2], [2,3]] → 1 → 2 → 3 (chain)
budget = 7

Tree:
1 (boss of 2)
2 (boss of 3)
3 (leaf)

Possible scenarios:

Only employee 1 buys:
Cost = 5, Profit = 8-5=3 → Total profit=3.

Only employee 2 buys:
Cost = 2, Profit = 5-2=3 → Total=3.

Only employee 3 buys:
Cost = 3, Profit = 6-3=3 → Total=3.

Employee 1 buys, then 2 gets discount:
Employee 1: cost=5, profit=3
Employee 2: discounted price = floor(2/2)=1, profit=5-1=4
Employee 3: not bought
Total cost = 5+1=6 ≤ budget
Total profit = 3+4=7.

Employee 1 buys, 2 gets discount, 3 gets discount (because 2 is boss of 3):
Employee 1: cost=5, profit=3
Employee 2: discounted price=1, profit=4
Employee 3: discounted price=floor(3/2)=1, profit=6-1=5
Total cost=5+1+1=7 ≤ budget
Total profit=3+4+5=12 ← Maximum.

Employee 2 buys, then 3 gets discount:
Employee 2: cost=2, profit=3
Employee 3: discounted price=1, profit=5
Total cost=3, profit=8
But employee 1 not bought, so 2 didn’t get discount from 1, so 2’s price was 2, not 1 — wait, this is wrong:
Actually: if employee 2 buys without boss 1 buying, then employee 2 pays full price 2, then employee 3 gets discount from 2: floor(3/2)=1.
So cost=2+1=3, profit=(5-2)+(6-1)=3+5=8.
Still less than 12.

Optimal: Buy all three with discount chain: profit=12.



3) Dry Run 
Input: n=3, present=[5,2,3], future=[8,5,6], hierarchy=[[1,2],[2,3]], budget=7

Initialization:
B = 7
tree[0] = [1]
tree[1] = [2]
tree[2] = []
dp[3][2][8] (n=3, parentBought=0/1, budget=0..7)

DFS call order: dfs(2) → dfs(1) → dfs(0)

Step 1: dfs(2) (employee 3, index 2)
tree[2] empty → no children.
parentBought=0 (boss 2 not bought):

price = present[2] = 3, profit = 6-3 = 3

skip array: no children → skip = [0, -inf, -inf, ...]

take: price=3, profit=3
base array from children (dp[child][1]) empty → base = [0, -inf, ...]
So for b=3..7: take[b] = base[b-3] + 3
base[0]=0 → take[3] = 0+3=3, others -inf.
Merge skip and take:
dp[2][0][0..2] = skip[0..2] = 0
dp[2][0][3] = max(skip[3]=0, take[3]=3) = 3
dp[2][0][4..7] = 3 (since take[4..7] = -inf, skip[4..7]=0? Wait skip[4] is 0, so max(0, -inf)=0 — hmm let's check carefully.)

Actually skip = [0, -inf, -inf, ...] initially from no children? Let's check code:
skip = new int[B+1] (all zeros initially).
No children → skip stays [0,0,0,0,0,0,0,0].
take array: base = new int[B+1] (all zeros), then for b from price to B: take[b] = base[b-price] + profit.
base = [0,0,0,0,0,0,0,0]
price=3, profit=3
take[3] = base[0]+3=3
take[4] = base[1]+3=3
...
take[7] = base[4]+3=3
So take = [ -inf, -inf, -inf, 3, 3, 3, 3, 3 ] (with -inf for 0..2)

Now merge skip and take:
skip[b] already 0 for all b. So dp[2][0][b] = max(skip[b], take[b])
So dp[2][0][0..2] = 0, dp[2][0][3..7] = 3.

parentBought=1 (boss 2 bought):
price = present[2]/2 = floor(3/2) = 1
profit = 6-1 = 5
skip same as before (children dp[child][0] merged).
take: price=1, profit=5
base from children dp[child][1] = empty → base = [0,0,...]
take[b] = base[b-1] + 5 for b=1..7
So take[1]=5, take[2]=5, ..., take[7]=5
dp[2][1][b] = max(skip[b]=0, take[b]=5) for b≥1
So dp[2][1][0]=0, dp[2][1][1..7]=5.

End of dfs(2).

Step 2: dfs(1) (employee 2)
Children: [2]
parentBought=0 (boss 1 not bought):
price = present[1] = 2, profit = 5-2=3
skip: merge dp[2][0] → skip = dp[2][0] = [0,0,0,3,3,3,3,3]
take: price=2, profit=3
base = merge dp[child][1] = dp[2][1] = [0,5,5,5,5,5,5,5]
Now base[b-2] + 3:
For b=2: base[0]+3=0+3=3
b=3: base[1]+3=5+3=8
b=4: base[2]+3=5+3=8
...
b=7: base[5]+3=5+3=8
So take = [-inf,-inf, 3,8,8,8,8,8] for b=0..7.

Now dp[1][0][b] = max(skip[b], take[b]):
b=0: max(0,-inf)=0
b=1: max(0,-inf)=0
b=2: max(0,3)=3
b=3: max(3,8)=8
b=4: max(3,8)=8
...
b=7: max(3,8)=8

So dp[1][0] = [0,0,3,8,8,8,8,8]

parentBought=1 (boss 1 bought):
price = present[1]/2 = 1, profit = 5-1=4
skip: merge dp[2][0] = [0,0,0,3,3,3,3,3]
take: price=1, profit=4
base = merge dp[2][1] = [0,5,5,5,5,5,5,5]
take[b] = base[b-1]+4 for b=1..7
b=1: base[0]+4=4
b=2: base[1]+4=5+4=9
b=3: base[2]+4=5+4=9
...
b=7: base[6]+4=5+4=9
take = [-inf,4,9,9,9,9,9,9]

dp[1][1][b] = max(skip[b], take[b]):
b=0: max(0,-inf)=0
b=1: max(0,4)=4
b=2: max(0,9)=9
b=3: max(3,9)=9
...
b=7: max(3,9)=9

So dp[1][1] = [0,4,9,9,9,9,9,9]

Step 3: dfs(0) (employee 1, CEO)
Children: [1] (index 1)
parentBought=0 (no parent, but parameter still 0):
price = present[0] = 5, profit = 8-5=3
skip: merge dp[1][0] = [0,0,3,8,8,8,8,8]
take: price=5, profit=3
base = merge dp[1][1] = [0,4,9,9,9,9,9,9]
take[b] = base[b-5]+3 for b=5..7
b=5: base[0]+3=0+3=3
b=6: base[1]+3=4+3=7
b=7: base[2]+3=9+3=12
take = [-inf,-inf,-inf,-inf,-inf,3,7,12]

dp[0][0][b] = max(skip[b], take[b]):
b=0: 0
b=1: 0
b=2: 3
b=3: 8
b=4: 8
b=5: max(8,3)=8
b=6: max(8,7)=8
b=7: max(8,12)=12 ← max profit found!

parentBought=1 (irrelevant for root but computed anyway)
price=2, profit=6
skip = dp[1][0]
take: price=2, profit=6, base = dp[1][1]
take[b] = base[b-2]+6 for b=2..7
b=2: base[0]+6=0+6=6
b=3: base[1]+6=4+6=10
b=4: base[2]+6=9+6=15
b=5: base[3]+6=9+6=15
b=6: base[4]+6=9+6=15
b=7: base[5]+6=9+6=15
take = [-inf,-inf,6,10,15,15,15,15]
dp[0][1][b] = max(skip[b], take[b])
b=7: max(8,15)=15 but parentBought=1 not possible for root, so ignored.

Final answer:
From dp[0][0][b] for b=0..7, max is dp[0][0][7] = 12.
*/

