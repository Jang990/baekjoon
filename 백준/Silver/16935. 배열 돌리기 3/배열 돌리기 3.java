import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] array;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		M = input[1];
		R = input[2];
		array = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			array[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[] rotate = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		for (int i = 0; i < rotate.length; i++) {
			switch (rotate[i]) {
				case 1:
					rotate1();
					break;
				case 2:
					rotate2();
					break;
				case 3:
					rotate3();
					break;
				case 4:
					rotate4();
					break;
				case 5:
					rotate5();
					break;
				case 6:
					rotate6();
					break;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sb.append(array[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
	
	static void rotate1() {
		int[][] rotatedArray = new int[N][M];
		for (int i = 0; i < rotatedArray.length; i++) {
			rotatedArray[i] = array[N - i - 1];
		}
		array = rotatedArray;
	}
	
	static void rotate2() {
		int[][] rotatedArray = new int[N][M];
		for (int i = 0; i < rotatedArray[0].length; i++) {
			for (int j = 0; j < rotatedArray.length; j++) {
				rotatedArray[j][i] = array[j][M - 1 - i];
			}
		}
		array = rotatedArray;
	}
	
	static void rotate3() {
		int[][] rotatedArray = new int[M][N];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				rotatedArray[j][N - 1 - i] = array[i][j];
			}
		}
		int temp = N;
		N = M;
		M = temp;
		
		array = rotatedArray;
	}
	
	static void rotate4() {
		int[][] rotatedArray = new int[M][N];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				rotatedArray[M-1-j][i] = array[i][j];
			}
		}
		int temp = N;
		N = M;
		M = temp;
		array = rotatedArray;
	}
	
	static void rotate5() {
		int[][] rotatedArray = new int[N][M];
		int[][] part = new int[N/2][M/2];
		
		part = getPart4();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[i][j] = part[i][j];
			}
		}
		
		
		part = getPart1();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[i][M/2 + j] = part[i][j];
			}
		}
		
		part = getPart2();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[N/2 + i][M/2 + j] = part[i][j];
			}
		}
		
		part = getPart3();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[N/2 + i][j] = part[i][j];
			}
		}
		
		array = rotatedArray;
	}
	
	static void rotate6() {
		int[][] rotatedArray = new int[N][M];
		
		int[][] part = new int[N/2][M/2];
		
		part = getPart2();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[i][j] = part[i][j];
			}
		}
		
		
		part = getPart3();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[i][M/2 + j] = part[i][j];
			}
		}
		
		part = getPart4();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[N/2 + i][M/2 + j] = part[i][j];
			}
		}
		
		part = getPart1();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				rotatedArray[N/2 + i][j] = part[i][j];
			}
		}
		
		array = rotatedArray;
	}
	
	private static int[][] getPart1() {
		int[][] part = new int[N/2][M/2];
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				part[i][j] = array[i][j];
			}
		}
		
		return part;
	}
	
	private static int[][] getPart2() {
		int[][] part = new int[N/2][M/2];
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				part[i][j] = array[i][M/2+j];
			}
		}
		
		return part;
	}
	
	private static int[][] getPart3() {
		int[][] part = new int[N/2][M/2];
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				part[i][j] = array[N/2+i][M/2+j];
			}
		}
		
		return part;
	}
	
	private static int[][] getPart4() {
		int[][] part = new int[N/2][M/2];
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				part[i][j] = array[N/2+i][j];
			}
		}
		
		return part;
	}
}
