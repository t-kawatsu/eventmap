package in.eventmap.common.service;

import in.eventmap.common.crypt.BlowfishCrypt;
import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.LanguageCode;
import in.eventmap.common.entity.TmpUser;
import in.eventmap.common.entity.User;
import in.eventmap.common.entity.UserAccountType;
import in.eventmap.common.entity.UserSecession;
import in.eventmap.common.entity.UserStatus;
import in.eventmap.common.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
// @Scope("prototype")
@Service
public class UserService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private static final String KEEP_LOGIN_PARAM_NAME = "kl";
	private static final String TOKEN_PARAM_NAME = "t";
	private static final String IDENTIFIER_PARAM_NAME = "uid";

	private static final int keepLoginExpired = 60 * 60 * 24 * 7;

	@Resource
	protected UserDao userDao;

	public User login(User user, boolean keep) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		updateLoginState(user, request.getHeader("User-Agent"));

		if (!keep) {
			return user;
		}

		HttpServletResponse response = ServletActionContext.getResponse();

		String token = BlowfishCrypt.encrypt(RandomStringUtils.random(10));
		user.setToken(token);
		userDao.update(user);

		Cookie klc = new Cookie(KEEP_LOGIN_PARAM_NAME, String.valueOf(keep));
		klc.setMaxAge(keepLoginExpired);
		klc.setPath("/");
		response.addCookie(klc);

		Cookie idc = new Cookie(IDENTIFIER_PARAM_NAME,
				BlowfishCrypt.encrypt(user.getId().toString()));
		idc.setMaxAge(keepLoginExpired);
		idc.setPath("/");
		response.addCookie(idc);

		Cookie tc = new Cookie(TOKEN_PARAM_NAME, user.getToken());
		tc.setMaxAge(keepLoginExpired);
		tc.setPath("/");
		response.addCookie(tc);

		return user;
	}

	public User login(String mailAddress, String password, boolean keep)
			throws Exception {
		User user = userDao.findByMailAddressAndPasswordAndStatus(mailAddress,
				password, UserStatus.LIVE);
		return login(user, keep);
	}

	public void logout() {
		clearKeepLoginState();
	}

	public User reloginIfKeep() throws Exception {
		// no login
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();

		boolean isKeepLogin = null != detectCookieValue(cookies,
				KEEP_LOGIN_PARAM_NAME);
		if (!isKeepLogin) {
			return null;
		}

		String uid = detectCookieValue(cookies, IDENTIFIER_PARAM_NAME);
		if (StringUtils.isEmpty(uid)) {
			return null;
		}

		String token = detectCookieValue(cookies, TOKEN_PARAM_NAME);

		User user = userDao
				.findById(Integer.valueOf(BlowfishCrypt.decrypt(uid)));
		if (user == null || !user.getToken().equals(token)) {
			clearKeepLoginState();
			return null;
		}

		return login(user, true);
	}

	private String detectCookieValue(Cookie[] cookies, String name) {
		for (Cookie c : cookies) {
			if (c.getName().equals(name)) {
				return c.getValue();
			}
		}
		return null;
	}

	public void clearKeepLoginState() {
		HttpServletResponse response = ServletActionContext.getResponse();

		Cookie klc = new Cookie(KEEP_LOGIN_PARAM_NAME, "");
		klc.setMaxAge(0);
		klc.setPath("/");
		response.addCookie(klc);

		Cookie idc = new Cookie(IDENTIFIER_PARAM_NAME, "");
		idc.setMaxAge(0);
		idc.setPath("/");
		response.addCookie(idc);

		Cookie tc = new Cookie(TOKEN_PARAM_NAME, "");
		tc.setMaxAge(0);
		tc.setPath("/");
		response.addCookie(tc);
	}

	public User create(TmpUser tmpUser, LanguageCode languageCode,
			String userAgent, UserAccountType userAccountType) throws Exception {
		return create(tmpUser, languageCode, userAgent, userAccountType, false);
	}

	public User create(TmpUser tmpUser, LanguageCode languageCode,
			String userAgent, UserAccountType userAccountType,
			boolean isTestUser) throws Exception {

		User user = new User();
		Date defaultValue = null;
		ConvertUtils.register(new DateConverter(defaultValue), Date.class);
		BeanUtils.copyProperties(user, tmpUser);
		ConvertUtils.deregister();

		user.setLastLoginedAt(new Date());
		user.setLastMailNoticedAt(new Date());
		user.setMailNoticeFrequencyCode(2);
		user.setStatus(UserStatus.LIVE);
		user.setIsTestUser(false);
		user.setLanguageCode(new Locale(languageCode.toString()));
		user.setUseragent(userAgent);
		user.addAccountType(userAccountType);
		String token = BlowfishCrypt.encrypt(RandomStringUtils.random(10));
		user.setToken(token);
		userDao.save(user);
		return user;
	}

	public User updateLoginState(User user, String userAgent) {
		user.setLastLoginedAt(new Date());
		user.setUseragent(userAgent);
		userDao.getSession().update(user);
		return user;
	}

	public void secession(User user, Integer reasonCode, String description) {
		user.setStatus(UserStatus.SECESSIONED);
		userDao.getSession().update(user);

		UserSecession userSecession = new UserSecession();
		userSecession.setId(user.getId());
		userSecession.setReasonCode(reasonCode);
		userSecession.setDescription(description);
		userDao.save(userSecession);

		// deleteUserRegistData(user.getId());
	}

	public List<User> fetchSiteUsers(int limit) {
		List<User> users = userDao.resultList(userDao.getSession()
				.createQuery("FROM User u ORDER BY u.id DESC")
				.setMaxResults(limit * 2));

		return CollectionUtils.detectRandom(users, limit);
	}

}