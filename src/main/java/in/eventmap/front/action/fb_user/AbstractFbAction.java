package in.eventmap.front.action.fb_user;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.User;
import in.eventmap.common.facebook.DisplayMode;
import in.eventmap.common.facebook.MyDefaultFacebookClient;
import in.eventmap.common.facebook.MyFacebookClient;
import in.eventmap.common.facebook.PictureSize;
import in.eventmap.common.service.FbUserService;
import in.eventmap.common.service.UserImageService;
import in.eventmap.common.service.UserService;
import in.eventmap.common.util.UrlHelper;
import in.eventmap.front.action.AbstractAction;
import in.eventmap.front.form.FbUserForm;

public abstract class AbstractFbAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "fbUserForm")
	protected FbUserForm userForm;
	@Resource
	protected UserDao userDao;
	@Resource
	protected FbUserService fbUserService;
	@Resource
	protected UserService userService;
	@Resource
	protected UserImageService userImageService;

	protected MyFacebookClient facebookClient = new MyDefaultFacebookClient();

	protected String loginUrl;

	protected String detectLoginUrl(String callbackActionName) throws Exception {
		UrlHelper urlHelper = new UrlHelper();
		return facebookClient.buildLoginUrl(getText("app.facebook.appId"),
				urlHelper.buildUrl(callbackActionName, null, true, true),
				DisplayMode.PAGE, new String[] { "email" });
	}

	public MyFacebookClient getAccessTokenObteinedFacebookClient(
			String callbackActionName, String code) {
		UrlHelper urlHelper = new UrlHelper();
		MyFacebookClient.AccessToken accessToken = facebookClient
				.obtainAccessToken(getText("app.facebook.appId"),
						getText("app.facebook.secret"), code, urlHelper
								.buildUrl(callbackActionName, null, true, true));
		return new MyDefaultFacebookClient(accessToken.getAccessToken());
	}

	public FbUserForm convertFbClientUserBean2UserForm(
			com.restfb.types.User facebookUser) {
		userForm.setFbId(facebookUser.getId());
		userForm.setNickname(facebookUser.getName());
		userForm.setGender(facebookUser.getGender());
		userForm.setBirthday(facebookUser.getBirthdayAsDate());
		userForm.setMailAddress(facebookUser.getEmail());
		// userForm.setMessage(facebookUser.get)
		return userForm;
	}

	public void registFbUserImage(User user, String fbId)
			throws MalformedURLException, Exception {
		userImageService
				.createFromUrl(
						user,
						new URL(facebookClient.buildPictureUrl(fbId,
								PictureSize.large)));
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public FbUserForm getUserForm() {
		return userForm;
	}
}
