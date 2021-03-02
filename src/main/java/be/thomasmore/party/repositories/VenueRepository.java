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

    /*Iterable<Venue> findByCapacityGreaterThanEqual(int min);
    @Query("SELECT v FROM Venue v WHERE v.capacity >= :min")
    List<Venue> findByBigCapacity(@Param("min") int min);

    @Query("SELECT v FROM Venue v WHERE v.capacity <= :max")
    List<Venue> findBySmallCapacity(@Param("max") int max);

    @Query("SELECT v FROM Venue v WHERE :min IS NULL OR v.capacity >= :min")
    List<Venue> findByBigCapacity(@Param("min") Integer min);*/
}