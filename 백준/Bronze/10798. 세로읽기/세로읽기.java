import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Character>> lines = new LinkedList<>();
        int maxLen = 0;
        for (int i = 0; i < 5; i++) {
            LinkedList<Character> list = new LinkedList<>();
            br.readLine().chars().forEach(c -> list.add((char) c));
            lines.add(list);
            maxLen = Math.max(maxLen, list.size());
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            for (int j = 0; j < lines.size(); j++) {
                List<Character> current = lines.get(j);
                if(current.size() <= i)
                    continue;
                sb.append(current.get(i));
            }
        }

        System.out.println(sb);
    }
}
