class Solution {
    public int numberOfBeams(String[] bank) {
        int count = 0;
        int prev = 0;

        for (String row : bank) {
            long curr = row.chars().filter(ch -> ch == '1').count();
            if (curr == 0) continue; 
            count += prev * curr;
            prev = (int) curr;
        }
        return count;
    }
}

/*
1. PROBLEM :-
   You are given a bank represented as a binary string array.

Each row represents a floor:
'1' → security device present
'0' → empty space
A laser beam exists between two devices if:
1. Devices are in different rows
2. All rows between them contain no devices
Goal: Find the total number of laser beams.
---

Core Idea: Only rows containing devices matter.

For every row:
* Count number of devices ('1')
* Ignore empty rows

If current row has devices:
Laser beams formed:
previousRowDevices × currentRowDevices
Why?
Because every device in previous valid row connects with every device in current valid row.
Then update: previousRowDevices = currentRowDevices
---

How the Code Works:
Variable: count → total laser beams
prev → number of devices in previous valid row
Initially:
prev = 0

---
Loop through every row:
Count number of '1'
Code: row.chars().filter(ch -> ch == '1').count()

---

If current row has no devices:
continue
Because empty rows do not contribute.

---

If row has devices:
Add beams:
count += prev × curr
Then update:
prev = curr

---

Why multiplication?
Suppose:
Previous row:
3 devices
Current row:
2 devices
Every device connects with every other device:
3 × 2 = 6 beams

---

Time Complexity: O(m × n)
m → rows
n → columns
Every cell is checked once.
Space Complexity: O(1)
Only variables are used.

---

2. EXAMPLE :-
Input:
bank =
["011001",
"000000",
"010100",
"001000"]

---

Row 1:
"011001"
Devices = 3
prev = 0
Beams:
0 × 3 = 0
Update:
prev = 3

---

Row 2:
"000000"
Devices = 0
Skip row

---

Row 3:
"010100"
Devices = 2
Beams:
3 × 2 = 6
count = 6
Update:
prev = 2

---

Row 4: "001000"
Devices = 1
Beams: 2 × 1 = 2
count = 8

---

Final Answer: 8

---

3. DRY RUN :-
Example (custom tough case)
Input:
bank =
["10101",
"00000",
"11000",
"00000",
"01110"]

---
 
Row 1:"10101"
Devices = 3
Beams: 0 × 3 = 0
prev = 3
count = 0
---

Row 2: "00000"
Devices = 0
Skip
---

Row 3: "11000"
Devices = 2
Beams: 3 × 2 = 6
count = 6
prev = 2
---

Row 4: "00000"
Devices = 0
Skip
---

Row 5: "01110"
Devices = 3
Beams: 2 × 3 = 6
count = 12
prev = 3
---

Final Answer: 12


*/
