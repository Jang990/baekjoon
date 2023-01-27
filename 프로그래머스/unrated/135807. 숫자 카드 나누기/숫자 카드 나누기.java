import java.util.Arrays;
class Solution {
    public static int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        int a = gcd(arrayA);
        if(checkArray(a, arrayB))
        	answer = a;
        int b = gcd(arrayB);
        if(checkArray(b, arrayA))
        	answer = Math.max(answer, b);
        
        return answer;
    }
	
	static int gcd(int[] array) {
		if(array.length == 1)
			return array[0];
		
		int a = array[array.length-1], b;
		
		for (int i = array.length-2; i >= 0; i--) {
			b = array[i];
			while(b != 0) {
				int r = a%b;
				a = b;
				b = r;
			}
		}
		
		return a;
	}
	
	static boolean checkArray(int gcd, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if(array[i]%gcd == 0)
				return false;
		}
		
		return true;
	}
}