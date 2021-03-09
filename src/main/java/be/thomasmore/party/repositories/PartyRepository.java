package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PartyRepository extends CrudRepository<Party, Integer> {
    @Query("SELECT p FROM Party p WHERE lower(p.name) LIKE concat('%', lower(:keyword), '%') OR " +
            "lower(p.extraInfo) LIKE concat('%', lower(:keyword), '%')")
    List<Party> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p FROM Party p WHERE p.venue = :venue")
    List<Party> findByVenue(@Param("venue") Optional<Venue> venue);
}