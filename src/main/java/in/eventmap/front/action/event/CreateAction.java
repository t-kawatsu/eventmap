package in.eventmap.front.action.event;

import java.util.Date;

import in.eventmap.common.dao.EventDao;
import in.eventmap.common.entity.Event;
import in.eventmap.common.service.EventService;
import in.eventmap.front.action.AbstractAction;
import in.eventmap.front.form.EventForm;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class CreateAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private EventForm eventForm;
	@Resource
	private EventService eventService;
	@Resource
	private EventDao eventDao;

	private Event event;
	private Integer id;
	private Date date = new Date();

	@Action(value = "event/create", results = {
			@Result(name = "input", location = "event/create.ftl"),
			@Result(name = "success", location = "event/create-complete/${id}", type = "redirect") })
	public String createAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}

		if (!"POST".equals(request.getMethod())) {
			return INPUT;
		}
		if (!eventForm.validate(this)) {
			return INPUT;
		}

		// create
		eventService.create(
				eventForm.buildEntity(), eventForm.getImageMetas(), getCurrentUser());
		
		return SUCCESS;
	}
	
	@Action(value = "event/create-complete/${id}", results = {
			@Result(name = "success", location = "event/create-complete.ftl") })
	public String completeAction() throws Exception {
		event = eventDao.findById(id);
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setEventForm(EventForm eventForm) {
		this.eventForm = eventForm;
	}

	public EventForm getEventForm() {
		return eventForm;
	}

	public Event getEvent() {
		return event;
	}
}
