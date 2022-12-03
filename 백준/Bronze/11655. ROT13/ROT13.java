import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		br.close();
		
		String output = "";
		for (int i = 0; i < str.length(); i++) {
			int idx = (int)str.charAt(i);
			char c;
			if(65 <= idx && idx <= 90) {
				c = calcUpper(idx);
			}
			else if(97 <= idx && idx <= 122) {
				c = calcLower(idx);
			}
			else {
				c = (char)idx;
			}
			output += c;
		}
		
		System.out.println(output);
	}
	
	static char calcUpper(int idx) {
		// 65 ~ 90
		idx += 13;
		if(idx > 90) {
			idx = 65 + idx%91;
		}
		return (char)idx;
	}
	
	static char calcLower(int idx) {
		// 97~122
		idx += 13;
		if(idx > 122) {
			idx = 97 + idx%123;
		}
		return (char)idx;
	}

}