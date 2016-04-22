package in.eventmap.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.entity.UserFriend;

@Repository
@Transactional
public class UserFriendDao extends AbstractDao<UserFriend> {
	
	public List<UserFriend> findByUserId(Integer userId) {
		if (userId == null) {
			return null;
		}
		return resultList(getSession()
				.createQuery(
						"FROM UserFriend uf WHERE uf.id.userId = :userId")
				.setInteger("userId", userId));
	}

	public UserFriend findById(Integer userId, Integer friendUserId) {
		if (userId == null || friendUserId == null) {
			return null;
		}
		return (UserFriend) getSession()
				.createQuery(
						"FROM UserFriend uf WHERE uf.id.userId = :userId AND uf.id.friendUserId = :friendUserId")
				.setInteger("userId", userId)
				.setInteger("friendUserId", friendUserId).uniqueResult();
	}

	public boolean isFriendUser(Integer userId, Integer friendUserId) {
		if (userId == null || friendUserId == null) {
			return false;
		}
		int c = detectCountValue(getSession()
				.createQuery(
						"SELECT COUNT(*) FROM UserFriend uf WHERE uf.id.userId = :userId AND uf.id.friendUserId = :friendUserId")
				.setInteger("userId", userId)
				.setInteger("friendUserId", friendUserId));
		return 0 < c;
	}
}
