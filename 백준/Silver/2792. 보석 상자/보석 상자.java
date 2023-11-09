import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int kidCnt = Integer.valueOf(st.nextToken());
        int jewelryCnt = Integer.valueOf(st.nextToken());

        int[] jewelry = new int[jewelryCnt];
        int low = 1, high = 1;
        for (int i = 0; i < jewelryCnt; i++) {
            jewelry[i] = Integer.valueOf(br.readLine());
            high = Math.max(jewelry[i], high);
        }
        br.close();

        while (low < high) {
            int mid = (high + low) / 2;
            int receivedKid = 0;
            for (int i = 0; i < jewelryCnt; i++) {
                receivedKid += jewelry[i] / mid;
                if(jewelry[i] % mid != 0)
                    receivedKid++;
            }

            if (receivedKid > kidCnt) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        System.out.println(low);
    }
}
