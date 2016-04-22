package in.eventmap.admin.action.index;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.admin.action.AbstractAction;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "", results = { @Result(name = "success", location = "index/index.ftl") })
	public String execute() throws Exception {
		return SUCCESS;
	}

}
