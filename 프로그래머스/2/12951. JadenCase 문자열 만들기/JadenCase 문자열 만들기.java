class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        char prev = ' ';
        for (char c : s.toCharArray()) {
            if (prev == ' ' && 'a' <= c && c <= 'z') {
                sb.append((char) (c - 'a' + 'A'));
            }
            else{
                sb.append(c);
            }
            prev = c;
        }
        return sb.toString();
    }
	
}