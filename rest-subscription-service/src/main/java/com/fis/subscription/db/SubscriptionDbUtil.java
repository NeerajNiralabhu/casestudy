package com.fis.subscription.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.fis.subscription.model.Subscription;

@Component
public class SubscriptionDbUtil {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("subscriptionRowMapper")
	private RowMapper<Subscription> rowMapper;
	
	@Bean(name = "subscriptionRowMapper")
	public SubscriptionRowMapper provideSubscriptionRowMapperBean() {
		return new SubscriptionRowMapper();
	} 

	private class SubscriptionRowMapper implements RowMapper<Subscription>{
		@Override
		public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
			Subscription subscription = new Subscription();
			subscription.setBookId(rs.getString("BOOK_ID"));
			subscription.setSubscriberName(rs.getString("SUBSCRIBER_NAME"));
			subscription.setDateReturned(rs.getString("DATE_RETURNED"));
			subscription.setDateSubscribed(rs.getString("DATE_SUBSCRIBED"));
			return subscription;
		}
	}
	
	public Subscription getByName(String name) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("subscriptionName", name);
		List<Subscription> subscriptionList = jdbcTemplate.query("select book_id,SUBSCRIBER_NAME,DATE_RETURNED,DATE_SUBSCRIBED from subscription where SUBSCRIBER_NAME=:subscriptionName",mapSqlParameterSource,this.rowMapper);
		return subscriptionList!=null && !subscriptionList.isEmpty()?subscriptionList.get(0):null;
	}
	public List<Subscription> getSubscriptions() {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		List<Subscription> subscriptionList = jdbcTemplate.query("select book_id,SUBSCRIBER_NAME,DATE_RETURNED,DATE_SUBSCRIBED from subscription",mapSqlParameterSource,this.rowMapper);
		return subscriptionList!=null && !subscriptionList.isEmpty()?subscriptionList:null;
	}
	public void insertData(Subscription subscription) {
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		paramMap.put("bookId", subscription.getBookId());
		paramMap.put("subscriptionName", subscription.getSubscriberName());
		paramMap.put("datereturned", subscription.getDateReturned());
		paramMap.put("dateSubscribed", subscription.getDateSubscribed());

		 jdbcTemplate.update("insert into subscription (BOOK_ID,SUBSCRIBER_NAME,DATE_RETURNED,DATE_SUBSCRIBED) values (:bookId,:subscriptionName,:datereturned,:dateSubscribed)", paramMap);
	}
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RowMapper<Subscription> getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper<Subscription> rowMapper) {
		this.rowMapper = rowMapper;
	}

	
}
