class Solution {
    public int solution(int n, int k) {
        String[] arr = Integer.toString(n, k).split("0");
        int result = 0;
        for (String s : arr) {
            if(s.equals("")) continue;
            if(isPrime(Long.parseLong(s)))
                result++;
        }
        return result;
    }

    boolean isPrime(long n) {
        if(n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}