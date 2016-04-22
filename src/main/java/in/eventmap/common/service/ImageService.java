package in.eventmap.common.service;

import java.io.File;
import java.net.URL;

import in.eventmap.common.entity.ImageSize;
import in.eventmap.common.util.ImageStoreHelper;

import org.springframework.beans.factory.annotation.Value;

abstract public class ImageService {

	private ImageStoreHelper imageHelper;

	@Value("#{appProperties[ 'app.image.baseDir' ]}")
	private String baseDir;

	private String baseURLPath = "/assets/external/images";

	public ImageService() {
		//imageHelper = new ImageStoreHelper(getImageCategory(), getImageSizes(),
		//		baseURLPath, baseDir);
	}
	
	protected ImageStoreHelper getImageHelper() {
		if(imageHelper == null) {
			imageHelper = new ImageStoreHelper(getImageCategory(), getImageSizes(),
				baseURLPath, baseDir);
		}
		return imageHelper;
	}

	public String storeTmpImage(File file, Integer id) throws Exception {
		return getImageHelper().storeTmpImage(file, id);
	}

	public String storeTmpImageFromUrl(URL url, Integer id) throws Exception {
		return getImageHelper().storeTmpImageFromUrl(url, id);
	}

	public File buildTmpImageFile(String fileId, ImageSize is, Integer id) {
		return getImageHelper().buildTmpFile(fileId, is, id);
	}

	abstract protected ImageSize[] getImageSizes();

	abstract protected String getImageCategory();

	abstract public String getImageURLPath(Integer imageId, ImageSize is,
			Integer id);
}
