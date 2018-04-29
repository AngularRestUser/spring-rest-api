package com.sample.api.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

import com.sample.api.dao.*;

@Configuration
@PropertySources({
	@PropertySource("classpath:application.properties")
})
public class JdbcConfiguration {

	@Value("${db.rest.jndi}")
	private String restJndi;

	@Bean
    public DataSource dataSource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate.lookup(restJndi);

        return dataSource;
    }

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public ApplicationDAO applicationDAO() throws NamingException{
        return new ApplicationDAOImpl();
    }


}
