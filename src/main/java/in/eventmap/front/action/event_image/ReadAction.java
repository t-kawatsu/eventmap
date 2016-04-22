package in.eventmap.front.action.event_image;

import in.eventmap.common.entity.EventImageSize;
import in.eventmap.common.entity.User;
import in.eventmap.common.service.EventImageService;
import in.eventmap.front.action.AbstractAction;

import java.io.File;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class ReadAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private EventImageService eventImageService;

	private String fileId;
	private String fileSize;
	private InputStream inputStream;

	@Action(value = "event-image/read-tmp/{fileId}/{fileSize}", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "image/jpeg" }) })
	@Override
	public String execute() throws Exception {
		User user = getCurrentUser();
		EventImageSize is = EventImageSize.nameOf(fileSize);
		File file = eventImageService
				.buildTmpImageFile(fileId, is, user.getId());
		inputStream = FileUtils.openInputStream(file);
		return SUCCESS;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}