import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        int M = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        br.close();

        List<Integer> minus = new ArrayList<>();
        List<Integer> plus = new ArrayList<>();

        int max = 0;
        for (int i : arr) {
            int now = Math.abs(i);
            if (now > Math.abs(max)) {
                max = now;
            }

            if (i < 0) {
                minus.add(i*-1);
            }
            else {
                plus.add(i);
            }
        }


        List<Integer> distanceList = new ArrayList<>();
        for (int i = 0; i < minus.size(); i+=M) {
            distanceList.add(minus.get(i));
        }

        for (int i = plus.size()-1; i >= 0; i-=M) {
            distanceList.add(plus.get(i));
        }

        int[] distances = distanceList.stream().sorted()
                .mapToInt(Integer::valueOf).toArray();
        int result = distances[distances.length-1];
        for (int i = 0; i < distances.length-1; i++) {
            result += (distances[i]*2);
        }
        System.out.println(result);
    }
}
