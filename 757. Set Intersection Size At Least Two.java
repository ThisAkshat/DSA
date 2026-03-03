class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] vec1, int[] vec2){
                if(vec1[1] != vec2[1]){
                    return Integer.compare(vec1[1],vec2[1]);
                }
                return Integer.compare(vec2[0],vec1[0]);
            }
        });
        int result = 0;
        int first = -1;
        int second = -1;

        for(int i =0; i<n; i++){
            int l = intervals[i][0];
            int r = intervals[i][1];

            if(l <= first)
            continue;
            if(l>second){
                result = result + 2;
                second = r;
                first = r-1;
            }else{
                result = result+1;
                first = second;
                second = r;
            }
        }
        return result;
    }
}

/*
PROBLEM--
Tumhare paas kuch number ranges hain.

Example:
[1,3] ka matlab hai 1, 2, 3
[3,7] ka matlab hai 3, 4, 5, 6, 7

Ab tumhe ek alag list banani hai numbers ki.
Is list ka naam maan lo “magic list”.
Rule kya hai?
Har range ke andar se kam se kam 2 numbers tumhari magic list me hone chahiye.
Matlab:
Agar range hai [1,3]
To tumhari list me 1,2,3 me se kam se kam 2 numbers hone chahiye.

Agar range hai [3,7]
To 3,4,5,6,7 me se kam se kam 2 numbers hone chahiye.
Ab important baat:
Tumhe aisi magic list banani hai jisme numbers kam se kam ho.
Matlab list chhoti se chhoti ho, lekin har range ko satisfy kare.

To problem basically yeh puch rahi hai:
Aisi sabse chhoti list ka size kya hoga
jisme har given range ke liye kam se kam 2 numbers mil jayein?

********************************************************************************************

Dry run for example:
intervals = [[1,3],[1,4],[2,5],[3,5]]

---

Step 1: Sorting
Sort by end increasing, and if end same then start decreasing.
After sorting:
[1,3]
[1,4]
[3,5]
[2,5]

---

Initialize:
result = 0
first = -1
second = -1

---

i = 0
interval = [1,3]
l = 1, r = 3

l <= first ?
1 <= -1 → false

l > second ?
1 > -1 → true

So we add 2 numbers

result = 2
second = 3
first = 2

---

i = 1
interval = [1,4]
l = 1, r = 4

l <= first ?
1 <= 2 → true

So continue
Nothing changes

result = 2
first = 2
second = 3

---

i = 2
interval = [3,5]
l = 3, r = 5

l <= first ?
3 <= 2 → false

l > second ?
3 > 3 → false

So else case runs

result = result + 1
result = 3

first = second = 3
second = r = 5

---

i = 3
interval = [2,5]
l = 2, r = 5

l <= first ?
2 <= 3 → true

continue

---

Loop ends
Final result = 3
Return 3

****************************************************************************

Example 1:
Input: intervals = [[1,3],[3,7],[8,9]]
Output: 5
Explanation: let nums = [2, 3, 4, 8, 9].
It can be shown that there cannot be any containing array of size 4.

Example 2:
Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
Output: 3
Explanation: let nums = [2, 3, 4].
It can be shown that there cannot be any containing array of size 2.

*/


