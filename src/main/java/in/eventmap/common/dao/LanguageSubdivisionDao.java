package in.eventmap.common.dao;

import in.eventmap.common.entity.LanguageSubdivision;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LanguageSubdivisionDao extends AbstractDao<LanguageSubdivision> {

	public LanguageSubdivision findBySubdivisionCode(String subdivisionCode) {
		Locale languageCode = new Locale("ja");
		return findBySubdivisionCode(subdivisionCode, languageCode);
	}

	public LanguageSubdivision findBySubdivisionCode(
			final String subdivisionCode, final Locale languageCode) {
		if (languageCode == null || subdivisionCode == null) {
			return null;
		}
		return (LanguageSubdivision) getSession()
				.createQuery(
						"FROM LanguageSubdivision ls WHERE ls.id.subdivisionCode = :subdivisionCode AND ls.id.languageCode = :languageCode")
				.setString("subdivisionCode", subdivisionCode)
				.setLocale("languageCode", languageCode).uniqueResult();
	}

	public List<LanguageSubdivision> findByCountryCodeAndLanguageCode(
			String countryCode) {
		Locale languageCode = new Locale("ja");
		return findByCountryCodeAndLanguageCode(countryCode, languageCode);
	}

	public List<LanguageSubdivision> findByCountryCodeAndLanguageCode(
			final String countryCode, final Locale languageCode) {
		if (languageCode == null || countryCode == null) {
			return null;
		}
		String escapedCountryCode = countryCode.replaceAll("%", "\\\\%")
				.replaceAll("_", "\\\\_");
		return resultList(getSession()
				.createQuery(
						"FROM LanguageSubdivision ls WHERE ls.id.subdivisionCode like :subdivisionCode AND ls.id.languageCode = :languageCode")
				.setString("subdivisionCode", escapedCountryCode + "-%")
				.setLocale("languageCode", languageCode));
	}

	public Map<String, String> findSelectListByCountryCodeAndLanguageCode(
			String subdivisionCode) {
		Locale languageCode = new Locale("ja");
		return findSelectListByCountryCodeAndLanguageCode(subdivisionCode,
				languageCode);
	}

	public Map<String, String> findSelectListByCountryCodeAndLanguageCode(
			final String subdivisionCode, final Locale languageCode) {
		List<LanguageSubdivision> subdivisions = findByCountryCodeAndLanguageCode(
				subdivisionCode, languageCode);
		if (subdivisions == null) {
			return null;
		}
		Map<String, String> ret = new HashMap<String, String>();
		for (LanguageSubdivision row : subdivisions) {
			ret.put(row.getId().getSubdivisionCode(), row.getName());
		}
		return ret;
	}
}
