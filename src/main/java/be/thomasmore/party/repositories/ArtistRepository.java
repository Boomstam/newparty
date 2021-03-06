package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    @Query("SELECT a FROM Artist a WHERE lower(a.artistName) LIKE concat('%', lower(:keyword), '%') OR " +
            "lower(a.bio) LIKE concat('%', lower(:keyword), '%') OR " +
            "lower(a.genre) LIKE concat('%', lower(:keyword), '%') OR " +
            "lower(a.portfolio) LIKE concat('%', lower(:keyword), '%')")
    List<Artist> findByKeyword(@Param("keyword") String keyword);

    List<Artist> findByIdIn(Integer[] ids);
}