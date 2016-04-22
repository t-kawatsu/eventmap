package in.eventmap.common.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.UserImage;

@Repository
@Transactional
public class UserImageDao extends AbstractDao<UserImage> {

}
