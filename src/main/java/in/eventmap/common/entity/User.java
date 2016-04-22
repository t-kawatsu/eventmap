package in.eventmap.common.entity;

import java.util.Date;
import java.util.EnumSet;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String mailAddress;
	private String password;
	private EnumSet<UserAccountType> accountTypes;
	private Integer userImageId;
	private Integer userBgImageId;
	private String nickname;
	private String message;
	private Date birthday;
	private Sex sex;
	private Integer mailNoticeFrequencyCode;
	private Date lastMailNoticedAt;
	private UserStatus status;
	private Locale languageCode;
	private Date lastLoginedAt;
	private String useragent;
	private Boolean isTestUser;
	private String residenceCountryCode;
	private String residenceSubdivisionCode;
	private String nativeCountryCode;
	private String nativeSubdivisionCode;
	private String token;
	private Date createdAt;
	private Date updatedAt;

	private FbUser fbUser;
	private TwUser twUser;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "mail_address")
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

	@Column(name = "account_types")
	@Type(type = "in.eventmap.common.hibernate.type.UserAccountTypeUserType")
	public EnumSet<UserAccountType> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(EnumSet<UserAccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}

	@Column(name = "user_image_id")
	public Integer getUserImageId() {
		return userImageId;
	}

	public void setUserImageId(Integer userImageId) {
		this.userImageId = userImageId;
	}

	@Column(name = "user_bg_image_id")
	public Integer getUserBgImageId() {
		return userBgImageId;
	}

	public void setUserBgImageId(Integer userBgImageId) {
		this.userBgImageId = userBgImageId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_logined_at")
	public Date getLastLoginedAt() {
		return lastLoginedAt;
	}

	public void setLastLoginedAt(Date lastLoginedAt) {
		this.lastLoginedAt = lastLoginedAt;
	}

	@Column(name = "language_code")
	public Locale getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(Locale languageCode) {
		this.languageCode = languageCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_mail_noticed_at")
	public Date getLastMailNoticedAt() {
		return lastMailNoticedAt;
	}

	public void setLastMailNoticedAt(Date lastMailNoticedAt) {
		this.lastMailNoticedAt = lastMailNoticedAt;
	}

	@Column(name = "mail_notice_frequency_code")
	public Integer getMailNoticeFrequencyCode() {
		return mailNoticeFrequencyCode;
	}

	public void setMailNoticeFrequencyCode(Integer mailNoticeFrequencyCode) {
		this.mailNoticeFrequencyCode = mailNoticeFrequencyCode;
	}

	@Enumerated(EnumType.ORDINAL)
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	@Column(name = "is_test_user")
	public Boolean getIsTestUser() {
		return isTestUser;
	}

	public void setIsTestUser(Boolean isTestUser) {
		this.isTestUser = isTestUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "residence_country_code")
	public String getResidenceCountryCode() {
		return residenceCountryCode;
	}

	public void setResidenceCountryCode(String residenceCountryCode) {
		this.residenceCountryCode = residenceCountryCode;
	}

	@Column(name = "residence_subdivision_code")
	public String getResidenceSubdivisionCode() {
		return residenceSubdivisionCode;
	}

	public void setResidenceSubdivisionCode(String residenceSubdivisionCode) {
		this.residenceSubdivisionCode = residenceSubdivisionCode;
	}

	@Column(name = "native_country_code")
	public String getNativeCountryCode() {
		return nativeCountryCode;
	}

	public void setNativeCountryCode(String nativeCountryCode) {
		this.nativeCountryCode = nativeCountryCode;
	}

	@Column(name = "native_subdivision_code")
	public String getNativeSubdivisionCode() {
		return nativeSubdivisionCode;
	}

	public void setNativeSubdivisionCode(String nativeSubdivisionCode) {
		this.nativeSubdivisionCode = nativeSubdivisionCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Transient
	public void addAccountType(UserAccountType accountType) {
		if (accountTypes == null) {
			accountTypes = EnumSet.noneOf(UserAccountType.class);
		}
		accountTypes.add(accountType);
	}

	@Transient
	public boolean hasAccountType(UserAccountType accountType) {
		if (accountTypes == null) {
			return false;
		}
		return accountTypes.contains(accountType);
	}

	@Transient
	public boolean isFacebookUser() {
		if (accountTypes == null) {
			return false;
		}
		return accountTypes.contains(UserAccountType.FACEBOOK);
	}

	@Transient
	public boolean isProperUser() {
		if (accountTypes == null) {
			return false;
		}
		return accountTypes.contains(UserAccountType.PROPER);
	}

	@Transient
	public boolean isTwitterUser() {
		if (accountTypes == null) {
			return false;
		}
		return accountTypes.contains(UserAccountType.TWITTER);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
	public FbUser getFbUser() {
		return fbUser;
	}

	public void setFbUser(FbUser fbUser) {
		this.fbUser = fbUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
	public TwUser getTwUser() {
		return twUser;
	}

	public void setTwUser(TwUser twUser) {
		this.twUser = twUser;
	}
}
