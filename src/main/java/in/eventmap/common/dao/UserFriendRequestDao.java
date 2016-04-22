package in.eventmap.common.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.UserFriendRequest;

@Repository
@Transactional
public class UserFriendRequestDao extends AbstractDao<UserFriendRequest> {

	public UserFriendRequest findById(Integer fromUserId, Integer toUserId) {
		if (toUserId == null || fromUserId == null) {
			return null;
		}
		return (UserFriendRequest) getSession()
				.createQuery(
						"FROM UserFriendRequest ufr WHERE ufr.id.toUserId = :toUserId AND ufr.id.fromUserId = :fromUserId")
				.setInteger("toUserId", toUserId)
				.setInteger("fromUserId", fromUserId).uniqueResult();
	}

	public boolean isUnderRequest(Integer fromUserId, Integer toUserId) {
		if (fromUserId == null || toUserId == null) {
			return false;
		}
		int c = detectCountValue(getSession()
						.createQuery(
								"SELECT COUNT(*) FROM UserFriendRequest ufr WHERE ufr.id.toUserId = :toUserId AND ufr.id.fromUserId = :fromUserId")
						.setInteger("toUserId", toUserId)
						.setInteger("fromUserId", fromUserId));
		return 0 < c;
	}
}
