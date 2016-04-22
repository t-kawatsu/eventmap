package in.eventmap.front.action.help;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import in.eventmap.common.dao.LanguageHelpDao;
import in.eventmap.common.entity.LanguageHelp;
import in.eventmap.front.action.AbstractAction;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private LanguageHelpDao languageHelpDao;

	private List<LanguageHelp> languageHelps;

	@Action(value = "help")
	@Override
	public String execute() throws Exception {
		languageHelps = languageHelpDao.findAll();
		return SUCCESS;
	}

	public List<LanguageHelp> getLanguageHelps() {
		return languageHelps;
	}
}