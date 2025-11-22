import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<char[]> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            char[] arr = br.readLine().toCharArray();
            if(!containsWord(arr))
                words.add(arr);
        }
        br.close();

        System.out.println(words.size());
    }

    private static boolean containsWord(char[] newWord) {
        for (char[] word : words) {
            if(isSame(word, newWord))
                return true;
        }
        return false;
    }

    private static boolean isSame(char[] word, char[] newWord) {
        if(word.length != newWord.length)
            return false;

        for (int i = 0; i < word.length; i++) {
            boolean isSame = false;
            for (int j = 0; j < word.length; j++) {
                int current = (j + i) % word.length;
                if (word[j] != newWord[current]) {
                    isSame = false;
                    break;
                }
                isSame = true;
            }
            if(isSame)
                return true;
        }
        return false;
    }
}
