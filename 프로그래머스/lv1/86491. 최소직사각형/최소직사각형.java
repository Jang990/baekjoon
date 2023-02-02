class Solution {
    public int solution(int[][] sizes) {
        int width = 0, height = 0;
        for (int i = 0; i < sizes.length; i++) {
        	int n1 = Math.max(sizes[i][0], sizes[i][1]);
        	int n2 = Math.min(sizes[i][0], sizes[i][1]);
        	
        	width = Math.max(width, n1);
        	height = Math.max(height, n2);
		}
        
        return width * height;
    }
}