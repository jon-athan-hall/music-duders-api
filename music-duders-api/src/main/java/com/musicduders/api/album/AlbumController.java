package com.musicduders.api.album;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicduders.api.artist.ArtistService;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private AlbumMapper albumMapper;

	@GetMapping
	public List<AlbumResponse> getAlbums() {
		List<Album> albums = albumService.getAllAlbums();
		return albumMapper.toResponse(albums);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')") or hasAnyRole, hasAuthority, hasAnyAuthority
	public AlbumResponse getAlbum(@PathVariable String id) {
		Optional<Album> album = albumService.getAlbum(id);
		return albumMapper.toResponse(album.get());
	}
	
	@PostMapping
	public void postAlbum(@RequestBody AlbumRequest albumRequest) {
		artistService.createAlbum(albumRequest);
	}
	
	@PutMapping("/{id}")
	public void putAlbum(@PathVariable String id, @RequestBody AlbumRequest albumRequest) {
		artistService.updateAlbum(id, albumRequest);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAlbum(@PathVariable String id) {
		albumService.deleteAlbum(id);
	}
	
}
