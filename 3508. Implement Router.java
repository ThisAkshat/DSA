class Packet {
    int source, destination, timestamp;
    public Packet(int s, int d, int t) {
        source = s;
        destination = d;
        timestamp = t;
    }
}

class Router {
    int memoryLimit;
    Queue<Packet> q;
    HashSet<Long> dupKey;
    HashMap<Integer, ArrayList<Integer>> desToTime;
    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        q = new LinkedList<>();
        dupKey = new HashSet<>();
        desToTime = new HashMap<>();
    }
    private long makeKey(int s, int d, int t) {
        return (long)s * 10000000000L + (long)d * 100000 + t;
    }
    public boolean addPacket(int source, int destination, int timestamp) {
        long key = makeKey(source, destination, timestamp);
        if (dupKey.contains(key)) return false;
        if (q.size() == memoryLimit) {
            Packet old = q.poll();
            long oldKey = makeKey(old.source, old.destination, old.timestamp);
            dupKey.remove(oldKey);

            ArrayList<Integer> list = desToTime.get(old.destination);
            list.remove(0);
            if (list.isEmpty()) desToTime.remove(old.destination);
        }
        Packet p = new Packet(source, destination, timestamp);
        q.add(p);
        dupKey.add(key);
        desToTime.putIfAbsent(destination, new ArrayList<>());
        desToTime.get(destination).add(timestamp);
        return true;
    }
    public int[] forwardPacket() {
        if (q.isEmpty()) return new int[0];
        Packet p = q.poll();

        long key = makeKey(p.source, p.destination, p.timestamp);
        dupKey.remove(key);

        ArrayList<Integer> list = desToTime.get(p.destination);
        list.remove(0);
        if (list.isEmpty()) desToTime.remove(p.destination);

        return new int[]{p.source, p.destination, p.timestamp};
    }
    public int getCount(int destination, int startTime, int endTime) {
        if (!desToTime.containsKey(destination)) return 0;
        ArrayList<Integer> list = desToTime.get(destination);
        int left = lowerBound(list, startTime);
        int right = upperBound(list, endTime);
        return right - left;
    }
    private int lowerBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) >= target) r = m;
            else l = m + 1;
        }
        return l;
    }
    private int upperBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) > target) r = m;
            else l = m + 1;
        }
        return l;
    }
}

/*
Explanation:

This problem needs a router that stores packets up to a memory limit, rejects duplicates, forwards packets in FIFO order, and can count how many packets with a certain destination are stored within a time range.

Three structures are used:
q: a simple FIFO queue holding Packet objects in the order they arrived. This is the main storage.
dupKey: a HashSet of long keys, one per stored packet, used to detect duplicates instantly without scanning the whole queue.
desToTime: a HashMap from destination to an ArrayList of timestamps of packets currently stored for that destination. Since addPacket always comes in non-decreasing timestamp order, this list is always sorted, which allows binary search for getCount.

makeKey(s, d, t): combines source, destination, timestamp into a single unique long number so we can store it in the HashSet and check duplicates in O(1). The multipliers (10000000000 and 100000) are large enough to keep each part separate without collision given the constraints.

addPacket: first checks if this key already exists in dupKey, if yes returns false. If the queue is already at memoryLimit, it evicts the oldest packet from the front of the queue, removes its key from dupKey, and also removes its timestamp from the front of that destination's list in desToTime. Then it adds the new packet to the back of the queue, adds its key to dupKey, and appends its timestamp to that destination's list in desToTime.

forwardPacket: simply polls from the front of the queue (FIFO), removes that packet's key from dupKey and its timestamp from the front of that destination's list in desToTime, then returns the packet as an array.

getCount: looks up the destination's timestamp list in desToTime. Runs lowerBound to find the first index where timestamp is >= startTime, and upperBound to find the first index where timestamp is > endTime. The count of packets in range is upperBound result minus lowerBound result.

Dry Run (line by line on Example 1):

new Router(3)

line: this.memoryLimit = 3
line: q = new LinkedList() -> q = []
line: dupKey = new HashSet() -> dupKey = {}
line: desToTime = new HashMap() -> desToTime = {}

Call: addPacket(1, 4, 90)

line: key = makeKey(1,4,90) -> 1*10000000000 + 4*100000 + 90 = 10000400090
line: dupKey.contains(key) -> false, not a duplicate, continue
line: q.size() == memoryLimit -> 0 == 3 -> false, no eviction needed
line: p = new Packet(1,4,90)
line: q.add(p) -> q = [(1,4,90)]
line: dupKey.add(10000400090) -> dupKey = {10000400090}
line: desToTime.putIfAbsent(4, new ArrayList()) -> desToTime = {4:[]}
line: desToTime.get(4).add(90) -> desToTime = {4:[90]}
return true

Call: addPacket(2, 5, 90)

line: key = makeKey(2,5,90) -> 2*10000000000 + 5*100000 + 90 = 20000500090
line: dupKey.contains(20000500090) -> false, continue
line: q.size() == 3 -> 1 == 3 -> false, no eviction
line: q.add(Packet(2,5,90)) -> q = [(1,4,90),(2,5,90)]
line: dupKey.add(20000500090) -> dupKey = {10000400090, 20000500090}
line: desToTime.putIfAbsent(5, new ArrayList()) -> desToTime = {4:[90], 5:[]}
line: desToTime.get(5).add(90) -> desToTime = {4:[90], 5:[90]}
return true

Call: addPacket(1, 4, 90)

line: key = makeKey(1,4,90) -> 10000400090
line: dupKey.contains(10000400090) -> true, this is a duplicate
return false

Call: addPacket(3, 5, 95)

line: key = makeKey(3,5,95) -> 30000500095
line: dupKey.contains(30000500095) -> false, continue
line: q.size() == 3 -> 2 == 3 -> false, no eviction
line: q.add(Packet(3,5,95)) -> q = [(1,4,90),(2,5,90),(3,5,95)]
line: dupKey.add(30000500095) -> dupKey = {10000400090, 20000500090, 30000500095}
line: desToTime.putIfAbsent(5, ...) -> 5 already exists, does nothing
line: desToTime.get(5).add(95) -> desToTime = {4:[90], 5:[90,95]}
return true

Call: addPacket(4, 5, 105)

line: key = makeKey(4,5,105) -> 40000500105
line: dupKey.contains(40000500105) -> false, continue
line: q.size() == memoryLimit -> 3 == 3 -> true, must evict oldest
line: old = q.poll() -> old = Packet(1,4,90), q = [(2,5,90),(3,5,95)]
line: oldKey = makeKey(1,4,90) -> 10000400090
line: dupKey.remove(10000400090) -> dupKey = {20000500090, 30000500095}
line: list = desToTime.get(old.destination) -> desToTime.get(4) -> list = [90]
line: list.remove(0) -> removes index 0, list becomes []
line: list.isEmpty() -> true
line: desToTime.remove(4) -> desToTime = {5:[90,95]}
eviction done, now add new packet:
line: q.add(Packet(4,5,105)) -> q = [(2,5,90),(3,5,95),(4,5,105)]
line: dupKey.add(40000500105) -> dupKey = {20000500090, 30000500095, 40000500105}
line: desToTime.putIfAbsent(5,...) -> 5 already exists, does nothing
line: desToTime.get(5).add(105) -> desToTime = {5:[90,95,105]}
return true

Call: forwardPacket()

line: q.isEmpty() -> false, continue
line: p = q.poll() -> p = Packet(2,5,90), q = [(3,5,95),(4,5,105)]
line: key = makeKey(2,5,90) -> 20000500090
line: dupKey.remove(20000500090) -> dupKey = {30000500095, 40000500105}
line: list = desToTime.get(p.destination) -> desToTime.get(5) -> list = [90,95,105]
line: list.remove(0) -> removes index 0 (value 90), list = [95,105]
line: list.isEmpty() -> false, so desToTime.remove not called
desToTime = {5:[95,105]}
line: return new int[]{2,5,90}

Output: [2,5,90] (matches expected)

Call: addPacket(5, 2, 110)

line: key = makeKey(5,2,110) -> 50000200110
line: dupKey.contains(50000200110) -> false, continue
line: q.size() == 3 -> 2 == 3 -> false, no eviction
line: q.add(Packet(5,2,110)) -> q = [(3,5,95),(4,5,105),(5,2,110)]
line: dupKey.add(50000200110) -> dupKey = {30000500095, 40000500105, 50000200110}
line: desToTime.putIfAbsent(2, new ArrayList()) -> desToTime = {5:[95,105], 2:[]}
line: desToTime.get(2).add(110) -> desToTime = {5:[95,105], 2:[110]}
return true

Call: getCount(5, 100, 110)

line: desToTime.containsKey(5) -> true, continue
line: list = desToTime.get(5) -> list = [95,105]
line: left = lowerBound(list, 100)
 inside lowerBound, target = 100, l=0, r=2
 m = 1, list.get(1) = 105, 105 >= 100 -> r = 1
 m = 0, list.get(0) = 95, 95 >= 100 is false -> l = 1
 l == r, exit loop, return 1
 left = 1
line: right = upperBound(list, 110)
 inside upperBound, target = 110, l=0, r=2
 m = 1, list.get(1) = 105, 105 > 110 is false -> l = 2
 l == r, exit loop, return 2
 right = 2
line: return right - left -> 2 - 1 = 1

Output: 1 (matches expected, only packet (4,5,105) has destination 5 with timestamp in [100,110])

Final full output sequence: null, true, true, false, true, true, [2,5,90], true, 1

This exactly matches the expected output: [null, true, true, false, true, true, [2,5,90], true, 1]
*/
