import java.util.Scanner;

public class Main {
	private static int x = 0;
	private static int y = 0;
	private static int step = 1;
	private static int snailNumber = 1;
	private static int searchNumber = 0;
	private static int searchX = 0;
	private static int searchY = 0;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		if(num % 2 == 0) return;
		
		searchNumber = sc.nextInt();
		if(searchNumber < 1 || searchNumber > (num*num)) {
			return;
		}
		
		int[][] map = new int[num][num];
		
		int centerNumber = num / 2;
		x = centerNumber;
		y = centerNumber;
		
		snailNumber = 1;
		step = 1;
		int checkNum = num -1;
		
		setSnailNumber(map, x, y);
		
		for (int i = 0; i < num-1; i++) {
//			moveY(map);
			int movedY = y - step;
			while(movedY != y) {
				if (movedY > y) y++;
				else y--;
				setSnailNumber(map, x, y);
			}
			step *= -1;
//			moveX(map);
			int movedX = x - step;
			while(movedX != x) {
				if (movedX > x) x++;
				else x--;
				setSnailNumber(map, x, y);
			}
			
			if((step*step) == (int)(checkNum*checkNum)) {
//				moveY(map);
				movedY = y - step;
				while(movedY != y) {
					if (movedY > y) y++;
					else y--;
					setSnailNumber(map, x, y);
				}
			}
			else if (step < 0) step--;
			else step++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j]+ " ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		System.out.println(searchY + " " + searchX);
		sc.close();
		
	}
	
	private static void setSnailNumber(int[][] map, int x, int y) {
		map[y][x] = snailNumber;
		checkNumber();
		snailNumber++;
	}

	private static void checkNumber() {
		if(snailNumber == searchNumber) {
			searchX = x + 1;
			searchY = y + 1;
		}
	}

}