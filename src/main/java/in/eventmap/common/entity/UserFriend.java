package in.eventmap.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_friends")
public class UserFriend extends AbstractCompositeIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserFriendId id;
	private Date createdAt;
	
	@EmbeddedId
	public UserFriendId getId() {
		return id;
	}
	public void setId(UserFriendId id) {
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
	
	
}
