import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String[] cond = br.readLine().split(" ");
            if(cond[1].equals("enter"))
                set.add(cond[0]);
            else
                set.remove(cond[0]);
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        set.stream().sorted(Comparator.reverseOrder())
                .forEach(s -> sb.append(s).append("\n"));
        System.out.println(sb);
    }
}
