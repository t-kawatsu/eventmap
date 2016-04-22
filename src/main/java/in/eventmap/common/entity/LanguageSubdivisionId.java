package in.eventmap.common.entity;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LanguageSubdivisionId extends AbstractEmbeddable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subdivisionCode;
	private Locale languageCode;

	@Column(name = "subdivision_code")
	public String getSubdivisionCode() {
		return subdivisionCode;
	}

	public void setSubdivisionCode(String subdivisionCode) {
		this.subdivisionCode = subdivisionCode;
	}

	@Column(name = "language_code")
	public Locale getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(Locale languageCode) {
		this.languageCode = languageCode;
	}
}
