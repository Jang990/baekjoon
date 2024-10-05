import java.io.*;

public class Main {

    static String question = "\"재귀함수가 뭔가요?\"\n";
    static String[] story = {
            "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n"
    };

    static String answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static String last = "라고 답변하였지.\n";

    static StringBuilder sb = new StringBuilder();
    static String depthStr = "____";
    private static int depth;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        depth = Integer.parseInt(br.readLine());
        br.close();

        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        rec(0);
        System.out.println(sb);
    }

    private static void rec(int current) {
        if(current > depth) return;

        String currentDepthStr = depthStr.repeat(current);
        sb.append(currentDepthStr).append(question);
        if (current == depth) {
            sb.append(currentDepthStr).append(answer);
        } else {
            printStory(currentDepthStr);
            rec(current + 1);
        }
        sb.append(currentDepthStr).append(last);
    }

    private static void printStory(String currentDepthStr) {
        for (String s : story) {
            sb.append(currentDepthStr).append(s);
        }
    }
}
