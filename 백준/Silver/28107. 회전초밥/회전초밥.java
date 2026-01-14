import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        Queue<Integer>[] sushiAndCustomer = new LinkedList[200_001];
        for (int i = 0; i < 200_001; i++)
            sushiAndCustomer[i] = new LinkedList<>();

        for (int customerId = 0; customerId < N; customerId++) {
            int[] sushi = readSushi(br);
            for (int i = 0; i < sushi.length; i++)
                sushiAndCustomer[sushi[i]].offer(customerId);
        }

        int[] sushiOrders = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        br.close();

        int[] result = new int[N];
        for (int sushiOrder : sushiOrders) {
            Integer customerId = sushiAndCustomer[sushiOrder].poll();
            if(customerId == null)
                continue;
            result[customerId]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static int[] readSushi(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] result = new int[Integer.parseInt(st.nextToken())];
        for (int i = 0; i < result.length; i++)
            result[i] = Integer.parseInt(st.nextToken());
        return result;
    }
}
