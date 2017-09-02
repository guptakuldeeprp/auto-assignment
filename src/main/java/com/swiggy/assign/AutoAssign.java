package com.swiggy.assign;

import com.swiggy.assign.delivery.DeliveryExecProvider;
import com.swiggy.assign.entity.DeliveryExec;
import com.swiggy.assign.entity.Order;
import com.swiggy.assign.strategy.AssignmentStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class AutoAssign {

    private final AssignmentStrategy assignmentStrategy;
    private final DeliveryExecProvider deliveryExecProvider;
    private static final int CHUNK_THRESHOLD = 256 * 1024;

    public static AutoAssignBuilder builder() {
        return new AutoAssignBuilder();
    }

    AutoAssign(AssignmentStrategy assignmentStrategy, DeliveryExecProvider deliveryExecProvider) {
        if (assignmentStrategy == null) throw new NullPointerException("assignmentStrategy cannot be null");
        if (deliveryExecProvider == null) throw new NullPointerException("deliveryExecProvider cannot be null");
        this.assignmentStrategy = assignmentStrategy;
        this.deliveryExecProvider = deliveryExecProvider;
    }

    public Map<Order, DeliveryExec> assing(List<Order> orderList) {
        //ExecutorService execService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        int nSplits = numSplits(orderList);
        if (nSplits == 0) return computeOrderAssignment(orderList, 0, orderList.size() - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();


        return null;

    }

    /**
     * Should experiment and figure out what chunk size works best with the thread pool
     */
    protected int numSplits(List<Order> orderList) {
        return orderList.size() / CHUNK_THRESHOLD;
    }


    public Map<Order, DeliveryExec> computeOrderAssignment(List<Order> orderList, int start, int end) {
        Map<Order, DeliveryExec> result = new HashMap<>();
        for (int i = start; i <= end; i++) {
            Order order = orderList.get(i);
            DeliveryExec deliveryExec = assignmentStrategy.getDeliveryExec(order, deliveryExecProvider);
            deliveryExecProvider.assigned(deliveryExec);
            result.put(order, deliveryExec);
        }

        return result;
    }

    class SplitAssignTask extends RecursiveTask<Map<Order, DeliveryExec>> {

        private List<Order> orders;
        private int nSplits;

        public SplitAssignTask(List<Order> orders, int nSplits) {
            this.orders = orders;
            this.nSplits = nSplits;
        }

        @Override
        protected Map<Order, DeliveryExec> compute() {
            if (nSplits == 0)
                return computeOrderAssignment(orders, 0, orders.size() - 1);
            else {
                Map<Order, DeliveryExec> result = new HashMap<>();
                List<RecursiveTask<Map<Order, DeliveryExec>>> tasks = new ArrayList<>();
                int splitSize = orders.size() / nSplits;

                // can be made neater using while
                for (int i = 0; i < orders.size(); i += splitSize) {
                    int end = i + splitSize - 1 > orders.size() ? orders.size() - 1 : i + splitSize - 1;
                    BasicAssignTask basicAssignTask = new BasicAssignTask(orders, i, end);
                    tasks.add(basicAssignTask);
                    basicAssignTask.fork();
                }

                for (RecursiveTask<Map<Order, DeliveryExec>> task : tasks) {
                    result.putAll(task.join());
                }

                return result;
            }
        }
    }

    class BasicAssignTask extends RecursiveTask<Map<Order, DeliveryExec>> {
        private List<Order> orders;
        private int start;
        private int end;

        public BasicAssignTask(List<Order> orderList, int start, int end) {
            this.orders = orderList;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Map<Order, DeliveryExec> compute() {
            return computeOrderAssignment(orders, start, end);
        }
    }


}
