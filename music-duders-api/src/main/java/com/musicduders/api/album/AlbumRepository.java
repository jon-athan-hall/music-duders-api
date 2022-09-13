package com.musicduders.api.album;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepository {

	/**
	 * Java database connectivity takes care of building SQL statements,
	 * establishing connections, handling SQLExceptions, etc.
	 */
	private final JdbcTemplate jdbc;
	
	/**
	 * Basic constructor that sets some private attributes.
	 * 
	 * @param jdbc	the JDBC core instance
	 */
	public AlbumRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	/**
	 * Creates and executes the SQL statement for storing an album.
	 * 
	 * @param album	the Album object to store.
	 */
	public void saveAlbum(Album album) {
		String sql = "INSERT INTO album VALUES (NULL, ?)";
		
		jdbc.update(sql, album.getTitle());
	}

}
