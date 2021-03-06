package in.eventmap.admin.action.language_setting;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.LanguageSettingDao;
import in.eventmap.common.entity.LanguageSetting;
import in.eventmap.common.util.DbSelectPaginator;
import in.eventmap.common.util.Paginator;
import in.eventmap.common.util.SimplePager;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private LanguageSettingDao languageSettingDao;
	private Integer page;
	private SimplePager<LanguageSetting> pager;

	@Action(value = "language-setting/index", results = { @Result(name = "success", location = "language-setting/index.ftl") })
	public String execute() throws Exception {
		Paginator<LanguageSetting> p = new DbSelectPaginator<LanguageSetting>(
				languageSettingDao);
		p.setLimit(DISP_ITEMS_NUM_PAR_PAGE);
		p.setPage(page);
		pager = p.paginate();
		return SUCCESS;
	}

	public SimplePager<LanguageSetting> getPager() {
		return pager;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
