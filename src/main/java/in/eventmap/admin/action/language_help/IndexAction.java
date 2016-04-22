package in.eventmap.admin.action.language_help;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.LanguageHelpDao;
import in.eventmap.common.entity.LanguageHelp;
import in.eventmap.common.util.DbSelectPaginator;
import in.eventmap.common.util.Paginator;
import in.eventmap.common.util.SimplePager;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@javax.annotation.Resource
	private LanguageHelpDao languageHelpDao;
	private Integer page;
	private SimplePager<LanguageHelp> pager;

	@Action(value = "language-help/index", results = { @Result(name = "success", location = "language-help/index.ftl") })
	public String execute() throws Exception {
		Paginator<LanguageHelp> p = new DbSelectPaginator<LanguageHelp>(
				languageHelpDao);
		p.setLimit(DISP_ITEMS_NUM_PAR_PAGE);
		p.setPage(page);
		pager = p.paginate();
		return SUCCESS;
	}

	public SimplePager<LanguageHelp> getPager() {
		return pager;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
