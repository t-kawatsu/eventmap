package in.eventmap.common.dao;

import in.eventmap.common.entity.EventImage;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EventImageDao extends AbstractDao<EventImage> {

}
