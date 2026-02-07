class Solution {
    public int numMagicSquaresInside(int[][] grid) {
     int rows = grid.length;
     int cols = grid[0].length;

     int count = 0;
     for(int i = 0; i<=rows-3; i++)   {
        for(int j = 0; j<=cols-3; j++) {
            if(isMagicGrid(grid, i, j)){
                count++;
            }
        }
     }
     return count;
    }
    private boolean isMagicGrid(int[][] grid, int r, int c){
        Set<Integer> st = new HashSet<>();
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                int num = grid[r+i][c+j];
                if(num<1 || num>9 || st.contains(num)){
                    return false;
                }else{
                    st.add(num);
                }
            }
        }
        int SUM = grid[r][c] + grid[r][c+1] + grid[r][c+2];
        for(int i=0; i<3; i++){
            if(grid[r+i][c] + grid[r+i][c+1] + grid[r+i][c+2] != SUM){
                return false;
            }
            if(grid[r][c+i] + grid[r+1][c+i] + grid[r+2][c+i] != SUM){
                return false;
            }
        }
        if(grid[r][c] + grid[r+1][c+1] + grid[r+2][c+2] != SUM){
            return false;
        }
        if(grid[r][c+2] + grid[r+1][c+1] + grid[r+2][c] != SUM){
            return false;
        }
        return true;
    }
}
/*
grid = [
    [8, 1, 6, 3, 5],
    [3, 5, 7, 4, 2],
    [4, 9, 2, 1, 6],
    [2, 7, 9, 8, 4],
    [6, 3, 5, 7, 1]
]
*/




/*
* Problem :-

Tumhe ek big grid diya hai (matrix).
Uske andar tumhe dekhna hai:
Kitne 3×3 ke chhote squares “Magic Square” hain?

* Magic Square ka matlab kya?
3×3 ka square magic hota hai agar:
1. Usme 1 se 9 tak ke number ho
2. Koi number repeat na ho
3. Har row ka sum same ho
4. Har column ka sum same ho
5. Dono diagonal ka sum same ho Bas.

*******************************************************************************

* Example Magic Square :-
8 1 6
3 5 7
4 9 2

Rows:
8+1+6 = 15
3+5+7 = 15
4+9+2 = 15

Columns:
8+3+4 = 15
1+5+9 = 15
6+7+2 = 15

Diagonals:
8+5+2 = 15
6+5+4 = 15

Sab 15 → Magic Square ✅
*/
