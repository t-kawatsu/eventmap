package in.eventmap.admin.action.language_mail;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;
import in.eventmap.common.dao.LanguageMailDao;
import in.eventmap.common.entity.LanguageMail;
import in.eventmap.common.entity.LanguageMailId;

public class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private LanguageMailDao languageMailDao;

	private LanguageMailId id;

	@Action(value = "language-mail/delete", results = {
			@Result(name = "input", location = "language-mail/delete.ftl"),
			@Result(name = "success", location = "language-mail/index", type = "redirect") })
	public String execute() throws Exception {
		LanguageMail languageMail = languageMailDao.findByCode(id.getCode());
		if (languageMail == null) {
			throw new Exception();
		}
		languageMailDao.delete(languageMail);

		return SUCCESS;
	}

	public void setId(LanguageMailId id) {
		this.id = id;
	}

	public LanguageMailId getId() {
		return id;
	}

	public boolean getIsUpdate() {
		return true;
	}
}
