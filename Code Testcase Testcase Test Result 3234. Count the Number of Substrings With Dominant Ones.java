class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] prev_zero = new int[n + 1];
        prev_zero[0] = -1;
        int res = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0 || s.charAt(i - 1) == '0') {
                prev_zero[i + 1] = i;
            } else {
                prev_zero[i + 1] = prev_zero[i];
            }
        }

        for (int end = 1; end <= n; end++) {
            int zero_count = s.charAt(end - 1) == '0' ? 1 : 0;
            int current_pos = end;

            while (current_pos > 0 && zero_count * zero_count <= n) {
                int segment_length = end - prev_zero[current_pos]; 
                int one_count = segment_length - zero_count;

                if (zero_count * zero_count <= one_count) {
                    int max_valid_length = one_count - zero_count * zero_count + 1;
                    int available_length = current_pos - prev_zero[current_pos];
                    res += Math.min(available_length, max_valid_length);
                }

                current_pos = prev_zero[current_pos];
                zero_count++;
            }
        }

        return res;
    }
}


/*
Problem :-
Tumhe ek binary string s diya gaya hai, jo sirf '0' aur '1' se bana hai.
Tumhe us string ke sabhi possible substrings consider karne hain.
Substring ka matlab:
Continuous characters ka part.
Jaise agar s = "101" hai, to substrings ho sakte hain:
"1", "0", "1", "10", "01", "101"

Ab condition kya hai:
Ek substring tab valid hai (dominant ones) jab:

number of ones >= (number of zeros)^2

Matlab:
Substring me jitne zero hain,
unke square se zyada ya barabar ones hone chahiye.

Example samajh lo:

Agar kisi substring me:
zeros = 2
ones = 3

To check:
2^2 = 4
3 >= 4 ?
Nahi → valid nahi hai.

Agar:
zeros = 1
ones = 2

1^2 = 1
2 >= 1 ?
Haan → valid hai.

Special case:
Agar zeros = 0
To 0^2 = 0
To koi bhi substring jisme sirf ones hain, wo valid hoga.

Tumhara kaam:

Poore string ke andar jitne bhi substrings bante hain,
unme se kitne substrings aise hain
jahan ones >= (zeros)^2

Bas unki total count return karni hai.

*************************************************************************************************8

Example 1:

Input: s = "00011"

Output: 5

Explanation:

The substrings with dominant ones are shown in the table below.

i	j	s[i..j]	Number of Zeros  	Number of Ones
3	3	1	         0	                       1
4	4	1	         0	                       1
2	3	01	         1                          1
3	4	11	         0                     	   2
2	4	011	         1	                        2

*************************************************************************************************8

Dry run for example:

s = "00011"
n = 5

We check all possible substrings and count those where:

number of ones >= (number of zeros)^2

---

Start from index 0

Substring: "0"
zeros = 1, ones = 0
1^2 = 1
0 >= 1 → false

Substring: "00"
zeros = 2, ones = 0
2^2 = 4
0 >= 4 → false

Substring: "000"
zeros = 3, ones = 0
3^2 = 9
0 >= 9 → false

Substring: "0001"
zeros = 3, ones = 1
3^2 = 9
1 >= 9 → false

Substring: "00011"
zeros = 3, ones = 2
3^2 = 9
2 >= 9 → false

---

Start from index 1

Substring: "0"
zeros = 1, ones = 0
1 >= 1 → false

Substring: "00"
zeros = 2, ones = 0
0 >= 4 → false

Substring: "001"
zeros = 2, ones = 1
1 >= 4 → false

Substring: "0011"
zeros = 2, ones = 2
2 >= 4 → false

---

Start from index 2

Substring: "0"
zeros = 1, ones = 0
0 >= 1 → false

Substring: "01"
zeros = 1, ones = 1
1 >= 1 → true
count = 1

Substring: "011"
zeros = 1, ones = 2
2 >= 1 → true
count = 2

---

Start from index 3

Substring: "1"
zeros = 0, ones = 1
1 >= 0 → true
count = 3

Substring: "11"
zeros = 0, ones = 2
2 >= 0 → true
count = 4

---

Start from index 4

Substring: "1"
zeros = 0, ones = 1
1 >= 0 → true
count = 5

---

Final count = 5


*/




