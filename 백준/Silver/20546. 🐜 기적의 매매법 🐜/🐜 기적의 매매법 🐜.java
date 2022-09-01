import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] stockPrice;  
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int money = Integer.parseInt(sc.nextLine());
		
		Person bnp = new Person("BNP", money);
		Person timing = new Person("TIMING", money);
		
		String[] stockPriceStr = sc.nextLine().split(" ");
		stockPrice = Arrays.stream(stockPriceStr).mapToInt(Integer::parseInt).toArray();
		
		int upCnt = 0;
		int downCnt = 0;
		
		bnp.buy(stockPrice[0]);
		for (int i = 1; i < stockPrice.length; i++) {
			bnp.buy(stockPrice[i]);
			if(stockPrice[i] > stockPrice[i-1]) {
				upCnt++;
				downCnt = 0;
			}
			else if (stockPrice[i] < stockPrice[i-1]) {
				downCnt++;
				upCnt = 0;
			}
			
			if(upCnt > 2)
				timing.sell(stockPrice[i]);
			else if(downCnt > 2)
				timing.buy(stockPrice[i]);
		}
		
		if(bnp.total() > timing.total())
			System.out.println(bnp.getName());
		else if(bnp.total() < timing.total())
			System.out.println(timing.getName());
		else
			System.out.println("SAMESAME");
			
		sc.close();
	}
	
	static class Person {
		String name;
		int money;
		int ownStockCnt;
		
		public Person(String name, int money) {
			this.name = name;
			this.money = money;
			ownStockCnt = 0;
		}
		
		boolean sell(int price) {
			if(ownStockCnt != 0) {
				int stockMoney = price * ownStockCnt;
				money += stockMoney;
				ownStockCnt = 0;
				return true;
			}
			return false;
		}
		
		boolean buy(int price) {
			if(money == 0 || price > money) return false;
			
			if(money >= price) {
				int cnt = (int)(money / price);
				ownStockCnt += cnt;
				money -= price * cnt;
				return true;
			}
			return false;		
		}
		
		int total() {
			if(ownStockCnt == 0)
				return money;
			
			sell(stockPrice[stockPrice.length - 1]);
			return money;
		}
		
		String getName() {
			return name;
		}
	}
}
