package in.eventmap.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_friend_requests")
public class UserFriendRequest extends AbstractCompositeIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserFriendRequestId id;
	private Date createdAt;
	private Date updatedAt;
	
	@EmbeddedId
	public UserFriendRequestId getId() {
		return id;
	}
	public void setId(UserFriendRequestId id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
