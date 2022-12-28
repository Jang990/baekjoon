class Solution {
    int[] answer = {0,0};
	public int[] solution(int[][] arr) {
        count(arr, arr.length, 0, 0);
        System.out.println(answer[0] + ", " + answer[1]);
        return answer;
    }
	private void count(int[][] arr, int size, int x, int y) {
		if(check(arr, size, x, y)) {
			answer[arr[y][x]]++;
			return;
		}
		
		count(arr, size/2, x, y);
		count(arr, size/2, x+size/2, y);
		count(arr, size/2, x, y + size/2);
		count(arr, size/2, x+size/2, y+size/2);
	}
	
	private boolean check(int[][] arr, int size, int x, int y) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(arr[y][x] != arr[y+i][x+j])
					return false;
			}
		}
		
		return true;
	}
}