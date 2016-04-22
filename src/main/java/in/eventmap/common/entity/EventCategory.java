package in.eventmap.common.entity;

public enum EventCategory {
	UNDEFINED("undefined"), MOVIE("movie"), BOOK("book"), MUSIC("music"), GAME(
			"game"), GOURMET("gourmet"), HOBBY("hobby"), PLACE("place"), SPORTS(
			"sports"), ART("art"), GOODS("goods"), GROUP("group"), PERSON(
			"person"), PET("pet"), STUDY("study"), TV("tv"), BRAND("brand"), OTHER(
			"other");

	private String name;

	private EventCategory(String name) {
		this.name = name;
	}

	public static EventCategory nameOf(String name) {
		for (EventCategory is : values()) {
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
