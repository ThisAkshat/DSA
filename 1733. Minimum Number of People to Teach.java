class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        List<Set<Integer>> lang = new ArrayList<>();
        for (int[] userLangs : languages) {
            Set<Integer> set = new HashSet<>();
            for (int l : userLangs) {
                set.add(l);
            }
            lang.add(set);
        }
        Set<Integer> need = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1; //for  converting to 0-based indexing
            int v = f[1] - 1;
            boolean canTalk = false;
            for (int l : lang.get(u)) {
                if (lang.get(v).contains(l)) {
                    canTalk = true;
                    break;
                }
            }
            if (!canTalk) {
                need.add(u);
                need.add(v);
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int language=1;language<=n;language++){
            int teach = 0;
            for(int user:need) {
                if(!lang.get(user).contains(language)) {
                    teach++;
                }
            }
            ans=Math.min(ans, teach);
        }
        return ans;
    }
}

/*
problem - There are users and each user knows some languages.
Friendships are given.
Two friends can communicate only if they share at least one common language.
You are allowed to choose only one language and teach it to some users.
Your task is to make sure every friendship can communicate while teaching the minimum number of people.
Important idea:
First, find only those users involved in "bad friendships".

Bad friendship = two friends that cannot communicate.
After finding those users, try every language from 1 to n.
For each language:
Count how many users from the bad friendship group do not know this language.
That count is how many people we need to teach.
Take the minimum.
Code understanding:
List<Set<Integer>> lang = new ArrayList<>();
Convert every user's languages into HashSet for fast searching.

Example:
languages = [[1],[2],[1,2]]

Becomes:
User1 → {1}
User2 → {2}
User3 → {1,2}
Set<Integer> need = new HashSet<>();
Stores users that belong to bad friendships.
for(int[] f:friendships)
Visit every friendship.
int u=f[0]-1
int v=f[1]-1
Convert 1-based indexing to 0-based indexing.
Check if they have a common language.
for(int l:lang.get(u))
if(lang.get(v).contains(l))

If yes:
canTalk=true
If they cannot talk:
need.add(u)
need.add(v)
Now we have all problematic users.

Next:
for(int language=1;language<=n;language++)
Try teaching every language.
teach=0
for(int user:need)
if(!lang.get(user).contains(language))
teach++
Count users needing this language.
Take minimum answer.
example -
n=2
languages=[[1],[2],[1,2]]
friendships=[[1,2],[1,3],[2,3]]

Users:
User1={1}
User2={2}
User3={1,2}
Friendship (1,2)
User1={1}
User2={2}
No common language.
need={1,2}
Friendship (1,3)
User1={1}
User3={1,2}
Common language=1
Nothing happens.
Friendship (2,3)
User2={2}
User3={1,2}
Common language=2
Nothing happens.

Now:
need={1,2}
Try language=1
User1 already knows.
User2 doesn't know.
teach=1
Try language=2
User1 doesn't know.
User2 already knows.
teach=1
Minimum=1
Answer=1
dry run -
n=3
languages=[[2],[1,3],[1,2],[3]]
friendships=[[1,4],[1,2],[3,4],[2,3]]

Users:
1→{2}
2→{1,3}
3→{1,2}
4→{3}

Initially:
need={}
Friendship (1,4)
{2} and {3}
No common language
need={1,4}
Friendship (1,2)
{2} and {1,3}
No common language
need={1,4,2}
Friendship (3,4)
{1,2} and {3}
No common language
need={1,4,2,3}
Friendship (2,3)
{1,3} and {1,2}
Common language=1
Nothing happens.
Now:
need={1,2,3,4}

Try language=1
User1 doesn't know
teach=1
User2 knows
teach=1
User3 knows
teach=1
User4 doesn't know
teach=2
Total=2

Try language=2
User1 knows
teach=0
User2 doesn't know
teach=1
User3 knows
teach=1
User4 doesn't know
teach=2
Total=2

Try language=3
User1 doesn't know
teach=1
User2 knows
teach=1
User3 doesn't know
teach=2
User4 knows
teach=2
Total=2
Minimum=2
Answer=2
*/
