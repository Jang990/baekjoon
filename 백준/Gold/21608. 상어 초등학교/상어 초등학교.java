import java.util.Arrays; 
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private static int N; 
	private static Map<Integer, Integer[]> Favorite;
	private static int[][] Room;
	private static int sum = 0;
	
	public static void main(String[] args) {
		scanAll();
		Room = new int[N][N]; 
		setSeat();
		
		for (int i = 0; i < Room.length; i++) {
			for (int j = 0; j < Room[i].length; j++) {
				sum += checkAroundFavoirt(Room[i][j], i, j);
			}
		}
		
		System.out.println(sum);
		
		
	}
	
	private static int checkAroundFavoirt(int sharkNumber, int y, int x) {
		Integer[] favoritArray = Favorite.get(sharkNumber);
		List<Integer> list = Arrays.asList(favoritArray);
		int cnt = 0;
		
		int checkY = 0, checkX = 0;
		if(y > 0) {
			checkY = y - 1;
			checkX = x;
			if(list.contains(Room[checkY][checkX])) cnt++;
		}
		if(y + 1 != Room.length) {
			checkY = y + 1;
			checkX = x;
			if(list.contains(Room[checkY][checkX])) cnt++;
		}
		
		
		if(x > 0) {
			checkY = y;
			checkX = x - 1;
			if(list.contains(Room[checkY][checkX])) cnt++;
		}
		if(x + 1 != Room.length) {
			checkY = y;
			checkX = x + 1;
			if(list.contains(Room[checkY][checkX])) cnt++;
		}
		
		switch (cnt) {
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return 10;		
			case 3:
				return 100;
			case 4:
				return 1000;
		}
		
		return 0;
	}

	private static void setSeat() {
		Favorite.forEach((sharkNumber, value) -> {
			Check bestSeat = null;
			for (int i = 0; i < Room.length; i++) {
				for (int j = 0; j < Room[i].length; j++) {
					if(Room[i][j] == 0) {
						Check emptySeat = checkAroundSeat(sharkNumber, i, j);
						if(bestSeat == null) bestSeat = emptySeat;
						else {
							bestSeat = Check.betterSeat(bestSeat, emptySeat);
						}
					}
				}
			}
			Room[bestSeat.getX()][bestSeat.getY()] = sharkNumber;
		});
	}

	private static Check checkAroundSeat(int sharkNumber, int y, int x) {
		Check check = new Check(y, x);
		Integer[] favoritArray = Favorite.get(sharkNumber);
		
		int checkY = 0, checkX = 0;
		if(y > 0) {
			checkY = y - 1;
			checkX = x;
			if(Room[checkY][checkX] == 0) check.plusEmptyNumber();
			else if(Arrays.asList(favoritArray).contains(Room[checkY][checkX])) check.plusAbjacentFavoritNumber();
		}
		if(y + 1 != Room.length) {
			checkY = y + 1;
			checkX = x;
			if(Room[checkY][checkX] == 0) check.plusEmptyNumber();
			else if(Arrays.asList(favoritArray).contains(Room[checkY][checkX])) check.plusAbjacentFavoritNumber();
		}
		
		
		if(x > 0) {
			checkY = y;
			checkX = x - 1;
			if(Room[checkY][checkX] == 0) check.plusEmptyNumber();
			else if(Arrays.asList(favoritArray).contains(Room[checkY][checkX])) check.plusAbjacentFavoritNumber();
		}
		if(x + 1 != Room.length) {
			checkY = y;
			checkX = x + 1;
			if(Room[checkY][checkX] == 0) check.plusEmptyNumber();
			else if(Arrays.asList(favoritArray).contains(Room[checkY][checkX])) check.plusAbjacentFavoritNumber();
		}
		
		return check;
	}

	static class Check {
		private int emptyNumber;
		private int adjacentFavoritNumber;
		private int x;
		private int y;
		
		public Check(int x, int y) {
			this.x = x;
			this.y = y;
			emptyNumber = 0;
			adjacentFavoritNumber = 0;
		}
		
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
		public void plusEmptyNumber() {
			this.emptyNumber++;
		}
		public void plusAbjacentFavoritNumber() {
			this.adjacentFavoritNumber++;
		}
		
		public int getSeatPoint() {
			return adjacentFavoritNumber * 10 + emptyNumber;
		}
		
		public static Check betterSeat(Check c1, Check c2) {
			int c1Point = c1.getSeatPoint();
			int c2Point = c2.getSeatPoint();
			
			if(c1Point > c2Point) return c1;
			else if(c1Point < c2Point) return c2;
			
			int c1X = c1.getX();
			int c2X = c2.getX();
			if(c1X > c2X) return c2;
			else if(c1X < c2X) return c1;
			
			int c1Y = c1.getY();
			int c2Y = c2.getY();
			if(c1Y > c2Y) return c2;
			else if(c1Y < c2Y) return c1;
			
			return null;
		}
	}


	private static void scanAll() {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		Favorite = new LinkedHashMap<>();
		int studentNumber = N * N;
		
		for (int i = 0; i < studentNumber; i++) {
			String[] line = sc.nextLine().split(" ");
			int[] arrayFavorite = Arrays.stream(line)
						.mapToInt(Integer::parseInt)
						.toArray();
			Integer key = arrayFavorite[0];
			Integer[] values = {arrayFavorite[1], arrayFavorite[2], arrayFavorite[3], arrayFavorite[4]};
			Favorite.put(key, values);
		}

		sc.close();
	}
}
