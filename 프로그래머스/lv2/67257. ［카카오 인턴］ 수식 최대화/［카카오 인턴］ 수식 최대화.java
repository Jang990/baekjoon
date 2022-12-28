import java.util.ArrayList;
import java.util.StringTokenizer;
class Solution {
    ArrayList<String> prioritys = new ArrayList<>();
	boolean[] visited;
	String exp;
	public long solution(String expression) {
        long answer = -1;
        exp = "";
        if(expression.contains("+")) {
        	exp += "+";
        }
        if(expression.contains("-")) {
        	exp += "-";
        }
        if(expression.contains("*")) {
        	exp += "*";
        }
        visited = new boolean[exp.length()];
        createExps("");
        
        StringTokenizer stNum = new StringTokenizer(expression,"-+*");
        StringTokenizer stExp = new StringTokenizer(expression,"0123456789");
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Character> exps = new ArrayList<>();
        
        while(stNum.hasMoreTokens()) {
        	nums.add(Long.valueOf(stNum.nextToken()));
        }
        while(stExp.hasMoreTokens()) {
        	exps.add(stExp.nextToken().charAt(0));
        }
        
        
        
        for (int i = 0; i < prioritys.size(); i++) {
			answer = Math.max(answer, calc(prioritys.get(i), new ArrayList<Long>(nums), new ArrayList<Character>(exps)));
		}
        
        
        return answer;
    }
	
	private long calc(String priority, ArrayList<Long> numList, ArrayList<Character> expList) {
		int idx = 0;
		while(!expList.isEmpty()) {
			for (int i = 0; i < expList.size(); i++) {
				if(expList.get(i) == priority.charAt(idx)) {
					long result = 0;
					switch (expList.get(i)) {
					case '+':
						result = numList.get(i) + numList.get(i+1); 
						break;
					case '-':
						result = numList.get(i) - numList.get(i+1);
						break;
					case '*':
						result = numList.get(i) * numList.get(i+1);
						break;
					}
					numList.set(i, result);
					numList.remove(i+1);
					expList.remove(i);
					i--;
				}
			}
			idx++;
		}
		
		return Math.abs(numList.get(0));
	}

	private void createExps(String str) {
		if(str.length() == exp.length()) {
			prioritys.add(str);
			return;
		}
		
		for (int i = 0; i < exp.length(); i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			createExps(str+exp.charAt(i));
			visited[i] = false;
		}
	}
}