/*
Intuition:-
Filter valid coupons based on three rules, then sort them by business category priority and coupon code.

Approach:-
Filter valid coupons using 3 conditions:
Code: non-empty, only alphanumeric + underscore
Business line: must be in predefined list
Active status: must be true
Sort valid coupons:
First by business category priority (electronics → grocery → pharmacy → restaurant)
Then by coupon code alphabetically

Example Dry Run
Input:
code = ["SAVE20", "", "PHARMA5", "SAVE@20"]
businessLine = ["restaurant", "grocery", "pharmacy", "restaurant"]
isActive = [true, true, true, true]

Step 1: Filter valid coupons
Index 0: "SAVE20" ✓, "restaurant" ✓, active ✓ → VALID
Index 1: "" ❌ (empty code) → INVALID
Index 2: "PHARMA5" ✓, "pharmacy" ✓, active ✓ → VALID
Index 3: "SAVE@20" ❌ (contains '@') → INVALID
Valid coupons: [(SAVE20, restaurant), (PHARMA5, pharmacy)]

Step 2: Sort
Business priority: electronics(0) < grocery(1) < pharmacy(2) < restaurant(3)
PHARMA5 (pharmacy=2) comes before SAVE20 (restaurant=3)
Output: ["PHARMA5", "SAVE20"] ✓

*/


class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> validBusinesses = Arrays.asList("electronics", "grocery", "pharmacy", "restaurant");
        List<Coupon> validCoupons = new ArrayList<>();
        for(int i=0; i<code.length; i++){
            if(isValidCode(code[i]) && validBusinesses.contains(businessLine[i]) && isActive[i]){
                validCoupons.add(new Coupon(code[i], businessLine[i]));
            }
        }
        /* 
        code = ["SAVE20", "", "PHARMA5", "SAVE@20"]
        businessLine = ["restaurant", "grocery", "pharmacy", "restaurant"]
        isActive = [true, true, true, true]
        */
        validCoupons.sort((a, b)->{
            int businessCompare = Integer.compare(
                validBusinesses.indexOf(a.businessLine),
                validBusinesses.indexOf(b.businessLine)
            );
            if(businessCompare != 0)return businessCompare;
            return a.code.compareTo(b.code);
        });
        List<String> result = new ArrayList<>();
        for(Coupon c : validCoupons){
            result.add(c.code);
        }
        return result;
    }
    private boolean isValidCode(String code){
        if(code == null || code.isEmpty()) return false;
        for(char c : code.toCharArray()){
            if(!Character.isLetterOrDigit(c) && c != '_'){
                return false;
            }
        }
        return true;
    }
    class Coupon{
        String code;
        String businessLine;
        Coupon(String code, String businessLine){
            this.code = code;
            this.businessLine = businessLine;
        }
    }
}
/*
# **Detailed Line-by-Line Dry Run**

**Example Input:**
```
code = ["SAVE20", "", "PHARMA5", "SAVE@20"]
businessLine = ["restaurant", "grocery", "pharmacy", "restaurant"]
isActive = [true, true, true, true]
```

---

## **Line 1-7: Initialization**
```java
class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> validBusinesses = Arrays.asList("electronics", "grocery", "pharmacy", "restaurant");
        List<Coupon> validCoupons = new ArrayList<>();
```

**Execution:**
- `validBusinesses` = ["electronics", "grocery", "pharmacy", "restaurant"]
- `validCoupons` = empty list []

---

## **Line 8-15: Filtering Loop - Iteration 1 (i=0)**
```java
for(int i=0; i<code.length; i++) {  // i=0
    if(isValidCode(code[i]) && validBusinesses.contains(businessLine[i]) && isActive[i]) {
```

**Check conditions:**
1. `isValidCode("SAVE20")` → called
   ```java
   private boolean isValidCode(String code) {
       if(code == null || code.isEmpty()) return false;  // "SAVE20" not empty ✓
       for(char c : code.toCharArray()) {
           if(!Character.isLetterOrDigit(c) && c != '_') {
               return false;
           }
       }
       // Check each character: S,A,V,E,2,0 → all alphanumeric ✓
       return true;  // Returns true
   }
   ```
   **Result:** true

2. `validBusinesses.contains("restaurant")` → true ✓
3. `isActive[0]` → true ✓

**All conditions true:**
```java
validCoupons.add(new Coupon(code[i], businessLine[i]));
// Creates new Coupon("SAVE20", "restaurant")
// validCoupons = [("SAVE20", "restaurant")]
```

---

## **Iteration 2 (i=1)**
```java
// i=1
if(isValidCode(code[i]) && validBusinesses.contains(businessLine[i]) && isActive[i]) {
```

**Check conditions:**
1. `isValidCode("")` → called
   ```java
   if(code == null || code.isEmpty()) return false;  // "" is empty → returns false
   ```
   **Result:** false

**First condition false → skip entire if**
`validCoupons` remains = [("SAVE20", "restaurant")]

---

## **Iteration 3 (i=2)**
```java
// i=2
if(isValidCode(code[i]) && validBusinesses.contains(businessLine[i]) && isActive[i]) {
```

**Check conditions:**
1. `isValidCode("PHARMA5")` → all alphanumeric → true ✓
2. `validBusinesses.contains("pharmacy")` → true ✓
3. `isActive[2]` → true ✓

**All conditions true:**
```java
validCoupons.add(new Coupon("PHARMA5", "pharmacy"));
// validCoupons = [("SAVE20", "restaurant"), ("PHARMA5", "pharmacy")]
```

---

## **Iteration 4 (i=3)**
```java
// i=3
if(isValidCode(code[i]) && validBusinesses.contains(businessLine[i]) && isActive[i]) {
```

**Check conditions:**
1. `isValidCode("SAVE@20")` → called
   ```java
   for(char c : "SAVE@20".toCharArray()) {
       // S,A,V,E → alphanumeric ✓
       // '@' → !Character.isLetterOrDigit('@') && '@' != '_' → true → return false
   }
   ```
   **Result:** false

**First condition false → skip**
`validCoupons` remains = [("SAVE20", "restaurant"), ("PHARMA5", "pharmacy")]

---

## **Line 16-30: Sorting**
```java
validCoupons.sort((a, b)->{
    int businessCompare = Integer.compare(
        validBusinesses.indexOf(a.businessLine),
        validBusinesses.indexOf(b.businessLine)
    );
    if(businessCompare != 0) return businessCompare;
    return a.code.compareTo(b.code);
});
```

**Sorting the two coupons:**
1. Compare business lines:
   - `a = ("SAVE20", "restaurant")`
   - `b = ("PHARMA5", "pharmacy")`
   - `validBusinesses.indexOf("restaurant")` = 3
   - `validBusinesses.indexOf("pharmacy")` = 2
   - `Integer.compare(3, 2)` = 1 (positive)
   - `businessCompare != 0` → return 1 (b comes before a)

**After sort:**
`validCoupons` = [("PHARMA5", "pharmacy"), ("SAVE20", "restaurant")]

---

## **Line 31-35: Extract Codes**
```java
List<String> result = new ArrayList<>();
for(Coupon c : validCoupons) {
    result.add(c.code);
}
```

**Loop:**
1. `c = ("PHARMA5", "pharmacy")` → `result.add("PHARMA5")`
2. `c = ("SAVE20", "restaurant")` → `result.add("SAVE20")`

**Result:** `["PHARMA5", "SAVE20"]`

---

## **Line 36: Return Result**
```java
return result;  // Returns ["PHARMA5", "SAVE20"]
```

---

**Final Output: `["PHARMA5", "SAVE20"]` ✓**

  */
