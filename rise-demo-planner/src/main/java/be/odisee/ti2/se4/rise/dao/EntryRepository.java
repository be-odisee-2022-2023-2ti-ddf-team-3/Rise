package be.odisee.ti2.se4.rise.dao;

import be.odisee.ti2.se4.rise.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {

    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public Entry findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    public Entry findFirstByOrderByIdDesc();

    /**
     *
     * @param startDateTime
     * @param endDateTime
     * @return The entries with DateTimeFrom between startDateTime and endDateTime
     */
    public List<Entry> findByDateTimeFromBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
