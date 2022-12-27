class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        String str;
        for (int i = 0; i < numbers.length; i++) {
			String binaryStr = Long.toBinaryString(numbers[i]);
			if(binaryStr.contains("0")) {
				int idx = binaryStr.lastIndexOf("0");
				if(idx == binaryStr.length()-1) {
					str = binaryStr.substring(0, idx) + "1" + binaryStr.substring(idx+1);
				}
				else {
					str = binaryStr.substring(0, idx) + "10" + binaryStr.substring(idx+2);
				}
			}
			else {
				str = "10" + binaryStr.substring(1);
			}
			answer[i] = Long.valueOf(str, 2);
		}
        
        return answer;
    }
}