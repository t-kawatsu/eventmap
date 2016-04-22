package in.eventmap.common.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.FbUser;

@Repository
@Transactional
public class FbUserDao extends AbstractDao<FbUser> {

}
