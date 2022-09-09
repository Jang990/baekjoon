import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		StringBuffer sb = new StringBuffer();
		
		while(N-- > 0) {
			int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int arrayNum = arr[0]; 
			int degree = (arr[1] / 45) % 8;
			if(degree < 0) degree += 8;
			
			int step = 0; //이동거리
			int idx = 0; //뎁스
			if(arrayNum > 1) step = arrayNum / 2;
			
			int[][] array = new int[arrayNum][arrayNum];
			for (int i = 0; i < array.length; i++) {
				array[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			for (int i = 0; i < degree; i++) {
				int usingStep = step;
				int usingIdx = idx;
				//바꾸기
				while(usingStep != 0) {
					int x = usingIdx, y = usingIdx;
					int rotatedNum = array[y][x];
					int j = 0;
					//x이동
					while(j < 2) { 
						x += usingStep;
						
						int temp = array[y][x];
						array[y][x] = rotatedNum;
						rotatedNum = temp;
						j++;
					}
					
					j = 0;
					//y이동
					while(j < 2) { 
						y += usingStep;
						
						int temp = array[y][x];
						array[y][x] = rotatedNum;
						rotatedNum = temp;
						j++;
					}
					
					j = 0;
					//x이동
					while(j < 2) { 
						x -= usingStep;
						
						int temp = array[y][x];
						array[y][x] = rotatedNum;
						rotatedNum = temp;
						j++;
					}
					
					j = 0;
					//y이동
					while(j < 2) { 
						y -= usingStep;
						
						int temp = array[y][x];
						array[y][x] = rotatedNum;
						rotatedNum = temp;
						j++;
					}
					
					usingIdx++;
					usingStep--;
				}
			}
			
			
			for (int i = 0; i < array.length; i++) {
				Arrays.stream(array[i]).forEach(n -> sb.append(n+" "));
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
		
	}
	
}