package com.musicduders.api.album;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public List<Album> getAllAlbums() {
		List<Album> albums = albumRepository.findAll();
		return albums;
	}
	
	public Optional<Album> getAlbum(String id) {
		Optional<Album> album = albumRepository.findById(id);
		return album;
	}

}
