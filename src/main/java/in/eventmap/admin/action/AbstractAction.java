package in.eventmap.admin.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.eventmap.common.entity.AdminUser;
import in.eventmap.common.interceptor.LoginAuthenticationAware;
import in.eventmap.common.interceptor.LoginAuthorizationAware;
import in.eventmap.common.util.SessionManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.Before;

@ParentPackage("base")
@Namespace("/admin")
@InterceptorRefs({ @InterceptorRef("myAdminStack"), })
public abstract class AbstractAction extends ActionSupport implements
		SessionAware, ServletRequestAware, LoginAuthenticationAware,
		LoginAuthorizationAware<AdminUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TOP = "admin-top";
	public static final String LOGIN = "admin-login";
	protected static final int DISP_ITEMS_NUM_PAR_PAGE = 10;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpServletRequest request;
	protected SessionManager sessionManager;
	protected AdminUser user;

	@Before
	public void beforeAction() {
	}

	@Override
	public boolean getIsLogined() {
		return sessionManager.getCurrentUser() != null;
	}

	@Override
	public AdminUser getCurrentUser() {
		return (AdminUser) sessionManager.getCurrentUser();
	}

	@Override
	public void removeCurrentUser() {
		sessionManager.removeCurrentUser();
	}

	@Override
	public void setCurrentUser(AdminUser user) {
		sessionManager.putCurrentUser(user);
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	@Override
	public String getLoginActionName() {
		return LOGIN;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.sessionManager = new SessionManager(session, ServletActionContext
				.getRequest().getContextPath(), ServletActionContext
				.getActionMapping().getNamespace(), ServletActionContext
				.getActionMapping().getName());
	}

}
