class Solution {
    public long solution(int w, int h) {
        int n1 = Math.max(w, h);
        int n2 = Math.min(w, h);
        while(n2 != 0) {
        	int r = n1 % n2;
        	n1 = n2;
        	n2 = r;
        }
        
        return (long)w*h - (w + h - n1);
    }
}