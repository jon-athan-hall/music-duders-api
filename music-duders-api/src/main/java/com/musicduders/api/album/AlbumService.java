package com.musicduders.api.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicduders.api.artist.Artist;
import com.musicduders.api.artist.ArtistRepository;

@Service
public class AlbumService {
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	public List<Album> getAllAlbums() {
		List<Album> albums = albumRepository.findAll();
		return albums;
	}
	
	public void createAlbum(Album album) {
		Artist artist = artistRepository.findArtistByName(album.getArtist().getName());
		album.setArtist(artist);
		albumRepository.save(album);
	}

}
