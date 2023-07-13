import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.readLine();
        String s = br.readLine();
        br.close();

        int ioiCnt = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            if (ioiCnt % 2 == 0) {
                if (now == 'I') {
                    ioiCnt++;
                }
                else {
                    result += calcIoICnt(n, ioiCnt);
                    ioiCnt = 0;
                }
            }
            else {
                if (now == 'O') {
                    ioiCnt++;
                }
                else {
                    result += calcIoICnt(n, ioiCnt);
                    ioiCnt = (now == 'I') ? 1 : 0;
                }
            }
        }

        if (ioiCnt > 1) {
            result += calcIoICnt(n, ioiCnt);
        }

        System.out.println(result);

    }

    private static int calcIoICnt(int n, int ioiCnt) {
        int result = (ioiCnt + 1) / 2 - n;
        return result > 0 ? result : 0;
    }
}
