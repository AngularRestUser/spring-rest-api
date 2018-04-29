package com.sample.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.sample.api.controller.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	      User user = new User();
	      user.setId(arg0.getLong("id"));
	      user.setFirstName(arg0.getString("firstname"));
	     user.setLastName(arg0.getString("lastname"));
	     user.setEmail((arg0.getString("emailid")));
	      
	      return user;
		
	}

}
