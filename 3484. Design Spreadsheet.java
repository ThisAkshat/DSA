class Spreadsheet {
    int[][] mat;
    
    public Spreadsheet(int rows) {
        mat = new int[rows][26];    
    }
    
    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.valueOf(cell.substring(1))-1;
        mat[row][col] = value;
    }
    
    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.valueOf(cell.substring(1))-1;
        mat[row][col] = 0;
    }
    
    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] value = formula.split("\\+");

        int value1 = resolveValue(value[0]);
        int value2 = resolveValue(value[1]);
        
        return value1 + value2;
    }

    int resolveValue(String token) {
        char firstChar = token.charAt(0);

        if(firstChar >= 'A' && firstChar <= 'Z') {
            int col = firstChar - 'A';
            int row = Integer.valueOf(token.substring(1))-1; 
            return mat[row][col];
        }

        return Integer.parseInt(token);
    }
}

/*
Explanation:

This problem is basically simulating a simple spreadsheet, like a tiny version of Excel. It has 26 columns (A to Z) and some number of rows, and each cell holds a number. We need to support setting a cell's value, resetting it back to 0, and evaluating a simple formula that adds two things together (each thing can be either a plain number or a reference to another cell).

The core idea is to store everything in a 2D array mat[row][col], where col is found by converting the letter (A to Z) into an index 0 to 25, and row is found by taking the number part of the cell name and subtracting 1 (since the problem uses 1-indexed rows but arrays are 0-indexed).

setCell(cell, value): extracts the column letter and row number from the cell string, then directly stores value at mat[row][col].

resetCell(cell): same extraction logic, but stores 0 instead, effectively clearing that cell.

getValue(formula): the formula always looks like "=X+Y". First we strip away the leading "=" sign. Then we split the remaining string on the "+" character, which gives us two tokens, X and Y. Each token is resolved individually using a helper function, and the two resolved numbers are added together and returned.

resolveValue(token): this helper checks the first character of the token. If it's an uppercase letter (A to Z), it means this token is a cell reference, so it extracts column and row the same way as before and returns whatever value is sitting in mat[row][col]. If the first character is not a letter (meaning it's a digit), it means this token is a plain number, so it just parses the whole token as an integer and returns it directly.

Dry Run (line by line, using the example given):

new Spreadsheet(3)

line: mat = new int[3][26] -> mat is created, all values default to 0
state: mat = 3 rows x 26 cols, everything 0

Call: getValue("=5+7")

line: formula = formula.substring(1) -> formula becomes "5+7" (removed the "=")
line: value = formula.split("\\+") -> value = ["5", "7"]
line: value1 = resolveValue("5")
 inside resolveValue: firstChar = '5', not between 'A' and 'Z', so condition is false
 line: return Integer.parseInt("5") -> returns 5
 value1 = 5
line: value2 = resolveValue("7")
 same logic, firstChar = '7', not a letter
 line: return Integer.parseInt("7") -> returns 7
 value2 = 7
line: return value1 + value2 -> return 5 + 7 = 12

Output: 12 (matches expected)

Call: setCell("A1", 10)

line: col = cell.charAt(0) - 'A' -> 'A' - 'A' = 0, col = 0
line: row = Integer.valueOf(cell.substring(1)) - 1 -> cell.substring(1) = "1", so row = 1 - 1 = 0
line: mat[0][0] = 10
state: mat[0][0] = 10 now, rest still 0

Call: getValue("=A1+6")

line: formula = formula.substring(1) -> "A1+6"
line: value = formula.split("\\+") -> value = ["A1", "6"]
line: value1 = resolveValue("A1")
 inside resolveValue: firstChar = 'A', this is between 'A' and 'Z', condition true
 line: col = firstChar - 'A' -> 'A' - 'A' = 0, col = 0
 line: row = Integer.valueOf(token.substring(1)) - 1 -> token.substring(1) = "1", row = 1 - 1 = 0
 line: return mat[row][col] -> return mat[0][0] -> returns 10
 value1 = 10
line: value2 = resolveValue("6")
 firstChar = '6', not a letter
 line: return Integer.parseInt("6") -> returns 6
 value2 = 6
line: return value1 + value2 -> return 10 + 6 = 16

Output: 16 (matches expected)

Call: setCell("B2", 15)

line: col = cell.charAt(0) - 'A' -> 'B' - 'A' = 1, col = 1
line: row = Integer.valueOf(cell.substring(1)) - 1 -> cell.substring(1) = "2", row = 2 - 1 = 1
line: mat[1][1] = 15
state: mat[0][0] = 10, mat[1][1] = 15, rest still 0

Call: getValue("=A1+B2")

line: formula = formula.substring(1) -> "A1+B2"
line: value = formula.split("\\+") -> value = ["A1", "B2"]
line: value1 = resolveValue("A1")
 firstChar = 'A', is a letter
 col = 0, row = 0 (same calculation as before)
 return mat[0][0] -> returns 10
 value1 = 10
line: value2 = resolveValue("B2")
 firstChar = 'B', is a letter
 col = 'B' - 'A' = 1
 row = Integer.valueOf("2") - 1 = 1
 return mat[1][1] -> returns 15
 value2 = 15
line: return value1 + value2 -> return 10 + 15 = 25

Output: 25 (matches expected)

Call: resetCell("A1")

line: col = cell.charAt(0) - 'A' -> 'A' - 'A' = 0, col = 0
line: row = Integer.valueOf(cell.substring(1)) - 1 -> "1" - 1 = 0, row = 0
line: mat[0][0] = 0
state: mat[0][0] = 0 (reset), mat[1][1] = 15, rest still 0

Call: getValue("=A1+B2")

line: formula = formula.substring(1) -> "A1+B2"
line: value = formula.split("\\+") -> value = ["A1", "B2"]
line: value1 = resolveValue("A1")
 firstChar = 'A', is a letter
 col = 0, row = 0
 return mat[0][0] -> returns 0 (since it was reset)
 value1 = 0
line: value2 = resolveValue("B2")
 firstChar = 'B', is a letter
 col = 1, row = 1
 return mat[1][1] -> returns 15
 value2 = 15
line: return value1 + value2 -> return 0 + 15 = 15

Output: 15 (matches expected)

Final full output sequence: null, 12, null, 16, null, 25, null, 15

This exactly matches the expected output: [null, 12, null, 16, null, 25, null, 15]
*/
