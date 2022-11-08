package com.musicduders.api.album;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musicduders.api.artist.Artist;

@ExtendWith(MockitoExtension.class)
public class AlbumControllerUnitTests {
	
	@Mock
	private AlbumService albumService;
	
	@Mock
	private AlbumMapper albumMapper;
	
	// AlbumService is the class actually being tested here.
	@InjectMocks
	private AlbumController albumController;
	
	@Test
	@DisplayName("The album controller gets one album by id if no exception occurs.")
	public void getAlbumHappyFlow() {
		Artist testArtist = new Artist();
		testArtist.setName("The Weakerthans");
		
		Album testAlbum = new Album();
		testAlbum.setArtist(testArtist);
		testAlbum.setTitle("Left and Leaving");
		testAlbum.setUrl("https://www.youtube.com/watch?v=0LbPzvQf-U4");
		
		AlbumResponse testAlbumResponse = new AlbumResponse();
		testAlbumResponse.setArtistName(testArtist.getName());
		testAlbumResponse.setTitle(testAlbum.getTitle());
		testAlbumResponse.setUrl(testAlbum.getUrl());
		
		when(albumService.getAlbum(null)).thenReturn(Optional.of(testAlbum));
		when(albumMapper.toAlbum(null)).thenReturn(testAlbum);
		
		AlbumResponse albumResponse = albumController.getAlbum(testAlbum.getId());
		
		assert(albumResponse).equals(testAlbumResponse);
	}

}
