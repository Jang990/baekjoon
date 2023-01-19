class Solution {
    public static int[] solution(long begin, long end) {
		int[] answer = new int[(int)(end - begin + 1)];
		
		for (long i = begin; i <= end; i++) {
			answer[(int)(i-begin)] = div(i);
		}
		
        return answer;
    }
	
	public static int div(long n) {
		if(n == 1)
			return 0;
		
		for (int i = 2; i <= (int)Math.sqrt(n); i++) {
			if(n%i == 0)
				if((int) (n/i) > 10000000)
					continue;
                else
				    return (int) (n/i);
		}
		
		return 1;
	}
}