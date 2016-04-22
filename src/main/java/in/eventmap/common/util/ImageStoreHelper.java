package in.eventmap.common.util;

import in.eventmap.common.entity.ImageSize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageStoreHelper {

	protected final ImageUtil imageUtil = new ImageUtil();

	private String baseDir;
	private String baseURLPath;
	private String category;
	private ImageSize[] imageSizes;

	public ImageStoreHelper(String category, ImageSize[] imageSizes,
			String baseURLPath, String baseDir) {
		this.category = category;
		this.imageSizes = imageSizes;
		this.baseDir = baseDir;
		this.baseURLPath = baseURLPath;
	}

	public ImageSize[] getImageSizes() {
		return imageSizes;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public String getTmpBaseDir() {
		return getBaseDir() + "/" + category + "-tmp-images";
	}

	public File buildTmpDir(String fileId, Integer id) {
		return new File(getTmpBaseDir() + "/" + id + "/" + fileId);
	}

	public File buildTmpFile(String fileId, ImageSize is, Integer id) {
		String dir = buildTmpDir(fileId, id).toString();
		return new File(dir + "/" + is.getName() + ".jpg");
	}

	public File buildDir(Integer imageId, Integer id) {
		return new File(getBaseDir() + buildSubDir(id, imageId));
	}

	public File buildFile(Integer imageId, ImageSize is, Integer id) {
		return new File(buildDir(imageId, id) + "/" + is.getName() + ".jpg");
	}

	public String buildSubDir(Integer imageId, int id) {
		int uid1000 = (int) Math.floor(id / 1000) * 1000;
		int uid100 = (int) Math.floor((id - uid1000) / 100) * 100 + uid1000;

		return "/" + category + "/" + uid1000 + "/" + uid100 + "/" + id + "/"
				+ imageId;
	}

	public String buildFileURLPath(Integer imageId, ImageSize is, Integer id) {
		return baseURLPath + buildSubDir(imageId, id) + "/" + is.getName()
				+ ".jpg";
	}

	public String storeTmpImageFromUrl(URL url, Integer id) throws Exception {
		File dest = File.createTempFile(id + "-" + System.currentTimeMillis(),
				"tmp");
		dest.deleteOnExit();
		InputStream in = url.openStream();
		OutputStream out = new FileOutputStream(dest);
		try {
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
		} finally {
			out.close();
			in.close();
		}
		return storeTmpImage(dest, id);
	}

	public String storeTmpImage(File file, Integer id) throws Exception {
		file.deleteOnExit();
		String fileId = String.valueOf(System.currentTimeMillis());
		File dir = buildTmpDir(fileId, id);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		for (ImageSize is : getImageSizes()) {
			imageUtil.trimResize(file, buildTmpFile(fileId, is, id),
					is.getWidth(), is.getHeight());
		}
		return fileId;
	}

	public void substantiateTmpImage(String fileId, Integer userId, Integer id, Integer imageId)
			throws Exception {
		File dir = buildDir(imageId, id);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		for (ImageSize is : getImageSizes()) {
			File tmp = buildTmpFile(fileId, is, userId);
			File dest = buildFile(imageId, is, id);
			if (!tmp.renameTo(dest)) {
				throw new Exception();
			}
		}
		clearTmpImageDir(id);
	}

	public boolean clearTmpImageDir(Integer id) {
		File dir = new File(getTmpBaseDir() + "/" + id);
		if (dir.isDirectory()) {
			return dir.delete();
		}
		return true;
	}

}
