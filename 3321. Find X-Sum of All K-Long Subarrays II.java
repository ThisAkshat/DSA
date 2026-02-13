class Solution {
    long sum = 0;
    TreeSet<Pair> main = new TreeSet<>();
    TreeSet<Pair> sec = new TreeSet<>();

    static class Pair implements Comparable<Pair> {
        int freq, val;
        Pair(int f, int v) { freq = f; val = v; }

        public int compareTo(Pair p) {
            if (freq == p.freq) return val - p.val;
            return freq - p.freq;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return freq == p.freq && val == p.val;
        }

        public int hashCode() {
            return Objects.hash(freq, val);
        }
    }

    void insertInSet(Pair p, int x) {
        if (main.size() < x || p.compareTo(main.first()) > 0) {
            sum += 1L * p.freq * p.val;
            main.add(p);

            if (main.size() > x) {
                Pair smallest = main.first();
                sum -= 1L * smallest.freq * smallest.val;
                main.remove(smallest);
                sec.add(smallest);
            }
        } else {
            sec.add(p);
        }
    }

    void removeFromSet(Pair p, int x) {
        if (main.remove(p)) {
            sum -= 1L * p.freq * p.val;
            if (!sec.isEmpty()) {
                Pair largest = sec.last();
                sec.remove(largest);
                main.add(largest);
                sum += 1L * largest.freq * largest.val;
            }
        } else {
            sec.remove(p);
        }
    }

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        sum = 0;
        List<Long> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        int i = 0, j = 0;
        while (j < n) {
            if (map.getOrDefault(nums[j], 0) > 0)
                removeFromSet(new Pair(map.get(nums[j]), nums[j]), x);

            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            insertInSet(new Pair(map.get(nums[j]), nums[j]), x);

            if (j - i + 1 == k) {
                result.add(sum);

                removeFromSet(new Pair(map.get(nums[i]), nums[i]), x);
                map.put(nums[i], map.get(nums[i]) - 1);

                if (map.get(nums[i]) == 0)
                    map.remove(nums[i]);
                else
                    insertInSet(new Pair(map.get(nums[i]), nums[i]), x);

                i++;
            }
            j++;
        }

        long[] ans = new long[result.size()];
        for (int idx = 0; idx < ans.length; idx++)
            ans[idx] = result.get(idx);
        return ans;
    }
}


/*
1) Problem in simple words
Same story as before:
For every subarray of size k:
* Count how often each number appears
* Keep only the top x most frequent numbers
   . If two have same frequency → bigger number wins
* Add only those kept numbers → that’s the X-sum
Do this for every sliding window of size k.

But now:
* nums is huge (up to 10^5)
* We cannot recompute everything from scratch for each window
  So we must update smartly when the window moves

2) Key idea behind this code
The code maintains two groups:
main
Stores the top x elements (based on (frequency, value))
sec
Stores the rest of the elements
And a variable:

sum = total contribution of main
(sum of freq × value of top x elements)

So at any time:
sum = the X-sum of the current window

3) What is stored in TreeSet
Each number is stored as:
(freq, value)
Example:
If 2 appears 3 times → (3, 2)
Sorting rule:
. Higher frequency = better
. If frequency tie → bigger value = better
So (3,2) beats (2,5)
and (2,10) beats (2,5)

4) How sliding window works
When window moves:
* One number enters
* One number leaves
For both:
. Remove old (freq, value) from sets
. Update frequency
. Insert new (freq, value) back

During insertion:
. If it belongs in top x → put in main
. Otherwise → put in sec
. If main becomes too big → push smallest out to sec
. Keep updating sum
This way: sum is always correct without recomputing everything

5) Example (same one)
nums = [1,1,2,2,3,4,2,3]
k = 6
x = 2

First window: [1,1,2,2,3,4]
Freq:
1 → 2
2 → 2
3 → 1
4 → 1

Top 2: (2,1) and (2,2)
X-sum: 1+1+2+2 = 6


Stored as:
main = {(2,1), (2,2)}
sec  = {(1,3), (1,4)}
sum = 6

Slide window forward
Remove 1, add 2
Now freq of 2 becomes 3

New freq:
2 → 3
1 → 1
3 → 1
4 → 1

Top 2 now: (3,2) and (1,4)   ← 4 wins tie
So: sum = 2+2+2 + 4 = 10

Next window
[2,2,3,4,2,3]

Freq:
2 → 3
3 → 2
4 → 1

Top 2: (3,2) and (2,3)

Sum:
2+2+2 + 3+3 = 12

Final Answer
[6, 10, 12]

*/


