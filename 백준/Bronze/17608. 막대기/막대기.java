import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int max = 0;
        int result = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] <= max)
                continue;
            max = arr[i];
            result++;
        }

        System.out.println(result);
    }
}
