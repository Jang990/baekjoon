import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static Egg[] eggs;
	static int Max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.valueOf(br.readLine());
		
		eggs = new Egg[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()));
		}
		
		Rec(0);
		
		if(Max == Integer.MIN_VALUE) 
			System.out.println(0);
		
		else
			System.out.println(Max);
		
		br.close();
	}
	
	private static void Rec(int idx) {
		if(idx == n) {
			int cnt = 0;
			for (Egg egg : eggs) {
				if(egg.isBroken()) cnt++;
			}
			Max = Math.max(Max, cnt);
			return;
		}
		
		// 손에 든 계란을 제외하고 모두 깨졌을 때
		int otherCnt = 0;
		for (int i = 0; i < eggs.length; i++) {
			if(idx == i) continue;
			if(eggs[i].isBroken())
				otherCnt++;
		}
		if(eggs[idx].isBroken() || n-1 == otherCnt) {
			Rec(idx+1);
			return;
		}
		
		for (int i = 0; i < eggs.length; i++) {
			if(idx == i || eggs[i].isBroken()) 
				continue;
			
			eggs[i].s = eggs[i].s - eggs[idx].w;
			eggs[idx].s = eggs[idx].s - eggs[i].w;
			
			Rec(idx+1);
			
			eggs[i].s = eggs[i].s + eggs[idx].w;
			eggs[idx].s = eggs[idx].s + eggs[i].w;
		}
	}

	static class Egg{
		int s,w;

		public Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
		
		public boolean isBroken() {
			if(s <= 0)
				return true;
			
			return false;
		}
	}

}