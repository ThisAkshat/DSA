class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int lastOne = -(k+1);
        for(int i = 0; i<n; i++){
            if(nums[i] == 1){
                if(i - lastOne - 1< k)
                return false;

                lastOne = i;
            }
        }
        return true;
    }
}

/*
Problem - Given an binary array nums and an integer k, return true if all 1's are at least k places away from each other, otherwise return false.


Example
nums = [1,0,0,0,1,0,0,1], k = 2

index: 0 1 2 3 4 5 6 7
value: 1 0 0 0 1 0 0 1

Diagram
1 _ _ _ 1 _ _ 1

Check gaps between 1s
index 0 → index 4 → zeros = 3 → valid (3 ≥ 2)
index 4 → index 7 → zeros = 2 → valid (2 ≥ 2)

Result → true because every two 1s have at least 2 zeros between them ✅

***********************************************************************************************************

Dry run for
nums = [1,0,0,0,1,0,0,1], k = 2

Initialize
n = 8
lastOne = -(k+1) = -3

---

i = 0
nums[0] = 1

distance = i − lastOne − 1
distance = 0 − (−3) − 1
distance = 2

check: distance < k
2 < 2 → false

update lastOne = 0

---

i = 1
nums[1] = 0
nothing happens

---

i = 2
nums[2] = 0
nothing happens

---

i = 3
nums[3] = 0
nothing happens

---

i = 4
nums[4] = 1

distance = 4 − 0 − 1
distance = 3

check: 3 < 2 → false

update lastOne = 4

---

i = 5
nums[5] = 0
nothing happens

---

i = 6
nums[6] = 0
nothing happens

---

i = 7
nums[7] = 1

distance = 7 − 4 − 1
distance = 2

check: 2 < 2 → false

update lastOne = 7

---

loop ends

return true

*/
