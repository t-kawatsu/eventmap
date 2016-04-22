package in.eventmap.admin.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.eventmap.common.dao.AdminUserDao;
import in.eventmap.common.entity.AdminUser;
import in.eventmap.common.entity.AdminUserRole;

@Transactional
// @Scope("prototype")
@Service
public class AdminUserService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected AdminUserDao adminUserDao;

	public AdminUser create(String name, String password, AdminUserRole role)
			throws Exception {
		return adminUserDao.create(name, password, role);
	}

	public AdminUser login(String name, String password) throws Exception {
		AdminUser user = adminUserDao.find(name, password);
		return user;
	}

}
