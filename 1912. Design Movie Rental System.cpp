class MovieRentingSystem {
public:

    unordered_map<int, set<pair<int, int>>> avilable; // movie -> {price, shop}
    unordered_map<int, set<pair<int, int>>> movieToShop; // movie -> {shop, price}
    set<tuple<int, int, int>> rented;  // {price, shop, movie}

    MovieRentingSystem(int n, vector<vector<int>>& entries) {

        for(auto& entry : entries) {

            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            avilable[movie].insert(make_pair(price, shop));
            movieToShop[movie].insert(make_pair(shop, price));

        }
        
    }
    
    vector<int> search(int movie) {
        
        set<pair<int, int>>& movies = avilable[movie];
        int count = 0;
        vector<int> result;

        for(auto& [price, shop] : movies) {
            result.push_back(shop);
            count += 1;

            if(count == 5) {
                break;
            }
        }

        return result;

    }
    
    void rent(int shop, int movie) {

        auto it = movieToShop[movie].lower_bound({shop, INT_MIN});
        int price = it->second;

        avilable[movie].erase({price, shop});

        rented.insert({price, shop, movie});
  
    }
    
    void drop(int shop, int movie) {

        auto it = movieToShop[movie].lower_bound({shop, INT_MIN});
        int price = it->second;

        avilable[movie].insert({price, shop});

        rented.erase({price, shop, movie});
        
    }
    
    vector<vector<int>> report() {

        int count = 0;
        vector<vector<int>> result;

        for(auto& [price, shop, movie] : rented) {

            result.push_back({shop, movie});
            count += 1;

            if(count == 5) {
                break;
            }

        }

        return result;
        
    }
};
/*
Explanation:

This problem needs a system to search for cheap copies of a movie, rent them, return them, and report the cheapest currently rented movies. The key challenge is that all three operations need to be fast and always sorted correctly, so three sorted sets are maintained at all times.

Three structures are used:

available: maps each movieId to a sorted set of (price, shop) pairs. The set automatically keeps entries sorted by price first, then by shop if prices tie. This makes search fast since cheapest copies are always at the front of the set.

movieToShop: maps each movieId to a sorted set of (shop, price) pairs. This is a lookup helper. When we rent or drop a movie at a specific shop, we need to quickly find what price that shop charges for that movie. We cannot look it up from available because it may have already been removed. So this map always holds (shop, price) for every movie at every shop regardless of rented state.

rented: a single global sorted set of (price, shop, movie) tuples for all currently rented copies. Since it is a set sorted by price then shop then movie, the cheapest rented copies are always at the front, making report fast.

search(movie): directly iterates the front of available[movie] set, collecting up to 5 shop numbers and returning them. Since the set is sorted by (price, shop), the cheapest and then smallest shop comes first naturally.

rent(shop, movie): uses movieToShop to find the price for this shop and movie. Then removes (price, shop) from available[movie] since it is no longer available. Then inserts (price, shop, movie) into the rented global set.

drop(shop, movie): same price lookup using movieToShop. Then inserts (price, shop) back into available[movie] since it is available again. Then removes (price, shop, movie) from rented.

report(): iterates the front of the rented set, collecting up to 5 entries as [shop, movie] pairs and returning them.

Dry Run (line by line on the example given):

entries = [[0,1,5],[0,2,6],[0,3,7],[1,1,4],[1,2,7],[2,1,5]]

Constructor:

entry = [0,1,5], shop=0, movie=1, price=5
line: available[1].insert({5,0}) -> available = {1:{(5,0)}}
line: movieToShop[1].insert({0,5}) -> movieToShop = {1:{(0,5)}}

entry = [0,2,6], shop=0, movie=2, price=6
line: available[2].insert({6,0}) -> available = {1:{(5,0)}, 2:{(6,0)}}
line: movieToShop[2].insert({0,6}) -> movieToShop = {1:{(0,5)}, 2:{(0,6)}}

entry = [0,3,7], shop=0, movie=3, price=7
line: available[3].insert({7,0}) -> available = {1:{(5,0)}, 2:{(6,0)}, 3:{(7,0)}}
line: movieToShop[3].insert({0,7}) -> movieToShop = {..., 3:{(0,7)}}

entry = [1,1,4], shop=1, movie=1, price=4
line: available[1].insert({4,1}) -> available[1] = {(4,1),(5,0)} (4 < 5 so 1's copy is cheaper, sits first)
line: movieToShop[1].insert({1,4}) -> movieToShop[1] = {(0,5),(1,4)}

entry = [1,2,7], shop=1, movie=2, price=7
line: available[2].insert({7,1}) -> available[2] = {(6,0),(7,1)}
line: movieToShop[2].insert({1,7}) -> movieToShop[2] = {(0,6),(1,7)}

entry = [2,1,5], shop=2, movie=1, price=5
line: available[1].insert({5,2}) -> available[1] = {(4,1),(5,0),(5,2)} (both shop 0 and 2 have price 5, shop 0 < 2 so 0 comes first)
line: movieToShop[1].insert({2,5}) -> movieToShop[1] = {(0,5),(1,4),(2,5)}

State after constructor:
available[1] = {(4,1),(5,0),(5,2)}, available[2] = {(6,0),(7,1)}, available[3] = {(7,0)}
movieToShop[1] = {(0,5),(1,4),(2,5)}, movieToShop[2] = {(0,6),(1,7)}, movieToShop[3] = {(0,7)}
rented = {} (empty)

Call: search(1)

line: movies = available[1] -> {(4,1),(5,0),(5,2)}
line: count = 0, result = []
iteration 1: price=4, shop=1 -> result.push_back(1) -> result=[1], count=1, count != 5 continue
iteration 2: price=5, shop=0 -> result.push_back(0) -> result=[1,0], count=2, count != 5 continue
iteration 3: price=5, shop=2 -> result.push_back(2) -> result=[1,0,2], count=3, count != 5 continue
set exhausted, loop ends
line: return result -> [1,0,2]

Output: [1,0,2] (matches expected)

Call: rent(0, 1)

line: it = movieToShop[1].lower_bound({0, INT_MIN}) -> finds first entry where shop >= 0, that is (0,5)
line: price = it->second -> price = 5
line: available[1].erase({5,0}) -> available[1] = {(4,1),(5,2)} (shop 0's copy of movie 1 removed)
line: rented.insert({5,0,1}) -> rented = {(5,0,1)}

Call: rent(1, 2)

line: it = movieToShop[2].lower_bound({1, INT_MIN}) -> finds first entry where shop >= 1, that is (1,7)
line: price = it->second -> price = 7
line: available[2].erase({7,1}) -> available[2] = {(6,0)} (shop 1's copy of movie 2 removed)
line: rented.insert({7,1,2}) -> rented = {(5,0,1),(7,1,2)}

Call: report()

line: count=0, result=[]
iteration 1: price=5, shop=0, movie=1 -> result.push_back({0,1}) -> result=[[0,1]], count=1, count != 5 continue
iteration 2: price=7, shop=1, movie=2 -> result.push_back({1,2}) -> result=[[0,1],[1,2]], count=2, count != 5 continue
rented set exhausted, loop ends
line: return result -> [[0,1],[1,2]]

Output: [[0,1],[1,2]] (matches expected)

Call: drop(1, 2)

line: it = movieToShop[2].lower_bound({1, INT_MIN}) -> finds (1,7)
line: price = it->second -> price = 7
line: available[2].insert({7,1}) -> available[2] = {(6,0),(7,1)} (shop 1's copy of movie 2 back in available)
line: rented.erase({7,1,2}) -> rented = {(5,0,1)}

Call: search(2)

line: movies = available[2] -> {(6,0),(7,1)}
line: count=0, result=[]
iteration 1: price=6, shop=0 -> result.push_back(0) -> result=[0], count=1
iteration 2: price=7, shop=1 -> result.push_back(1) -> result=[0,1], count=2
set exhausted, loop ends
line: return result -> [0,1]

Output: [0,1] (matches expected)

Final full output sequence: null, [1,0,2], null, null, [[0,1],[1,2]], null, [0,1]

This exactly matches the expected output: [null, [1,0,2], null, null, [[0,1],[1,2]], null, [0,1]]
*/
