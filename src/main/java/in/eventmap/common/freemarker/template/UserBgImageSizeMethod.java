package in.eventmap.common.freemarker.template;

import java.util.List;

import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.entity.UserBgImageSize;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class UserBgImageSizeMethod implements TemplateMethodModel {

	public TemplateModel exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() < 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		String name = (String) args.get(0);
		ImageSize uis = UserBgImageSize.nameOf(name);

		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		return wrapper.wrap(uis);
	}
}
