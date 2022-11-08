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
public class AlbumServiceUnitTests {
	
	/**
	 * AlbumRepository must be mocked since it is a dependency of AlbumService, and
	 * these are unit tests.
	 */
	@Mock
	private AlbumRepository albumRepository;
	
	// AlbumService is the class actually being tested here.
	@InjectMocks
	private AlbumService albumService;
	
	@Test
	@DisplayName("The album service gets all albums if no exception occurs.")
	public void getAllAlbumsHappyFlow() {
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
