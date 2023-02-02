class Solution {
    static String answer = "";
	public static String solution(int[] numbers, String hand) {
        Location left = new Location(3, 0, "L");
        Location right = new Location(3, 2, "R");
        
        int leftDistance, rightDistance;
        for (int num : numbers) {
        	leftDistance = left.calcDistance(num);
        	rightDistance = right.calcDistance(num);
        	
        	switch (num) {
			case 1:
			case 4:
			case 7:
				left.move(num);
				continue;
			case 3:
			case 6:
			case 9:
				right.move(num);
				continue;
			default:
				if(leftDistance == rightDistance) {
					if(hand.equals("left"))
						left.move(num);
					else {
						right.move(num);
					}
				}
				else if(leftDistance < rightDistance)
					left.move(num);
				else {
					right.move(num);
				}
			}
		}
        
        return answer;
    }
	
	static class Location {
		int x, y;
		String hand;

		public Location(int x, int y, String hand) {
			this.x = x;
			this.y = y;
			this.hand = hand;
		}

		public void move(int n) {
			if(n == 0) {
				x = 3;
				y = 1;
			}
			else {
				n -= 1;
				x = n/3;
				y = n%3;
			}
			
			answer += hand;
		}
		
		public int calcDistance(int n) {
			if(n == 0) {
				return Math.abs(this.x-3)+Math.abs(this.y-1);
			}
			
			n -= 1;
			return Math.abs(this.x-n/3)+Math.abs(this.y-n%3);
		}
	}
}