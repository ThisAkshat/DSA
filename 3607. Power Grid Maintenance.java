import java.util.*;

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1];
        int[] minOnline = new int[c + 1];
        boolean[] online = new boolean[c + 1];
        
        // Priority Queue for each component
        Map<Integer, PriorityQueue<Integer>> componentMin = new HashMap<>();

        // Initialize
        for (int i = 1; i <= c; i++) {
            parent[i] = i;
            minOnline[i] = i;
            online[i] = true;
            componentMin.put(i, new PriorityQueue<>());
            componentMin.get(i).add(i);
        }

        // Build connections with union-by-rank
        for (int[] conn : connections) {
            union(parent, minOnline, componentMin, conn[0], conn[1]);
        }

        List<Integer> result = new ArrayList<>();
        
        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];
            
            if (type == 1) {
                if (online[x]) {
                    result.add(x);
                } else {
                    int root = find(parent, x);
                    // Get min from priority queue
                    PriorityQueue<Integer> pq = componentMin.get(root);
                    while (!pq.isEmpty() && !online[pq.peek()]) {
                        pq.poll(); // Remove offline stations
                    }
                    result.add(pq.isEmpty() ? -1 : pq.peek());
                }
            } else {
                online[x] = false;
                // No need to update immediately - we'll clean during query
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    
    private void union(int[] parent, int[] minOnline, Map<Integer, PriorityQueue<Integer>> componentMin, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        
        if (rootX != rootY) {
            // Always attach smaller to larger
            if (componentMin.get(rootX).size() < componentMin.get(rootY).size()) {
                // Merge rootX into rootY
                parent[rootX] = rootY;
                componentMin.get(rootY).addAll(componentMin.get(rootX));
                componentMin.remove(rootX);
            } else {
                // Merge rootY into rootX
                parent[rootY] = rootX;
                componentMin.get(rootX).addAll(componentMin.get(rootY));
                componentMin.remove(rootY);
            }
        }
    }
}



/*

* What is the problem really about?
You have power stations.
Some are connected by cables → they form power grids (groups).
Inside one grid:
. Stations are connected directly or indirectly
. Even if a station goes offline, it is still part of that grid
You’ll get two types of queries:
Type 1 — [1, x]
You ask: “Who should handle the job for station x?”
Rules:
. If x is online → x does the job
. If x is offline → find the smallest-numbered online station in the same grid
. If nobody is online → return -1

Type 2 — [2, x]
Station x goes offline
It stays in the grid, but can’t help anymore.

*******************************************************************************************************************************************************************************************************

Example 1:
Input: c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]
Output: [3,2,3]

Explanation:
Initially, all stations {1, 2, 3, 4, 5} are online and form a single power grid.
Query [1,3]: Station 3 is online, so the maintenance check is resolved by station 3.
Query [2,1]: Station 1 goes offline. The remaining online stations are {2, 3, 4, 5}.
Query [1,1]: Station 1 is offline, so the check is resolved by the operational station with the smallest id among {2, 3, 4, 5}, which is station 2.
Query [2,2]: Station 2 goes offline. The remaining online stations are {3, 4, 5}.
Query [1,2]: Station 2 is offline, so the check is resolved by the operational station with the smallest id among {3, 4, 5}, which is station 3.

*******************************************************************************************************************************************************************************************************

DRY RUN :-

n = 5
connections = [[1,2],[2,3],[3,4],[4,5]]
queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]

. STEP 1 — Initialization
For every station 1..5:

i	parent[i]	online[i]	pq[i]
1	  1	       true	   {1}
2	  2	       true	   {2}
3	  3	       true	   {3}
4	  4      	 true	   {4}
5	  5	       true	   {5}

Each node is its own grid.

🔗 STEP 2 — Process connections
We now union stations.

union(1,2)
root1 = 1
root2 = 2
parent[2] = 1
pq[1] = {1,2}

union(2,3)
root(2) → 1
root(3) → 3
parent[3] = 1
pq[1] = {1,2,3}

union(3,4)
root(3) → 1
root(4) → 4
parent[4] = 1
pq[1] = {1,2,3,4}

union(4,5)
root(4) → 1
root(5) → 5
parent[5] = 1
pq[1] = {1,2,3,4,5}

Final grid:
All stations → root 1
PQ[1] = {1,2,3,4,5}

🧪 Now process queries
We also keep:
online[] = [true,true,true,true,true]
Query 1 → [1,3]
Check online[3] → true
So directly return 3

No heap needed.

Query 2 → [2,1]
Station 1 goes offline:
online[1] = false
Heap still has 1, but we don’t remove it now.

Query 3 → [1,1]
Station 1 is offline → need replacement.
root = find(1) → 1
pq = {1,2,3,4,5}
Now we clean heap top:

top	online?	action
1	false	pop
2	true	stop

So answer = 2

Heap now effectively:

{2,3,4,5}
Query 4 → [2,2]
online[2] = false
Heap still {2,3,4,5} but 2 is now invalid.

Query 5 → [1,2]
2 is offline → check grid
root = find(2) → 1
pq = {2,3,4,5}
Clean heap:
top	online?	action
2	   false   pop
3	   true 	stop

Answer = 3

Heap now:
{3,4,5}

📤 Final Output
From all type-1 queries:
[3, 2, 3]
*/
