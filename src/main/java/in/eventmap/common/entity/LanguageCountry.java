package in.eventmap.common.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "language_countries")
public class LanguageCountry extends AbstractCompositeIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LanguageCountryId id;
	private String name;

	@EmbeddedId
	public LanguageCountryId getId() {
		return id;
	}

	public void setId(LanguageCountryId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
