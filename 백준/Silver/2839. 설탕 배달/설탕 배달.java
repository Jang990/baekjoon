import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        br.close();

        int bag5 = n / 5;
        int leftoverSugar = n - bag5*5;
        int bag3 = 0;
        while (bag5 >= 0) {
            leftoverSugar = n - bag5 * 5;
            if (leftoverSugar % 3 == 0) {
                bag3 = leftoverSugar / 3;
                break;
            }
            bag5--;
        }

        if (bag5 == -1) {
            System.out.println(-1);
        }
        else {
            System.out.println(bag3 + bag5);
        }
    }
}
