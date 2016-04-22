package in.eventmap.front.form;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import in.eventmap.common.dao.LanguageCountryDao;
import in.eventmap.common.dao.LanguageSubdivisionDao;
import in.eventmap.common.dao.UserDao;
import in.eventmap.common.entity.Sex;
import in.eventmap.common.entity.UserAccountType;
import in.eventmap.common.entity.UserStatus;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component
public class UserForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SEX_SETTING_CODE = "C008";
	private static final String MAIL_NOTICE_FREQUENCY_SETTING_CODE = "C001";
	@Resource
	private LanguageCountryDao languageCountryDao;
	@Resource
	private LanguageSubdivisionDao languageSubdivisionDao;

	private String nickname;
	private String mailAddress;
	private String password;
	private String passwordCon;
	private Sex sex;
	private Date birthday;
	private Boolean agree;
	private Map<String, String> sexes;
	private String message;
	private Integer mailNoticeFrequencyCode;
	private String residenceCountryCode;
	private String residenceSubdivisionCode;
	private String nativeCountryCode;
	private String nativeSubdivisionCode;
	private Map<String, String> mailNoticeFrequencyCodes;
	private Map<String, String> residenceCountryCodes;
	private Map<String, String> residenceSubdivisionCodes;
	private Map<String, String> nativeCountryCodes;
	private Map<String, String> nativeSubdivisionCodes;

	@javax.annotation.Resource
	protected UserDao userDao;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCon() {
		return passwordCon;
	}

	public void setPasswordCon(String passwordCon) {
		this.passwordCon = passwordCon;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Boolean getAgree() {
		return agree;
	}

	public void setAgree(Boolean agree) {
		this.agree = agree;
	}

	public Map<String, String> getSexes() {
		if (sexes == null) {
			sexes = getEnumSelectList(Sex.class, SEX_SETTING_CODE);
		}
		return sexes;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setMailNoticeFrequencyCode(Integer mailNoticeFrequencyCode) {
		this.mailNoticeFrequencyCode = mailNoticeFrequencyCode;
	}

	public Integer getMailNoticeFrequencyCode() {
		return mailNoticeFrequencyCode;
	}

	public String getResidenceCountryCode() {
		return residenceCountryCode;
	}

	public void setResidenceCountryCode(String residenceCountryCode) {
		this.residenceCountryCode = residenceCountryCode;
	}

	public String getResidenceSubdivisionCode() {
		return residenceSubdivisionCode;
	}

	public void setResidenceSubdivisionCode(String residenceSubdivisionCode) {
		this.residenceSubdivisionCode = residenceSubdivisionCode;
	}

	public String getNativeCountryCode() {
		return nativeCountryCode;
	}

	public void setNativeCountryCode(String nativeCountryCode) {
		this.nativeCountryCode = nativeCountryCode;
	}

	public String getNativeSubdivisionCode() {
		return nativeSubdivisionCode;
	}

	public void setNativeSubdivisionCode(String nativeSubdivisionCode) {
		this.nativeSubdivisionCode = nativeSubdivisionCode;
	}

	public Map<String, String> getMailNoticeFrequencyCodes() {
		if (mailNoticeFrequencyCodes == null) {
			mailNoticeFrequencyCodes = getSettingSelectList(MAIL_NOTICE_FREQUENCY_SETTING_CODE);
		}
		return mailNoticeFrequencyCodes;
	}

	public Map<String, String> getResidenceCountryCodes() {
		if (residenceCountryCodes == null) {
			residenceCountryCodes = languageCountryDao
					.findSelectListByLanguageCode();
		}
		return residenceCountryCodes;
	}

	public void setResidenceCountryCodes(
			Map<String, String> residenceCountryCodes) {
		this.residenceCountryCodes = residenceCountryCodes;
	}

	public Map<String, String> getResidenceSubdivisionCodes() {
		if (residenceSubdivisionCodes == null) {
			if (residenceCountryCode != null) {
				residenceSubdivisionCodes = languageSubdivisionDao
						.findSelectListByCountryCodeAndLanguageCode(getResidenceCountryCode());
			}
		}
		return residenceSubdivisionCodes;
	}

	public void setResidenceSubdivisionCodes(
			Map<String, String> residenceSubdivisionCodes) {
		this.residenceSubdivisionCodes = residenceSubdivisionCodes;
	}

	public Map<String, String> getNativeCountryCodes() {
		if (nativeCountryCodes == null) {
			nativeCountryCodes = languageCountryDao
					.findSelectListByLanguageCode();
		}
		return nativeCountryCodes;
	}

	public void setNativeCountryCodes(Map<String, String> nativeCountryCodes) {
		this.nativeCountryCodes = nativeCountryCodes;
	}

	public Map<String, String> getNativeSubdivisionCodes() {
		if (nativeSubdivisionCodes == null) {
			if (nativeCountryCode != null) {
				nativeSubdivisionCodes = languageSubdivisionDao
						.findSelectListByCountryCodeAndLanguageCode(getNativeCountryCode());
			}
		}
		return nativeSubdivisionCodes;
	}

	public void setNativeSubdivisionCodes(
			Map<String, String> nativeSubdivisionCodes) {
		this.nativeSubdivisionCodes = nativeSubdivisionCodes;
	}

	public boolean validate(ActionSupport as) {
		// nickname
		validateNickname(as);
		// mailAddress
		validateMailAddress(as);
		// password
		validatePassword(as);

		// agree
		if (BooleanUtils.isFalse(getAgree())) {
			as.addFieldError("userForm.agree", as.getText("invalidate.agree"));
		}

		return !as.hasFieldErrors();
	}

	public boolean validateNickname(ActionSupport as) {
		// nickname
		if (StringUtils.isEmpty(getNickname())) {
			as.addFieldError("userForm.nickname",
					as.getText("invalidate.required"));
		} else if (getNickname().length() < 2 || 20 < getNickname().length()) {
			as.addFieldError(
					"userForm.nickname",
					as.getText("invalidate.between", null,
							Arrays.asList("2", "20")));
		}
		return !as.hasFieldErrors();
	}

	public boolean validateMessage(ActionSupport as) {
		// message
		if (StringUtils.isEmpty(getMessage())) {
			as.addFieldError("userForm.message",
					as.getText("invalidate.required"));
		} else if (500 < getMessage().length()) {
			as.addFieldError(
					"userForm.message",
					as.getText("invalidate.maxLength", null,
							Arrays.asList("500")));
		}
		return !as.hasFieldErrors();
	}

	public boolean validateSex(ActionSupport as) {
		if (!getSexes().containsKey(getSex().name())) {
			as.addFieldError("userForm.sex", as.getText("invalidate.valueForm"));
		}
		return !as.hasFieldErrors();
	}

	public boolean validateBirthday(ActionSupport as) {
		as.addFieldError("userForm.birthday",
				as.getText("invalidate.valueForm"));
		return !as.hasFieldErrors();
	}

	public boolean validateMailAddress(ActionSupport as) {
		// mail address
		if (StringUtils.isEmpty(getMailAddress())) {
			as.addFieldError("userForm.mailAddress",
					as.getText("invalidate.required"));
		} else if (!getMailAddress()
				.matches(
						"([a-zA-Z0-9][a-zA-Z0-9_.+\\-]*)@(([a-zA-Z0-9][a-zA-Z0-9_\\-]+\\.)+[a-zA-Z]{2,6})")) {
			as.addFieldError("userForm.mailAddress",
					as.getText("invalidate.email"));
		} else if (100 < getMailAddress().length()) {
			as.addFieldError(
					"userForm.mailAddress",
					as.getText("invalidate.maxLength", null,
							Arrays.asList("100")));
		} else {
			if (0 < userDao.countByMailAddressAndStatusAndAccountType(
					getMailAddress(), UserStatus.LIVE, UserAccountType.PROPER)) {
				as.addFieldError("userForm.mailAddress",
						as.getText("invalidate.alreadyExists"));
			}
		}
		return !as.hasFieldErrors();
	}

	public boolean validatePassword(ActionSupport as) {
		// password
		if (StringUtils.isEmpty(getPassword())) {
			as.addFieldError("userForm.password",
					as.getText("invalidate.required"));
		} else if (StringUtils.isEmpty(getPasswordCon())) {
			as.addFieldError("userForm.passwordCon",
					as.getText("invalidate.required"));
		} else if (!getPassword().equals(getPasswordCon())) {
			as.addFieldError("userForm.password",
					as.getText("invalidate.notSamePassword"));
		}
		return !as.hasFieldErrors();
	}

	public boolean validateMailNoticeFrequencyCode(ActionSupport as) {
		String code = String.valueOf(getMailNoticeFrequencyCode());
		if (!getMailNoticeFrequencyCodes().containsKey(code)) {
			as.addFieldError("userForm.mailNoticeFrequencyCode",
					as.getText("invalidate.unKnownValue"));
		}
		return !as.hasFieldErrors();
	}

	public boolean validateResidenceCode(ActionSupport as) {
		if (StringUtils.isEmpty(getResidenceCountryCode())) {
			as.addFieldError("userForm.residence",
					as.getText("invalidate.required"));
		} else if (!getResidenceCountryCodes().containsKey(
				getResidenceCountryCode())) {
			as.addFieldError("userForm.residence",
					as.getText("invalidate.unKnownValue"));
		} else if (StringUtils.isEmpty(getResidenceSubdivisionCode())) {
			as.addFieldError("userForm.residence",
					as.getText("invalidate.required"));
		} else if (!getResidenceSubdivisionCodes().containsKey(
				getResidenceSubdivisionCode())) {
			as.addFieldError("userForm.residence",
					as.getText("invalidate.unKnownValue"));
		}
		return !as.hasFieldErrors();
	}

	public boolean validateNativeCode(ActionSupport as) {
		if (StringUtils.isEmpty(getNativeCountryCode())) {
			as.addFieldError("userForm.native",
					as.getText("invalidate.required"));
		} else if (!getNativeCountryCodes().containsKey(getNativeCountryCode())) {
			as.addFieldError("userForm.native",
					as.getText("invalidate.unKnownValue"));
		} else if (StringUtils.isEmpty(getNativeSubdivisionCode())) {
			as.addFieldError("userForm.native",
					as.getText("invalidate.required"));
		} else if (!getNativeSubdivisionCodes().containsKey(
				getNativeSubdivisionCode())) {
			as.addFieldError("userForm.native",
					as.getText("invalidate.unKnownValue"));
		}
		return !as.hasFieldErrors();
	}
}
