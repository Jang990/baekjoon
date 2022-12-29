import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Queue<String> q = new LinkedList<>();
        for (String skills : skill.split("")) {
			q.add(skills);
		}
        
        
        for (String skill_tree : skill_trees) {
        	Queue<String> cloneQ = new LinkedList<>(q);
        	int idx = 0;
        	while(idx < skill_tree.length()) {
        		String s = String.valueOf(skill_tree.charAt(idx));
        		if(!q.contains(s)) {
        			idx++;
        			continue;
        		}
        		
        		if(s.equals(cloneQ.peek())) {
        			cloneQ.poll();
        		}
        		else {
        			break;
        		}
        		
        		idx++;
        	}
        	
        	if(idx == skill_tree.length())
        		answer++;
		}
        
        return answer;
    }
}