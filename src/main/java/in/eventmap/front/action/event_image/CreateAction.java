package in.eventmap.front.action.event_image;

import in.eventmap.common.entity.User;
import in.eventmap.common.service.EventImageService;
import in.eventmap.front.action.AbstractAction;
import in.eventmap.front.form.ImageForm;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class CreateAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private ImageForm imageForm;
	@Resource
	private EventImageService eventImageService;

	private Map<String, Object> uploadResultJson;

	@Action(value = "event-image/upload-ajax", results = {
			@Result(name = "success", type = "json", params = { "statusCode",
					"200", "contentType", "text/html", "noCache", "true",
					"root", "uploadResultJson" }),
			@Result(name = "login", type = "json", params = { "statusCode",
					"401", "contentType", "text/html", "noCache", "true",
					"root", "uploadResultJson" }),
			@Result(name = "input", type = "json", params = { "statusCode",
					"200", "contentType", "text/html", "noCache", "true",
					"root", "uploadResultJson" }) })
	public String uplodAjaxAction() throws Exception {
		uploadResultJson = new HashMap<String, Object>();
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!imageForm.validate(this)) {
			uploadResultJson.put("messages", getFieldErrors());
			return INPUT;
		}
		User user = getCurrentUser();
		String fileId = eventImageService.storeTmpImage(imageForm.getFile(),
				user.getId());
		uploadResultJson = eventImageService.formatTmpCreatedData(fileId);
		return SUCCESS;
	}

	public ImageForm getImageForm() {
		return imageForm;
	}

	public void setImageForm(ImageForm imageForm) {
		this.imageForm = imageForm;
	}

	public Map<String, Object> getUploadResultJson() {
		return uploadResultJson;
	}
}