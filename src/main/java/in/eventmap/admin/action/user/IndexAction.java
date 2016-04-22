package in.eventmap.admin.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.User;
import in.eventmap.common.util.DbSelectPaginator;
import in.eventmap.common.util.Paginator;
import in.eventmap.common.util.SimplePager;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@javax.annotation.Resource
	private UserDao userDao;
	private Integer page;
	private SimplePager<User> pager;

	@Action(value = "user/index", results = { @Result(name = "success", location = "user/index.ftl") })
	public String execute() throws Exception {
		Paginator<User> p = new DbSelectPaginator<User>(userDao);
		p.setLimit(DISP_ITEMS_NUM_PAR_PAGE);
		p.setPage(page);
		pager = p.paginate();
		return SUCCESS;
	}

	public SimplePager<User> getPager() {
		return pager;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
