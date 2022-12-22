class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String numbers = Integer.toString(n, k);
        String str = "";
        for (int i = 0; i < numbers.length(); i++) {
			int num = numbers.charAt(i) - '0';
			if(num == 0) {
				if(str.length() == 0)
					continue;
				else {
					if(isPrime(Long.parseLong(str))) 
                        answer++;
					str = "";
				}
			}
			else {
				str += num;
			}
		}
        
        if(str.length() > 0 && isPrime(Long.parseLong(str))) 
            answer++;
        return answer;
    }

	private boolean isPrime(long val) {
		if(val < 2)
			return false;
		if(val == 2)
			return true;
		
        long sqrt = (long)Math.sqrt(val);
		for (int i = 2; i <= sqrt; i++) {
			if(val%i == 0) 
				return false;
		} 
		
		return true;
	}
}