class Solution {
    public String solution(int n) {
        int[] arr = {4, 1, 2};
		StringBuilder sb = new StringBuilder();
		while(n > 0) {
			int mod = n%3;
			n /= 3;
			
			if(mod == 0)
				n--;
			
			sb.append(arr[mod]);
		}
		
        return sb.reverse().toString();
    }
}