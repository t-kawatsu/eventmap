package in.eventmap.front.action.user;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import in.eventmap.common.entity.User;
import in.eventmap.front.form.UserForm;
import in.eventmap.common.service.MailService;

public class UpdateAction extends AbstractReadAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private UserForm userForm;
	@Resource
	private MailService mailService;

	private boolean updated;

	@Action(value = "user/update", results = {
			@Result(name = "input", location = "user/update.ftl"),
			@Result(name = "success", location = "user/update-complete.ftl") })
	@Override
	public String execute() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		String ret = beforeReadProcess(getCurrentUser().getId());
		if (!SUCCESS.equals(ret)) {
			return ret;
		}
		if (!"POST".equals(request.getMethod())) {
			Date defaultValue = null;
			ConvertUtils.register(new DateConverter(defaultValue), Date.class);
			BeanUtils.copyProperties(userForm, user);
			return INPUT;
		}
		if (!userForm.validate(this)) {
			return INPUT;
		}
		return SUCCESS;
	}

	@Action(value = "user/update-nickname-ajax", results = {
			@Result(name = "input", location = "user/_update-nickname.ftl"),
			@Result(name = "success", location = "user/_update-nickname.ftl") })
	public String nicknameAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateNickname(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setNickname(userForm.getNickname());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-mail-address-ajax", results = {
			@Result(name = "input", location = "user/_update-mail-address.ftl"),
			@Result(name = "success", location = "user/_update-mail-address.ftl") })
	public String mailAddressAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateMailAddress(this)) {
			return INPUT;
		}
		// TODO とりあえずセッションで保持
		// 完了時にはセッション持続&同ブラウザである必要がある
		sessionManager.putNamespace("updateMailAddress",
				userForm.getMailAddress());
		mailService.sendUpdateMailAddress(userForm.getMailAddress());
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-mail-address-complete", results = {
			@Result(name = "expired", location = "user/update-mail-address-expired.ftl"),
			@Result(name = "success", location = "user/update-mail-address-complete.ftl") })
	public String mailAddressCompleteAction() throws Exception {
		String mailAddress = (String) sessionManager
				.getNamespace("updateMailAddress");

		if (StringUtils.isEmpty(mailAddress)) {
			return "expired";
		}
		sessionManager.removeNamespace("updateMailAddress");
		User user = getCurrentUser();
		user.setMailAddress(mailAddress);
		userDao.update(user);
		setCurrentUser(user);
		return SUCCESS;
	}

	@Action(value = "user/update-password-ajax", results = {
			@Result(name = "input", location = "user/_update-password.ftl"),
			@Result(name = "success", location = "user/_update-password.ftl") })
	public String passwordAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validatePassword(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		userDao.updatePassword(user, userForm.getPassword());
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-message-ajax", results = {
			@Result(name = "input", location = "user/_update-message.ftl"),
			@Result(name = "success", location = "user/_update-message.ftl") })
	public String messageAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateMessage(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setMessage(userForm.getMessage());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-sex-ajax", results = {
			@Result(name = "input", location = "user/_update-sex.ftl"),
			@Result(name = "success", location = "user/_update-sex.ftl") })
	public String sexAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateSex(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setSex(userForm.getSex());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-birthday-ajax", results = {
			@Result(name = "input", location = "user/_update-birthday.ftl"),
			@Result(name = "success", location = "user/_update-birthday.ftl") })
	public String birthdayAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateBirthday(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setBirthday(userForm.getBirthday());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-mail-notice-frequency-ajax", results = {
			@Result(name = "input", location = "user/_update-mail-notice-frequency.ftl"),
			@Result(name = "success", location = "user/_update-mail-notice-frequency.ftl") })
	public String mailNoticeFrequencyAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateMailNoticeFrequencyCode(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setMailNoticeFrequencyCode(userForm.getMailNoticeFrequencyCode());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-residence-ajax", results = {
			@Result(name = "input", location = "user/_update-residence.ftl"),
			@Result(name = "success", location = "user/_update-residence.ftl") })
	public String residenceAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateResidenceCode(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setResidenceCountryCode(userForm.getResidenceCountryCode());
		user.setResidenceSubdivisionCode(userForm.getResidenceSubdivisionCode());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	@Action(value = "user/update-native-ajax", results = {
			@Result(name = "input", location = "user/_update-native.ftl"),
			@Result(name = "success", location = "user/_update-native.ftl") })
	public String nativeAction() throws Exception {
		if (!getIsLogined()) {
			return LOGIN;
		}
		if (!userForm.validateNativeCode(this)) {
			return INPUT;
		}
		User user = getCurrentUser();
		user.setNativeCountryCode(userForm.getNativeCountryCode());
		user.setNativeSubdivisionCode(userForm.getNativeSubdivisionCode());
		userDao.update(user);
		setCurrentUser(user);
		updated = true;
		return SUCCESS;
	}

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public boolean getUpdated() {
		return updated;
	}
}
