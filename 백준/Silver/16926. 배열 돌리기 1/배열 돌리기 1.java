import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static int N = 0; // Y
	private static int M = 0; // X
	private static int R = 0;
	private static int TURN = 0;
	
	public static void main(String[] args) {
		int[][] array = scanAll();
		if(array == null) return;

		TURN = Math.min(N, M)/2;
		for (int i = 0; i < TURN; i++) {
			List<Integer> rotateList = getRotateNumber(array, i);
			rotate(rotateList);
			setRotateNumber(array, rotateList, i);
		}		
		
		System.out.println(getOutputString(array));
	}

	private static String getOutputString(int[][] array) {
		StringBuilder sb = new StringBuilder();
		for (int[] num : array) {
			for (int is : num) {
				sb.append(is+ " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private static void rotate(List<Integer> rotateList) {
		int size = rotateList.size();
		int rotateNum = R % size;
		for (int i = 0; i < rotateNum; i++) {
			rotateList.add(0, rotateList.get(size-1));
			rotateList.remove(size);
		}
	}

	private static void setRotateNumber(int[][] array, List<Integer> rotatedList, int currentTurn) {
		int listIdx = 0;
		int currentIdxX = currentTurn;
		int currentIdxY = currentTurn;
		for (int j = currentIdxY; j < N - currentTurn; j++) {
			array[currentIdxY++][currentIdxX] = rotatedList.get(listIdx++);
		}
		currentIdxY--;
		currentIdxX++;
		for (int j = currentIdxX; j < M - currentTurn; j++) {
			array[currentIdxY][currentIdxX++] = rotatedList.get(listIdx++);
		}
		currentIdxX--;
		currentIdxY--;
		for (int j = currentIdxY; j >= currentTurn; j--) {
			array[currentIdxY--][currentIdxX] = rotatedList.get(listIdx++);
		}
		currentIdxY++;
		currentIdxX--;
		for (int j = currentIdxX; j >= currentTurn + 1; j--) {
			array[currentIdxY][currentIdxX--] = rotatedList.get(listIdx++);
		}
	}

	private static List<Integer> getRotateNumber(int[][] array, int currentTurn) {
		List<Integer> rotateList = new ArrayList<>();
		int currentIdxX = currentTurn;
		int currentIdxY = currentTurn;
		for (int j = currentIdxY; j < N - currentTurn; j++) {
			rotateList.add(array[currentIdxY++][currentIdxX]);
		}
		currentIdxY--;
		currentIdxX++;
		for (int j = currentIdxX; j < M - currentTurn; j++) {
			rotateList.add(array[currentIdxY][currentIdxX++]);
		}
		currentIdxX--;
		currentIdxY--;
		for (int j = currentIdxY; j >= currentTurn; j--) {
			rotateList.add(array[currentIdxY--][currentIdxX]);
		}
		currentIdxY++;
		currentIdxX--;
		for (int j = currentIdxX; j >= currentTurn + 1; j--) {
			rotateList.add(array[currentIdxY][currentIdxX--]);
		}
		return rotateList;
	}

	private static int[][] scanAll() {
		Scanner sc = new Scanner(System.in);
		String[] optionsStr = sc.nextLine().split(" ");
		int[] optionNum = Arrays.stream(optionsStr)
				.mapToInt(Integer::parseInt)
				.toArray();
		
		N = optionNum[0]; 
		M = optionNum[1]; 
		R = optionNum[2];
//		System.out.println(String.format("N: %d\tM: %d\tR: %d", N, M, R));
		
		if(N < 2 || M > 300) return null;
		if(R < 1 || R > 1000) return null;
		if(Math.min(N, M) % 2 != 0) return null;
		
		int[][] array = new int[N][M];
		String[] line = new String[N];
		for (int i = 0; i < array.length; i++) {
			line = sc.nextLine().split(" ");
			if(line.length > M) return null;
			array[i] = Arrays.stream(line)
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		sc.close();
		return array;
	}
	
	
}