package Message;

import java.util.ArrayList;
import java.util.List;

import User.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	
	private static final MessageList msgList = new MessageList();

	private final List<Message> list = new ArrayList<Message>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  private MessageList() {}
	
	public synchronized void add(Message m) {
		list.add(m);
	}
	
	public synchronized String toJSON(String login, int n) {
		List<Message> res = new ArrayList<Message>();
		UserList userList = UserList.getInstance();
		User user = userList.getUser(login);

		for (int i = n; i < list.size(); i++)
			if (list.get(i).getTo().equals("all")) {
				Message tmp = list.get(i);
				tmp.setReceivedMsgs(list.size());
				if (tmp.getRoom() == null || tmp.getRoom().equals(user.getRoom())) {
					res.add(tmp);
				}
			} else {
				if (list.get(i).getTo().equals(login) || list.get(i).getFrom().equals(login)) {
					Message tmp = list.get(i);
					tmp.setReceivedMsgs(list.size());
					res.add(tmp);
				}
			}

		if (res.size() > 0) {
			Gson gson = new GsonBuilder().create();
			return gson.toJson(res.toArray());
		} else
			return null;
	}
}
