package com.musicduders.api.artist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistRepository extends JpaRepository<Artist, String> {
	
	@Query("SELECT a from Artist WHERE a.name = ?1")
	Optional<Artist> findArtistByName(String name);

}
