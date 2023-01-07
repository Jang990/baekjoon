class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
			String str1 = initStr(n, arr1[i]);
			String str2 = initStr(n, arr2[i]);
			String line = "";
			
			for (int j = 0; j < n; j++) {
				char c1 = str1.charAt(j);
				char c2 = str2.charAt(j);
				
				if(c1 != c2)
					line += "#";
				else
					switch (c1) {
					case '0':
						line += " ";
						break;
					case '1':
						line += "#";
						break;

					}
			}
			
			answer[i] = line;
		}
        
        return answer;
    }

	private String initStr(int n, int input) {
		String s = Integer.toBinaryString(input);
		while(n != s.length()) {
			s = "0"+s;
		}
		return s;
	}
}