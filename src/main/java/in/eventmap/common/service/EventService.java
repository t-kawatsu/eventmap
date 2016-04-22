package in.eventmap.common.service;

import in.eventmap.common.dao.EventDao;
import in.eventmap.common.dao.LanguageSubdivisionDao;
import in.eventmap.common.dao.PlaceDao;
import in.eventmap.common.entity.Event;
import in.eventmap.common.entity.Place;
import in.eventmap.common.entity.User;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderResult;

//@Scope("prototype")
@Service
public class EventService {
	
	@Resource
	private EventDao eventDao;
	@Resource
	private PlaceDao placeDao;
	@Resource
	private LanguageSubdivisionDao languageSubdivisionDao;
	@Resource
	private GeocoderClient geocoderClient;

	@Transactional
	public void create(Event event, List<ImageMeta> imageMetas, User user) {
		Place place = convertEventPlaceByAddress(event.getPlace().getName());
		Place _place = placeDao.findByLatLng(place.getLat(), place.getLng());
		if(_place != null) {
			event.setPlace(_place);
			event.setPlaceId(_place.getId());
		} else {
			event.setPlace(place);
			event.setPlaceId(place.getId());
			placeDao.save(place);
		}
		
		event.setUserId(user.getId());
		eventDao.save(event);
		
		EventImageService eventImageService = getEventImageService();
		try {
			eventImageService.create(event, user, imageMetas);
		} catch(Exception e) {
			
		}
	}
	
	public Place convertEventPlaceByAddress(String address) {
		GeocoderResult geoResult = geocoderClient.geoFromAddress(address);
		if(geoResult == null) {
			return null;
		}
		
		Place place = new Place();
		
		// name
		place.setName(address);
		
		// latlng
		place.setLat(geoResult.getGeometry().getLocation().getLat());
		place.setLng(geoResult.getGeometry().getLocation().getLng());
		
		// address
		place.setAddress(geoResult.getFormattedAddress());
		
		// country
		List<GeocoderAddressComponent> aComponents = geoResult.getAddressComponents();
		Collections.reverse(aComponents);
		for(GeocoderAddressComponent row : aComponents) {
			String type = row.getTypes().get(0);
			if(type.equals("country")) {
				place.setCountryCode(row.getShortName());
				place.setCountry(row.getLongName());
			} else if(type.equals("postal_code")) {
				place.setPostalCode(row.getLongName());
			} else if(type.equals("administrative_area_level_1")) {
				place.setArea1(row.getLongName());
			} else if(type.equals("locality")) {
				place.setArea2(row.getLongName());
			}
		}
		
		return place;
	}
	
	public EventImageService getEventImageService() {
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext
						.getServletContext());
		return (EventImageService) context.getBean("eventImageService");
	}
}
