import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String secret = br.readLine();
        int n = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(br.readLine());
        br.close();

        for (String word : list) {
            for(int i = 1; i <= 28; i++){
                String newWord = createWord(i, word);
                if (secret.contains(newWord)) {
                    System.out.println(createWord(i * -1, secret));
                    return;
                }
            }
        }
    }

    private static String createWord(int diff, String base) {
        char[] arr = base.toCharArray();
        char[] changed = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            changed[i] = (char) (arr[i] + diff);
            if (changed[i] > 'z')
                changed[i] = (char) (changed[i] - 'z' + 'a' - 1);
            if(changed[i] < 'a')
                changed[i] = (char) (changed[i] - 'a' + 'z' + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : changed)
            sb.append(c);
        return sb.toString();
    }
}
