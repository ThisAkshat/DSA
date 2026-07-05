class Solution {
    public int compareVersion(String version1, String version2) {

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int n = Math.max(v1.length, v2.length);

        for (int i = 0; i < n; i++) {

            int num1 = (i < v1.length) ? Integer.parseInt(v1[i]) : 0;
            int num2 = (i < v2.length) ? Integer.parseInt(v2[i]) : 0;

            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
        }

        return 0;
    }
}

/*
Explanation:

This problem asks us to compare two version strings like "1.2" and "1.10" revision by revision from left to right. The trick is that "10" is greater than "2" numerically even though "2" comes after "1" alphabetically, so we must compare them as integers not strings. Also if one version has fewer parts, the missing parts are treated as 0.

The approach splits both version strings on the dot character to get individual revision parts as string arrays. Then we loop up to the length of whichever array is longer. At each index we parse both sides as integers, treating out of bounds as 0. If one side is smaller we return -1, if larger we return 1. If we get through all revisions without any difference we return 0.

Dry Run (line by line on version1 = "1.2", version2 = "1.10"):

line: v1 = version1.split("\\.") -> splits "1.2" on dot -> v1 = ["1","2"]
line: v2 = version2.split("\\.") -> splits "1.10" on dot -> v2 = ["1","10"]
line: n = Math.max(v1.length, v2.length) -> Math.max(2,2) -> n = 2

Loop i = 0:
line: i < v1.length -> 0 < 2 -> true, so num1 = Integer.parseInt(v1[0]) = Integer.parseInt("1") = 1
line: i < v2.length -> 0 < 2 -> true, so num2 = Integer.parseInt(v2[0]) = Integer.parseInt("1") = 1
line: num1 < num2 -> 1 < 1 -> false, skip
line: num1 > num2 -> 1 > 1 -> false, skip
no return, continue loop

Loop i = 1:
line: i < v1.length -> 1 < 2 -> true, so num1 = Integer.parseInt(v1[1]) = Integer.parseInt("2") = 2
line: i < v2.length -> 1 < 2 -> true, so num2 = Integer.parseInt(v2[1]) = Integer.parseInt("10") = 10
line: num1 < num2 -> 2 < 10 -> true
line: return -1

Output: -1 (matches expected, version1 is less than version2)

---

Dry Run (line by line on version1 = "1.0", version2 = "1.0.0.0"):

line: v1 = "1.0".split("\\.") -> v1 = ["1","0"]
line: v2 = "1.0.0.0".split("\\.") -> v2 = ["1","0","0","0"]
line: n = Math.max(2,4) -> n = 4

Loop i = 0:
line: i < v1.length -> 0 < 2 -> true, num1 = Integer.parseInt("1") = 1
line: i < v2.length -> 0 < 4 -> true, num2 = Integer.parseInt("1") = 1
line: 1 < 1 -> false, 1 > 1 -> false, continue

Loop i = 1:
line: i < v1.length -> 1 < 2 -> true, num1 = Integer.parseInt("0") = 0
line: i < v2.length -> 1 < 4 -> true, num2 = Integer.parseInt("0") = 0
line: 0 < 0 -> false, 0 > 0 -> false, continue

Loop i = 2:
line: i < v1.length -> 2 < 2 -> false, so num1 = 0 (out of bounds, treated as 0)
line: i < v2.length -> 2 < 4 -> true, num2 = Integer.parseInt("0") = 0
line: 0 < 0 -> false, 0 > 0 -> false, continue

Loop i = 3:
line: i < v1.length -> 3 < 2 -> false, so num1 = 0
line: i < v2.length -> 3 < 4 -> true, num2 = Integer.parseInt("0") = 0
line: 0 < 0 -> false, 0 > 0 -> false, continue

Loop ends (i = 4, i < n is false)
line: return 0

Output: 0 (matches expected, all missing revisions in version1 treated as 0, both versions are equal)
*/
