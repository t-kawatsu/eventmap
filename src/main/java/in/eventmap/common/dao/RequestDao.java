package in.eventmap.common.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.Request;

@Repository
@Transactional
public class RequestDao extends AbstractDao<Request> {

}
