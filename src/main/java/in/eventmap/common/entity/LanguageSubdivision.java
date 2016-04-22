package in.eventmap.common.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "language_subdivisions")
public class LanguageSubdivision extends AbstractCompositeIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LanguageSubdivisionId id;
	private String name;

	@EmbeddedId
	public LanguageSubdivisionId getId() {
		return id;
	}

	public void setId(LanguageSubdivisionId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
