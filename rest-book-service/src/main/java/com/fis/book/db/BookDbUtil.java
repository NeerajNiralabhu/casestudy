package com.fis.book.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.fis.book.model.Book;

@Component
public class BookDbUtil {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("bookRowMapper")
	private RowMapper<Book> rowMapper;
	
	@Bean(name = "bookRowMapper")
	public BookRowMapper provideBookRowMapperBean() {
		return new BookRowMapper();
	} 

	private class BookRowMapper implements RowMapper<Book>{
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setBookId(rs.getString("BOOK_ID"));
			book.setName(rs.getString("BOOK_NAME"));
			book.setAuthor(rs.getString("AUTHOR"));
			book.setCopiesAvailable(rs.getInt("AVAILABLE_COPIES"));
			book.setTotalCopies(rs.getInt("TOTAL_COPIES"));
			return book;
		}
	}
	
	public Book getById(String id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("bookId", id);
		List<Book> bookList = jdbcTemplate.query("select book_id,book_name,author,AVAILABLE_COPIES,TOTAL_COPIES from book where book_id=:bookId",mapSqlParameterSource,this.rowMapper);
		return bookList!=null && !bookList.isEmpty()?bookList.get(0):null;
	}
	public List<Book> getBooks() {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		List<Book> bookList = jdbcTemplate.query("select book_id,book_name,author,AVAILABLE_COPIES,TOTAL_COPIES from book",mapSqlParameterSource,this.rowMapper);
		return bookList!=null && !bookList.isEmpty()?bookList:null;
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

	public RowMapper<Book> getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper<Book> rowMapper) {
		this.rowMapper = rowMapper;
	}

	
}
