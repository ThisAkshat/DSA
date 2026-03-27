class Solution {
    public int maxArea(int[] height) {
        int maxWater =0;
        int left_pointer =0;
        int right_pointer=height.length-1;

        while(left_pointer<right_pointer){
            int width = right_pointer - left_pointer;
            int ht = Math.min(height[left_pointer],height[right_pointer]);
            int currentWater = width*ht;
            maxWater = Math.max(maxWater, currentWater);
            if(height[left_pointer]<height[right_pointer]){
                left_pointer++;
            }
            else{
                right_pointer--;
            }
        }
        return maxWater;
        
    }
}

/*
1. PROBLEM :-
   You are given an array height where each value represents the height of a vertical line on a graph. The index of the array represents the x-coordinate.

So each line is like:
(i, 0) to (i, height[i])

If you pick two lines, they form a container with the x-axis. This container can hold water.

Water stored depends on two things:

1. Width between the two lines
2. Minimum height of the two lines

Formula used:
Area = width × minimum height

Where:
width = distance between the two lines
height = smaller height of the two lines

Goal:
Find two lines that form a container that holds the maximum water.
Important rule:
You cannot tilt the container.
Efficient approach used in the code:
Two Pointer Technique

Steps:
1. Place one pointer at the start.
2. Place another pointer at the end.
3. Calculate the water stored.
4. Move the pointer which has smaller height.
5. Repeat until pointers meet.

Why this works:
Moving the taller line will not increase water because the smaller line limits the height.
Time Complexity: O(n)
Space Complexity: O(1)

2. EXAMPLE :-
Input
height = [1,8,6,2,5,4,8,3,7]

Visual heights:
Index:   0 1 2 3 4 5 6 7 8
Height:  1 8 6 2 5 4 8 3 7

The best container is formed by:
Index 1 and Index 8
Heights:
8 and 7
Width = 8 - 1 = 7
Minimum height = 7
Water stored =
7 × 7 = 49
Output = 49

3. DRY RUN :-
Example (detailed run)
height = [4,3,2,1,4]

Start
left_pointer = 0
right_pointer = 4
maxWater = 0

Step 1
Width = 4 - 0 = 4
Height = min(4,4) = 4
Water = 4 × 4 = 16
maxWater = 16
Since both heights are equal, move right pointer
right_pointer = 3

Step 2
left_pointer = 0
right_pointer = 3
Width = 3
Height = min(4,1) = 1
Water = 3 × 1 = 3
maxWater remains 16
Move right pointer
right_pointer = 2

Step 3
left_pointer = 0
right_pointer = 2
Width = 2
Height = min(4,2) = 2
Water = 2 × 2 = 4
maxWater remains 16
Move right pointer
right_pointer = 1

Step 4
left_pointer = 0
right_pointer = 1

Width = 1
Height = min(4,3) = 3
Water = 1 × 3 = 3
maxWater remains 16
Move right pointer
right_pointer = 0
Loop ends.
Final Answer = 16




*/
