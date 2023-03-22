import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;
    static List<String> check;
    static String[] alphabet;
    static boolean[] visited;
    static int L, C;

    public static void main(String[] args) throws IOException {
        // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
        // 알파벳 증가 순서

        check = Arrays.asList("a", "e", "i", "o", "u");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        L = Integer.valueOf(st.nextToken());
        C = Integer.valueOf(st.nextToken());

        alphabet = new String[C];
        visited = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = st.nextToken();
        }
        Arrays.sort(alphabet);

        Rec(0, 0);
        System.out.println(sb.toString());

        br.close();
    }

    private static void Rec(int start, int depth) {
        if(depth == L) {
            String s = "";
            int vowelCnt = 0;
            int consonantCnt = 0;
            for (int i = 0; i < visited.length; i++) {
                if(!visited[i])
                    continue;
                if(check.contains(alphabet[i]))
                    vowelCnt++;
                else
                    consonantCnt++;

                s = s.concat(alphabet[i]);
            }

            if(vowelCnt > 0 && consonantCnt > 1) {
                sb.append(s.concat("\n"));
            }
            return;
        }

        for (int i = start; i < alphabet.length; i++) {
            if(visited[i])
                continue;

            visited[i] = true;
            Rec(i+1, depth+1);
            visited[i] = false;
        }


    }

}
