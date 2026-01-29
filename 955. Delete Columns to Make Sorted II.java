class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int deleteCount = 0;
        boolean[] sorted = new boolean[n-1];
       /* strs = ["a c"
                , "a d"
                , "b b"
                , "b c"] n = 4 (rows), m = 2 (columns) */
        for(int col = 0; col<m; col++){
            boolean needToDelete = false;

            for(int row =0; row < n-1; row++){
                if(!sorted[row] && strs[row].charAt(col) > strs[row +1].charAt(col)){
                    needToDelete = true;
                    break;
                }         
           }
           if(needToDelete){
            deleteCount++;
           }else{
            for(int row = 0; row<n-1; row++){
                if(strs[row].charAt(col)<strs[row+1].charAt(col)){
                    sorted[row] = true;
                }
            }
           }
        }
        return deleteCount;
    }
}




/*
Tere paas strings ki list hai (rows),
har string me same number of characters hote hain (columns).
Tu kisi bhi column ko delete kar sakta hai (sab rows se).
Goal :-
Delete karke aisi strings bana do jisme
strs[0] <= strs[1] <= strs[2] <= ...
dictionary order me aa jaye.
Aur minimum columns delete karne hain.

***************************************************************************

🧩 Code me 3 main cheezein
1️⃣ sorted[] kya hai?
boolean[] sorted = new boolean[n-1];
Agar sorted[i] = true
toh iska matlab:
strs[i] and strs[i+1] already decide ho chuke hain
ab unko future columns me check nahi karna
Matlab unka order already fix ho gaya.

2️⃣ Column by column check hota hai
for(int col = 0; col < m; col++)
Hum har column ko left se right check karte hain.
Har column pe 2 kaam:
Ya toh ❌ delete karo
Ya ✔️ isse order fix karo

3️⃣ Kab column delete hota hai?
if(!sorted[row] && strs[row].charAt(col) > strs[row+1].charAt(col))

Matlab:
Agar koi do strings abhi tak sorted nahi hain
aur upar wali ka letter > neeche wali ka
toh yeh column order bigaad raha hai
→ is column ko delete karo.

*****************************************************************************

Input:
["ca","bb","ac"]

Rows:
0: ca
1: bb
2: ac

sorted = [false, false]
Column 0 → c, b, a
Check:
c > b  ❌  (problem)

Isliye:
deleteCount = 1
sorted change nahi hota.
Column 1 → a, b, c

Check:
a < b  → sorted[0] = true
b < c  → sorted[1] = true

Ab sab sorted ho gaye.
No delete.
Final:
deleteCount = 1
✅ Correct

*/
