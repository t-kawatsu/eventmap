package in.eventmap.common.service;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.dao.UserDao;
import in.eventmap.common.dao.UserImageDao;
import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.entity.Status;
import in.eventmap.common.entity.User;
import in.eventmap.common.entity.UserImage;
import in.eventmap.common.entity.UserImageSize;
import in.eventmap.common.entity.UserImageType;

@Scope("prototype")
@Service
public class UserImageService extends ImageService {

	@Resource
	protected UserDao userDao;
	@Resource
	protected UserImageDao userImageDao;

	public Map<String, Object> formatTmpCreatedData(String fileId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("fileId", fileId);
		ret.put("type", getUserImageType());
		ret.put("vendorData", null);
		for (ImageSize is : getImageSizes()) {
			ret.put(is.getName() + "Src", "/user-image/read-tmp/" + fileId
					+ "/" + is.getName());
		}
		return ret;
	}

	protected Integer processCreateImage(String fileId, Integer userId)
			throws Exception {
		UserImage image = new UserImage();
		image.setPath(fileId);
		image.setType(getUserImageType());
		image.setUserId(userId);
		image.setLikeCnt(0);
		image.setStatus(Status.LIVE);
		Integer imageId = (Integer) userImageDao.save(image);

		try {
			getImageHelper().substantiateTmpImage(fileId, userId, userId, imageId);
		} catch (Exception e) {
			userImageDao.delete(image);
			throw e;
		}
		return imageId;
	}

	protected void processDeleteImage(User user, Integer userImageId) {
		if (userImageId == null) {
			return;
		}
		UserImage userImage = userImageDao.findById(userImageId);
		if (userImage == null) {
			return;
		}
		if (!userImage.getUserId().equals(user.getId())) {
			return;
		}
		userImageDao.delete(userImage);
		for (ImageSize is : getImageSizes()) {
			getImageHelper().buildFile(userImageId, is, user.getId()).delete();
		}
	}

	@Transactional
	public User deleteImage(User user, Integer userImageId) {
		processDeleteImage(user, userImageId);
		if (userImageId.equals(user.getUserImageId())) {
			user.setUserImageId(null);
			userDao.update(user);
		}
		return user;
	}

	@Transactional
	public User createFromUrl(User user, URL url) throws Exception {
		String fileId = getImageHelper().storeTmpImageFromUrl(url, user.getId());
		Integer imageId = processCreateImage(fileId, user.getId());
		user.setUserImageId(imageId);
		userDao.update(user);
		return user;
	}

	@Transactional
	public User create(User user, List<ImageMeta> imageMetas) throws Exception {
		if (imageMetas == null || 0 == imageMetas.size()) {
			return user;
		}
		Integer imageId = null;
		for (ImageMeta im : imageMetas) {
			imageId = processCreateImage(im.getId(), user.getId());
		}
		user.setUserImageId(imageId);
		userDao.update(user);
		return user;
	}

	UserImageType getUserImageType() {
		return UserImageType.PERSON;
	}

	@Override
	protected ImageSize[] getImageSizes() {
		return UserImageSize.values();
	}

	@Override
	protected String getImageCategory() {
		return "user";
	}

	@Override
	public String getImageURLPath(Integer imageId, ImageSize is, Integer id) {
		return getImageHelper().buildFileURLPath(imageId, is, id);
	}
}
