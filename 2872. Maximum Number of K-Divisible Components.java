class Solution {
    int components = 0;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>(); /*
graph[0] = []
graph[1] = [] 
graph[2] = []
graph[3] = []
graph[4] = []
*/
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
/*
graph[0] = [2]
graph[1] = [2, 3]
graph[2] = [0, 1, 4] 
graph[3] = [1]
graph[4] = [2]
*/
        dfs(0, -1, graph, values, k);
        return components;
    }
        private long dfs(int node, int parent, List<Integer>[] graph, int[] values, int k){
            long sum = values[node];
            for(int neighbor : graph[node]){
                if(neighbor != parent){
                    sum += dfs(neighbor, node, graph, values, k);
                }
            }
            if(sum % k == 0){
                components++;
                return 0;
            }
            return sum;
    }
}
/*
n = 5
edges = [[0,2],[1,2],[1,3],[2,4]]
values = [1,8,1,4,4]
k = 6 
*/







/*
Explanation :-
Is problem mein hume tree ko aise todna hai ki har part (component) ka total sum **k se divisible ho**, aur aise maximum number of components banane hain.
Hum DFS (Depth First Search) use karte hain, bottom se top tak. Har node apne subtree ka total sum calculate karta hai (khud ka value + bachon ka sum).
Agar kisi node ka subtree sum **k se divisible ho jata hai**, toh hum us part ko alag component maan lete hain (components++ karte hain) aur parent ko **0 return karte hain**, kyunki wo part already cut ho chuka hai.
Agar sum divisible nahi hai, toh hum wo sum parent ko return kar dete hain taaki upar wale nodes ke saath combine ho sake.
Is approach mein hum greedily kaam karte hain — jaise hi koi valid component milta hai, usse turant cut kar dete hain, taki maximum components mil sake.
End mein total components hi answer hota hai.

***********************************************************************************************************************************

Example -
Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
Output: 2
Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
It can be shown that no other valid split has more than 2 connected components.

***********************************************************************************************************************************

Dry Run :-

Start DFS from node 0
Node 3 (leaf)
sum = 4 → not divisible → return 4
Node 1
sum = 8 + 4 = 12 → divisible → components = 1 → return 0
Node 4 (leaf)
sum = 4 → not divisible → return 4
Node 2
sum = 1 + 0 + 4 = 5 → not divisible → return 5
Node 0
sum = 1 + 5 = 6 → divisible → components = 2 → return 0
Final Answer = 2

*/
