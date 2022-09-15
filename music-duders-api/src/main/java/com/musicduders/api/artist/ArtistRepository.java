package com.musicduders.api.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
	@Query("SELECT a from Artist a WHERE a.name = ?1")
	Artist findArtistByName(String name);

}
