class FoodRatings {
    class FoodRating {
        String food;
        Integer rating;

        FoodRating(String food, Integer rating) {
            this.food = food;
            this.rating = rating;
        }
    }

    HashMap<String, String> foodToCuisine;
    HashMap<String, Integer> foodToRating;
    HashMap<String, PriorityQueue<FoodRating>> highestInCuisine;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        highestInCuisine = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            foodToCuisine.put(foods[i], cuisines[i]);
            foodToRating.put(foods[i], ratings[i]);
            highestInCuisine.putIfAbsent(cuisines[i], new PriorityQueue<>((a, b) -> {
                if (a.rating.equals(b.rating)) {
                    return (a.food).compareTo(b.food);
                }
                return b.rating - a.rating;
            }));
            highestInCuisine.get(cuisines[i]).add(new FoodRating(foods[i], ratings[i]));
        }
    }

    public void changeRating(String food, int newRating) {
        foodToRating.put(food, newRating);
        String cuisine = foodToCuisine.get(food);
        highestInCuisine.get(cuisine).add(new FoodRating(food, newRating));
        System.out.println(food + " " + foodToRating.get(food));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<FoodRating> pq = highestInCuisine.get(cuisine);
        while (!pq.isEmpty()) {
            if (!foodToRating.get(pq.peek().food).equals(pq.peek().rating)) {
                pq.poll();
                continue;
            }
            return pq.peek().food;
        }
        return "";
    }



/*
Explanation:
This problem needs a system that can quickly find the highest rated food in a cuisine, and also update a food's rating anytime. The hard part is updating a value inside a heap is slow, so this code uses a trick called lazy deletion.
Three maps are used:

foodToCuisine: tells which cuisine a food belongs to.

foodToRating: always holds the latest rating of a food.

highestInCuisine: one max heap per cuisine, sorted so highest rating is on top, and if ratings are same, smaller name comes first.
When changeRating is called, we just update foodToRating with the new value, and add a new entry into that cuisine's heap. We do not remove the old entry, it just stays inside the heap as junk for now.
When highestRated is called, we look at the top of the heap for that cuisine. We check if that entry's rating matches the food's real rating in foodToRating. If it does not match, it means this entry is old and useless, so we throw it away and check the next one. If it matches, it is correct and current, so we return that food name.
This way we never have to search or update inside the heap, we just throw away old junk whenever it comes to the top.



Dry Run (line by line on the example from the question):

foods = ["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"]
cuisines = ["korean", "japanese", "japanese", "greek", "japanese", "korean"]
ratings = [9, 12, 8, 15, 14, 7]

Constructor: public FoodRatings(...)

Three empty maps created: foodToCuisine, foodToRating, highestInCuisine

Loop starts, i = 0 to 5

i = 0, food = kimchi, cuisine = korean, rating = 9
line: foodToCuisine.put("kimchi", "korean") -> foodToCuisine = {kimchi:korean}
line: foodToRating.put("kimchi", 9) -> foodToRating = {kimchi:9}
line: highestInCuisine.putIfAbsent("korean", new PQ) -> korean heap created (empty)
line: highestInCuisine.get("korean").add(FoodRating(kimchi,9)) -> korean heap = [kimchi(9)]

i = 1, food = miso, cuisine = japanese, rating = 12
foodToCuisine = {kimchi:korean, miso:japanese}
foodToRating = {kimchi:9, miso:12}
japanese heap created (empty), then add miso(12) -> japanese heap = [miso(12)]

i = 2, food = sushi, cuisine = japanese, rating = 8
foodToCuisine = {..., sushi:japanese}
foodToRating = {..., sushi:8}
japanese heap already exists, putIfAbsent does nothing, add sushi(8) -> japanese heap = [miso(12), sushi(8)] (miso on top since 12 > 8)

i = 3, food = moussaka, cuisine = greek, rating = 15
foodToCuisine = {..., moussaka:greek}
foodToRating = {..., moussaka:15}
greek heap created, add moussaka(15) -> greek heap = [moussaka(15)]

i = 4, food = ramen, cuisine = japanese, rating = 14
foodToCuisine = {..., ramen:japanese}
foodToRating = {..., ramen:14}
add ramen(14) to japanese heap -> japanese heap = [ramen(14), miso(12), sushi(8)] (ramen now on top since 14 is highest)

i = 5, food = bulgogi, cuisine = korean, rating = 7
foodToCuisine = {..., bulgogi:korean}
foodToRating = {..., bulgogi:7}
add bulgogi(7) to korean heap -> korean heap = [kimchi(9), bulgogi(7)]

Constructor done. State now:
foodToRating = {kimchi:9, miso:12, sushi:8, moussaka:15, ramen:14, bulgogi:7}
korean heap top to bottom = kimchi(9), bulgogi(7)
japanese heap top to bottom = ramen(14), miso(12), sushi(8)
greek heap top to bottom = moussaka(15)

Call 1: highestRated("korean")

line: pq = highestInCuisine.get("korean") -> pq = [kimchi(9), bulgogi(7)]
line: while(!pq.isEmpty()) enters loop, pq not empty
line: if(!foodToRating.get(pq.peek().food).equals(pq.peek().rating)) 
pq.peek() = kimchi(9), foodToRating.get("kimchi") = 9, 9 equals 9, condition is false, so skip the if block
line: return pq.peek().food -> returns "kimchi"

Output so far: kimchi (correct)

Call 2: highestRated("japanese")

pq = japanese heap = [ramen(14), miso(12), sushi(8)]
while loop, pq not empty
pq.peek() = ramen(14), foodToRating.get("ramen") = 14, matches, if condition false
return pq.peek().food -> returns "ramen"

Output so far: kimchi, ramen (correct)

Call 3: changeRating("sushi", 16)

line: foodToRating.put("sushi", 16) -> foodToRating now has sushi:16 (overwritten)
line: cuisine = foodToCuisine.get("sushi") -> cuisine = "japanese"
line: highestInCuisine.get("japanese").add(FoodRating("sushi",16)) -> push new entry sushi(16) into japanese heap

japanese heap now logically contains: ramen(14), miso(12), sushi(8) [old, stale], sushi(16) [new]
heap reorders itself, top to bottom by rating: sushi(16), ramen(14), miso(12), sushi(8)
line: System.out.println("sushi 16") -> just prints debug info, no effect on logic

Call 4: highestRated("japanese")

pq = japanese heap = [sushi(16), ramen(14), miso(12), sushi(8)]
while loop, pq not empty
pq.peek() = sushi(16), foodToRating.get("sushi") = 16, matches, if condition false
return pq.peek().food -> returns "sushi"

Output so far: kimchi, ramen, sushi (correct)

Call 5: changeRating("ramen", 16)

foodToRating.put("ramen", 16) -> foodToRating now has ramen:16
cuisine = foodToCuisine.get("ramen") -> "japanese"
add FoodRating("ramen",16) into japanese heap

japanese heap now logically contains: sushi(16), ramen(14) [stale], miso(12), sushi(8) [stale], ramen(16) [new]
heap reorders, top to bottom: ramen(16) and sushi(16) tie on rating, comparator breaks tie by food name, "ramen" < "sushi" lexicographically, so ramen(16) goes above sushi(16)
order becomes: ramen(16), sushi(16), ramen(14), miso(12), sushi(8)

Call 6: highestRated("japanese")

pq = japanese heap = [ramen(16), sushi(16), ramen(14), miso(12), sushi(8)]
while loop, pq not empty
pq.peek() = ramen(16), foodToRating.get("ramen") = 16, matches, if condition false
return pq.peek().food -> returns "ramen"

Final output sequence: null, kimchi, ramen, null, sushi, null, ramen

This exactly matches the expected output given in the problem: [null, "kimchi", "ramen", null, "sushi", null, "ramen"]

Note: in this particular run, the stale entries (sushi(8), ramen(14)) never actually reached the top of the heap, so the lazy deletion if-block (popping junk entries) was never triggered in this specific dry run, but it would trigger in other input sequences where an old low-priority duplicate ends up on top after the higher ones are popped or replaced.


*/
