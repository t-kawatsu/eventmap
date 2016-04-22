package in.eventmap.common.freemarker;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.apache.struts2.views.freemarker.ScopesHashModel;

import in.eventmap.common.freemarker.template.AssetsMethod;
import in.eventmap.common.freemarker.template.CamelCase2DashMethod;
import in.eventmap.common.freemarker.template.CutStrMethod;
import in.eventmap.common.freemarker.template.EmotionMethod;
import in.eventmap.common.freemarker.template.FTimeMethod;
import in.eventmap.common.freemarker.template.LanguageCountryMethod;
import in.eventmap.common.freemarker.template.LanguageSettingMethod;
import in.eventmap.common.freemarker.template.LanguageSubdivisionMethod;
import in.eventmap.common.freemarker.template.MaskPasswordMethod;
import in.eventmap.common.freemarker.template.PathInfoMethod;
import in.eventmap.common.freemarker.template.StripTagsMethod;
import in.eventmap.common.freemarker.template.UrlMethod;
import in.eventmap.common.freemarker.template.UserBgImageMethod;
import in.eventmap.common.freemarker.template.UserBgImageSizeMethod;
import in.eventmap.common.freemarker.template.UserImageMethod;
import in.eventmap.common.freemarker.template.UserImageSizeMethod;

import com.opensymphony.xwork2.util.ValueStack;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class CustomFreemarkerManager extends FreemarkerManager {

	@Override
	protected Configuration createConfiguration(ServletContext servletContext)
			throws TemplateException {
		Configuration cfg = super.createConfiguration(servletContext);
		// TODO これつけないと何故かブラウザ毎にdate formatが異なる -> 調査
		cfg.setDateFormat("yyyy/MM/dd HH:mm:ss");
		cfg.setDateTimeFormat("yyyy/MM/dd HH:mm:ss");
		cfg.setNumberFormat("0.######");
		// cfg.setWhitespaceStripping(true);
		/*
		 * //cfg.setDefaultEncoding("UTF-8");
		 * cfg.setSetting(Configuration.OUTPUT_ENCODING_KEY, "UTF-8");
		 * cfg.addAutoImport("my", "/WEB-INF/content/lib/my.ftl");
		 */
		return cfg;
	}

	/*
	 * @Override protected void loadSettings(ServletContext servletContext) {
	 * super.loadSettings(servletContext); }
	 */
	@Override
	public void populateContext(ScopesHashModel model, ValueStack stack,
			Object action, HttpServletRequest request,
			HttpServletResponse response) {
		super.populateContext(model, stack, action, request, response);
		model.put("assets", new AssetsMethod());
		model.put("url", new UrlMethod());
		model.put("pathinfo", new PathInfoMethod());
		model.put("stripTags", new StripTagsMethod());
		model.put("cutStr", new CutStrMethod());
		model.put("emotion", new EmotionMethod());
		model.put("maskPassword", new MaskPasswordMethod());
		model.put("languageSetting", new LanguageSettingMethod());
		model.put("languageCountry", new LanguageCountryMethod());
		model.put("languageSubdivision", new LanguageSubdivisionMethod());
		model.put("fTime", new FTimeMethod());
		model.put("camelCase2dash", new CamelCase2DashMethod());

		model.put("userImageSize", new UserImageSizeMethod());
		model.put("userImageSrc", new UserImageMethod());
		model.put("userBgImageSize", new UserBgImageSizeMethod());
		model.put("userBgImageSrc", new UserBgImageMethod());
	}
}
