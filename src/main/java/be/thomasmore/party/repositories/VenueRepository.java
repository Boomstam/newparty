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

    @Query("SELECT v FROM Venue v WHERE (:minCapacity IS NULL OR v.capacity >= :minCapacity) AND " +
            "(:maxCapacity IS NULL OR v.capacity <= :maxCapacity) AND " +
            "(:maxDistance IS NULL OR v.distanceFromPublicTransportInKm <= :maxDistance) AND " +
            "(:isFoodProvided IS NULL OR v.foodProvided is :isFoodProvided) AND " +
            "(:isIndoor IS NULL OR v.indoor is :isIndoor) AND " +
            "(:isOutdoor IS NULL OR v.outdoor is :isIndoor)")
    List<Venue> findByCompositeFilters(
            @Param("minCapacity") Integer minCapacity, @Param("maxCapacity") Integer maxCapacity, @Param("maxDistance") Double maxDistance,
            @Param("isFoodProvided") Boolean isFoodProvided, @Param("isIndoor") Boolean isIndoor, @Param("isOutdoor") Boolean isOutdoor);

    @Query("SELECT v FROM Venue v WHERE (:isFoodProvided IS NULL OR v.foodProvided is :isFoodProvided) AND " +
            "(:minCapacity IS NULL OR v.capacity >= :minCapacity) AND " +
            //"(:maxCapacity IS NULL OR v.capacity <= :maxCapacity) AND " +
            "(:maxCapacity IS NULL OR v.capacity <= :maxCapacity)")
    List<Venue> findByFoodProvided(@Param("isFoodProvided") Boolean isFoodProvided,
                                   @Param("minCapacity") Integer minCapacity,
                                   @Param("maxCapacity") Integer maxCapacity);
                                   //@Param("maxDistance") Integer maxDistance);
    @Query("SELECT v FROM Venue v WHERE (:maxDistance IS NULL OR v.distanceFromPublicTransportInKm <= :maxDistance)")
    List<Venue> findByMaxDistance(@Param("maxDistance") Double maxDistance);
}