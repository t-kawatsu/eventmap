package in.eventmap.front.action.user_friend;

import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.FriendStatus;
import in.eventmap.common.entity.User;
import in.eventmap.common.service.UserFriendService;
import in.eventmap.front.action.AbstractAction;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserFriendService userFriendService;
	@Resource
	private UserDao userDao;
	private Integer userId;
	
	@Action(value="user-friend/delete-ajax/{userId}",
		results={
            @Result(name="request", location="user-friend/request-cancel.ftl"),
            @Result(name="success", location="user-friend/delete-complete.ftl")
		}
	)
	@Override
    public String execute() throws Exception {
		
		if(!getIsLogined()) {
			return LOGIN;
		}
		
        User myUser = getCurrentUser();
        if(myUser.equals(userId)) {
        	return ERROR;
        }
        
        User user = userDao.findById(userId);
        if(user == null) {
        	return ERROR;
        }
        
        if(userFriendService.isUnderRequest(myUser.getId(), userId)) {
        	userFriendService.cancelRequest(myUser.getId(), userId);
        	return "request";
        }
        
        if(userFriendService.isFriendUser(myUser.getId(), userId)) {
        	userFriendService.deleteFriend(myUser.getId(), userId);
        	return SUCCESS;
        }
        
        return SUCCESS;
    }

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public FriendStatus getFriendStatus() {
		return FriendStatus.NONE;
	}
}