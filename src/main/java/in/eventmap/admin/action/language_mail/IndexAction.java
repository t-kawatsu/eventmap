package in.eventmap.admin.action.language_mail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.LanguageMailDao;
import in.eventmap.common.entity.LanguageMail;
import in.eventmap.common.util.DbSelectPaginator;
import in.eventmap.common.util.Paginator;
import in.eventmap.common.util.SimplePager;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@javax.annotation.Resource
	private LanguageMailDao languageMailDao;
	private Integer page;
	private SimplePager<LanguageMail> pager;

	@Action(value = "language-mail/index", results = { @Result(name = "success", location = "language-mail/index.ftl") })
	public String execute() throws Exception {
		Paginator<LanguageMail> p = new DbSelectPaginator<LanguageMail>(
				languageMailDao);
		p.setLimit(DISP_ITEMS_NUM_PAR_PAGE);
		p.setPage(page);
		pager = p.paginate();
		return SUCCESS;
	}

	public SimplePager<LanguageMail> getPager() {
		return pager;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
