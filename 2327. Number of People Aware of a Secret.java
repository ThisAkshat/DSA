class Solution {
    private final static int MOD = 1_000_000_007;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int res = 0;
        int negRes = 0;
        int[] arr = new int[n+1];
        int[] forgetArr = new int[n+1];
        arr[1] = 1;

        for (int i = 1; i < arr.length; i++){
            int amt = arr[i];
            if (amt > 0) {
                for (int j = i + delay; j < Math.min(arr.length, i + forget); j++) {
                    arr[j] = (int)(((long)arr[j] + amt) % MOD);
                }
            }
            if (i + forget < n+1) {
                forgetArr[i+forget] = amt;
            }
            res = (int)(((long)res + amt) % MOD);
            res = (int)(((long)res + MOD - forgetArr[i]) % MOD);
        }

        return res;
    }
}
/*
problem - On day 1, one person knows a secret.
After discovering the secret:
1. A person starts sharing after delay days.
2. A person forgets after forget days.
3. Once a person forgets, they cannot share anymore.
You have to find how many people still know the secret at the end of day n.
Every person who is eligible to share tells exactly one new person every day.
Since the answer can become very large, return it modulo 10^9+7.
Code idea:
arr[i]
= number of people who discover the secret on day i.
forgetArr[i]
= number of people who will forget the secret on day i.

res
= current number of people who still know the secret.

Initially:
arr[1]=1
Because only one person knows the secret on day 1.

Code understanding:
for(int i=1;i<arr.length;i++)
Process every day.
int amt = arr[i];
amt = number of people who learned the secret today.
if(amt>0)
These people will start sharing after delay days and stop before forget days.
for(int j=i+delay;j<Math.min(arr.length,i+forget);j++)
Sharing range.
arr[j]=(arr[j]+amt)%MOD;
Every sharer creates one new person each day.
if(i+forget<n+1)
forgetArr[i+forget]=amt;
These people will forget later.
res=(res+amt)%MOD;
Add today's new people.
res=(res+MOD-forgetArr[i])%MOD;
Remove people who forgot today.
Return res.
example - n=6 , delay=2 , forget=4

Day 1
A learns secret
People = A
Total = 1

Day 2
Nobody can share yet
People = A
Total = 1

Day 3
A starts sharing
New person = B
People = A,B
Total = 2

Day 4
A shares again
New person = C
People = A,B,C
Total = 3

Day 5
A forgets
B starts sharing
New person = D
People = B,C,D
Total = 3

Day 6
B shares
New person = E
C shares
New person = F
People = B,C,D,E,F
Total = 5
Answer = 5
dry run - n=7 , delay=2 , forget=4

Initial:
arr:
[0,1,0,0,0,0,0,0]
forgetArr:
[0,0,0,0,0,0,0,0]
res=0

Day 1
amt=1
A can share from:
1+2=3
until:
1+4-1=4
So:
arr[3]+=1
arr[4]+=1
forgetArr[5]=1
res=0+1=1

Day 2
amt=0
res=1

Day 3
amt=1
B discovered
B can share:
5 and 6
arr[5]+=1
arr[6]+=1
forgetArr[7]=1
res=1+1
=2

Day 4
amt=1
C discovered
C can share:
6 and 7
arr[6]+=1
arr[7]+=1
forgetArr[8]=1
res=2+1
=3

Day 5
amt=1
D discovered
forgetArr[5]=1
First add D
res=3+1
=4
Then A forgets
res=4-1
=3

Day 6
amt=2
E and F discovered
res=3+2
=5
Nobody forgets today

Day 7
amt=2
G and H discovered
res=5+2
=7
B forgets today
res=7-1
=6

Final answer = 6
*/
