package in.eventmap.common.service;

import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.entity.UserBgImageSize;
import in.eventmap.common.entity.UserImageType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class UserBgImageService extends UserImageService {

	UserImageType getUserImageType() {
		return UserImageType.BACKGROUND;
	}

	@Override
	protected ImageSize[] getImageSizes() {
		return UserBgImageSize.values();
	}

	@Override
	protected String getImageCategory() {
		return "user-bg";
	}

}
