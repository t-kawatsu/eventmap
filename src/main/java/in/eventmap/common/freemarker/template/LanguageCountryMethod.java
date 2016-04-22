package in.eventmap.common.freemarker.template;

import in.eventmap.common.dao.LanguageCountryDao;
import in.eventmap.common.entity.LanguageCountry;

import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class LanguageCountryMethod implements TemplateMethodModel {

	private LanguageCountryDao languageCountryDao;

	public TemplateModel exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() < 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		String code = (String) args.get(0);

		LanguageCountry lc = null;
		if (args.size() == 2) {
			Locale langbuf = new Locale((String) args.get(1));
			lc = getLanguageCountryDao().findByCountryCode(code, langbuf);
		} else {
			lc = getLanguageCountryDao().findByCountryCode(code);
		}
		return new SimpleScalar(lc.getName());
	}

	public LanguageCountryDao getLanguageCountryDao() {
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext
						.getServletContext());
		if (languageCountryDao == null) {
			languageCountryDao = (LanguageCountryDao) context
					.getBean("languageCountryDao");
		}
		return languageCountryDao;
	}
}
