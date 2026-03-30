class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> lastRains = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        for(int i=0;i<n;i++){
            int lake = rains[i];
            if(lake > 0){
                if(lastRains.containsKey(lake)){
                    Integer dryDay = dryDays.higher(lastRains.get(lake));
                    if(dryDay == null) return new int[0];
                    ans[dryDay] = lake;
                    dryDays.remove(dryDay);
                }
                lastRains.put(lake, i);
                ans[i]=-1;
            }
            else{
                    dryDays.add(i);
                    ans[i] = 1;
                }
            }
        return ans;
    }
}

/*
1. PROBLEM :-
   You are given an array rains where each index represents a day.

Meaning of values:
rains[i] > 0 → It rains on lake number rains[i], and that lake becomes full.
rains[i] = 0 → No rain that day, and you can choose one lake to dry.

Important rule:
If it rains on a lake that is already full, a flood occurs. You must prevent this.

Goal:
Return an array ans such that:
ans[i] = -1 if it rains that day
ans[i] = lake number that you decide to dry on a dry day

If it is impossible to avoid a flood, return an empty array.

Core idea of the solution:
We track:

1. When a lake was last filled.
2. Which days are available to dry lakes.

Data structures used:
HashMap lastRains → stores the last day a lake was filled.
TreeSet dryDays → stores indices of days where drying is possible.

Why TreeSet is used:
It helps find the next available dry day after a certain day.

Algorithm logic:
1. Traverse each day.
2. If it rains on a lake:
   * Check if the lake was already full.
   * If yes, we must dry it before today.
   * Find a valid dry day using TreeSet.
   * If not found → flood is unavoidable.
3. If it is a dry day:
   * Store the day in dryDays.
4. Update records accordingly.
Time Complexity: O(n log n)
Space Complexity: O(n)

2. EXAMPLE :-
Input
rains = [1,2,0,0,2,1]

Day by day process:

Day 0
Rain on lake 1
Lake 1 becomes full
ans = [-1, ?, ?, ?, ?, ?]

Day 1
Rain on lake 2
Lake 2 becomes full
ans = [-1, -1, ?, ?, ?, ?]

Day 2
No rain → drying possible
Store day 2 as dry day
ans = [-1, -1, 1, ?, ?, ?]
(default value temporarily)

Day 3
No rain → drying possible
Store day 3 as dry day
ans = [-1, -1, 1, 1, ?, ?]

Day 4
Rain on lake 2 again
Lake 2 was previously filled on day 1
We must dry lake 2 after day 1 and before day 4

Available dry days:
2 and 3
Choose day 2
Dry lake 2 on day 2
ans = [-1, -1, 2, 1, -1, ?]

Day 5
Rain on lake 1 again
Lake 1 was filled on day 0
Available dry day: 3
Dry lake 1 on day 3
ans = [-1, -1, 2, 1, -1, -1]

Final Answer:
[-1, -1, 2, 1, -1, -1]

3. DRY RUN :-
Example (custom tough case)
rains = [5,3,0,0,3,0,5]
Initial state
lastRains = empty
dryDays = empty
ans = size 7

Day 0
Rain on lake 5
Lake 5 becomes full
lastRains = {5 → 0}
ans[0] = -1

Day 1
Rain on lake 3
Lake 3 becomes full
lastRains = {5 → 0, 3 → 1}
ans[1] = -1

Day 2
Dry day
dryDays = {2}
ans[2] = 1

Day 3
Dry day
dryDays = {2,3}
ans[3] = 1

Day 4
Rain on lake 3
Lake 3 was previously filled on day 1
Find dry day after day 1
Available dry days:
2,3
Choose day 2
Dry lake 3
ans[2] = 3
Remove 2 from dryDays
dryDays = {3}
Update last rain
lastRains = {5 → 0, 3 → 4}
ans[4] = -1

Day 5
Dry day
dryDays = {3,5}
ans[5] = 1

Day 6
Rain on lake 5
Lake 5 was filled on day 0
Find dry day after day 0

Available dry days:
3,5
Choose day 3
Dry lake 5
ans[3] = 5
Remove from dryDays
dryDays = {5}
lastRains = {5 → 6, 3 → 4}
ans[6] = -1

Final Answer:
[-1, -1, 3, 5, -1, 1, -1]


*/



