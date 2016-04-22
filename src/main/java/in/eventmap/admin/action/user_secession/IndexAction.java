package in.eventmap.admin.action.user_secession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.UserSecessionDao;
import in.eventmap.common.entity.UserSecession;
import in.eventmap.common.util.DbSelectPaginator;
import in.eventmap.common.util.Paginator;
import in.eventmap.common.util.SimplePager;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@javax.annotation.Resource
	private UserSecessionDao userSecessionDao;
	private Integer page;
	private SimplePager<UserSecession> pager;

	@Action(value = "user-secession/index", results = { @Result(name = "success", location = "user-secession/index.ftl") })
	public String execute() throws Exception {
		Paginator<UserSecession> p = new DbSelectPaginator<UserSecession>(
				userSecessionDao);
		p.setLimit(DISP_ITEMS_NUM_PAR_PAGE);
		p.setPage(page);
		pager = p.paginate();
		return SUCCESS;
	}

	public SimplePager<UserSecession> getPager() {
		return pager;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
