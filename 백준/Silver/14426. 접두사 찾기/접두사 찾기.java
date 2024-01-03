import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int wordCnt = Integer.parseInt(arr[0]);
        int prefixCnt = Integer.parseInt(arr[1]);

        String[] word = new String[wordCnt];
        String[] prefix = new String[prefixCnt];
        for (int i = 0; i < wordCnt; i++) {
            word[i] = br.readLine();
        }
        for (int i = 0; i < prefixCnt; i++) {
            prefix[i] = br.readLine();
        }
        br.close();

        int result = 0;
        Arrays.sort(word);
        int idx = 0;
        for (int i = 0; i < prefixCnt; i++) {
            int left = 0, right = wordCnt;
            while (left < right) {
                int mid = (left + right) / 2;
                if (word[mid].startsWith(prefix[i])) {
                    result++;
                    break;
                }

                if(word[mid].compareTo(prefix[i]) < 0)
                    left = mid + 1;
                else
                    right = mid;
            }
        }
        System.out.println(result);
    }
}
