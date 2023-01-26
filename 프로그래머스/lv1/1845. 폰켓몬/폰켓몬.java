import java.util.HashSet;
import java.util.Set;
class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
        	set.add(nums[i]);
        }
        
        int size = nums.length/2;
        if(size < set.size())
        	return size;
        else
        	return set.size();
    }
}