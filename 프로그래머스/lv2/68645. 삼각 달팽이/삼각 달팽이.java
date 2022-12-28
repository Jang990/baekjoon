class Solution {
    public int[] solution(int n) {
        if(n == 1) {
			return new int[] {1};
		}
        
        int[][] arr = new int[n][n];
        
        int num = 1;
        int nowX = 0, nowY = 0;
        int dir = 0;
        boolean flag = false;
        while(true) {
        	arr[nowY][nowX] = num++;
        	switch (dir % 3) {
			case 0: // 아래로
				if(nowY + 1 < n && arr[nowY+1][nowX] == 0)
					nowY++;
				else {
					if(arr[nowY][nowX+1] != 0) flag = true;
					nowX++;
					dir++;
				}
				break;
			case 1: // 옆으로
				if(nowX + 1 < n && arr[nowY][nowX+1] == 0)
					nowX++;
				else {
					if(arr[nowY-1][nowX-1] != 0) flag = true;
					nowY--;
					nowX--;
					dir++;
				}
				break;
			case 2: // 대각으로
				if(nowX > 1 && nowY > 1 && arr[nowY-1][nowX-1] == 0) {
					nowX--;
					nowY--;
				}
				else {
					if(arr[nowY+1][nowX] != 0) flag = true;
					dir++;
					nowY++;
				}
				break;
			}
        	
        	if(flag) 
        		break;
        }
        
        int[] answer = new int[num-1]; 
        
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == 0) break;
				answer[idx++] = arr[i][j];
			}
		}
        
        return answer;
    }
}