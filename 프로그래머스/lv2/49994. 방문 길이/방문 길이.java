import java.util.HashSet;
import java.util.Objects;
class Solution {
    public int solution(String dirs) {
        String[] dirArr = dirs.split("");
        HashSet<Direction> set = new HashSet<>();
        int nowX = 0, nowY = 0, movedX = 0, movedY = 0;
        for (String dir : dirArr) {
        	if(dir.equals("U") && nowY <= 4) {
				movedY = nowY + 1;
			}
			else if(dir.equals("D") && nowY >= -4) {
				movedY = nowY - 1;
			}
			else if(dir.equals("L") && nowX >= -4) {
				movedX = nowX - 1;
			}
			else if(dir.equals("R") && nowX <= 4) {
				movedX = nowX + 1;
			}
			
			if(movedX == nowX && movedY == nowY)
				continue;
			
			set.add(new Direction((double)(nowX+movedX)/2, (double)(nowY+movedY)/2));
			nowX = movedX;
			nowY = movedY;
		}
        
        return set.size();
    }
	
	class Direction {
		double dirX, dirY;

		public Direction(double x, double y) {
			this.dirX = x;
			this.dirY = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Direction))
				return false;
			
			Direction dir = (Direction)obj; 
			return dir.dirX == this.dirX && dir.dirY == this.dirY; 
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.dirX, this.dirY);
		}
	}
}