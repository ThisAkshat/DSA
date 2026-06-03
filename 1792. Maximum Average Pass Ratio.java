class Solution {

    public double gain(int p, int t){
        return ((double)(p+1)/(t+1) - ((double)p/t));
    }
    
    public double maxAverageRatio(int[][] classes, int k) {
        int n = classes.length;
        double res =0;
        PriorityQueue<int []> pq = new PriorityQueue<>((a,b)-> Double.compare(gain(b[0],b[1]),gain(a[0],a[1])));


        for(int [] c: classes){
            pq.add(new int[] {c[0],c[1]});
        }


        while(k-->0){
            int top[] = pq.poll();
            top[0]++;
            top[1]++;
            pq.add(top);
        }
        double sum =0;

        while(!pq.isEmpty()){
            int [] top = pq.poll();
            sum+= (double)top[0]/top[1];
        }

        return sum/n;
    }
}
/*
problem -
You are given many classes.
Each class has:
pass = students who will pass
total = total students

Example:
[3,5] means 3 out of 5 students pass.
Pass Ratio = pass / total
You also have k extra brilliant students.

Every extra student:
* always passes
* can be assigned to any class

If you add one extra student to a class:
pass = pass + 1
total = total + 1

Your goal:
Distribute all k students so that the final average pass ratio of all classes becomes maximum.
Return that maximum average.

example -
classes = [[1,2],[3,5],[2,2]]
k = 2

Initial ratios:
Class1 = 1/2 = 0.5
Class2 = 3/5 = 0.6
Class3 = 2/2 = 1
Average = (0.5 + 0.6 + 1)/3
= 0.7

dry run -
classes = [[2,4],[3,9],[4,5],[2,10]]
k = 4

Step 1
Calculate gain for every class.

Gain means:
How much pass ratio increases if we add 1 brilliant student.

Formula:
gain =
((p+1)/(t+1))
-------------
(p/t)

Class1
(3/5) - (2/4)
= 0.6 - 0.5
= 0.1

Class2
(4/10) - (3/9)
= 0.4 - 0.3333
= 0.0667

Class3
(5/6) - (4/5)
= 0.8333 - 0.8
= 0.0333

Class4
(3/11) - (2/10)
= 0.2727 - 0.2
= 0.0727

Priority Queue:
Class1 = 0.1
Class4 = 0.0727
Class2 = 0.0667
Class3 = 0.0333

Top = Class1
Step 2 (1st extra student)
Class1:
[2,4] → [3,5]
Recalculate gain:
(4/6) - (3/5)
= 0.6667 - 0.6
= 0.0667

PQ:
Class4 = 0.0727
Class1 = 0.0667
Class2 = 0.0667
Class3 = 0.0333

Top = Class4
Step 3 (2nd extra student)

Class4:
[2,10] → [3,11]

New gain:
(4/12) - (3/11)
= 0.3333 - 0.2727
= 0.0606

PQ:
Class1 = 0.0667
Class2 = 0.0667
Class4 = 0.0606
Class3 = 0.0333
Top = Class1
Step 4 (3rd extra student)

Class1:
[3,5] → [4,6]

New gain:
(5/7) - (4/6)
= 0.7143 - 0.6667
= 0.0476

PQ:
Class2 = 0.0667
Class4 = 0.0606
Class1 = 0.0476
Class3 = 0.0333
Top = Class2
Step 5 (4th extra student)

Class2:
[3,9] → [4,10]

Final classes:
[4,6]
[4,10]
[4,5]
[3,11]
Now calculate average.

Class1
4/6 = 0.6667

Class2
4/10 = 0.4

Class3
4/5 = 0.8

Class4
3/11 = 0.2727

Sum
= 0.6667 + 0.4 + 0.8 + 0.2727
= 2.1394

Average
= 2.1394 / 4
= 0.53485
Output = 0.53485

program logic -
Priority Queue always keeps the class with maximum gain at the top.

Every time:
1. Pick class giving maximum improvement.
2. Add one brilliant student.
3. Recalculate its gain.
4. Push it back into PQ.
5. Repeat k times.

After all students are assigned:
1. Calculate pass ratio of every class.
2. Sum them.
3. Divide by number of classes.

Time Complexity:
O((n + k) log n)

Space Complexity:
O(n)



*/
