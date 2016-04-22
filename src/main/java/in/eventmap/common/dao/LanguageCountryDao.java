package in.eventmap.common.dao;

import in.eventmap.common.entity.LanguageCountry;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LanguageCountryDao extends AbstractDao<LanguageCountry> {

	public LanguageCountry findByCountryCode(String countryCode) {
		Locale languageCode = new Locale("ja");
		return findByCountryCode(countryCode, languageCode);
	}

	public LanguageCountry findByCountryCode(String countryCode,
			Locale languageCode) {
		if (languageCode == null) {
			return null;
		}
		return (LanguageCountry) getSession()
				.createQuery(
						"FROM LanguageCountry lc WHERE lc.id.countryCode = :countryCode AND lc.id.languageCode = (:languageCode)")
				.setString("countryCode", countryCode)
				.setLocale("languageCode", languageCode).uniqueResult();
	}

	public List<LanguageCountry> findByLanguageCode() {
		Locale languageCode = new Locale("ja");
		return findByLanguageCode(languageCode);
	}

	public List<LanguageCountry> findByLanguageCode(final Locale languageCode) {
		if (languageCode == null) {
			return null;
		}
		return resultList(getSession()
				.createQuery(
						"FROM LanguageCountry lc WHERE lc.id.languageCode = (:languageCode)")
				.setLocale("languageCode", languageCode));
	}

	public Map<String, String> findSelectListByLanguageCode() {
		Locale languageCode = new Locale("ja");
		return findSelectListByLanguageCode(languageCode);
	}

	public Map<String, String> findSelectListByLanguageCode(
			final Locale languageCode) {
		List<LanguageCountry> countries = findByLanguageCode(languageCode);
		if (countries == null) {
			return null;
		}
		Map<String, String> ret = new HashMap<String, String>();
		for (LanguageCountry row : countries) {
			ret.put(row.getId().getCountryCode(), row.getName());
		}
		return ret;
	}

}
