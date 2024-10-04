import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        boolean[] isSelfNumber = new boolean[10_001];
        Arrays.fill(isSelfNumber, true);

        int current = 1;
        while (calc(current) <= 20_000) {
            int result = calc(current);
            if(result <= 10_000)
                isSelfNumber[result] = false;
            current++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < isSelfNumber.length; i++) {
            if(isSelfNumber[i])
                sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static int calc(int num) {
        int result = num;
        for (char c : String.valueOf(num).toCharArray()) {
            result += (c - '0');
        }
        return result;
    }
}
