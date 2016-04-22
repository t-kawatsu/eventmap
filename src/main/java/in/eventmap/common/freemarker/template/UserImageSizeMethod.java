package in.eventmap.common.freemarker.template;

import java.util.List;

import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.entity.UserImageSize;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class UserImageSizeMethod implements TemplateMethodModel {

	public TemplateModel exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() < 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		String name = (String) args.get(0);
		ImageSize uis = UserImageSize.nameOf(name);

		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		return wrapper.wrap(uis);
	}
}
