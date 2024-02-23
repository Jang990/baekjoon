import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, String> relation = new HashMap<>();
    static Map<String, Integer> size;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        while (testcase-- > 0) {
            relation = new HashMap<>();
            size = new HashMap<>();
            int F = Integer.parseInt(br.readLine());

            for (int i = 0; i < F; i++) {
                String[] friend = br.readLine().split(" ");
                init(friend[0]);
                init(friend[1]);
                sb.append(union(friend[0], friend[1])).append("\n");
            }
        }
        br.close();

        System.out.println(sb);
    }

    private static void init(String friend) {
        if (relation.get(friend) == null) {
            relation.put(friend, friend);
        }

        if (size.get(friend) == null) {
            size.put(friend, 1);
        }
    }

    private static int union(String f1, String f2) {
        String parentF1 = find(f1);
        String parentF2 = find(f2);

        if (parentF1.equals(parentF2)) {
            return size.get(parentF1);
        }

        if (parentF1.compareTo(parentF2) < 0) {
            relation.put(parentF2, parentF1);
            size.put(parentF1, size.get(parentF1) + size.get(parentF2));
            size.remove(parentF2);
            return size.get(parentF1);
        }

        relation.put(parentF1, parentF2);
        size.put(parentF2, size.get(parentF1) + size.get(parentF2));
        size.remove(parentF1);
        return size.get(parentF2);
    }

    private static String find(String name) {
        if(relation.get(name).equals(name))
            return name;

        String parent = find(relation.get(name));
        relation.put(name, parent);
        return parent;
    }
}
