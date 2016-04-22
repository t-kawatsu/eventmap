package in.eventmap.common.dao;

import in.eventmap.common.entity.Place;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PlaceDao extends AbstractDao<Place> {

	public Place findByLatLng(BigDecimal lat, BigDecimal lng) {
		return (Place) getSession()
				.createQuery(
						"FROM Place p WHERE p.lat = :lat AND p.lng = :lng")
				.setBigDecimal("lat", lat)
				.setBigDecimal("lng", lng).uniqueResult();
	}
}
