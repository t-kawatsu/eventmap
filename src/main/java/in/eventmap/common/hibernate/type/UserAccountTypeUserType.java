package in.eventmap.common.hibernate.type;

import in.eventmap.common.entity.UserAccountType;

public class UserAccountTypeUserType extends
		BitEnumSetUserType<UserAccountType> {
	public UserAccountTypeUserType() {
		super(UserAccountType.class);
	}
}
