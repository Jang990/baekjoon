class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            if(isBinaryTreePossible(numbers[i]))
                answer[i] = 1;
            else
                answer[i] = 0;
        }
        return answer;
    }
    
    
    boolean isBinaryTreePossible(long num) {
        String str = Long.toBinaryString(num);
        int repeatsCount = getRepeatsCount(str.length());
        str = "0".repeat(repeatsCount) + str;
        // System.out.println(repeatsCount + ", " + str);
        
        // return true;
        return rec(str, false);
    }
    
    int getRepeatsCount(int targetLength) {
        if(targetLength < 2)
            return 0;
        
        int length = 1;
        while(length <= targetLength) {
            length *= 2;
        }
        return length - targetLength - 1;
    }
    
    boolean rec(String str, boolean isDummy) {
        int len = str.length();
        int mid = len/2;
        int midNumber = str.charAt(mid) - '0';
        if(len == 1) {
            return !(isDummy && midNumber == 1);
        }
        
        if(isDummy && midNumber == 1)
            return false;
            
        
        isDummy = (midNumber == 0);
        
        return rec(str.substring(0, mid), isDummy) 
            && rec(str.substring(mid+1, len), isDummy);
    }
}