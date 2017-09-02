# Auto Assignment


Basic Assignment algorithm is used which assigns one DeliveryExecutive per order
It greedily selects minimum cost (local minimum) of available DeliveryExecutive as per the cost strategy used. This does not guarantee global minimum cost.
It used fork-join to split the task of finding minumum cost for a list of orders.

Assumptions:
 * One order will be assigned to one delivery executive

 ## Sample Main class example
 ```
  com.swiggy.assign.Runner
 ```

 ## Foot notes:
 Problem of finding a global minimum cost of DeliveryExecutive to all orders can be thought of as weighted maximum bipartite matching.
 After some research, I came across an algorithm called Hungarian algorithm which can be used to solve it in O(n^3) running time.
 We can use a [JGraphT](http://jgrapht.org/) - The java graph library, to calculate the Order -> DeliveryExecutive assignment.
 This seemed like an over kill as it will increase time and space complexity a lot.
 For production use case, we will need to implement and optimize it if optimal assignment strategy is required.
