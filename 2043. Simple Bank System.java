class Bank {
    long[] balance;
    int n;
    public Bank(long[] balance) {
        n = balance.length;
        this.balance = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if(account1 > n || account2 > n || balance[account1 - 1] < money){
            return false;
        }
        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }
    
    public boolean deposit(int account, long money) {
        if(account > n) return false;
        
        balance[account-1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if(account > n || balance[account - 1] < money) return false;

        balance[account -1] -= money;
        return true;
    }
}



/*
1. PROBLEM :-
   You are given a bank system with n accounts.
Each account has an initial balance stored in the balance array.
Account numbering starts from 1 to n.
You need to implement 3 banking operations:
1. Transfer
   Move money from one account to another.
2. Deposit
   Add money to an account.
3. Withdraw
   Remove money from an account.
A transaction is valid only if:

* Account number exists (between 1 and n)
* Enough balance is available for withdraw/transfer

Goal:
Execute operations and return:
* true → transaction successful
* false → invalid transaction

---

Core Idea:
Store all balances in a long[] array.
For every operation:
Validate account number.
For transfer/withdraw:
Check sufficient balance.

If valid:
Update balances.

Otherwise:
Return false.

---

How the Code Works:
Constructor:
Stores:
* total number of accounts
* balance array

Code:
n = balance.length
this.balance = balance
---

1. transfer(account1, account2, money)
Conditions checked:
* account1 exists
* account2 exists
* account1 has enough balance

If invalid:
return false
Else:
Subtract money from account1
Add money to account2

Return true.

---

2. deposit(account, money)
Check:
Does account exist?
If invalid:
return false
Else:
Add money to account
Return true.

---

3. withdraw(account, money)

Conditions:
* account exists
* enough balance available

If invalid:
return false
Else:
Subtract money
Return true.

---

Why long is used?
Constraint:
money ≤ 10¹²
This exceeds int range.

So: long
is required to avoid overflow.

---

Time Complexity:
Transfer → O(1)
Deposit → O(1)
Withdraw → O(1)
Because only direct array access is used.

Space Complexity: O(n)
For storing balances.

---

2. EXAMPLE :-
Input:
balance = [10,100,20,50,30]

---

Operation 1:
withdraw(3,10)
Account 3 balance:
20
After withdrawal:
20 - 10 = 10

Result:
true
Balances:
[10,100,10,50,30]

---

Operation 2:
transfer(5,1,20)
Account 5: 30
Enough balance exists.

After transfer:
Account 5:30 - 20 = 10
Account 1:10 + 20 = 30
Result:true
Balances: [30,100,10,50,10]

---

Operation 3:
deposit(5,20)
Account 5: 10 + 20 = 30

Result: true

Balances: [30,100,10,50,30]

---

Operation 4: transfer(3,4,15)
Account 3 balance: 10

Need: 15
Not enough money.
Result: false

---

Operation 5:
withdraw(10,50)
Account 10 does not exist.
Result: false

---

3. DRY RUN :-
Example (custom tough case)

Input:
balance = [50,80,30]

---

Initial State:
Account 1 = 50
Account 2 = 80
Account 3 = 30

---

Operation 1:
transfer(1,2,20)

Check:
Account 1 exists ✔
Account 2 exists ✔
Balance sufficient ✔

Update:
Account 1: 50 - 20 = 30
Account 2: 80 + 20 = 100

Result: true
Balances: [30,100,30]

---

Operation 2:
withdraw(3,25)

Balance: 30
Enough money ✔
Update: 30 - 25 = 5
Result: true
Balances: [30,100,5]

---

Operation 3:
deposit(1,40)
Update: 30 + 40 = 70

Result: true
Balances: [70,100,5]
---

Operation 4:
withdraw(3,10)
Balance:5

Need: 10
Not enough balance ❌

Result: false
---

Final Balances: [70,100,5]







*/
