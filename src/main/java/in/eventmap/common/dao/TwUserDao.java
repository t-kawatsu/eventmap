package in.eventmap.common.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.TwUser;

@Repository
@Transactional
public class TwUserDao extends AbstractDao<TwUser> {

}
