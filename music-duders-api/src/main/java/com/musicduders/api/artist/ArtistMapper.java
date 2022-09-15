package com.musicduders.api.artist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
	
	/**
	 * Converts an ArtistRequest to an Artist.
	 * 
	 * @param artistRequest
	 * @return Artist
	 */
	public Artist toArtist(ArtistRequest artistRequest) {
		Artist artist = new Artist();
		
		artist.setName(artistRequest.getName());
		
		return artist;
	}
	
	/**
	 * Converts an Artist to an ArtistResponse.
	 * 
	 * @param artist
	 * @return ArtistResponse
	 */
	public ArtistResponse toResponse(Artist artist) {
		ArtistResponse artistResponse = new ArtistResponse();
		
		artistResponse.setId(artist.getId());
		artistResponse.setName(artist.getName());
		
		return artistResponse;
	}
	
	/**
	 * Converts an Artist list to an ArtistResponse list.
	 * 
	 * @param artists
	 * @return List<ArtistResponse>
	 */
	public List<ArtistResponse> toResponse(List<Artist> artists) {
		List<ArtistResponse> artistResponseList = new ArrayList<ArtistResponse>();
		
		for (int i = 0; i < artists.size(); i++) {
			ArtistResponse artistResponse = new ArtistResponse();
			artistResponse.setId(artists.get(i).getId());
			artistResponse.setName(artists.get(i).getName());
			artistResponseList.add(artistResponse);
		}
		
		return artistResponseList;
	}

}
