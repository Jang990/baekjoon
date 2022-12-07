import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		String[] st = br.readLine().split(" ");
		br.close();
		
		int n1 = Integer.valueOf(reverseStr(st[0]));
		int n2 = Integer.valueOf(reverseStr(st[1]));
		int result = n1 + n2;
		result = Integer.valueOf(reverseStr(String.valueOf(result)));
		
		System.out.println(result);		
	}

	private static String reverseStr(String string) {
		return new StringBuilder(string).reverse().toString();	
	}

}
