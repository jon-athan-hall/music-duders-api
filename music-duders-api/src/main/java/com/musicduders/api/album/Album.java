package com.musicduders.api.album;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.musicduders.api.artist.Artist;

@Entity
@Table(name="album")
public class Album {
	
	@Id
	@Column(length = 36)
	private String id = UUID.randomUUID().toString();
	
	@Column(nullable = false, unique = false)
	private String title;
	
	@Column(nullable = false, unique = false)
	private String url;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}
