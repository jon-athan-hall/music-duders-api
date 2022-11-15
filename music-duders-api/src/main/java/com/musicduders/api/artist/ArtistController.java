package com.musicduders.api.artist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private ArtistMapper artistMapper;
	
	@GetMapping
	public List<ArtistResponse> getArtists() {
		List<Artist> artists = artistService.getAllArtists();
		return artistMapper.toResponse(artists);
	}
	
	@GetMapping("/{id}")
	public ArtistResponse getArtist(@PathVariable("id") String id) {
		Optional<Artist> artist = artistService.getArtist(id);
		return artistMapper.toResponse(artist.get());
	}
	
	@PostMapping
	public void postArtist(@RequestBody ArtistRequest artist) {
		artistService.createArtist(artist);
	}
	
	@PutMapping("/{id}")
	public void putArtist(@PathVariable String id, @RequestBody Artist artist) {
		artistService.updateArtist(id, artist);
	}
	
	@DeleteMapping("/{id}")
	public void deleteArtist(@PathVariable String id) {
		artistService.deleteArtist(id);
	}

}
