class Solution {
    public int triangularSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums){
            list.add(n);
        }
        while(list.size() > 1){
            List<Integer> newList = new ArrayList<>();
            for(int i =0; i<list.size()-1; i++){
                int a = list.get(i);
                int b = list.get(i+1);
                int sum = (a+b)%10;
                newList.add(sum);
            }
            list = newList;
        }
        return list.get(0);
    }
}

/*
Explanation:

This problem asks us to repeatedly shrink the array by replacing each adjacent pair with their sum mod 10, until only one element remains. That final element is the answer.

The approach copies nums into a list, then keeps replacing it with a new shorter list where each element is (current[i] + current[i+1]) % 10. The mod 10 ensures we only keep the last digit of the sum. Each iteration the list shrinks by one element. When the list has only one element left, we return it.

Dry Run (line by line on nums = [1,2,3,4,5]):

line: list = new ArrayList()
line: for loop adds all nums to list -> list = [1,2,3,4,5]

While iteration 1, list.size() = 5 > 1 -> true:
line: newList = new ArrayList() -> newList = []

i = 0:
line: a = list.get(0) = 1, b = list.get(1) = 2
line: sum = (1+2)%10 = 3
line: newList.add(3) -> newList = [3]

i = 1:
line: a = list.get(1) = 2, b = list.get(2) = 3
line: sum = (2+3)%10 = 5
line: newList.add(5) -> newList = [3,5]

i = 2:
line: a = list.get(2) = 3, b = list.get(3) = 4
line: sum = (3+4)%10 = 7
line: newList.add(7) -> newList = [3,5,7]

i = 3:
line: a = list.get(3) = 4, b = list.get(4) = 5
line: sum = (4+5)%10 = 9
line: newList.add(9) -> newList = [3,5,7,9]

line: list = newList -> list = [3,5,7,9]

While iteration 2, list.size() = 4 > 1 -> true:
line: newList = []

i = 0:
line: a = 3, b = 5, sum = (3+5)%10 = 8
line: newList = [8]

i = 1:
line: a = 5, b = 7, sum = (5+7)%10 = 12%10 = 2
line: newList = [8,2]

i = 2:
line: a = 7, b = 9, sum = (7+9)%10 = 16%10 = 6
line: newList = [8,2,6]

line: list = [8,2,6]

While iteration 3, list.size() = 3 > 1 -> true:
line: newList = []

i = 0:
line: a = 8, b = 2, sum = (8+2)%10 = 10%10 = 0
line: newList = [0]

i = 1:
line: a = 2, b = 6, sum = (2+6)%10 = 8
line: newList = [0,8]

line: list = [0,8]

While iteration 4, list.size() = 2 > 1 -> true:
line: newList = []

i = 0:
line: a = 0, b = 8, sum = (0+8)%10 = 8
line: newList = [8]

line: list = [8]

While condition: list.size() = 1 > 1 -> false, exit loop

line: return list.get(0) -> return 8

Output: 8 (matches expected)
*/
