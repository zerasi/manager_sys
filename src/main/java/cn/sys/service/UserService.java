package cn.sys.service;

import java.util.List;

import cn.sys.entity.User;
import cn.sys.entity.UserExample;
import cn.sys.utils.PageResult;

public interface UserService {

	List<User> findAll(UserExample example);

	void add(User User);

	void update(User User);

	PageResult findPage(UserExample example, Integer page, Integer rows);

	User findOne(Integer id);

	void delete(int parseInt);

	User login(UserExample example);

}
