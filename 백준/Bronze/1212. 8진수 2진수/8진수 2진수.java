import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String octalString = sc.nextLine();
		String[] octalToBinary = {"000","001","010","011", "100", "101", "110", "111"};
		StringBuilder sb = new StringBuilder();
        for(int i = 0; i < octalString.length(); i++) {
        	sb.append(octalToBinary[Integer.parseInt(String.valueOf(octalString.charAt(i)))]);
        }
        while(sb.charAt(0) == '0' && sb.length() > 1) sb.replace(0, 1, "");
        System.out.println(sb);
		sc.close();
	}
}