package in.eventmap.common.freemarker.template;

import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.entity.UserBgImageSize;
import in.eventmap.common.service.ImageService;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class UserBgImageMethod implements TemplateMethodModel {

	private ImageService imageService;

	public TemplateModel exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		Integer userId = Integer.parseInt((String) args.get(0));
		Integer imageId = Integer.parseInt((String) args.get(1));
		ImageSize is = UserBgImageSize.nameOf((String) args.get(2));
		return new SimpleScalar(getImageService().getImageURLPath(imageId, is,
				userId));
	}

	private ImageService getImageService() {
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext
						.getServletContext());
		if (imageService == null) {
			imageService = (ImageService) context.getBean("userBgImageService");
		}
		return imageService;
	}
}
