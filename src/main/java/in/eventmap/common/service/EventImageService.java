package in.eventmap.common.service;

import in.eventmap.common.dao.EventDao;
import in.eventmap.common.dao.EventImageDao;
import in.eventmap.common.entity.Event;
import in.eventmap.common.entity.EventImage;
import in.eventmap.common.entity.EventImageSize;
import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.entity.ImageType;
import in.eventmap.common.entity.Status;
import in.eventmap.common.entity.User;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service
public class EventImageService extends ImageService {

	@Resource
	protected EventDao eventDao;
	@Resource
	protected EventImageDao eventImageDao;

	protected Integer processCreateImage(String fileId, Integer userId, Integer eventId)
			throws Exception {
		EventImage image = new EventImage();
		image.setEventId(eventId);
		image.setPath(fileId);
		image.setType(ImageType.IMAGE);
		image.setUserId(userId);
		image.setLikeCnt(0);
		image.setCommentCnt(0);
		image.setStatus(Status.LIVE);
		Integer imageId = (Integer) eventImageDao.save(image);

		try {
			getImageHelper().substantiateTmpImage(fileId, userId, eventId, imageId);
		} catch (Exception e) {
			eventImageDao.delete(image);
			throw e;
		}
		return imageId;
	}

	protected void processDeleteImage(Event event, Integer eventImageId) {
		if (eventImageId == null) {
			return;
		}
		EventImage eventImage = eventImageDao.findById(eventImageId);
		if (eventImage == null) {
			return;
		}
		if (!eventImage.getEventId().equals(event.getId())) {
			return;
		}
		eventImageDao.delete(eventImage);
		for (ImageSize is : getImageSizes()) {
			getImageHelper().buildFile(eventImageId, is, event.getId()).delete();
		}
	}
	
	@Override
	protected ImageSize[] getImageSizes() {
		return EventImageSize.values();
	}

	@Override
	protected String getImageCategory() {
		return "event";
	}

	@Transactional
	public Event deleteImage(Event event, Integer eventImageId) {
		processDeleteImage(event, eventImageId);
		if (eventImageId.equals(event.getEventImageId())) {
			event.setEventImageId(null);
			eventDao.update(event);
		}
		return event;
	}

	@Transactional
	public User createFromUrl(Event event, User user, URL url) throws Exception {
		String fileId = getImageHelper().storeTmpImageFromUrl(url, user.getId());
		Integer imageId = processCreateImage(fileId, user.getId(), event.getId());
		event.setEventImageId(imageId);
		eventDao.update(event);
		return user;
	}

	@Transactional
	public Event create(Event event, User user, List<ImageMeta> imageMetas) throws Exception {
		if (imageMetas == null || 0 == imageMetas.size()) {
			return event;
		}
		Integer imageId = null;
		for (ImageMeta im : imageMetas) {
			imageId = processCreateImage(im.getId(), user.getId(), event.getId());
		}
		event.setEventImageId(imageId);
		eventDao.update(event);
		return event;
	}

	@Override
	public String getImageURLPath(Integer imageId, ImageSize is, Integer id) {
		return getImageHelper().buildFileURLPath(imageId, is, id);
	}
	
	public Map<String, Object> formatTmpCreatedData(String fileId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("fileId", fileId);
		ret.put("type", ImageType.IMAGE);
		ret.put("vendorData", null);
		Map<String, Object> srcMap = new HashMap<String, Object>();
		for (ImageSize is : getImageSizes()) {
			srcMap.put(is.getName(), "/event-image/read-tmp/" + fileId
					+ "/" + is.getName());
		}
		ret.put("src", srcMap);
		return ret;
	}

}
