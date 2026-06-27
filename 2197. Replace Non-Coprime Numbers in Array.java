class Solution {
    public long gcd(long a, long b){
        while(b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public long lcm(long a, long b){
        return (a * b) / gcd(a,b);
    }
    public List<Integer> replaceNonCoprimes(int[] nums) {
        int n = nums.length;
        Stack<Long> st = new Stack<>();
        for(int i = 0; i < n; i++){
            st.push((long)nums[i]);
            while(st.size() > 1){
                long x = st.pop();
                long y = st.peek();
                long a = gcd(y, x);
                if(a > 1){
                    long b = lcm(st.pop(), x);
                    st.push(b);
                }
                else {
                st.push(x);
                break;
                }
             }
        }
        List<Integer> list = new ArrayList<>();
        for(long ele : st){
            list.add((int)ele);
        }
        return list;
    }
}
/*
Explanation:

This problem is solved using a stack. We go through the array one number at a time and keep merging adjacent non-coprime numbers using their LCM, until what's left on the stack are numbers that are pairwise coprime with their neighbors.

Step by step logic:
1. Take the next number from nums and push it onto the stack.
2. Look at the top two elements of the stack (the one we just pushed, and the one below it).
3. Compute gcd of these two. If gcd > 1, they are non-coprime, so pop both, calculate their lcm, and push that lcm back onto the stack as a single merged number.
4. Because this new merged number might now be non-coprime with whatever is below it on the stack, repeat step 2 and 3 again on the new top two elements.
5. If gcd = 1 (they are coprime), stop merging, push the current number back if needed, and move on to the next number in the array.
6. After processing every number in nums, whatever remains in the stack, from bottom to top, is the final answer.

Why a stack works here: once a number is merged into a bigger LCM, that merged value could combine further with what was below it earlier, but it can never combine with something that comes later in the array until that later number is actually pushed. A stack naturally captures this last-in-first-out merging behavior.

Helper functions:
gcd(a, b): standard Euclidean algorithm, repeatedly replacing (a, b) with (b, a mod b) until b becomes 0, then a is the gcd.
lcm(a, b): computed as (a * b) / gcd(a, b).

Dry Run:

Input: nums = [6, 4, 3, 2, 7, 6, 2]

i = 0, push 6
stack = [6]

i = 1, push 4
stack = [6, 4]
top two: 4 and 6, gcd(6,4) = 2 > 1, non-coprime
pop both, lcm(6,4) = 12, push 12
stack = [12]
only one element left, stop inner loop

i = 2, push 3
stack = [12, 3]
top two: 3 and 12, gcd(12,3) = 3 > 1
pop both, lcm(12,3) = 12, push 12
stack = [12]

i = 3, push 2
stack = [12, 2]
top two: 2 and 12, gcd(12,2) = 2 > 1
pop both, lcm(12,2) = 12, push 12
stack = [12]

i = 4, push 7
stack = [12, 7]
top two: 7 and 12, gcd(12,7) = 1, coprime
push 7 back, break
stack = [12, 7]

i = 5, push 6
stack = [12, 7, 6]
top two: 6 and 7, gcd(7,6) = 1, coprime
push 6 back, break
stack = [12, 7, 6]

i = 6, push 2
stack = [12, 7, 6, 2]
top two: 2 and 6, gcd(6,2) = 2 > 1
pop both, lcm(6,2) = 6, push 6
stack = [12, 7, 6]
check again, top two: 6 and 7, gcd(7,6) = 1, coprime
push 6 back, break
stack = [12, 7, 6]

End of array, final stack from bottom to top = [12, 7, 6]

Output: [12, 7, 6]

This matches the expected result from the problem statement.
*/
