package in.eventmap.common.interceptor;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import in.eventmap.common.entity.User;
import in.eventmap.common.service.UserService;
import in.eventmap.common.util.SessionManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		/*
		 * http://stackoverflow.com/questions/244882/what-is-the-best-way-to-
		 * implement-remember-me-for-a-website
		 */
		SessionManager sessionManager = getSessionManager();

		// already login
		if (null != sessionManager.getCurrentUser()) {
			return invocation.invoke();
		}

		try {

			User user = userService.reloginIfKeep();
			if (user != null) {
				sessionManager.putCurrentUser(user);
			}

		} catch (Exception e) {
			userService.clearKeepLoginState();
		}

		return invocation.invoke();
	}

	public SessionManager getSessionManager() {
		return new SessionManager(ActionContext.getContext().getSession(),
				ServletActionContext.getRequest().getContextPath(),
				ServletActionContext.getActionMapping().getNamespace(),
				ServletActionContext.getActionMapping().getName());
	}

}
