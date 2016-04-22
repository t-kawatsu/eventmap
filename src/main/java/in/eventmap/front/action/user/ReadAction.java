package in.eventmap.front.action.user;

import in.eventmap.common.entity.UserFriend;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

public class ReadAction extends AbstractReadAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<UserFriend> userFriends;

	@Action(value = "user/mypage", results = { @Result(name = "success", location = "/user/read/${id}", type = "redirect") })
	public String mypageAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		id = getCurrentUser().getId();
		return SUCCESS;
	}

	@Actions({
			@Action(value = "user/read/{id}/{contents}", results = {
					@Result(name = "success", location = "user/read.ftl"),
					@Result(name = "pjax", location = "user/_u-sub-contents.ftl") }),
			@Action(value = "user/read/{id}", results = {
					@Result(name = "success", location = "user/read.ftl"),
					@Result(name = "pjax", location = "user/_u-sub-contents.ftl") }) })
	@Override
	public String execute() throws Exception {
		String ret = beforeReadProcess(id);
		if (!SUCCESS.equals(ret)) {
			return ret;
		}
		
		switch (getContents()) {
			case PROFILE:
				break;
				
			case ACTIVITY:
				break;
				
			case FRIENDS:
				userFriends = userFriendService.getFriends(user.getId());
				break;
				
			default:
		}
		
		return isPjax() ? PJAX : SUCCESS;
	}

	@Action(value = "user/read-ajax/{id}", results = { @Result(name = "success", location = "user/read-ajax.ftl") })
	public String readAjaxAction() throws Exception {
		user = userDao.findById(id);
		return SUCCESS;
	}

	public List<UserFriend> getUserFriends() {
		return userFriends;
	}
}
