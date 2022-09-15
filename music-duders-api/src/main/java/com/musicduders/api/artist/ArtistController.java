package com.musicduders.api.artist;

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

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {
	
	@Autowired
	ArtistService artistService;
	
	@GetMapping
	public List<Artist> getArtists() {
		List<Artist> artists = artistService.getAllArtists();
		return artists;
	}
	
	@GetMapping("/{id}")
	public Optional<Artist> getArtist(@PathVariable Long id) {
		Optional<Artist> artist = artistService.getArtist(id);
		return artist;
	}
	
	@PostMapping
	public void postArtist(@RequestBody Artist artist) {
		artistService.createArtist(artist);
	}
	
	@PutMapping("/{id}")
	public void putArtist(@PathVariable Long id, @RequestBody Artist artist) {
		artistService.updateArtist(id, artist);
	}
	
	@DeleteMapping("/{id}")
	public void deleteArtist(@PathVariable Long id) {
		artistService.deleteArtist(id);
	}

}
