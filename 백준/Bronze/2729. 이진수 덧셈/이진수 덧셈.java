import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		StringBuffer sb;
		StringBuffer sbResult = new StringBuffer();
		
		for (int i = 0; i < n; i++) {
			sb = new StringBuffer();
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			String n1Str = st.nextToken();
			String n2Str = st.nextToken();
			
			int diff = 0;
			int len = 0;
			if(n1Str.length() < n2Str.length()) {
				len = n2Str.length();
				diff = len - n1Str.length();
				while(diff-- > 0) {
					n1Str = '0' + n1Str;
				}
			}
			else if(n1Str.length() > n2Str.length()) {
				len = n1Str.length();
				diff = len - n2Str.length();
				while(diff-- > 0) {
					n2Str = '0' + n2Str;
				}
			}
			else len = n1Str.length();
			
			int remain = 0;
			int carry = 0;
			for (int j = len - 1; j >= 0; j--) {
				int n1 = n1Str.charAt(j) - 48;
				int n2 = n2Str.charAt(j) - 48;
				int sum = n1 + n2 + carry;
				carry = sum / 2;
				remain = sum % 2;
				sb.append(remain);
				if(j == 0 && carry == 1) sb.append(1); 
			}
			
			char[] result = sb.reverse().toString().toCharArray();
			int idx = 0;
			for (int j = 0; j < result.length; j++) {
				if(result[j] == '0') idx++; 
			}
			int replace = 0;
			for (int j = 0; j < result.length; j++) {
				if(result[j] == '0') replace++;
				else break;
			}
			
			if(idx == result.length) sbResult.append(0 + "\n");
			else {
				sbResult.append(String.valueOf(result).substring(replace)+"\n");
			}
		}
		System.out.println(sbResult.toString());
		
		sc.close();
	}
	
}