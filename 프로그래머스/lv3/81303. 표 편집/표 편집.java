import java.util.Stack;

class Solution {
    
    private static int pointer;
    private static int size;

    public static String solution(int n, int k, String[] cmd) {
        size = n;
        pointer = k;
        Stack<Integer> history = new Stack<>();
        for (String str : cmd) {
            String[] arr = str.split(" ");
            switch (arr[0].charAt(0)) {
                case 'U':
                    pointer -= Integer.valueOf(arr[1]);
                    break;
                case 'D':
                    pointer += Integer.valueOf(arr[1]);
                    break;
                case 'C':
                    // 삭제
                    remove(history);
                    break;
                case 'Z':
                    // 복구
                    restore(history);
                    break;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append("O".repeat(size));
        while (!history.isEmpty()) {
            int idx = history.pop();
            sb.insert(idx, "X");
        }

        return sb.toString();
    }

    private static void restore(Stack<Integer> history) {
        size++;
        int pop = history.pop();
        if(pop <= pointer)
            pointer++;
    }

    private static void remove(Stack<Integer> history) {
        history.push(pointer);
        size--;
        if (pointer == size) {
            pointer--;
        }
    }
}