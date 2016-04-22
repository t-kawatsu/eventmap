package in.eventmap.front.form;

import in.eventmap.common.entity.Event;
import in.eventmap.common.entity.EventCategory;
import in.eventmap.common.entity.EventStatus;
import in.eventmap.common.entity.Place;
import in.eventmap.common.service.GeocoderClient;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.geocoder.model.GeocoderResult;
import com.ibm.icu.util.Calendar;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component
public class EventForm extends AbstractImageContentsForm {

	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "yyyy/MM/dd";
	private static final String TIME_FORMAT = "HH:ss";
	private static final String CATEGORY_SETTING_CODE = "C006";
	private static final String STATUS_SETTING_CODE = "C009";
	
	@Resource
	private GeocoderClient geocoderClient;

	private String name;
	private String description;
	private String address;
	private BigDecimal lat;
	private BigDecimal lng;
	private String startAtDate;
	private String startAtTime;
	private String endAtDate;
	private String endAtTime;
	private Date startAt;
	private Date endAt;
	private EventCategory categoryId;
	private EventStatus status;

	private Map<String, String> categories;
	Map<String, String> statuses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public String getStartAtDate() {
		return startAtDate;
	}

	public void setStartAtDate(String startAtDate) {
		this.startAtDate = startAtDate;
	}

	public String getStartAtTime() {
		return startAtTime;
	}

	public void setStartAtTime(String startAtTime) {
		this.startAtTime = startAtTime;
	}

	public String getEndAtDate() {
		return endAtDate;
	}

	public void setEndAtDate(String endAtDate) {
		this.endAtDate = endAtDate;
	}

	public String getEndAtTime() {
		return endAtTime;
	}

	public void setEndAtTime(String endAtTime) {
		this.endAtTime = endAtTime;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		if (startAt != null) {
			SimpleDateFormat dsdf = new SimpleDateFormat(DATE_FORMAT);
			SimpleDateFormat tsdf = new SimpleDateFormat(TIME_FORMAT);
			startAtDate = dsdf.format(startAt);
			startAtTime = tsdf.format(startAt);
		}
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		if (endAt != null) {
			SimpleDateFormat dsdf = new SimpleDateFormat(DATE_FORMAT);
			SimpleDateFormat tsdf = new SimpleDateFormat(TIME_FORMAT);
			endAtDate = dsdf.format(endAt);
			endAtTime = tsdf.format(endAt);
		}
		this.endAt = endAt;
	}

	public EventCategory getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(EventCategory categoryId) {
		this.categoryId = categoryId;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

	public Map<String, String> getCategories() {
		if (categories == null) {
			categories = getEnumSelectList(EventCategory.class,
					CATEGORY_SETTING_CODE);
		}
		return categories;
	}
	
	public Map<String, String> getStatuses() {
		if (statuses == null) {
			statuses = getEnumSelectList(EventStatus.class, STATUS_SETTING_CODE);
			statuses.remove(EventStatus.DELETED.name());
		}
		return statuses;
	}

	public boolean validate(ActionSupport as) {

		// title
		if (StringUtils.isEmpty(getName())) {
			
		} else if (300 < getName().length()) {
			as.addFieldError(
					"eventForm.name",
					as.getText("invalidate.maxLength", null,
							Arrays.asList("300")));
		}

		// description
		if (StringUtils.isEmpty(getDescription())) {
			as.addFieldError("eventForm.description",
					as.getText("invalidate.required"));
		} else if (1500 < getDescription().length()) {
			as.addFieldError(
					"eventForm.description",
					as.getText("invalidate.maxLength", null,
							Arrays.asList("1500")));
		}

		// place
		if (!StringUtils.isEmpty(getAddress())
				&& 1500 < getAddress().length()) {
			as.addFieldError(
					"eventForm.place",
					as.getText("invalidate.maxLength", null,
							Arrays.asList("1500")));
		}

		// startAt
		if (StringUtils.isEmpty(getStartAtDate())
				|| StringUtils.isEmpty(getStartAtTime())) {
			as.addFieldError("eventForm.startAt",
					as.getText("invalidate.required"));
		} else {
			try {
				DateUtils.parseDate(getStartAtDate(), DATE_FORMAT);
				DateUtils.parseDate(getStartAtTime(), TIME_FORMAT);
				startAt = DateUtils.parseDate(getStartAtDate() + " "
						+ getStartAtTime(), DATE_FORMAT + " " + TIME_FORMAT);
			} catch (Exception e) {
				as.addFieldError("eventForm.startAt",
						as.getText("invalidate.valueForm"));
			}
		}

		// endAt
		if (!StringUtils.isEmpty(getEndAtDate())) {
			if (StringUtils.isEmpty(getStartAtTime())) {
				as.addFieldError("eventForm.startAt",
						as.getText("invalidate.required"));
			} else {
				try {
					DateUtils.parseDate(getEndAtDate(), DATE_FORMAT);
					DateUtils.parseDate(getEndAtTime(), TIME_FORMAT);
					endAt = DateUtils.parseDate(getEndAtDate() + " "
							+ getEndAtTime(), DATE_FORMAT + " " + TIME_FORMAT);
				} catch (Exception e) {
					as.addFieldError("eventForm.startAt",
							as.getText("invalidate.valueForm"));
				}
			}
		}
		
		if(StringUtils.isEmpty(getAddress())) {
			as.addFieldError("eventForm.address", as.getText("invalidate.required"));
		} else {
			GeocoderResult gret = geocoderClient.geoFromAddress(getAddress());
			if(gret == null) {
				as.addFieldError("eventForm.address", as.getText("invalidate.place"));
			}
		}

		if (as.hasFieldErrors()) {
			return false;
		}

		// valid date ??
		if (endAt != null) {
			Date now = DateUtils.truncate(new Date(), Calendar.DATE);
			if (endAt.getTime() < now.getTime()) {
			} else if (endAt.getTime() < startAt.getTime()) {
				as.addFieldError("eventForm.startAt",
						as.getText("invalidate.start2endDate"));
			}
		}

		return !as.hasFieldErrors();
	}

	public Event buildEntity() throws Exception {
		Event entity = new Event();
		Date defaultValue = null;
		ConvertUtils.register(new DateConverter(defaultValue), Date.class);
		BeanUtils.copyProperties(entity, this);
		Place place = new Place();
		place.setAddress(getAddress());
		place.setName(getAddress());
		entity.setPlace(place);
		return entity;
	}
}
