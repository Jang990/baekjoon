import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<String>[] qu = new Queue[n];
        for (int i = 0; i < n; i++) {
            qu[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            for (String word : br.readLine().split(" ")) {
                qu[i].offer(word);
            }
        }

        String[] words = br.readLine().split(" ");
        br.close();

        boolean result = true;
        for (String word : words) {
            boolean isPossible = false;
            for (int i = 0; i < n; i++) {
                if(qu[i].isEmpty() || !qu[i].peek().equals(word))
                    continue;
                qu[i].poll();
                isPossible = true;
                break;
            }
            if (!isPossible) {
                result = false;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if(!qu[i].isEmpty())
                result = false;
        }

        if(result)
            System.out.println("Possible");
        else
            System.out.println("Impossible");
    }
}
