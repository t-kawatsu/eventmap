package in.eventmap.common.entity;

public enum UserContentsCategory {
	UNDEFINED("undefined"),
	PROFILE("profile"),
	ACTIVITY("activity"),
	FRIENDS("friends"),
	EVENT("event"),
	MESSAGE("message");

	private String name;

	private UserContentsCategory(String name) {
		this.name = name;
	}

	public static UserContentsCategory nameOf(String name) {
		for (UserContentsCategory is : values()) {
			if (is.getName().equals(name)) {
				return is;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}
}
