class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
        	int checkPower = check(i);
			if(checkPower > limit)
				answer += power;
			else
				answer += checkPower;
		}
        
        return answer;
    }
	
	public int check(int num) {
		if(num == 1)
			return 1;
		if(num == 2 || num == 3)
			return 2;
		
		int sqrt = (int)Math.sqrt(num);
		int result = 0;
		
		for (int i = 1; i <= sqrt; i++) {
			if(num%i == 0)
				result++;
		}
		
		if((int)Math.pow(sqrt, 2) == num)
			result = (result-1)*2+1;
		else 
			result *= 2;
		
		return result;
	}
}