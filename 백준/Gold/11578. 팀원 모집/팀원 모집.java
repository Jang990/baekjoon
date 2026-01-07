import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int problemCnt;
    private static int teammateCnt;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = readLine(br);
        problemCnt = line[0];
        teammateCnt = line[1];

        for (int i = 1; i <= teammateCnt; i++) {
            ArrayList<Integer> problem = new ArrayList<>();
            line = readLine(br);
            for (int j = 1; j <= line[0]; j++) {
                problem.add(line[j]);
            }
            map.put(i, problem);
        }
        br.close();

        boolean[] solved = new boolean[problemCnt + 1];
        rec(0, 0, 0, solved);

        if(result == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(result);
    }

    private static void rec(int prevIdx, int selectedTeammateCnt, int solvedCnt, boolean[] solved) {
        if (solvedCnt == problemCnt) {
            result = Math.min(selectedTeammateCnt, result);
            return;
        }
        if(prevIdx >= teammateCnt)
            return;

        int currentIdx = prevIdx + 1;
        rec(currentIdx, selectedTeammateCnt, solvedCnt, solved);

        List<Integer> teammateProblems = map.get(currentIdx);
        List<Integer> currentSolvedProblems = new ArrayList<>();
        for (int problem : teammateProblems) {
            if(solved[problem])
                continue;
            solvedCnt++;
            solved[problem] = true;
            currentSolvedProblems.add(problem);
        }
        rec(currentIdx, selectedTeammateCnt + 1, solvedCnt, solved);
        for (int currentSolved : currentSolvedProblems) {
            solved[currentSolved] = false;
        }
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
