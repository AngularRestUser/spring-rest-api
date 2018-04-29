package com.sample.api.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.api.controller.User;
import com.sample.api.model.KeyValueBean;
import com.sample.api.util.DBUtil;
import com.sample.api.util.FormUtil;

@Service("applicationDAO")
@Transactional
public class ApplicationDAOImpl implements ApplicationDAO{
	
	private static Logger logger = Logger.getLogger(ApplicationDAOImpl.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DBUtil dbUtil;
	
	@Autowired
	FormUtil formUtil;

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
			    "INSERT INTO info (firstname,lastname,emailid) VALUES (?, ?,?)",
			    user.getFirstName(),user.getLastName(),user.getEmail()
			);
		
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		String stmt="select * from info where id=?";
		User user=jdbcTemplate.queryForObject(stmt, new Object[] {id}, new UserMapper());
		return user;
	}

	@Override
	public List<User> listUsers() {
		
		List<User> users = new ArrayList<User>();
		// TODO Auto-generated method stub
		
		String stmt = "select * from info";
		users = jdbcTemplate.query(stmt, new UserMapper());
		return users;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String stmt=" delete from info where id=?";
		jdbcTemplate.update(stmt,id);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String stmt=" update info set firstname=?,lastname=?,emailid=? where id=?";
		jdbcTemplate.update(stmt,user.getFirstName(),user.getLastName(),user.getEmail(),(int)(long)user.getId());
		
	}
	
	
	
	
	
}
	
	