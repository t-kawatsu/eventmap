package in.eventmap.admin.action.request;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.RequestDao;
import in.eventmap.common.entity.Request;
import in.eventmap.common.util.DbSelectPaginator;
import in.eventmap.common.util.Paginator;
import in.eventmap.common.util.SimplePager;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private RequestDao requestDao;
	private Integer page;
	private SimplePager<Request> pager;

	@Action(value = "request/index", results = { @Result(name = "success", location = "request/index.ftl") })
	public String execute() throws Exception {
		Paginator<Request> p = new DbSelectPaginator<Request>(requestDao);
		p.setLimit(DISP_ITEMS_NUM_PAR_PAGE);
		p.setPage(page);
		pager = p.paginate();
		return SUCCESS;
	}

	public SimplePager<Request> getPager() {
		return pager;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
