class Solution {
    public static long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = function(numbers[i]);
        }
        return result;
    }

    public static long function(long num) {
        long now = 2;
        long mod = num;
        while (mod > 1) {
            if (mod % 2 == 0) {
                if(now >= 4)
                    return num + now / 2 - now / 4;
                else
                    return num + now / 2;
            }
            mod /= 2;
            now *= 2;
        }

        return num + now/2;
    }//
}