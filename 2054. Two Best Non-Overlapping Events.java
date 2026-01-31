class Solution {
    public int maxTwoEvents(int[][] events) {
        // Events: [[1,3,2], [4,5,2], [2,4,3]]
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        // Sorted: [[1,3,2], [2,4,3], [4,5,2]]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]); // pq = []
        int maxSingle = 0;
        int ans = 0;
        for(int[] event : events){
            int start = event[0];
            int end = event[1];
            int value = event[2];

            while(!pq.isEmpty() && pq.peek()[0] < start){
                maxSingle = Math.max(maxSingle, pq.poll()[1]);
            }
            ans = Math.max(ans, value);
            ans = Math.max(ans, maxSingle + value);
            pq.offer(new int[]{end, value});
        }
        return ans;
    }
}

/*
What the problem wants
You can attend at most two events.
They must not overlap.
If one ends at time t, the next must start at t+1 or later.

Maximize:
value(event1) + value(event2)
Core idea of the solution
At every event, you ask:
“What is the best event that ended before this one starts?”
Then you try to pair it with the current event.
To do that efficiently:
Sort events by start time
Use a min-heap (priority queue) ordered by end time

What each variable means
PriorityQueue<int[]> pq   // stores [endTime, value] of active/past events
int maxSingle             // best value among all events that already ended
int ans                   // final answer

maxSingle is the key:
“What is the highest value of any event that is guaranteed not to overlap with the current one?”
Why sort by start time?
So that when we process an event with start = S,
all events that ended before S are sitting in the heap.
We can safely pop them.
What this loop does
while(!pq.isEmpty() && pq.peek()[0] < start){
    maxSingle = Math.max(maxSingle, pq.poll()[1]);
}
This removes all events that end before current start
and stores the best value among them in maxSingle.
Those events are now safe to pair with current event.

How we compute the answer
For each event:
ans = Math.max(ans, value);              // take only this event
ans = Math.max(ans, maxSingle + value);  // pair with best past event

So at every event, you try:
taking it alone
taking it with the best non-overlapping event
Then we add current event to heap
pq.offer(new int[]{end, value});
So future events can pair with it.


****************************************************************************

Short dry run :-

Input:
[[1,3,2],[4,5,2],[2,4,3]]
After sort:
[1,3,2], [2,4,3], [4,5,2]

Start with:
pq = []
maxSingle = 0
ans = 0

Process [1,3,2]:
nothing to pop
ans = max(0,2) = 2
ans = max(2, 0+2) = 2
pq = [(3,2)]
Process [2,4,3]:
pq.peek.end = 3, not < 2 → no pop
ans = max(2,3) = 3
ans = max(3,0+3) = 3
pq = [(3,2),(4,3)]
Process [4,5,2]:
pop (3,2) → maxSingle = 2
next pq.peek = (4,3), 4 < 4? no (must be < start)
ans = max(3,2) = 3
ans = max(3,2+2) = 4
pq add (5,2)
Final ans = 4

*/
