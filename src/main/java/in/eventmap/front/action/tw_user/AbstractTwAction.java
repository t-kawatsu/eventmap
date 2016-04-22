package in.eventmap.front.action.tw_user;

import javax.annotation.Resource;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import in.eventmap.common.dao.UserDao;
import in.eventmap.common.service.TwUserService;
import in.eventmap.common.service.UserImageService;
import in.eventmap.common.service.UserService;
import in.eventmap.common.twitter.MyTwitterClient;
import in.eventmap.common.util.UrlHelper;
import in.eventmap.front.action.AbstractAction;
import in.eventmap.front.form.TwUserForm;

public class AbstractTwAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "twUserForm")
	protected TwUserForm userForm;
	@Resource
	protected UserDao userDao;
	@Resource
	protected UserService userService;
	@Resource
	protected TwUserService twUserService;
	@Resource
	protected UserImageService userImageService;

	protected Twitter twitter;

	protected String loginUrl;

	protected String detectLoginUrl(String callbackActionName) throws Exception {
		twitter = MyTwitterClient.factoryTwitter(getText("app.twitter.key"),
				getText("app.twitter.secret"));
		UrlHelper urlHelper = new UrlHelper();
		RequestToken requestToken = twitter.getOAuthRequestToken(urlHelper
				.buildUrl(callbackActionName, null, true, true));
		sessionManager.putNamespace("twRequestToken", requestToken);
		return requestToken.getAuthenticationURL();
	}

	public Twitter getAccessTokenObteinedClient(String oauthVerifier)
			throws TwitterException {

		twitter = MyTwitterClient.factoryTwitter(getText("app.twitter.key"),
				getText("app.twitter.secret"));

		AccessToken accessToken = twitter.getOAuthAccessToken(
				(RequestToken) sessionManager.getNamespace("twRequestToken"),
				oauthVerifier);

		sessionManager.removeNamespace("twRequestToken");
		sessionManager.putNamespace("twToken", accessToken.getToken());
		sessionManager.putNamespace("twTokenSecret",
				accessToken.getTokenSecret());

		return twitter;
	}

	public TwUserForm convertClientUserBean2UserForm(Twitter twitter)
			throws TwitterException {
		userForm.setTwId(twitter.getId());
		userForm.setNickname(twitter.getScreenName());
		// userForm.setGender(twitter);
		// userForm.setBirthday(twitter.verifyCredentials());
		// userForm.setMailAddress(facebookUser.getEmail());
		// userForm.setMessage(facebookUser.get)
		return userForm;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public TwUserForm getUserForm() {
		return userForm;
	}

}
