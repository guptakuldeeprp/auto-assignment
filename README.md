# Auto Assignment


Basic Assignment algorithm is used which assigns one DeliveryExecutive per order
It greedily selects minimum cost (local minimum) of available DeliveryExecutive as per the cost strategy used. This does not guarantee global minimum cost.
It used fork-join to split the task of finding minumum cost for a list of orders.

Assumptions:
 - One order will be assigned to one DeliveryExecutive
 - This part of application does not deal with setting the lastOrderDeliveryTime of DeliveryExecutive.
 It is the calling code's responsibility to set it.

 ## Sample Main class example
 ```
  com.swiggy.autoassign.Runner
 ```

 ## Foot notes:
 Problem of finding a global minimum cost of DeliveryExecutive to all orders can be thought of as weighted maximum/minimum bipartite matching. <br /> <br />
 After some research, I came across an algorithm called Hungarian algorithm / Kuhn-Munkres algorithm which can be used to solve it in O(n^3) running time.<br />
 There are research papers available to make this algorithm faster by running in parallel. <br /> <br />
 We can use a [JGraphT](http://jgrapht.org/) - The java graph library, to calculate the Order -> DeliveryExecutive assignment.<br />
 This seemed like an over kill for assignment problem as it will increase time and space complexity a lot.<br />
 For production use case, we will need to implement and optimize it if optimal minimal cost assignment is required.

