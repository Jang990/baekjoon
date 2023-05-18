import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arg = br.readLine().split(" ");
        br.close();
        int N = Integer.parseInt(arg[0]); // 시작 물병 수
        int K = Integer.parseInt(arg[1]); // 원하는 물병 수

        int marketBottle = 0;
        int nowBottle = N + marketBottle;
        int countBottle = 0;

        while (true) {
            nowBottle = N + marketBottle;
            countBottle = 0;

            while (nowBottle > 0) {
                if (nowBottle % 2 == 1)
                    countBottle++;
                nowBottle /= 2;
            }

            if (countBottle <= K) {
                break;
            }
            else {
                marketBottle++;
            }
        }

        System.out.println(marketBottle);
    }
}
