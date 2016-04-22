package in.eventmap.common.service;

import java.util.List;

import in.eventmap.common.dao.UserFriendDao;
import in.eventmap.common.dao.UserFriendRequestDao;
import in.eventmap.common.entity.FriendStatus;
import in.eventmap.common.entity.UserFriend;
import in.eventmap.common.entity.UserFriendId;
import in.eventmap.common.entity.UserFriendRequest;
import in.eventmap.common.entity.UserFriendRequestId;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFriendService {

	@Resource
	private UserFriendDao userFriendDao;
	@Resource
	private UserFriendRequestDao userFriendRequestDao;
	
	public List<UserFriend> getFriends(Integer userId) {
		return userFriendDao.findByUserId(userId);
	}
	
	public boolean isFriendUser(Integer fromUserId, Integer toUserId) {
		return userFriendDao.isFriendUser(fromUserId, toUserId);
	}
	
	public boolean isUnderRequest(Integer fromUserId, Integer toUserId) {
		return userFriendRequestDao.isUnderRequest(fromUserId, toUserId);
	}
	
	public boolean isUnderRequested(Integer fromUserId, Integer toUserId) {
		return userFriendRequestDao.isUnderRequest(toUserId, fromUserId);
	}
	
	public void cancelRequest(Integer fromUserId, Integer toUserId) {
		UserFriendRequest row = userFriendRequestDao.findById(fromUserId, toUserId);
		userFriendRequestDao.delete(row);
	}
	
	public UserFriendRequest request(Integer fromUserId, Integer toUserId) {
		UserFriendRequest row = new UserFriendRequest();
		UserFriendRequestId id = new UserFriendRequestId();
		id.setFromUserId(fromUserId);
		id.setToUserId(toUserId);
		row.setId(id);
		userFriendRequestDao.save(row);
		return row;
	}
	
	@Transactional
	public void deleteFriend(Integer fromUserId, Integer toUserId) {
		
		UserFriend row = userFriendDao.findById(fromUserId, toUserId);
		userFriendDao.delete(row);
		
		UserFriend row2 = userFriendDao.findById(toUserId, fromUserId);
		userFriendDao.delete(row2);
	}
	
	@Transactional
	public UserFriend makeFriend(Integer fromUserId, Integer toUserId) {
		
		UserFriend row = new UserFriend();
		UserFriendId id = new UserFriendId();
		id.setUserId(fromUserId);
		id.setUserId(toUserId);
		row.setId(id);
		userFriendDao.save(row);
		
		UserFriend row2 = new UserFriend();
		UserFriendId id2 = new UserFriendId();
		id2.setUserId(toUserId);
		id2.setFriendUserId(fromUserId);
		row2.setId(id2);
		userFriendDao.save(row2);
		
		UserFriendRequest req = userFriendRequestDao.findById(fromUserId, toUserId);
		if(req != null) {
			userFriendRequestDao.delete(req);
		}
		
		UserFriendRequest req2 = userFriendRequestDao.findById(toUserId, fromUserId);
		if(req2 != null) {
			userFriendRequestDao.delete(req2);
		}
		
		return row;
	}
	
	public FriendStatus detectFriendStatus(Integer myUserId, Integer userId) {
		if(userFriendDao.isFriendUser(myUserId, userId)) {
			return FriendStatus.FRIEND;
		}
		if(userFriendRequestDao.isUnderRequest(myUserId, userId)) {
			return FriendStatus.REQUESTED;
		}
		return FriendStatus.NONE;
	}
}
