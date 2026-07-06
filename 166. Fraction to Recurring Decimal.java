import java.util.*;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }

        // Use long to avoid overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Integer part
        sb.append(num / den);
        long rem = num % den;

        if (rem == 0) return sb.toString();

        sb.append(".");

        // Map remainder -> index in StringBuilder
        Map<Long, Integer> map = new HashMap<>();

        while (rem != 0) {
            if (map.containsKey(rem)) {
                int index = map.get(rem);
                sb.insert(index, "(");
                sb.append(")");
                break;
            }

            map.put(rem, sb.length());

            rem *= 10;
            sb.append(rem / den);
            rem %= den;
        }

        return sb.toString();
    }
}

/*
Explanation:

This problem asks us to convert a fraction into a decimal string, and if the decimal part repeats, wrap the repeating part in parentheses. The key insight is that a decimal repeats when the same remainder appears again during long division. So we simulate manual long division and use a HashMap to remember where each remainder was first seen in the result string. The moment a remainder repeats, we know the part starting from that earlier position is the repeating block.

Sign is handled separately using XOR. If exactly one of numerator or denominator is negative the result is negative, otherwise positive.

Long is used instead of int to avoid overflow since numerator and denominator can be at the edge of int range and negating them or multiplying by 10 can overflow.

Integer part is computed by simple division num / den. Remainder is num % den. If remainder is 0 we are done.

Otherwise we enter long division loop. Before processing a remainder we check if we have seen it before. If yes, we found the start of the repeating cycle, so we insert an opening parenthesis at that earlier saved index and append a closing parenthesis at the end, then break. If it is a new remainder, we save its position (current length of sb) in the map, then multiply remainder by 10, append rem/den as the next digit, and update rem to rem%den. This exactly mirrors how you do long division by hand.

Dry Run (line by line on numerator = 4, denominator = 333):

line: numerator == 0 -> 4 == 0 -> false, continue
line: (numerator < 0) ^ (denominator < 0) -> false ^ false -> false, no minus sign appended
line: num = Math.abs((long)4) -> num = 4
line: den = Math.abs((long)333) -> den = 333
line: sb.append(num / den) -> 4 / 333 = 0, sb = "0"
line: rem = num % den -> rem = 4 % 333 = 4
line: rem == 0 -> false, continue
line: sb.append(".") -> sb = "0."
line: map = new HashMap() -> map = {}

While loop iteration 1, rem = 4:
line: map.containsKey(4) -> false, skip if block
line: map.put(4, sb.length()) -> sb.length() = 2, map = {4:2}
line: rem *= 10 -> rem = 40
line: sb.append(rem / den) -> 40 / 333 = 0, sb = "0.0"
line: rem %= den -> rem = 40 % 333 = 40

While loop iteration 2, rem = 40:
line: map.containsKey(40) -> false, skip if block
line: map.put(40, sb.length()) -> sb.length() = 3, map = {4:2, 40:3}
line: rem *= 10 -> rem = 400
line: sb.append(rem / den) -> 400 / 333 = 1, sb = "0.01"
line: rem %= den -> rem = 400 % 333 = 67

While loop iteration 3, rem = 67:
line: map.containsKey(67) -> false, skip if block
line: map.put(67, sb.length()) -> sb.length() = 4, map = {4:2, 40:3, 67:4}
line: rem *= 10 -> rem = 670
line: sb.append(rem / den) -> 670 / 333 = 2, sb = "0.012"
line: rem %= den -> rem = 670 % 333 = 4

While loop iteration 4, rem = 4:
line: map.containsKey(4) -> true, we have seen remainder 4 before
line: index = map.get(4) -> index = 2
line: sb.insert(2, "(") -> inserts "(" at position 2 in "0.012" -> sb = "0.(012"
line: sb.append(")") -> sb = "0.(012)"
line: break

line: return sb.toString() -> return "0.(012)"

Output: "0.(012)" (matches expected)

---

Dry Run (line by line on numerator = 1, denominator = 2):

line: numerator == 0 -> false
line: sign check -> false ^ false -> false, no minus
line: num = 1, den = 2
line: sb.append(1 / 2) -> 0, sb = "0"
line: rem = 1 % 2 = 1
line: rem == 0 -> false, continue
line: sb.append(".") -> sb = "0."
line: map = {}

While loop iteration 1, rem = 1:
line: map.containsKey(1) -> false
line: map.put(1, sb.length()) -> sb.length() = 2, map = {1:2}
line: rem *= 10 -> rem = 10
line: sb.append(10 / 2) -> sb.append(5) -> sb = "0.5"
line: rem %= 2 -> rem = 10 % 2 = 0

While loop condition: rem != 0 -> 0 != 0 -> false, exit loop

line: return sb.toString() -> return "0.5"

Output: "0.5" (matches expected)


*/
