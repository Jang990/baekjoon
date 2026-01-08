import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        br.close();
        int len = Integer.parseInt(line[0]);
        int money = Integer.parseInt(line[1]);

        if (len > money || 26 * len < money) {
            System.out.println("!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            int min = len - i;
            int current = money - min;
            if(current > 26)
                current = 26;

            money -= current;
            sb.append((char)('A' - 1 + current));
        }

        System.out.println(sb.reverse());
    }
}
