import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        hanoi(n, 1, 3, 2);

        System.out.println(count);
        System.out.println(sb);
    }

    private static void hanoi(int n, int from, int to, int other) {
        if (n == 0) {
            return;
        }

        count++;
        hanoi(n-1, from, other, to);
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(n-1, other, to, from);
    }
}
