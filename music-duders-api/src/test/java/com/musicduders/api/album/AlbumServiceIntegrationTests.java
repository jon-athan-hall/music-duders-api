package com.musicduders.api.album;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.musicduders.api.artist.Artist;

public class AlbumServiceIntegrationTests {
	
	/**
	 * With this annotation, Spring will create the beans and add
	 * them to the application context.
	 */
	@MockBean
	private AlbumRepository albumRepository;
	
	@Autowired
	private AlbumService albumService;
	
	@Test
	@DisplayName("The album service get all albums if no exception occurs.")
	void albumServiceGetAllAlbumsTest() {
		Artist testArtistOne = new Artist();
		testArtistOne.setName("The Weakerthans");
		
		Artist testArtistTwo = new Artist();
		testArtistTwo.setName("Weezer");
		
		Album testAlbumOne = new Album();
		testAlbumOne.setArtist(testArtistOne);
		testAlbumOne.setTitle("Left and Leaving");
		testAlbumOne.setUrl("https://theweakerthans.org");
		
		Album testAlbumTwo = new Album();
		testAlbumTwo.setArtist(testArtistTwo);
		testAlbumTwo.setTitle("The Blue Album");
		testAlbumOne.setUrl("https://weezer.com");
		
		Album testAlbumThree = new Album();
		testAlbumThree.setArtist(testArtistOne);
		testAlbumThree.setTitle("Fallow");
		testAlbumThree.setUrl("https://theweakerthans.org");
		
		List<Album> testAlbums = List.of(testAlbumOne, testAlbumTwo, testAlbumThree);
		
		when(albumRepository.findAll()).thenReturn(testAlbums);
		
		List<Album> albums = albumService.getAllAlbums();
		
		assert(albums).equals(testAlbums);
	}

}
