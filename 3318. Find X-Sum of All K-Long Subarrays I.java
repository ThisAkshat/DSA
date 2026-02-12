class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] answer = new int[n-k+1];
        for(int i =0; i<=n-k; i++){
            Map<Integer, Integer> freq = new HashMap<>();
            for(int j = i; j<i+k; j++) freq.put(nums[j], freq.getOrDefault(nums[j], 0)+1);

            List<int[]> list = new ArrayList<>();
            for(Map.Entry<Integer,Integer> e: freq.entrySet()) list.add(new int[]{e.getKey(), e.getValue()});

            list.sort((a,b) -> b[1] == a[1] ? b[0] -a[0] : b[1] -a[1]);

            Set<Integer> top = new HashSet<>();
            for(int j=0; j<Math.min(x, list.size()); j++) top.add(list.get(j)[0]);

            int sum=0;
            for(int j=i;j<i+k;j++) if(top.contains(nums[j])) sum = sum+nums[j];

            answer[i] = sum;
        }
        return answer;
    }
}


/*
1) Problem :-
You are given:
an array nums
a window size k
a number x
For every subarray of length k, you must:
1. Count how many times each number appears.
2. Pick only the top x most frequent numbers.
  * If two numbers appear the same number of times, the bigger number wins.
3. From the subarray, keep only numbers that belong to those top x.
4. Add them → that sum is the x-sum for that subarray.
Do this for every window of size k.

*******************************************************************************

2) Example :-
nums = [1,1,2,2,3,4,2,3]
k = 6
x = 2

First subarray (index 0 → 5):
[1,1,2,2,3,4]

Frequencies:
1 → 2 times
2 → 2 times
3 → 1 time
4 → 1 time

Pick top 2 frequent numbers:
* 1 (2 times)
* 2 (2 times)
So we keep only 1 and 2:
1 + 1 + 2 + 2 = 6

So: answer[0] = 6

*******************************************************************************

3) Short Dry Run

Using: nums = [1,1,2,2,3,4,2,3]
k = 6, x = 2
Window 0 → [1,1,2,2,3,4]

Freq: 1:2, 2:2, 3:1, 4:1

Top 2 → {1,2}
Sum → 1+1+2+2 = 6
Window 1 → [1,2,2,3,4,2]
Freq: 2:3, 1:1, 3:1, 4:1

Three numbers have freq=1 → pick biggest → 4
Top 2 → {2,4}
Sum → 2+2+2+4 = 10
Window 2 → [2,2,3,4,2,3]
Freq:
2:3, 3:2, 4:1

Top 2 → {2,3}
Sum → 2+2+2+3+3 = 12
Final result:
[6, 10, 12]


*/
