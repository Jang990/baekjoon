import java.util.*;
import java.util.stream.Collectors;
public class Main {
	static int[][] board;
	static int bingo = 0;
	public static void main(String[] args) {
		board = new int[5][5];
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < board.length; i++) {
			board[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int check = checkAnswer(sc);
		System.out.println(check);
		
		sc.close();
	}
	
	private static int checkAnswer(Scanner sc) {
		int num = 0;
		int[] answer = new int[5];
		boolean flag = true;
		for (int i = 0; i < 5; i++) {
			answer = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(flag) {
				for (int j = 0; j < answer.length; j++) {
					searchBoard(answer[j]);
					num++;
					checkBingo();
					
					if(bingo >= 3) {
						flag = false;
						break;
					}
					else bingo = 0;
				}
			}
		}
		
		return num;
	}

	private static void checkBingo() {
		for (int i = 0; i < board.length; i++) {
			List<Integer> arr = Arrays.stream(board[i])
					.boxed()                
					.collect(Collectors.toList());
			
			if(arr.stream().filter((n) -> n == 0).count() == 5) bingo++;
			
			if(Arrays
					.stream(new int[] {board[0][i], board[1][i], board[2][i], board[3][i], board[4][i]})
					.filter((n) -> n == 0).count() == 5) bingo++;
		}
		
		if(Arrays
			.stream(new int[] {board[0][0], board[1][1], board[2][2], board[3][3], board[4][4]})
			.filter((n) -> n == 0).count() == 5) bingo++;
		if(Arrays
				.stream(new int[] {board[0][4], board[1][3], board[2][2], board[3][1], board[4][0]})
				.filter((n) -> n == 0).count() == 5) bingo++;
		
		
	}

	private static void searchBoard(int answer) {
		for (int i = 0; i < board.length; i++) {
			List<Integer> arr = Arrays.stream(board[i])
					.boxed()                
					.collect(Collectors.toList());
			
			if(!arr.contains(answer)) 
				continue;
			
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] == answer) { 
					board[i][j] = 0;
					return;
				}
			}
			
			
		}
	}
	
}
