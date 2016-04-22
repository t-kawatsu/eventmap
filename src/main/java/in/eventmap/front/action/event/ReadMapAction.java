package in.eventmap.front.action.event;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.common.dao.EventDao;
import in.eventmap.common.entity.Event;
import in.eventmap.front.action.AbstractAction;

public class ReadMapAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private EventDao eventDao;

	private Integer id;
	private Event event;

	@Action(value = "event/read-map-ajax/{id}", results = { @Result(name = "success", location = "event/read-map.ftl") })
	@Override
	public String execute() throws Exception {
		event = eventDao.findById(id);
		if (event == null || event.getPlace().getLat() == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}
}