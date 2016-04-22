package in.eventmap.common.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserFriendId extends AbstractEmbeddable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private Integer friendUserId;
	
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "friend_user_id")
	public Integer getFriendUserId() {
		return friendUserId;
	}
	public void setFriendUserId(Integer friendUserId) {
		this.friendUserId = friendUserId;
	}
	
}
