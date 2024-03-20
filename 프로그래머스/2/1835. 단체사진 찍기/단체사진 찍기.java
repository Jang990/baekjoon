import java.util.*;
class Solution {
    char[] elements = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited = new boolean[elements.length];
    HashMap<Character, List<Command>> rule = new HashMap<>();
    int answer = 0;
    public int solution(int n, String[] data) {
        for(int i = 0 ; i < elements.length; i++) {
            rule.put(elements[i], new LinkedList<>());
        }
        for(int i = 0 ; i < data.length; i++) {
            Command now = new Command(data[i]);
            rule.get(now.target1).add(now);
            rule.get(now.target2).add(now);
        }
        
        rec(0, new char[elements.length]);
        
        return answer;
    }
    
    void rec(int depth, char[] line) {
        if(depth == elements.length) {
            answer++;
            return;
        }
        
        for(int i = 0; i < line.length; i++) {
            if(visited[i]) continue;
            
            line[depth] = elements[i];
            if(!isFail(elements[i], line)) {
                visited[i] = true;
                rec(depth + 1, line);   
            }
            line[depth] = 0;
            visited[i] = false;
        }
    }
    
    boolean isFail(char selected, char[] line) {
        boolean fail = false;
        for(Command c : rule.get(selected)) {
            if(c.vertify(line)) continue;
            fail = true;
        }
        return fail;
    }
    
    static class Command {
        private char target1, target2;
        private char command;
        private int range;
        
        public Command(String rawCommand) {
            target1 = rawCommand.charAt(0);
            target2 = rawCommand.charAt(2);
            command = rawCommand.charAt(3);
            range = Integer.parseInt(String.valueOf(rawCommand.charAt(4)));
        }
        
        public boolean vertify(char[] line) {
            int t1Idx = -1, t2Idx = -1;
            for(int i = 0; i < line.length; i++) {
                if(line[i] == target1)
                    t1Idx = i;
                else if(line[i] == target2)
                    t2Idx = i;
            }
            
            if(t1Idx == -1 || t2Idx == -1)
                return true;
            
            switch(command) {
                case '<':
                    return vertifyUnder(t1Idx, t2Idx);
                case '>':
                    return vertifyOver(t1Idx, t2Idx);
                case '=':
                    return vertifyEq(t1Idx, t2Idx);
            }
            return false;
        }
        
        boolean vertifyUnder(int t1Idx, int t2Idx) {
            return Math.abs(t1Idx - t2Idx) - 1 < range;
        }
        
        boolean vertifyOver(int t1Idx, int t2Idx) {
            return Math.abs(t1Idx - t2Idx) - 1 > range;
        }
        
        boolean vertifyEq(int t1Idx, int t2Idx) {
            return Math.abs(t1Idx - t2Idx) - 1 == range;
        }
    }
}