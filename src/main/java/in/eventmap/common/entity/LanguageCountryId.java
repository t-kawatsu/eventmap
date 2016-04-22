package in.eventmap.common.entity;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LanguageCountryId extends AbstractEmbeddable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String countryCode;
	private Locale languageCode;

	@Column(name = "country_code")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "language_code")
	public Locale getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(Locale languageCode) {
		this.languageCode = languageCode;
	}
}
