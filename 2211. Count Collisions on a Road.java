 /*
Intuition
Cars moving Left ('L') from leftmost end will never collide (exit road).
Cars moving Right ('R') from rightmost end will never collide (exit road).
Only cars in the middle can collide!

Example 1:
Input: directions = "RLRSLL"
Output: 5
Explanation:
The collisions that will happen on the road are:
- Cars 0 and 1 will collide with each other. Since they are moving in opposite directions, the number of collisions becomes 0 + 2 = 2.
- Cars 2 and 3 will collide with each other. Since car 3 is stationary, the number of collisions becomes 2 + 1 = 3.
- Cars 3 and 4 will collide with each other. Since car 3 is stationary, the number of collisions becomes 3 + 1 = 4.
- Cars 4 and 5 will collide with each other. After car 4 collides with car 3, it will stay at the point of collision and get hit by car 5. The number of collisions becomes 4 + 1 = 5.
Thus, the total number of collisions that will happen on the road is 5. 

Example 2:
Input: directions = "LLRR"
Output: 0
Explanation:
No cars will collide with each other. Thus, the total number of collisions that will happen on the road is 0.
 
Approach
1. Skip leading 'L's → They exit left without collision

2. Skip trailing 'R's → They exit right without collision

3. Count all non-'S' cars in middle → They will eventually collide
  */

class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();

        int i=0;
        while(i<n && directions.charAt(i) == 'L'){
            i++;
        }
        int j=n-1;
        while(j>=0 && directions.charAt(j) == 'R'){
            j--;
        }
        int collisions = 0;
        while(i<=j){
            if(directions.charAt(i) != 'S'){
                collisions++;
            }
            i++;
        }
        return collisions;
    }
}


