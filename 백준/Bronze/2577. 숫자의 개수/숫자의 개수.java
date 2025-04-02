import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;
        for (int i = 0; i < 3; i++) {
            num *= Integer.parseInt(br.readLine());
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        sb.append(findZero(String.valueOf(num))).append("\n");
        sb.append(findOneToNine(String.valueOf(num)));
        System.out.println(sb);
    }

    private static String findOneToNine(String s) {
        int[] arr = new int[10];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - '0']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++)
            sb.append(arr[i]).append("\n");

        return sb.toString();
    }

    private static int findZero(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0')
                result++;
        }
        return result;
    }
}
