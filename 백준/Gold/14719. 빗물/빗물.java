import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        int[][] arr = new int[width][height];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr[0].length; i++) {
            int H = Integer.parseInt(st.nextToken());
            for (int j = arr.length-H; j < arr.length; j++) {
                arr[j][i] = 1;
            }
        }
        
        br.close();

        int result = 0;
        boolean leftRock = false;
        for (int i = arr.length-1; i >= 0; i--) {
            int cnt = 0;
            for (int j = 0; j < arr[0].length; j++) {
                if (!leftRock && arr[i][j] == 1) {
                    leftRock = true;
                }
                else if (leftRock && arr[i][j] == 0) {
                    cnt++;
                }
                else if (leftRock && arr[i][j] == 1) {
                    result += cnt;
                    cnt = 0;
                }
            }

            cnt = 0;
            leftRock = false;
        }

        System.out.println(result);
    }
}
