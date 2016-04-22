package in.eventmap.front.action.event;

import in.eventmap.front.action.AbstractAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class UpdateAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Action(value = "event/update/{id}", results = {
			@Result(name = "input", location = "event/update.ftl"),
			@Result(name = "success", location = "event/read/${id}", type = "redirect") })
	public String createAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		return SUCCESS;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
