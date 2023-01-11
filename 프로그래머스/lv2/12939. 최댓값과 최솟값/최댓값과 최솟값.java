import java.util.Arrays;
class Solution {
    public String solution(String s) {
        int[] array = Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
        Arrays.sort(array);
        return array[0] + " " + array[array.length-1];
    }
}