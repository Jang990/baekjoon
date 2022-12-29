class Solution {
    public int[] solution(int brown, int yellow) {
        for (int i = 1; i <= (int)Math.ceil(Math.sqrt(yellow)); i++) {
			if(yellow%i != 0) 
				continue;
			if (brown == (yellow/i + i) * 2 + 4) {
				int w = yellow/i+2;
				int h = i+2;
				return new int[] {w, h};
			}
				
		}
		
        return null;
    }
}