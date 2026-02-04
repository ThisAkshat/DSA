class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        int[] roomsUsedCount = new int[n];
        long[] lastAvailableAt = new long[n];

        for(int[] meet : meetings){
            int start = meet[0];
            int end = meet[1];
            boolean found = false;

            long earlyEndRoomTime = Long.MAX_VALUE;
            int earlyEndRoom = 0;

            for(int room = 0; room < n; room++){
                if(lastAvailableAt[room] <= start){
                    found = true;
                    lastAvailableAt[room] = end;
                    roomsUsedCount[room]++;
                    break;
                }
                if(lastAvailableAt[room] < earlyEndRoomTime){
                    earlyEndRoom = room;
                    earlyEndRoomTime = lastAvailableAt[room];
                }
            }
            if(!found){
                lastAvailableAt[earlyEndRoom] += (end - start);
                roomsUsedCount[earlyEndRoom]++;
            }
        }
        int resultRoom = -1;
        int maxUse = 0;
        for(int room = 0;room<n;room++){
            if(roomsUsedCount[room] > maxUse){
                maxUse = roomsUsedCount[room];
                resultRoom = room;
            }
        }
        return resultRoom;
    }
}


/*
What this problem is really about:-
You have n meeting rooms (0 to n-1) and a list of meetings with start & end times.

Rules:
1. If a meeting starts and some rooms are free, it goes to the lowest numbered free room.
2. If no room is free, the meeting is delayed until the earliest room gets free.
3. A delayed meeting keeps the same duration.
4. After all meetings are scheduled, you must find:
Which room was used the most times
(tie → choose smaller room number)
That’s it.

*************************************************************************************************

How to think about it
You’re basically simulating a real office:
* Meetings arrive in time order
* Rooms get busy and then free
* If nobody is free, meetings wait in line
* You count how many times each room is used

*************************************************************************************************

Example (Example 1)
n = 2
meetings = [[0,10],[1,5],[2,7],[3,4]]


We process meetings in start-time order:

Time 0 → [0,10]
Both rooms free
→ room 0 gets it
Room 0 busy till 10

Time 1 → [1,5]
Room 1 is free
→ room 1 gets it
Room 1 busy till 5

Time 2 → [2,7]
Both rooms busy
→ must wait
Which room frees first?
Room 1 (at time 5)
So meeting starts at 5 and lasts (7−2)=5
→ runs from [5,10) in room 1

Time 3 → [3,4]
Both rooms busy again
Earliest free?
Room 0 at time 10
Duration = 4−3 = 1
So it runs [10,11) in room 0

Final room usage
Room 0 → 2 meetings
Room 1 → 2 meetings

Tie → pick smaller → 0

*************************************************************************************************

Short dry run (tiny)
n = 2
meetings = [[1,4],[2,3],[3,5]]


Step by step:
Meeting	            Action
[1,4]	              room 0
[2,3]             	room 1
[3,5]              	room 1 (room 1 frees at 3)

Room 0 → 1
Room 1 → 2

Answer → 1

*/
