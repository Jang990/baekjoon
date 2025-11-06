import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[26];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String action = br.readLine();

            boolean isCompleted = false;
            // 단어의 첫글자.
            String[] words = action.split(" ");
            for (int j = 0; j < words.length; j++) {
                int idx = words[j].toLowerCase().charAt(0) - 'a';
                if(visited[idx])
                    continue;
                visited[idx] = true;
                for (int k = 0; k < words.length; k++) {
                    if(j == k) sb.append(hotKey(words[k].charAt(0))).append(words[k].substring(1)).append(" ");
                    else sb.append(words[k]).append(" ");
                }
                sb.append("\n");
                isCompleted = true;
                break;
            }

            if(isCompleted)
                continue;

            for (int j = 0; j < action.length(); j++) {
                int idx = action.toLowerCase().charAt(j) - 'a';
                if(idx == ' ' - 'a' || visited[idx])
                    continue;
                for (int k = 0; k < action.length(); k++) {
                    if(j == k)
                        sb.append(hotKey(action.charAt(k)));
                    else
                        sb.append(action.charAt(k));
                }
                sb.append("\n");
                visited[idx] = true;
                isCompleted = true;
                break;
            }

            if(isCompleted)
                continue;

            sb.append(action).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    private static String hotKey(char key) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(key).append("]");
        return sb.toString();
    }
}
