class TaskManager {
    class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        // @Override
        // public boolean equals(Object o) {
        //     if (o == null || (o.getClass() != this.getClass()))
        //         return false;
        //     Pair t = (Pair) o;
        //     return t.a == this.a && t.b == this.b;
        // }

        // @Override
        // public int hashCode() {
        //     return Objects.hash(a, b);
        // }
    }

    Map<Integer, Pair> taskMap;
    PriorityQueue<Pair> pq;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        pq = new PriorityQueue<>((p, q) -> {
            if (q.a != p.a)
                return q.a - p.a;
            return q.b - p.b;
        });

        for (List<Integer> task : tasks) {
            taskMap.put(task.get(1), new Pair(task.get(0), task.get(2)));
            pq.offer(new Pair(task.get(2), task.get(1)));
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new Pair(userId, priority));
        pq.offer(new Pair(priority, taskId));
    }

    public void edit(int taskId, int newPriority) {
        Pair pair = taskMap.get(taskId);
        pair.b = newPriority;
        pq.offer(new Pair(newPriority, taskId));
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }

    public int execTop() {
        while(!pq.isEmpty()) {
            Pair p= pq.peek();
            Pair map= taskMap.get(p.b);
            if (map== null) {
                pq.poll();
                continue;
            }
            if (p.a!= map.b) {
                pq.poll();
                continue;
            }
            pq.poll();
            taskMap.remove(p.b);
            return map.a;
        }
        return -1;
    }
}


/*
Explanation:

This problem needs a system where we can add tasks, change a task's priority, remove a task, and always be able to pull out the task with the highest priority quickly (tie broken by highest taskId). Since priorities change and tasks get removed, the same lazy deletion trick as before is used here too.

Two structures are used:
taskMap: a HashMap from taskId to a Pair, where Pair.a = userId and Pair.b = priority. This always holds the current, true state of every task that still exists.
pq: a PriorityQueue of Pair, where here Pair.a = priority and Pair.b = taskId. This heap is ordered so highest priority is at top, and if priorities tie, the highest taskId is at top.

Note carefully: the Pair class is reused for two different meanings depending on where it's used, in taskMap it stores (userId, priority), in pq it stores (priority, taskId). The fields a and b mean different things in each place.

add(userId, taskId, priority): puts (userId, priority) into taskMap, and pushes (priority, taskId) into pq.

edit(taskId, newPriority): finds the existing Pair in taskMap and changes its b field (priority) directly. It also pushes a brand new (newPriority, taskId) entry into pq. It does not remove the old entry from pq, so the old entry becomes stale, sitting uselessly in the heap.

rmv(taskId): just deletes the entry from taskMap. The pq might still contain old entries for this taskId, they become stale too and get cleaned up later.

execTop(): looks at the top of pq. If taskMap has no entry for that taskId (it was removed), discard this pq entry. If the priority in the pq entry doesn't match the current priority stored in taskMap (it was edited), discard this pq entry too, since it's outdated. Once we find a pq entry whose priority actually matches what's currently in taskMap, that is the real top task, so we remove it from both pq and taskMap, and return its userId.

Dry Run (line by line on the example given):

tasks = [[1,101,10],[2,102,20],[3,103,15]]

Constructor:

task = [1,101,10]
line: taskMap.put(101, Pair(1,10)) -> taskMap = {101:(1,10)}
line: pq.offer(Pair(10,101)) -> pq = [(10,101)]

task = [2,102,20]
line: taskMap.put(102, Pair(2,20)) -> taskMap = {101:(1,10), 102:(2,20)}
line: pq.offer(Pair(20,102)) -> pq = [(20,102),(10,101)] (20 priority highest, so it sits on top)

task = [3,103,15]
line: taskMap.put(103, Pair(3,15)) -> taskMap = {101:(1,10), 102:(2,20), 103:(3,15)}
line: pq.offer(Pair(15,103)) -> pq now holds (10,101),(20,102),(15,103), top is (20,102)

State after constructor:
taskMap = {101:(1,10), 102:(2,20), 103:(3,15)}
pq top to bottom by priority = (20,102), (15,103), (10,101)

Call: add(4,104,5)

line: taskMap.put(104, Pair(4,5)) -> taskMap = {..., 104:(4,5)}
line: pq.offer(Pair(5,104)) -> pq adds (5,104), still top is (20,102) since 20 is highest priority

Call: edit(102, 8)

line: pair = taskMap.get(102) -> pair = (a=2, b=20)
line: pair.b = 8 -> taskMap[102] is mutated in place to (2,8)
line: pq.offer(Pair(8,102)) -> pq adds a new entry (8,102)
pq now logically contains (10,101), (20,102) [old, stale], (15,103), (5,104), (8,102) [new]
top of heap is still (20,102) because that pq entry's stored priority value 20 is numerically the highest, even though taskMap already shows 102's real priority is now 8

Call: execTop()

loop iteration 1:
line: p = pq.peek() -> p = (a=20, b=102)
line: map = taskMap.get(p.b) -> map = taskMap.get(102) = (a=2, b=8)
line: map == null check -> false, map exists, skip
line: p.a != map.b check -> 20 != 8 -> true, this entry is stale
line: pq.poll() -> removes (20,102) from pq, continue loop
pq now contains (15,103), (10,101), (5,104), (8,102), top is (15,103)

loop iteration 2:
line: p = pq.peek() -> p = (a=15, b=103)
line: map = taskMap.get(103) -> map = (a=3, b=15)
line: map == null -> false, skip
line: p.a != map.b -> 15 != 15 -> false, this entry is valid and current
line: pq.poll() -> removes (15,103) from pq
line: taskMap.remove(103) -> taskMap loses key 103, now = {101:(1,10), 102:(2,8), 104:(4,5)}
line: return map.a -> returns 3

execTop() output: 3 (matches expected: executes task 103 for User 3)

Call: rmv(101)

line: taskMap.remove(101) -> taskMap = {102:(2,8), 104:(4,5)}
pq is untouched here, it still has (10,101) sitting inside as a stale entry, plus (5,104), (8,102)

Call: add(5,105,15)

line: taskMap.put(105, Pair(5,15)) -> taskMap = {102:(2,8), 104:(4,5), 105:(5,15)}
line: pq.offer(Pair(15,105)) -> pq adds (15,105)
pq now logically has (10,101)[stale], (5,104), (8,102), (15,105); top is (15,105) since 15 is the highest priority value present

Call: execTop()

loop iteration 1:
line: p = pq.peek() -> p = (a=15, b=105)
line: map = taskMap.get(105) -> map = (a=5, b=15)
line: map == null -> false, skip
line: p.a != map.b -> 15 != 15 -> false, valid entry
line: pq.poll() -> removes (15,105) from pq
line: taskMap.remove(105) -> taskMap = {102:(2,8), 104:(4,5)}
line: return map.a -> returns 5

execTop() output: 5 (matches expected: executes task 105 for User 5)

Final full output sequence: null, null, null, 3, null, null, 5

This exactly matches the expected output: [null, null, null, 3, null, null, 5]

Note: the stale entry (10,101) that was sitting in pq from before the rmv(101) call never actually surfaced to the top in this particular run, since (15,105) and other valid entries kept beating it to the top, so the stale-check logic inside execTop never had to discard it in this specific trace, but it is still sitting in pq and would get cleaned up automatically the moment it reaches the top in some future execTop call.

*/
