class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int rangeStart = 1;
        int rangeEnd = n;
        int range = 0;
        
        while(true) {
        	range = (rangeEnd - rangeStart + 1) / 2;
        	
        	if(rangeEnd - range >= max) {
        		rangeEnd = rangeEnd - range;
        	}
        	else if(rangeStart + range <= min) {
        		rangeStart = rangeStart + range;
        	}
        	else {
        		break;
        	}
        }
        
        return Integer.toBinaryString(range).length();
    }
}