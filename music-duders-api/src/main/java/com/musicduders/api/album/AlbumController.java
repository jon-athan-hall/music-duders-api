package com.musicduders.api.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
	
	@Autowired
	AlbumService albumService;

	@GetMapping
	public List<Album> getAlbums() {
		List<Album> albums = albumService.getAlbums();
		return albums;
	}
}
