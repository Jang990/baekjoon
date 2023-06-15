import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                if(o1[0] == o2[0])
                    return 0;

                if(o1[0] > o2[0])
                    return 1;
                else
                    return -1;
            }

            if (o1[1] > o2[1]) {
                return 1;
            }
            else {
                return -1;
            }
        });

        int result = 1;
        int[] selected = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int[] now = arr[i];
            if (selected[1] > now[0]) {
                continue;
            }

            selected = now;
            result++;
        }

        System.out.println(result);
    }
}
