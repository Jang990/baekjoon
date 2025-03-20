import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        String[] antsA = br.readLine().split("");
        String[] antsB = br.readLine().split("");
        int step = Integer.parseInt(br.readLine());

        br.close();

        Stack<String> leftGroup = createLeftGroup(antsA);
        Queue<String> rightGroup = createRightGroup(antsB, leftGroup.size(), step);

        StringBuilder sb = new StringBuilder();
        if(leftGroup.size() - 1 - step < 0)
            for (int i = 0; i < step - (leftGroup.size() - 1); i++) {
                sb.append(rightGroup.poll());
                if (rightGroup.isEmpty())
                    break;
            }

        while (!leftGroup.isEmpty() || !rightGroup.isEmpty()) {
            if(!leftGroup.isEmpty())
                sb.append(leftGroup.pop());
            if(!rightGroup.isEmpty())
                sb.append(rightGroup.poll());
        }
        System.out.println(sb);
    }

    private static Queue<String> createRightGroup(String[] antsB, int leftGroupSize,int step) {
        Queue<String> rightGroup = new LinkedList<>();
        for (int i = 0; i < leftGroupSize - 1 - step; i++)
            rightGroup.offer("");
        for (String s : antsB)
            rightGroup.offer(s);
        return rightGroup;
    }

    private static Stack<String> createLeftGroup(String[] antsA) {
        Stack<String> leftGroup = new Stack<>();
        for (String s : antsA) {
            leftGroup.add(s);
        }
        return leftGroup;
    }
}
