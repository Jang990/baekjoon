class Solution {
    public  int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows+1][columns+1];
        
        int num = 1;
        for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				map[i][j] = num++;
			}
		}
        for (int i = 0; i < queries.length; i++) {
			answer[i] = rotate(map, queries[i]);
		}
        
        return answer;
    }

	private int rotate(int[][] map, int[] point) {
		int startRow = Math.min(point[0], point[2]), endRow = Math.max(point[0], point[2]);
		int startCol = Math.min(point[1], point[3]), endCol = Math.max(point[1], point[3]);
		int Min = Integer.MAX_VALUE;
		
		int num1 = 0, num2;
		for (int i = startCol; i <= endCol; i++) {
			num2 = map[startRow][i];
			map[startRow][i] = num1;
			num1 = num2;
			Min = Math.min(Min, num2);
		}
		
		for (int i = startRow+1; i <= endRow; i++) {
			Min = Math.min(Min, num1);
			num2 = map[i][endCol];
			map[i][endCol] = num1;
			num1 = num2;
		}
		
		for (int i = endCol-1; i >= startCol; i--) {
			Min = Math.min(Min, num1);
			num2 = map[endRow][i];
			map[endRow][i] = num1;
			num1 = num2;
		}
		
		for (int i = endRow-1; i >= startRow; i--) {
			Min = Math.min(Min, num1);
			num2 = map[i][startCol];
			map[i][startCol] = num1;
			num1 = num2;
		}
		
        if(point[0] >= point[2] || point[1] >= point[3])
			Min = Integer.MAX_VALUE + 1;

		return Min;
	}
}