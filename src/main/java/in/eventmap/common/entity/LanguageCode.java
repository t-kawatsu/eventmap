package in.eventmap.common.entity;

public enum LanguageCode {
	JA("ja"), EN("en");

	private String name;

	private LanguageCode(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
