package com.wangsong.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangsong.common.controller.BaseController;
import com.wangsong.system.groups.UserAdd;
import com.wangsong.system.groups.UserUpdate;
import com.wangsong.system.model.User;
import com.wangsong.system.model.UserAddModel;
import com.wangsong.system.model.UserPage;
import com.wangsong.system.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	

	@RequiresPermissions("/system/user/list")
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(HttpServletRequest request,UserPage user) {
		return userService.findTByPage(user);
	}

	@RequiresPermissions("/system/user/add")
	@RequestMapping(value="/add")
	@ResponseBody
	public Object add(@Validated({UserAdd.class}) UserAddModel user,BindingResult bindingResult) {
		return userService.insertUser(user);
	}
	

	@RequestMapping(value="/selectByPrimaryKey")
	@ResponseBody
	public Object selectByPrimaryKey(String id) {
		return userService.selectByPrimaryKey(id);
	}
	
	@RequiresPermissions("/system/user/update")
	@RequestMapping(value="/update")
	@ResponseBody
	public Object update(@Validated({UserUpdate.class}) UserAddModel muser,BindingResult bindingResult) {
		return userService.updateUser(muser);
	}
	
	@RequiresPermissions("/system/user/delete")
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(String[] id) {
		return userService.deleteUser(id);
	}
	
	@RequestMapping(value="/findUserByUser")
	@ResponseBody
	public Object findUserByUser(User user) {
		return userService.findTByT(user);
	}
	
	@RequestMapping(value="/toUpdatePassword")
	@ResponseBody
	public Object toUpdatePassword() {
		return userService.toUpdatePassword((String)SecurityUtils.getSubject().getPrincipal());
	}
	
	@RequestMapping(value="/updatePassword")
	@ResponseBody
	public Object updatePassword(@Validated({UserUpdate.class}) UserAddModel muser,BindingResult bindingResult) {
		return userService.updatePassword(muser);
	}
	
}
