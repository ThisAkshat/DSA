class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = numBottles;
        while(numBottles>=numExchange){
            int newBottles = numBottles/numExchange;
            numBottles = (numBottles%numExchange)+newBottles;
            count = count+newBottles;
        }
    return count;
    }
}

/*
1. PROBLEM :-
   You are given two integers:

numBottles → number of full water bottles you initially have.
numExchange → number of empty bottles required to get 1 new full bottle.

Process:
When you drink a bottle, it becomes an empty bottle. These empty bottles can be exchanged in the market to get new full bottles. Every time you collect enough empty bottles equal to numExchange, you can exchange them for one full bottle.

Your goal is to calculate the maximum number of bottles you can drink in total.

Important points:

* Initially all bottles are full.
* After drinking, they become empty.
* Empty bottles can be reused for exchange.
* The process continues until you no longer have enough empty bottles to exchange.

Logic behind the code:

1. First, drink all initial bottles.
2. Count those bottles.
3. Use empty bottles to exchange for new bottles.
4. Add those new bottles to the total count.
5. Repeat until exchange is not possible.

Time Complexity: O(log n) approximately
Space Complexity: O(1)

2. EXAMPLE :-

Input
numBottles = 9
numExchange = 3

Step 1
Initially you have 9 full bottles.

Drink all bottles
Total drunk = 9
Empty bottles = 9

Step 2
Exchange empty bottles

9 empty / 3 = 3 new bottles

Drink those bottles

Total drunk =
9 + 3 = 12

Now empty bottles become
3

Step 3
Exchange again

3 empty / 3 = 1 new bottle

Drink it

Total drunk =
12 + 1 = 13

Remaining empty bottles = 1

Now exchange is not possible.

Final Answer = 13

3. DRY RUN :-

Example (tough case)

numBottles = 23
numExchange = 4

Initial State
count = 23
numBottles = 23

Step 1
Check condition

23 >= 4 (true)

newBottles = 23 / 4 = 5
remaining empty = 23 % 4 = 3

Update bottles

numBottles = 3 + 5 = 8
count = 23 + 5 = 28

Step 2

8 >= 4 (true)

newBottles = 8 / 4 = 2
remaining empty = 8 % 4 = 0

numBottles = 0 + 2 = 2
count = 28 + 2 = 30

Step 3

2 >= 4 (false)

Loop stops.

Total bottles you drank = 30

Final Answer = 30



*/
