package com.musicduders.api.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicduders.api.artist.Artist;
import com.musicduders.api.artist.ArtistRepository;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ArtistRepository artistRepository;

	@GetMapping
	public List<Album> getAlbums() {
		List<Album> albums = albumService.getAllAlbums();
		return albums;
	}
	
	@PostMapping
	public void postAlbum(@RequestBody Album album) {
		albumService.createAlbum(album);
	}
	
}
