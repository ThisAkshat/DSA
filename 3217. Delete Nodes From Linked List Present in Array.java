class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> remove = new HashSet<>();
        for(int x: nums) remove.add(x);
        while(head !=null && remove.contains(head.val)) head = head.next;
        ListNode temp = head;
        while(temp != null && temp.next != null){
            if(remove.contains(temp.next.val)) temp.next = temp.next.next;
            else temp = temp.next;
        }
    return head;
    }
}


/*
1) Problem :-

You are given:
An array nums
A linked list
Every number in nums is bad — if a node in the linked list has a value that appears in nums, you must delete that node.
Your job:
Remove all such nodes and return the new head of the linked list.

********************************************************************************************

2) Example
nums = [1,2,3]
linked list = 1 → 2 → 3 → 4 → 5
Numbers to remove: 1, 2, 3

So we delete:
1, 2, 3

Remaining list:
4 → 5

Output:
[4,5]

********************************************************************************************

3) Short Dry Run

Input:
nums = [1,2,3]
head = 1 → 2 → 3 → 4 → 5

Step 1 – Put nums into a set
remove = {1, 2, 3}

Step 2 – Fix the head
The first node is 1, which is in remove → delete it
Next head = 2 → delete
Next head = 3 → delete
Next head = 4 → stop

Now:
head = 4 → 5
Step 3 – Walk through the list

Start at 4:
Next is 5
5 is NOT in remove → keep it
Done.

Final list:
4 → 5
*/
