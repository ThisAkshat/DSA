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
/*
Example: corridor = "SSPPSPS"
Indices: 0 1 2 3 4 5 6
Chars: S S P P S P S

Line 1-4: Initialization

class Solution {
    int mod = 1000000007;
    public int numberOfWays(String corridor) {
        ArrayList<Integer> possSeats = new ArrayList<>();
        
Execution:
* mod = 1000000007
* corridor = "SSPPSPS"
* possSeats = [] (empty ArrayList)

Line 5-9: Collect Seat Positions

for(int i = 0; i < corridor.length(); i++) {  // i=0 to 6
    if(corridor.charAt(i) == 'S') {
        possSeats.add(i);
    }
}

Loop Execution:
i=0: char='S' → add(0) → [0]
i=1: char='S' → add(1) → [0,1]
i=2: char='P' → skip
i=3: char='P' → skip
i=4: char='S' → add(4) → [0,1,4]
i=5: char='P' → skip
i=6: char='S' → add(6) → [0,1,4,6]
After loop: possSeats = [0, 1, 4, 6]


Line 10-13: Validity Check

if(possSeats.size() % 2 != 0 || possSeats.size() == 0) {
    return 0;
}
Check:
* possSeats.size() = 4
* 4 % 2 = 0 → not odd ✓
* 4 == 0? false ✓
* Condition false → continue

Line 14-16: Initialization for Calculation

long result = 1;
int prev = possSeats.get(1);

Execution:
* result = 1
* prev = possSeats.get(1) = 1

Line 17-23: Main Calculation Loop

for(int i = 2; i < possSeats.size(); i += 2) {

Iteration 1: i = 2
// i=2 (less than 4 → continue)
int length = possSeats.get(i) - prev;
// length = possSeats.get(2) - prev = 4 - 1 = 3

result = (result * length) % mod;
// result = (1 × 3) % mod = 3

prev = possSeats.get(i + 1);
// prev = possSeats.get(3) = 6
State after iteration 1:

result = 3
prev = 6
i = 2 + 2 = 4

Loop Condition Check:
for(int i = 2; i < possSeats.size(); i += 2) {
// i=4, possSeats.size()=4 → 4 < 4? false → exit loop
Line 24: Return Result

return (int) result;  // return (int)3 = 3
Final Output: 3 ✓
*/
