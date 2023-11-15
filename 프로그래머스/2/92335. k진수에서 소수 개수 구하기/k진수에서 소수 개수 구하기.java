import java.util.*;
class Solution {
    public int solution(int n, int k) {
        String convertedStr = Integer.toString(n, k);
        long[] numbers = Arrays.stream(convertedStr.split("0"))
            .filter(s -> s.length() > 0)
            .mapToLong(Long::valueOf)
            .filter(number -> number != 1).toArray();
        
        int result = 0;
        for(int i = 0; i< numbers.length; i++)
            if(isPrime(numbers[i]))
                result++;
        
        return result;
	}
    
    boolean isPrime(long n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
}