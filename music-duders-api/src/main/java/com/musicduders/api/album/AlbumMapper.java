package com.musicduders.api.album;

import org.springframework.stereotype.Component;

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

}
