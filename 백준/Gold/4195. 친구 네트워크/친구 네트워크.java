import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, String> parent;
    static Map<String, Integer> size;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCnt = Integer.parseInt(br.readLine());
        while (caseCnt-- > 0) {
            parent = new HashMap<>();
            size = new HashMap<>();

            int relationCnt = Integer.parseInt(br.readLine());
            while (relationCnt-- > 0) {
                String[] relation = br.readLine().split(" ");
                if (parent.get(relation[0]) == null) {
                    parent.put(relation[0], relation[0]);
                    size.put(relation[0], 1);
                }
                if (parent.get(relation[1]) == null) {
                    parent.put(relation[1], relation[1]);
                    size.put(relation[1], 1);
                }

                int now = union(relation[0], relation[1]);
                sb.append(now).append("\n");
            }

        }
        br.close();
        System.out.println(sb);
    }

    static int union(String f1, String f2) {
        String f1Parent = find(f1);
        String f2Parent = find(f2);

        if (f1Parent.equals(f2Parent)) {
            return size.get(f1Parent);
        }

        int mergedSize = size.get(f1Parent) + size.get(f2Parent);
        if (f1Parent.compareTo(f2Parent) > 0) {
            // 38% 틀림 - 부모를 합쳐줘야 한다.
            // parent.put(f1, f2Parent);
            parent.put(f1Parent, f2Parent);
            size.put(f2Parent, mergedSize);
            size.remove(f1Parent);
        } else {
            parent.put(f2Parent, f1Parent);
            size.put(f1Parent, mergedSize);
            size.remove(f2Parent);
        }
        return mergedSize;
    }

    static String find(String friend) {
        if(parent.get(friend).equals(friend))
            return friend;

        String nowParent = find(parent.get(friend));
        parent.put(friend, nowParent);
        return nowParent;

        // 38% 틀림 - 이전 값을 반환해서 문제가 된다.
//        return parent.put(friend, find(parent.get(friend)));
    }
}
