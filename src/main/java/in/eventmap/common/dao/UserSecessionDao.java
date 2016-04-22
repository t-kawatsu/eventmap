package in.eventmap.common.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.UserSecession;

@Transactional
@Repository
public class UserSecessionDao extends AbstractDao<UserSecession> {

}
