/*
Intuition
We need to divide corridor into sections with exactly 2 seats each. Between every pair of "2-seat sections", we can place dividers in the gaps between plants.

Approach :-
1. Find all seat positions
2. Check if valid: Total seats must be even and non-zero
3. Between each pair of 2-seat sections, count how many positions we can place dividers
4. Multiply these counts together

Example: "SSPPSPS"
Step 1: Find seat positions

Index: 0 1 2 3 4 5 6
String: S S P P S P S
Seats at: [0, 1, 4, 6]

Step 2: Group seats in pairs

Pair 1: seats 0 & 1 (indices 0,1)
Pair 2: seats 2 & 3 (indices 4,6)  
Gap between pairs: from index 1 to 4 → positions 2,3,4
Step 3: Count divider positions

* After first pair (seat at index 1) to start of next pair (seat at index 4)
* Possible divider positions: between indices 1-2, 2-3, 3-4 → 3 options
* Multiply: 3 ways

Answer: 3 ✓
*/


//  " S S P P S P S "
class Solution {
    int mod = 1000000007;
    public int numberOfWays(String corridor) {
        ArrayList<Integer> possSeats = new ArrayList<>();

        for(int i =0;i<corridor.length(); i++){
            if(corridor.charAt(i) == 'S'){
                possSeats.add(i);
            }
        } // possSeats = [0, 1, 4, 6]

        if(possSeats.size() %2 != 0 || possSeats.size() == 0){
            return 0;
        } // possSeats.size() = 4 → even and > 0 → valid

        long result = 1;
        int prev = possSeats.get(1);

        for(int i=2; i<possSeats.size(); i+=2){
            int length = possSeats.get(i) - prev;
            result = (result * length) % mod;

            prev = possSeats.get(i+1);
        }
        return(int) result;
    }
}
