package in.eventmap.front.action.about;

import in.eventmap.front.action.AbstractAction;

import org.apache.struts2.convention.annotation.Action;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "about")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
