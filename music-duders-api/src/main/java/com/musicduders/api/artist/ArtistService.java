package com.musicduders.api.artist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

	@Autowired
	ArtistRepository artistRepository;
	
	public List<Artist> getAllArtists() {
		List<Artist> artists = artistRepository.findAll();
		return artists;
	}
	
	public Optional<Artist> getArtist(Long id) {
		Optional<Artist> artist = artistRepository.findById(id);
		return artist;
	}
	
	public void createArtist(Artist artist) {
		artistRepository.save(artist);
	}
	
	public void updateArtist(Long id, Artist artist) {
		Optional<Artist> optionalArtist = artistRepository.findById(id);
		artist.setId(optionalArtist.get().getId());
		artistRepository.save(artist);
	}
	
	public void deleteArtist(Long id) {
		artistRepository.deleteById(id);
	}
	
}
