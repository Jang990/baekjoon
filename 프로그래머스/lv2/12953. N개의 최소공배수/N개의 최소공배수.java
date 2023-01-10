class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
			answer = lcm(answer, arr[i]);
		}
        return answer;
    }
	
	int gcd(int a, int b) {
		if(a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		while(b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		
		return a;
	}
	
	int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}