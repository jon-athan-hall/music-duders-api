package com.musicduders.api.artist;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.musicduders.api.album.Album;

@Entity
@Table(name="artist")
public class Artist {

	@Id
	@Column(length = 36)
	private String id = UUID.randomUUID().toString();
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "artist")
	private List<Album> albums;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

}
