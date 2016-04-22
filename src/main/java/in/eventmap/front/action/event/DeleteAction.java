package in.eventmap.front.action.event;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.common.dao.EventDao;
import in.eventmap.common.entity.Event;
import in.eventmap.front.action.AbstractAction;

public class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private EventDao eventDao;

	private Integer id;
	private Event event;

	@Action(value = "community-event/delete-ajax/{id}", results = { @Result(name = "success", location = "community-event/delete-complete.ftl") })
	@Override
	public String execute() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		event = eventDao.findById(id);
		if (event == null
				|| !event.getUserId().equals(getCurrentUser().getId())) {
			return ERROR;
		}
		eventDao.delete(event);

		return SUCCESS;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Event getEvent() {
		return event;
	}
}