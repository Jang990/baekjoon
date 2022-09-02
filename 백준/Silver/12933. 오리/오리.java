import java.util.ArrayList;  
import java.util.List;
import java.util.Scanner;

public class Main {
	static char[] quackArr = {'q', 'u', 'a', 'c', 'k'};
	static List<Integer> duck = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String quack = sc.nextLine();

		if(quack.charAt(0) == 'q') duck.add(0);
		else {
			System.out.println("-1");
			return;
		}
		
		for (int i = 1; i < quack.length(); i++) {
			if(!checkDuck(quack.charAt(i))) {
				System.out.println("-1");
				return;
			}
		}
		
		if(checkError()) {
			System.out.println("-1");
			return;
		}
		
		System.out.println(duck.size());
		sc.close();
	}

	private static boolean checkError() {
		for (int i = 0; i < duck.size(); i++) {
			if(duck.get(i)%5 != 4) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkDuck(char c) {
		int cnt = -1;
		for (int i = 0; i < quackArr.length; i++) {
			if(c == quackArr[i]) {
				cnt = i;
				break;
			}
		}

		for (int i = duck.size() - 1; i >= 0; i--) {
			if((duck.get(i)+1) % 5 == cnt) {
				duck.set(i, (duck.get(i)+1));
				return true;
			}
		}
		if(c == 'q') {
			duck.add(0);
			return true;
		}
		return false;
	}

}