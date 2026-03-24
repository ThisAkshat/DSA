class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int count = numBottles; 
        int empty = numBottles;  
        while (empty >= numExchange) {
            empty -= numExchange; 
            count++;             
            empty++;   
            numExchange++;      
        }

        return count;
    }
}

/*
1. PROBLEM :-
   You are given two integers:

numBottles → number of full water bottles you initially have
numExchange → number of empty bottles required to exchange for one full bottle

Rules of the problem:

1. You can drink any number of full bottles at any time.
2. When you drink a bottle, it becomes an empty bottle.
3. You can exchange empty bottles for one full bottle.
4. After every exchange, the value of numExchange increases by 1.
5. You cannot exchange multiple times using the same numExchange value in one step.

Goal:
Find the maximum number of water bottles you can drink.
Main idea of the solution:
1. Initially drink all the bottles.
2. Count those bottles.
3. Track how many empty bottles you have.
4. If empty bottles are enough to exchange:

   * Exchange bottles
   * Drink the new bottle
   * Increase numExchange
5. Continue until exchange is no longer possible.
This problem is different from Water Bottles I because the exchange cost keeps increasing.
Time Complexity: O(n) in worst case
Space Complexity: O(1)

2. EXAMPLE :-
Input
numBottles = 13
numExchange = 6

Step 1
Drink all bottles
Total drunk = 13
Empty bottles = 13

Step 2
Exchange bottles
13 >= 6 → exchange possible
Use 6 empty bottles → get 1 full bottle
Empty bottles left =
13 - 6 = 7
Drink the new bottle
Total drunk =
13 + 1 = 14
Empty bottles now =
7 + 1 = 8
Increase exchange requirement
numExchange = 7

Step 3
Check again
8 >= 7 → exchange possible
Use 7 empty bottles
Empty bottles left =
8 - 7 = 1
Drink new bottle
Total drunk =
14 + 1 = 15
Empty bottles now =
1 + 1 = 2
Increase exchange requirement
numExchange = 8

Step 4
Now
2 < 8 → exchange not possible
Final Answer = 15

3. DRY RUN :-
Example (tough case)
numBottles = 20
numExchange = 4
Initial State
Drink all bottles
count = 20
empty = 20
numExchange = 4

Step 1
20 >= 4
Exchange bottles
empty = 20 - 4 = 16
Drink new bottle
count = 21
empty = 16 + 1 = 17
Increase exchange cost
numExchange = 5

Step 2
17 >= 5
empty = 17 - 5 = 12
count = 22
empty = 12 + 1 = 13
numExchange = 6

Step 3
13 >= 6
empty = 13 - 6 = 7
count = 23
empty = 7 + 1 = 8
numExchange = 7

Step 4
8 >= 7
empty = 8 - 7 = 1
count = 24
empty = 1 + 1 = 2
numExchange = 8

Step 5
2 < 8
Stop.
Final Answer = 24 bottles drunk.


*/
