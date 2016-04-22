package in.eventmap.common.freemarker.template;

import in.eventmap.common.dao.LanguageSubdivisionDao;
import in.eventmap.common.entity.LanguageSubdivision;

import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class LanguageSubdivisionMethod implements TemplateMethodModel {

	private LanguageSubdivisionDao languageSubdivisionDao;

	public TemplateModel exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() < 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		String code = (String) args.get(0);

		LanguageSubdivision lc = null;
		if (args.size() == 2) {
			Locale langbuf = new Locale((String) args.get(1));
			lc = getLanguageSubdivisionDao().findBySubdivisionCode(code,
					langbuf);
		} else {
			lc = getLanguageSubdivisionDao().findBySubdivisionCode(code);
		}
		return new SimpleScalar(lc.getName());
	}

	public LanguageSubdivisionDao getLanguageSubdivisionDao() {
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext
						.getServletContext());
		if (languageSubdivisionDao == null) {
			languageSubdivisionDao = (LanguageSubdivisionDao) context
					.getBean("languageSubdivisionDao");
		}
		return languageSubdivisionDao;
	}
}
