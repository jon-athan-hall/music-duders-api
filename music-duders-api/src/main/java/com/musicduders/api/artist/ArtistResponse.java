package com.musicduders.api.artist;

import java.util.List;

import com.musicduders.api.album.Album;

public class ArtistResponse {
	
	private String id;

	private String name;
	
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
