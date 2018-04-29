package com.sample.api.dao;


import java.util.List;
import java.util.Map;
import com.sample.api.controller.User;

import com.sample.api.model.KeyValueBean;

public interface ApplicationDAO {
	
	public void create(User user);

	   public User getUser(Integer id);
	   
	   public List<User> listUsers();
	   
	   public void delete(Integer id);

	   public void update(User user);
	
}
