class Solution
{
    public static int solution(int [][]board)
    {
        for (int i = 1; i < board.length; i++) {
			for (int j = 1; j < board[0].length; j++) {
				if(board[i][j] == 0)
					continue;
				
				board[i][j] = Math.min(board[i-1][j], board[i][j-1]);
				board[i][j] = Math.min(board[i][j], board[i-1][j-1])+1;
			}
		}
		
		int size = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				size = Math.max(size, board[i][j]);
			}
		}
		
		return size*size;
    }
}