class Solution {
    public int solution(int n) {
        String str3 = Integer.toUnsignedString(n, 3);
        StringBuffer sb = new StringBuffer(str3);
        return Integer.valueOf(sb.reverse().toString(), 3);
    }
}