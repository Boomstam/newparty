package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    @Query("SELECT v FROM Venue v WHERE (:min IS NULL OR v.capacity >= :min) AND " +
            "(:max IS NULL OR v.capacity <= :max)")
    List<Venue> findByCapacity(@Param("min") Integer min, @Param("max") Integer max);
}