import java.util.LinkedList;
import java.util.Queue;

class Solution {
    boolean[][] visited;
	char[][] boardGraph; 
	
	public int solution(int m, int n, String[] board) {
        int answer = 0;
        boardGraph = new char[m][n];
        visited = new boolean[m][n];
        
        for (int i = 0; i < board.length; i++) {
        	boardGraph[i] = board[i].toCharArray();
		}
        
        boolean flag = true;
        while(flag) {
        	flag = false;
        	for (int i = 0; i < m-1; i++) {
    			for (int j = 0; j < n-1; j++) {
    				if(boardGraph[i][j] == '-') continue;
    				if(checkRec(i,j)) {
    					visited[i][j] = true;
    					visited[i+1][j] = true;
    					visited[i][j+1] = true;
    					visited[i+1][j+1] = true;
    					flag = true;
    				}
    			}
    		}
        	
        	answer += remove();
        	visited = new boolean[m][n];
        }
        
        return answer;
    }

	private int remove() {
		int cnt = 0;
		Queue<Character> qu = new LinkedList<>();
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				if(visited[i][j]) {
					boardGraph[i][j] = '.';
					cnt++;
				}
			}
		}
		
		for (int i = 0; i < boardGraph[0].length; i++) {
			for (int j = boardGraph.length-1; j >= 0; j--) {
				if(boardGraph[j][i] == '.')
					continue;
				else
					qu.offer(boardGraph[j][i]);
			}
			
			for (int j = boardGraph.length-1; j >= 0; j--) {
				if(qu.isEmpty())
					boardGraph[j][i] = '-';
				else
					boardGraph[j][i] = qu.poll();
			}
		}
		
		
		
		return cnt;
	}

	private boolean checkRec(int i, int j) {
		char c = boardGraph[i][j];
		if(boardGraph[i+1][j] == c && boardGraph[i][j+1] == c && boardGraph[i+1][j+1] == c)
			return true;
		
		return false;
	}
}