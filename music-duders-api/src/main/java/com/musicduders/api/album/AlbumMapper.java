package com.musicduders.api.album;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.musicduders.api.artist.Artist;
import com.musicduders.api.artist.ArtistResponse;

@Component
public class AlbumMapper {
	
	/**
	 * Converts an AlbumRequest to an Album, but does not take
	 * care of setting the Artist.
	 * 
	 * @param albumRequest
	 * @return Album
	 */
	public Album toAlbum(AlbumRequest albumRequest) {
		Album album = new Album();
		
		album.setTitle(albumRequest.getTitle());
		album.setUrl(albumRequest.getUrl());
		
		return album;
	}
	
	/**
	 * Converts an Album to an AlbumResponse.
	 * 
	 * @param album
	 * @return AlbumResponse
	 */
	public AlbumResponse toResponse(Album album) {
		AlbumResponse albumResponse = new AlbumResponse();
		
		albumResponse.setTitle(album.getTitle());
		albumResponse.setUrl(album.getUrl());
		albumResponse.setArtistName(album.getArtist().getName());
		
		return albumResponse;
	}

	
	/**
	 * Converts an Album list to an AlbumResponse list.
	 * 
	 * @param albums
	 * @return List<AlbumResponse>
	 */
	public List<AlbumResponse> toResponse(List<Album> albums) {
		List<AlbumResponse> albumResponseList = new ArrayList<AlbumResponse>();
		
		for (int i = 0; i < albums.size(); i++) {
			AlbumResponse albumResponse = new AlbumResponse();
			albumResponse.setTitle(albums.get(i).getTitle());
			albumResponse.setUrl(albums.get(i).getUrl());
			albumResponse.setArtistName(albums.get(i).getArtist().getName());
			albumResponseList.add(albumResponse);
		}
		
		return albumResponseList;
	}

}
