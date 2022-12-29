import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
class Solution {
    HashMap<String, User> users;
	public String[] solution(String[] record) {
		ArrayList<Object[]> logList = new ArrayList<>();
		users = new HashMap<>();
		for (String logStr : record) {
			String[] info = logStr.split(" ");
			
			if(info[0].equals(User.State.Enter.toString())) {
				User user = users.getOrDefault(info[1], null);
				if(user == null) {
					user = new User(info[1], info[2]);
					users.put(info[1], user);
				}
				else {
					user.changeName(info[2]);
				}
				logList.add(new Object[] {user, "님이 들어왔습니다."});
			}
			else if(info[0].equals(User.State.Leave.toString())) {
				User user = users.get(info[1]);
				user.exit();
				logList.add(new Object[] {user, "님이 나갔습니다."});
			}
			else if(info[0].equals("Change")) {
				users.get(info[1]).changeName(info[2]);
			}
			
		}
		
		String[] answer = new String[logList.size()];
		for (int i = 0; i < answer.length; i++) {
			Object[] log = logList.get(i);
			answer[i] = ((User)log[0]).nickname + ((String)log[1]);
		}
		
        return answer;
    }
	
	static class User {
		enum State {
			Enter, Leave
		}
		
		State state;
		String id;
		String nickname;
		
		public User(String id, String nickname) {
			this.id = id;
			this.nickname = nickname;
			this.state = State.Enter;
		}
		
		public void exit() {
			this.state = State.Leave;
		}
		
		public void changeName(String nickname) {
			this.nickname = nickname;
			this.state = State.Enter;
		}

	}
}