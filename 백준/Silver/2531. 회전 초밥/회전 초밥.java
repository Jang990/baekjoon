import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 단순 이중 for문 - 3만 * 3천 = 9천만 가능 => 불가능...
        // 먹을 수 있는 초밥 종류의 최댓값 구하기 - 끝

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = arr[0];
        int d = arr[1];
        int k = arr[2];
        int c = arr[3];

        int[] sushi = new int[N];
        int[] sushiEatCnt = new int[d + 1];
        for (int i = 0; i < N; i++)
            sushi[i] = Integer.parseInt(br.readLine());

        br.close();


        int way = 0;
        for (int i = 0; i < k; i++) {
            if(sushiEatCnt[sushi[i]] == 0)
                way++;
            sushiEatCnt[sushi[i]]++;
        }
        int max = way;
        for (int i = k; i < N + k; i++) {
            int popIdx = i - k;
            int pushIdx = i % N;

            sushiEatCnt[sushi[popIdx]]--;
            if(sushiEatCnt[sushi[popIdx]] == 0)
                way--;

            sushiEatCnt[sushi[pushIdx]]++;
            if(sushiEatCnt[sushi[pushIdx]] == 1)
                way++;
            if(sushiEatCnt[c] == 0)
                max = Math.max(max, way + 1);
            else
                max = Math.max(max, way);
        }

        System.out.println(max);
    }
}
