package in.eventmap.front.action.user_friend;

import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.FriendStatus;
import in.eventmap.common.entity.User;
import in.eventmap.common.service.UserFriendService;
import in.eventmap.front.action.AbstractAction;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class CreateAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserFriendService userFriendService;
	@Resource
	private UserDao userDao;
	private Integer userId;
	private FriendStatus friendStatus;
	
	@Action(value="user-friend/create-ajax/{userId}",
		results={
            @Result(name="request", location="user-friend/request-complete.ftl"),
            @Result(name="success", location="user-friend/create-complete.ftl")
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
        
        if(userFriendService.isFriendUser(myUser.getId(), userId)) {
        	friendStatus = FriendStatus.FRIEND;
        	return SUCCESS;
        }
        
        if(userFriendService.isUnderRequest(myUser.getId(), userId)) {
        	friendStatus = FriendStatus.REQUESTED;
        	return "request";
        }
        
        if(userFriendService.isUnderRequested(myUser.getId(), userId)) {
        	friendStatus = FriendStatus.FRIEND;
        	userFriendService.makeFriend(myUser.getId(), userId);
        	return SUCCESS;
        }
        
        userFriendService.request(myUser.getId(), userId);
        friendStatus = FriendStatus.REQUESTED;
        
		return "request";
    }

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public FriendStatus getFriendStatus() {
		return friendStatus;
	}
}