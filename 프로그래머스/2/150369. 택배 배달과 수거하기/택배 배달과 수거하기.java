import java.util.Stack;
class Solution {
    static long distance = 0;
    static int limit = 0;
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        limit = cap;

        Stack<Integer> deliveryStack = new Stack<>();
        Stack<Integer> pickupStack = new Stack<>();
        for(int i = 0; i < n; i++) {
            deliveryStack.push(deliveries[i]);
            pickupStack.push(pickups[i]);
        }
        
        searchZero(deliveryStack);
        searchZero(pickupStack);

        while(!deliveryStack.isEmpty() || !pickupStack.isEmpty()) {
            distance += (Math.max(deliveryStack.size(), pickupStack.size()) * 2);
            delivery(deliveryStack);
            delivery(pickupStack);
        }

        return distance;
    }

    private static void delivery(Stack<Integer> stack) {
        int cnt = 0;
        while (!stack.isEmpty() && cnt < limit) {
            int availableCnt = limit - cnt;
            if (stack.peek() <= availableCnt) {
                cnt += stack.peek();
                stack.pop();
            }
            else {
                cnt += availableCnt;
                int pop = stack.pop();
                pop -= availableCnt;
                stack.push(pop);
            }
        }

        searchZero(stack);
    }

    static void searchZero(Stack<Integer> stack) {
        while (!stack.isEmpty() && stack.peek() == 0) {
            stack.pop();
        }
    }
    
}