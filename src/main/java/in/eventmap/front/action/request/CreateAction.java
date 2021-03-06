package in.eventmap.front.action.request;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.common.dao.RequestDao;
import in.eventmap.common.entity.Request;
import in.eventmap.common.service.MailService;
import in.eventmap.front.action.AbstractAction;
import in.eventmap.front.form.RequestForm;

public class CreateAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private RequestDao requestDao;
	@Resource
	private RequestForm requestForm;
	@Resource
	private MailService mailService;

	@Action(value = "request/create-ajax", results = {
			@Result(name = "input", location = "request/create.ftl"),
			@Result(name = "success", location = "request/create-complete.ftl") })
	@Override
	public String execute() throws Exception {
		if (!"POST".equals(request.getMethod())) {
			return INPUT;
		}
		if (!requestForm.validate(this)) {
			return INPUT;
		}

		// save
		Request req = new Request();
		BeanUtils.copyProperties(req, requestForm);
		req.setUseragent(request.getHeader("User-Agent"));
		if (getIsLogined()) {
			req.setUserId(getCurrentUser().getId());
		}
		requestDao.save(req);

		// send mail
		try {
			mailService.sendRequestToSupport(getText("app.mail.support"), req);
		} catch (Exception e) {
		}

		return SUCCESS;
	}

	public RequestForm getRequestForm() {
		return requestForm;
	}

	public void setRequestForm(RequestForm requestForm) {
		this.requestForm = requestForm;
	}
}
