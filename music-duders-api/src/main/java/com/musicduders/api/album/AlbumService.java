package com.musicduders.api.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
	
	@Autowired
	AlbumRepository albumRepository;
	
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}
	
	public List<Album> getAlbums() {
		List<Album> albums = albumRepository.findAll();
		return albums;
	}

}
