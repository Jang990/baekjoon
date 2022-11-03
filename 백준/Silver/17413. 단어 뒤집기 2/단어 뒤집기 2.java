import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String changedStr = "";
        Stack<Character> st = new Stack<>();
        
        boolean flag = false;
        char c;
        for(int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if(c == '<') {
            	while(!st.isEmpty()) {
            		changedStr += st.pop();	
            	}
                flag = true;
                changedStr += c;
                continue;
            }
            else if(c == '>') {
                flag = false;
                changedStr += c;
                continue;
            }

            if(flag) {
                changedStr += c;
                continue;
            }
            
            if(c != ' ') {
            	st.add(c);
            }
            else {
            	while(!st.isEmpty()) {
            		changedStr += st.pop();	
            	}
            	changedStr += ' ';
            }
        }
        
        while(!st.isEmpty()) {
    		changedStr += st.pop();	
    	}

        System.out.println(changedStr);
        
        br.close();
	}

}