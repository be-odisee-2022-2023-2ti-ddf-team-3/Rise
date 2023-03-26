package be.odisee.ti2.se4.rise.service;

import be.odisee.ti2.se4.rise.domain.Entry;
import be.odisee.ti2.se4.rise.domain.Project;
import be.odisee.ti2.se4.rise.formdata.EntryData;
import jakarta.validation.Valid;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RiseService {

    public Map<String, List<Project> > getCategoriesWithProjects();

    public Object getObjectives();

    public EntryData prepareNewEntryData();

    public String processEntry(@Valid EntryData entryData);

    public List<Entry> getEntriesFromDate(LocalDate theDate);

    public Duration getTotalDuration(List<Entry> entries);

    public EntryData prepareEntryDataToEdit(long id);

    public void deleteEntry(long id);
}
