package in.eventmap.common.entity;

public enum EventImageSize implements ImageSize {
	SS("ss", 126, 95),
    S("s", 174, 130),
    M("m", 256, 192),
    L("l", 512, 384);

	private String name;
	private Integer width;
	private Integer height;

	private EventImageSize(String name, Integer width, Integer height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public static EventImageSize nameOf(String name) {
		for (EventImageSize is : values()) {
			if (is.getName().equals(name)) {
				return is;
			}
		}
		return null;
	}
}
