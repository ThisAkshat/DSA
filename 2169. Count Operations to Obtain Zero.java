class Solution {
    public int countOperations(int num1, int num2) {
        int count = 0;
        while(num1!=0 && num2!=0){
            if(num1 >= num2){
                num1 = num1 - num2;
            }else{
                num2 = num2 - num1;
            }
            count++;
        }
        return count;
    }
}


/*
* What the problem is doing

You have two numbers: num1 and num2
At every step:
. If num1 ≥ num2 → subtract num2 from num1
. Else → subtract num1 from num2
You keep doing this until one of them becomes zero.
You just count how many subtractions happen.
This is literally a slow-motion version of the Euclidean algorithm (the algorithm to find GCD)

************************************************************************************************

* Dry run – Example 1

num1 = 2 , num2 = 3

Step	                 num1        	 num2       	What happens
Start	                 2            	3         	num1 < num2
1	                     2            	1           	3 − 2
2	                     1            	1            	2 − 1
3	                     0            	1            	1 − 1
Stop	                 —            	—          	num1 is 0

Total operations = 3

************************************************************************************************

* Dry run – Example 2
num1 = 10 , num2 = 10

Step	              num1	            num2
Start	               10              	10
1	                    0             	10

Answer = 1


*/

