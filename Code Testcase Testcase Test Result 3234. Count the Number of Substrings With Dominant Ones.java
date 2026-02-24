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
Problem kya keh rahi hai:

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




*/




