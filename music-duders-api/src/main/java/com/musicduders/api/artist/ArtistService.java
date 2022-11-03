package com.musicduders.api.artist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicduders.api.album.Album;
import com.musicduders.api.album.AlbumMapper;
import com.musicduders.api.album.AlbumRepository;
import com.musicduders.api.album.AlbumRequest;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ArtistMapper artistMapper;
	
	@Autowired
	private AlbumMapper albumMapper;
	
	public List<Artist> getAllArtists() {
		List<Artist> artists = artistRepository.findAll();
		return artists;
	}
	
	public Optional<Artist> getArtist(String id) {
		Optional<Artist> artist = artistRepository.findById(id);
		return artist;
	}
	
	public void createArtist(ArtistRequest artistRequest) {
		Artist artist = artistMapper.toArtist(artistRequest);
		artistRepository.save(artist);
	}
	
	public void updateArtist(String id, Artist artist) {
		Optional<Artist> optionalArtist = artistRepository.findById(id);
		artist.setId(optionalArtist.get().getId());
		artistRepository.save(artist);
	}
	
	public void deleteArtist(String id) {
		artistRepository.deleteById(id);
	}
	
	public void createAlbum(AlbumRequest albumRequest) {
		Artist artist = new Artist();
		Album album = albumMapper.toAlbum(albumRequest);
		
		Optional<Artist> optionalArtist = artistRepository.findArtistByName(albumRequest.getArtistName());
		
		if (optionalArtist.isPresent()) {
			artist = optionalArtist.get();
		} else {
			artist.setName(albumRequest.getArtistName());
		}
		
		album.setArtist(artist);
		
		artistRepository.save(artist);
		albumRepository.save(album);
	}
	
	public void updateAlbum(String id, AlbumRequest albumRequest) {
		Artist artist = new Artist();
		Album album = albumMapper.toAlbum(albumRequest);
		
		Optional<Artist> optionalArtist = artistRepository.findArtistByName(albumRequest.getArtistName());
		Optional<Album> optionalAlbum = albumRepository.findById(id);
		
		if (optionalArtist.isPresent()) {
			artist = optionalArtist.get();
		} else {
			artist.setName(albumRequest.getArtistName());
		}
		
		album.setArtist(artist);
		album.setId(optionalAlbum.get().getId());
		
		artistRepository.save(artist);
		albumRepository.save(album);
	}
	
}
