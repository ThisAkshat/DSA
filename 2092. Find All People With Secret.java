class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Map<Integer, List<int[]>> timeMeetings = new TreeMap<>();
        for(int[] meeting : meetings){
            int x = meeting[0];
            int y = meeting[1];
            int t = meeting[2];

            timeMeetings.computeIfAbsent(t, k -> new ArrayList<>()).add(new int[]{x, y});
        }
            boolean[] knowSecret = new boolean[n];
            knowSecret[0] = true;
            knowSecret[firstPerson] = true;

            for(int t : timeMeetings.keySet()){
                Map<Integer, List<Integer>> meet = new HashMap<>();
                for(int[] meeting : timeMeetings.get(t)){
                    int x = meeting[0];
                    int y = meeting[1];
                    meet.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                    meet.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
                }

                Set<Integer> start = new HashSet<>();
                for(int[] meeting : timeMeetings.get(t)){
                    int x = meeting[0];
                    int y = meeting[1];
                    if(knowSecret[x]){
                        start.add(x);
                    }
                    if(knowSecret[y]){
                        start.add(y);
                    }
                }
                Queue<Integer> q = new LinkedList<>(start);
                while(!q.isEmpty()){
                    int person = q.poll();
                    for(int nextPerson : meet.getOrDefault(person, new ArrayList<>())){
                        if(!knowSecret[nextPerson]){
                            knowSecret[nextPerson] = true;
                            q.offer(nextPerson);
                        }
                    }
                }
            }

            List<Integer> result = new ArrayList<>();
            for(int i=0; i<n; i++){
                if(knowSecret[i]){
                    result.add(i);
                }
            }
            return result;
        }
}

/*
# **1) Problem Explanation **

Yeh problem **secret sharing** ki hai:  
- `n` log hain (0 se n-1 tak).  
- Person 0 ke paas ek secret hai.  
- Time 0 par, person 0 secret **firstPerson** ko batata hai.  
- Meetings hain: `[x, y, time]` — is time par x aur y milte hain.  
- Agar meeting ke time par kisi ke paas secret hai, toh woh dusre ko secret batayega.  
- **Important**: Secret instantly share hota hai — agar ek hi time par multiple meetings hain, toh secret un sabme flow kar sakta hai.  
- Goal: Final list batado jinhe secret pata hai.

---

# **2) Perfect Example**

**Example 3 se lete hain**:

n=5, meetings=[[3,4,2], [1,2,1], [2,3,1]], firstPerson=1

Time-wise:
- t=0: 0→1 (secret share)
- t=1: Meeting [1,2,1] aur [2,3,1] dono same time par  
  Secret 1 ke paas hai, woh 2 ko batayega.  
  2 secret lete hi 3 ko bhi bata dega (kyuki [2,3,1] meeting bhi same time hai).  
  Isliye t=1 ke baad secret: 0,1,2,3.
- t=2: Meeting [3,4,2] — 3 ke paas secret hai, woh 4 ko batayega.  
  Final: 0,1,2,3,4.

Output: [0,1,2,3,4]

---

# **3) Dry Run of Given Code on Example 3**

**Input**: n=5, meetings=[[3,4,2],[1,2,1],[2,3,1]], firstPerson=1

**Step 1: Group meetings by time**
timeMeetings TreeMap:
- t=1: [[1,2], [2,3]]
- t=2: [[3,4]]

**Step 2: Initialize**
knowSecret = [false]*5  
knowSecret[0] = true  
knowSecret[1] = true  (firstPerson)

**Loop over times in sorted order:**

**t=1:**
meet map banate hain:
- Meeting [1,2]:  
  meet[1].add(2), meet[2].add(1)
- Meeting [2,3]:  
  meet[2].add(3), meet[3].add(2)

meet = {1:[2], 2:[1,3], 3:[2]}

start set: meetings check karte hain jismein koi knowSecret=true ho:
1. Meeting [1,2]: knowSecret[1]=true → start.add(1)  
   knowSecret[2]=false → nahi
2. Meeting [2,3]: knowSecret[2]=false, knowSecret[3]=false → koi nahi

start = {1}

Queue q = [1]

**BFS**:
- q.poll() = 1  
  meet[1] = [2]  
  2 knowSecret[2]=false → true set karo, queue mein add karo  
  knowSecret = [t,t,t,f,f] (0,1,2 true)
  q = [2]
- q.poll() = 2  
  meet[2] = [1,3]  
  1 already true, skip  
  3 knowSecret[3]=false → true set karo, queue mein add karo  
  knowSecret = [t,t,t,t,f]  
  q = [3]
- q.poll() = 3  
  meet[3] = [2]  
  2 already true, skip  
  q empty

t=1 ke baad knowSecret = [true, true, true, true, false]

**t=2:**
meet map:
Meeting [3,4]:  
meet[3].add(4), meet[4].add(3)  
meet = {3:[4], 4:[3]}

start set:  
Meeting [3,4]: knowSecret[3]=true → start.add(3)  
knowSecret[4]=false → nahi  
start = {3}

Queue q = [3]

**BFS**:
- q.poll() = 3  
  meet[3] = [4]  
  4 knowSecret[4]=false → true set karo, queue mein add karo  
  knowSecret = [t,t,t,t,t]  
  q = [4]
- q.poll() = 4  
  meet[4] = [3]  
  3 already true, skip  
  q empty

**Final knowSecret**: [true,true,true,true,true]

**Result list**: [0,1,2,3,4] ✅
*/


