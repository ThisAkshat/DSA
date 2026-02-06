class Solution {
    private Map<String, Boolean> t = new HashMap<>();
    public boolean pyramidTransition(String bottom, List<String> allowed) {
     Map<String, List<Character>> mp = new HashMap<>();
     for(String pattern : allowed)   {
        String pair = pattern.substring(0, 2);
        char top = pattern.charAt(2);
        mp.computeIfAbsent(pair, k-> new ArrayList<>()).add(top);
     }
     return solve(bottom, mp, 0, new StringBuilder());
    }
    private boolean solve(String curr, Map<String, List<Character>> mp, int idx, StringBuilder above){
        if(curr.length() == 1){
            return true;
        }
        String key = curr + "_" + idx + "_" + above.toString();
        if(t.containsKey(key)){
            return t.get(key);
        }
        if(idx == curr.length() - 1){
            boolean result = solve(above.toString(), mp, 0, new StringBuilder());
            return result;
        }
        String pair = curr.substring(idx, idx+2);
        if(!mp.containsKey(pair)){
            t.put(key, false);
            return false;
        }
        for(char ch : mp.get(pair)){
            above.append(ch);
            if(solve(curr, mp, idx + 1, above)){
                t.put(key, true);
                return true;
            }
            above.deleteCharAt(above.length()-1);
        }
        t.put(key, false);
        return false;
    }
}


/*
Problem kya keh raha hai?
Tumhe ek 2-D matrix (grid) diya hai.
Usme numbers row-wise aur column-wise dono jagah decreasing order me sorted hain.
Matlab:
* Left → Right jaate hue numbers kam hote jaate hain
* Top → Bottom jaate hue bhi numbers kam hote jaate hain
Tumhara kaam:
Is matrix me kitne negative numbers hain wo count karo

*****************************************************************************************

What you’re allowed to do

For every adjacent pair in the row:
B C D
↑ ↑
BC  CD

You look up:
BC → possible top letters
CD → possible top letters
Using those, you build the next row.
Then repeat.

*****************************************************************************************

Example 1
bottom = "BCD"
allowed = ["BCC","CDE","CEA","FFF"]

Rules:
BC → C
CD → E
CE → A


Build:
Level 3:
B   C   D

Level 2:
C   E      (from BC and CD)

Level 1:
A          (from CE)

We reached the top → true

*****************************************************************************************

Short dry run
bottom = "ABC"
allowed = ["ABD","BCE","DEF"]

Step 1:
AB → D
BC → E

So next row = "DE"

Step 2:
DE → F

Top reached → true

*****************************************************************************************

Build pyramid level by level

We start from bottom "ABC"
We try to build the row above it.

Level 0 (Bottom)
        A     B     C
         \   /
          AB       BC
           \       /
            D     E


So the next row becomes:

"DE"

Now from "DE" build next level
Level 1
        D     E
          \ /
           DE
            |
            F


So the next row becomes:

"F"

Top reached

When the row length becomes 1, it means pyramid is complete.

        F
       / \
      D   E
     / \ / \
    A   B   C

✅ Final Answer

Since we successfully reached the top "F",
return TRUE ✔
*/
