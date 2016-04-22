package in.eventmap.common.dao;

import in.eventmap.common.entity.Event;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EventDao extends AbstractDao<Event> {

}
