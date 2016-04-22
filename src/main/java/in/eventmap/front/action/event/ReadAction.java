package in.eventmap.front.action.event;

import in.eventmap.front.action.AbstractAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class ReadAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Action(value = "event/read/{id}", results = { @Result(name = "success", location = "event/read.ftl") })
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}