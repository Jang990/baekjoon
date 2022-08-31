import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int cnt = Integer.parseInt(sc.nextLine());
		int moveCnt = 0;
		Map<String, String> cow = new HashMap<>();
		for (int i = 0; i < cnt; i++) {
			String[] str = sc.nextLine().split(" ");
			String where = cow.get(str[0]);
			if(where == null) {
				cow.put(str[0], str[1]);
				continue;
			}
			
			if(!where.equals(str[1])) {
				cow.remove(str[0]);
				cow.put(str[0], str[1]);
				moveCnt++;
			}
		}
		sc.close();
		System.out.println(moveCnt);
	}

}