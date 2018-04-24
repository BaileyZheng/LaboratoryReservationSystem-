package edu.just.reservation.manage.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.core.util.ExcelUtil;
import edu.just.reservation.manage.role.entity.Role;
import edu.just.reservation.manage.user.dao.UserDao;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;
import edu.just.reservation.manage.user.entity.UserRoleId;
import edu.just.reservation.manage.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}

	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportExcelUser(userList, outputStream);
	}

	public void importExcel(File headImg, String headImgFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(headImg);
			boolean is03excel = headImgFileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook = is03excel ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() > 2) {
				User user = null;
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					user = new User();
					Row row = sheet.getRow(i);
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					Cell cell2 = row.getCell(2);
					user.setGender(cell2.getStringCellValue().equals("男"));
					Cell cell3 = row.getCell(3);
					String mobile = "";
					try {
						mobile = cell3.getStringCellValue();
					} catch (Exception e) {
						double dM = cell3.getNumericCellValue();
						mobile = BigDecimal.valueOf(dM).toString();
					}
					user.setMobile(mobile);
					Cell cell4 = row.getCell(4);
					user.setEmail(cell4.getStringCellValue());
					Cell cell5 = row.getCell(5);
					if (cell5.getDateCellValue() != null) {
						user.setBirthday(cell5.getDateCellValue());
					}
					if(userDao.isNotExists(user)){
						user.setPassword("123456");
						user.setState(User.USER_STATE_VALID);
						add(user);
					}
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User> findUsersByAccountAndId(String account, String id) {
		return userDao.findUsersByAccountAndId(account, id);
	}

	public void addUserAndRole(User user, String... roleIds) {
		add(user);
		addUserRole(user, roleIds);
	}

	public void updateUserAndRole(User user, String... roleIds) {
		if(roleIds!=null){
			addUserRole(user, roleIds);
		}
		userDao.update(user);
	}

	private void addUserRole(User user, String... roleIds) {
		List<UserRole> urs = userDao.getUserRolesByUserId(user.getId());
		if (roleIds != null) {
			//添加现在选了但是数据库中没有的
			for (String roleId : roleIds) {
				boolean flag = true;
				for(UserRole ur :urs){
					if(ur.getId().getRole().getRoleId().equals(roleId)){
						flag = false;
						break;
					}
				}
				if(flag){
					userDao.addUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
				}
			}
			//去除数据库中有的但没有选的
			for(UserRole ur:urs){
				boolean flag = true;
				for(String roleId:roleIds){
					if(roleId.equals(ur.getId().getRole().getRoleId())){
						flag = false;
						break;
					}
				}
				if(flag){
					userDao.deleteUserRole(ur);
				}
			}
		}
	}

	public List<UserRole> getUserRolesByUserId(String id) {
		return userDao.getUserRolesByUserId(id);
	}

	public List<User> findUserByAccountAndPassword(String account,
			String password) {
		return userDao.findUserByAccountAndPassword(account, password);
	}

}
