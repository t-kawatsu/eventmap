package in.eventmap.front.action.user;

import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.FriendStatus;
import in.eventmap.common.entity.User;
import in.eventmap.common.entity.UserContentsCategory;
import in.eventmap.common.service.UserFriendService;
import in.eventmap.common.service.UserService;
import in.eventmap.front.action.AbstractAction;

import javax.annotation.Resource;

public abstract class AbstractReadAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected UserContentsCategory userContentsCategory = UserContentsCategory.ACTIVITY;

	@Resource
	protected UserService userService;
	@Resource
	protected UserFriendService userFriendService;
	@Resource
	protected UserDao userDao;

	protected Integer id;
	protected User user;
	protected boolean isMyPage;
	protected FriendStatus friendStatus;

	protected String beforeReadProcess(Integer id) throws Exception {
		if (id == null) {
			return ERROR;
		}
		if (getIsLogined() && getCurrentUser().getId().equals(id)) {
			// mypage
			user = getCurrentUser();
			isMyPage = true;
		} else {
			user = userDao.findById(id);
			if (user == null) {
				return ERROR;
			}
			friendStatus = userFriendService.detectFriendStatus(
					getCurrentUser().getId(), id);
		}

		if (isPjax()) {
			return SUCCESS;
		}

		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContents(String contents) {
		userContentsCategory = UserContentsCategory.nameOf(contents);
	}

	public UserContentsCategory getContents() {
		return userContentsCategory;
	}

	public User getUser() {
		return user;
	}

	public boolean getIsMyPage() {
		return isMyPage;
	}
	
	public FriendStatus getFriendStatus() {
		return friendStatus;
	}
}
