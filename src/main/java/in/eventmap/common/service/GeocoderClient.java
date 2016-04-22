package in.eventmap.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

@Service
public class GeocoderClient {

	public GeocoderResult geoFromAddress(final String address) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
			.setAddress(address).setLanguage("ja").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		switch(geocoderResponse.getStatus()) {
		case OK:
			break;
		default:
			return null;
		}
		List<GeocoderResult> results = geocoderResponse.getResults();
		if(results == null || results.isEmpty()) {
			return null;
		}
		
		for(GeocoderResult row : results) {
			return row;
		}
		return null;
	}
}
