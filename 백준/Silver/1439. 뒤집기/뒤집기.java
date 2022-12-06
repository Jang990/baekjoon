import java.io.*;

public class Main {

	static int Zero = 0, One = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		br.close();
		
		char prevC, nowC;
		for (int i = 0; i < str.length(); i++) {
			if(i == 0) {
				prevC = str.charAt(i);
				count(prevC);
			}
			else 
				prevC = str.charAt(i-1);
			
			nowC = str.charAt(i);
			
			if(nowC == prevC) 
				continue;
				
			count(nowC);
		}
		
		if(One == 0 || Zero == 0) 
			System.out.println(0);
		else 
			System.out.println(Math.min(Zero, One));
		
	}
	
	private static void count(char c) {
		switch (c) {
			case '0':
				Zero++;
				break;
			case '1':
				One++;
				break;
		}
	}

}