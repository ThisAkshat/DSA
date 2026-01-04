class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum =0;
        for(int num : nums){
            int divisorSum = 0;
            int divisorCount = 0;

            for(int i=1; i*i<=num; i++){
                if(num % i == 0){
                    divisorCount++;
                    divisorSum += i;

                    if(i != num/i){
                        divisorCount++;
                        divisorSum += num/i;
                    }
                }
            }
            if(divisorCount == 4){
                totalSum += divisorSum;
            }
        }
        return totalSum;
    }
}
/*
Given an integer array nums, return the sum of divisors of the integers
in that array that have exactly four divisors. If there is no such integer
in the array, return 0.

Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation: 
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.

Example 2:
Input: nums = [21,21]
Output: 64

Example 3:
Input: nums = [1,2,3,4,5]
Output: 0
  */

/*
Dry Run :- nums = [21, 4, 33, 49]
Number 1: num = 21

divisorSum = 0, divisorCount = 0

Inner loop: i from 1 to √21 ≈ 4 (i*i <= 21)

i = 1: 21 % 1 == 0 ✓
divisorCount++ = 1
divisorSum += 1 = 1
i != 21/1 (1 != 21) ✓
divisorCount++ = 2
divisorSum += 21 = 22

i = 2: 21 % 2 == 1 ❌ (skip)

i = 3: 21 % 3 == 0 ✓
divisorCount++ = 3
divisorSum += 3 = 25
i != 21/3 (3 != 7) ✓
divisorCount++ = 4
divisorSum += 7 = 32

i = 4: 21 % 4 == 1 ❌ (skip)

Loop ends (5*5=25 > 21)

Check: divisorCount == 4 ✓
totalSum += 32 = 32

Number 2: num = 4

divisorSum = 0, divisorCount = 0

Inner loop: i from 1 to √4 = 2

i = 1: 4 % 1 == 0 ✓
divisorCount++ = 1
divisorSum += 1 = 1
i != 4/1 (1 != 4) ✓
divisorCount++ = 2
divisorSum += 4 = 5

i = 2: 4 % 2 == 0 ✓
divisorCount++ = 3
divisorSum += 2 = 7
i != 4/2 (2 == 2) ❌ (perfect square, don't add again)

Loop ends

Check: divisorCount == 4? ❌ (count=3)
Skip, totalSum remains 32

Number 3: num = 33

divisorSum = 0, divisorCount = 0

Inner loop: i from 1 to √33 ≈ 5

i = 1: 33 % 1 == 0 ✓
divisorCount++ = 1
divisorSum += 1 = 1
i != 33/1 (1 != 33) ✓
divisorCount++ = 2
divisorSum += 33 = 34

i = 2: 33 % 2 == 1 ❌ (skip)

i = 3: 33 % 3 == 0 ✓
divisorCount++ = 3
divisorSum += 3 = 37
i != 33/3 (3 != 11) ✓
divisorCount++ = 4
divisorSum += 11 = 48

i = 4: 33 % 4 == 1 ❌ (skip)
i = 5: 33 % 5 == 3 ❌ (skip)

Check: divisorCount == 4 ✓
totalSum += 48 = 80

Number 4: num = 49

divisorSum = 0, divisorCount = 0

Inner loop: i from 1 to √49 = 7

i = 1: 49 % 1 == 0 ✓
divisorCount++ = 1
divisorSum += 1 = 1
i != 49/1 (1 != 49) ✓
divisorCount++ = 2
divisorSum += 49 = 50

i = 2: 49 % 2 == 1 ❌ (skip)
i = 3: 49 % 3 == 1 ❌ (skip)
i = 4: 49 % 4 == 1 ❌ (skip)
i = 5: 49 % 5 == 4 ❌ (skip)
i = 6: 49 % 6 == 1 ❌ (skip)

i = 7: 49 % 7 == 0 ✓
divisorCount++ = 3
divisorSum += 7 = 57
i != 49/7 (7 == 7) ❌ (perfect square)

Check: divisorCount == 4? ❌ (count=3)
Skip, totalSum remains 80

Final Result:

return totalSum = 80
  */
