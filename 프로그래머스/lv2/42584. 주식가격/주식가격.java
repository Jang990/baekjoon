import java.util.Stack;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
		Stack<Stock> st = new Stack<>();
		
		for (int i = 0; i < prices.length; i++) {
			if(st.size() < 1 || st.peek().price <= prices[i]) {
				st.push(new Stock(prices[i], i));
			}
			else {
				Stock stock = st.pop();
				answer[stock.index] = i - stock.index;
				i--;
			}
		}
		
		while(!st.isEmpty()) {
			Stock stock = st.pop();
			answer[stock.index] = prices.length - stock.index - 1; 
		}
        
        return answer;
    }
    
    class Stock {
		int price;
		int index;
		public Stock(int price, int index) {
			this.price = price;
			this.index = index;
		}
	}
}