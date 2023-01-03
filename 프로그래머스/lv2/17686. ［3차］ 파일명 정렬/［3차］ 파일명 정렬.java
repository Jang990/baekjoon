import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class Solution {
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
			list.add(new File(i, files[i]));
		}
        
        return list.stream()
        		.sorted(Comparator.comparing(File::getHead)
        				.thenComparing(File::getNumber)
        				.thenComparing(File::getIdx)
        				)
        		.map(File::getFullName).toArray(String[]::new);
    }
	
	class File {
		int idx;
		String fullName;
		String head;
		int number;
		
		public File(int idx, String name) {
			this.idx = idx;
			this.fullName = name;
			
			int startIdx = -1, endIdx = name.length();
			int n;
			for (int i = 0; i < name.length(); i++) {
				n = name.charAt(i) - '0';
				if(0 <= n && n <= 9) {
					startIdx = i;
					break;
				}
			}
			
			for (int i = startIdx+1; i < name.length(); i++) {
				n = name.charAt(i) - '0';
				if(n < 0 || n > 9) {
					endIdx = i;
					break;
				}
			}
			
			
			this.head = name.substring(0, startIdx).toLowerCase();
			this.number = Integer.valueOf(name.substring(startIdx, endIdx));
		}

		public int getIdx() {
			return idx;
		}

		public String getHead() {
			return head;
		}

		public int getNumber() {
			return number;
		}
		
		public String getFullName() {
			return fullName;
		}
	}
}