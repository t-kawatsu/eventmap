package in.eventmap.front.action.index;

import in.eventmap.front.action.AbstractAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "", results = { @Result(name = "success", location = "index/index.ftl") })
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
