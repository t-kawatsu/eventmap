package in.eventmap.front.action.user;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import in.eventmap.front.action.AbstractAction;
import in.eventmap.common.service.UserService;

public class LogoutAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;

	@Action(value = "user/logout")
	@Override
	public String execute() throws Exception {
		userService.logout();
		return TOP;
	}

}
