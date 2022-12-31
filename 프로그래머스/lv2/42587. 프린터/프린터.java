import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Document> docs = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) {
        	docs.offer(new Document(i, priorities[i]));
		}

        boolean flag;
        int idx = 0;
        while(!docs.isEmpty()) {
        	flag = false;
        	Document out = docs.peek();
        	for (int i = out.getPri()+1; i <= 9; i++) {
				if(docs.stream().map(Document::getPri).collect(Collectors.toList()).contains(i)) {
					flag = true;
					break;
				}
			}
        	
        	out = docs.poll();
        	if(flag) {
        		docs.offer(out);
        		continue;
        	}
        	
        	idx++;
        	if(out.loc == location)
        		break;
        }
        
        return idx;
    }
	
	class Document {
		int loc, pri;

		public Document(int loc, int pri) {
			this.loc = loc;
			this.pri = pri;
		}

		public int getPri() {
			return pri;
		}
	}
}