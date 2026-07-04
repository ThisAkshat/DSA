class Solution {
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int max = 0;
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);

            if(map.get(nums[i])>max){
                max = map.get(nums[i]);
            }
        }

        int total = 0;

        for(Map.Entry<Integer, Integer> mp : map.entrySet()){
            if(mp.getValue()==max){
                total+=max;
            }
        }

        return total;
    }
}
/*
Explanation:

This problem asks us to find which number appears the most times in the array, and then return the total count of all occurrences of every number that shares that maximum frequency.

The approach uses a HashMap to count how many times each number appears, while also tracking the maximum frequency seen so far. Then in a second pass over the map, every entry whose frequency equals the maximum gets its count added to the final total.

Step 1: Loop through nums, for each number increment its count in the map. After each update, check if this number's new count is greater than max, if yes update max. So by the end of this loop, map has frequencies of all numbers and max holds the highest frequency seen.

Step 2: Loop through all entries in the map. If an entry's frequency equals max, add that frequency value to total. This accumulates the total number of elements that have the maximum frequency.

Return total.

Dry Run (line by line on nums = [1,2,2,3,1,4]):

line: map = new HashMap() -> map = {}
line: max = 0

Loop i = 0, nums[0] = 1
line: map.put(1, map.getOrDefault(1,0)+1) -> map.getOrDefault(1,0) = 0, so map.put(1,1) -> map = {1:1}
line: map.get(1) > max -> 1 > 0 -> true
line: max = 1

Loop i = 1, nums[1] = 2
line: map.put(2, map.getOrDefault(2,0)+1) -> 0+1 = 1, map = {1:1, 2:1}
line: map.get(2) > max -> 1 > 1 -> false, max stays 1

Loop i = 2, nums[2] = 2
line: map.put(2, map.getOrDefault(2,0)+1) -> 1+1 = 2, map = {1:1, 2:2}
line: map.get(2) > max -> 2 > 1 -> true
line: max = 2

Loop i = 3, nums[3] = 3
line: map.put(3, map.getOrDefault(3,0)+1) -> 0+1 = 1, map = {1:1, 2:2, 3:1}
line: map.get(3) > max -> 1 > 2 -> false, max stays 2

Loop i = 4, nums[4] = 1
line: map.put(1, map.getOrDefault(1,0)+1) -> 1+1 = 2, map = {1:2, 2:2, 3:1}
line: map.get(1) > max -> 2 > 2 -> false, max stays 2

Loop i = 5, nums[5] = 4
line: map.put(4, map.getOrDefault(4,0)+1) -> 0+1 = 1, map = {1:2, 2:2, 3:1, 4:1}
line: map.get(4) > max -> 1 > 2 -> false, max stays 2

First loop done. map = {1:2, 2:2, 3:1, 4:1}, max = 2

line: total = 0

Second loop over map entries:

entry 1:2 (key=1, value=2)
line: mp.getValue() == max -> 2 == 2 -> true
line: total += max -> total = 0 + 2 = 2

entry 2:2 (key=2, value=2)
line: mp.getValue() == max -> 2 == 2 -> true
line: total += max -> total = 2 + 2 = 4

entry 3:1 (key=3, value=1)
line: mp.getValue() == max -> 1 == 2 -> false, skip

entry 4:1 (key=4, value=1)
line: mp.getValue() == max -> 1 == 2 -> false, skip

line: return total -> return 4

Output: 4 (matches expected, both 1 and 2 appear twice, so total elements with max frequency = 2 + 2 = 4)
*/
