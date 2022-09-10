import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = arr[0];
		int M = arr[1];
		char[][] photo = new char[N][M];
		for (int i = 0; i < photo.length; i++) {
			String str = sc.nextLine();
			
			for (int j = 0; j < photo[i].length; j++) {
				photo[i][j] = str.charAt(j);
			}
		}
		
		//최단거리 찾기
		int minLength = photo.length;
		for (int j = 0; j < M; j++) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (i+1<N && photo[i][j] == 'X' && photo[i + 1][j] == '.') {
					for(int k=i+1; k<N-1; k++) {
						if(photo[k][j]=='#') break;
						if(photo[k][j]=='X') {
							cnt = 0;
							break;
						}
						if(photo[k][j] =='.') cnt++;
					}
				}
				if(cnt>0 && photo[i][j] == '#') {
					minLength = Math.min(cnt, minLength);
					cnt=0;
				}
			}
		}

		
//		운석이동
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (photo[i][j] == 'X' && photo[i + minLength][j] == '.') {
					char temp = photo[i][j];
					photo[i][j] = photo[i + minLength][j];
					photo[i + minLength][j] = temp;
				}
			}
		}
		
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < photo.length; i++) {
			for (int j = 0; j < photo[i].length; j++) {
				sb.append(photo[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}
