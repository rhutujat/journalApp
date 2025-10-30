package net.engineeringdigest.journalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.JournalEntry;
import net.engineeringdigest.journalApp.User;
import net.engineeringdigest.journalApp.Repository.JournalRepository;

@Component
//@Slf4j
public class JournalEntryService {

	@Autowired
	JournalRepository repo;
	@Autowired
	UserService userservice;

	// @Transactional
	public void addEntry(JournalEntry entry, String username) {
		try {
			User u = userservice.findByUsername(username);
			entry.setLocalDateTime(LocalDateTime.now());
			JournalEntry saved = repo.save(entry);
			u.getJournalentry().add(saved);
		//	u.setUsername(null);
			userservice.addEntry(u);
		} catch (Exception e) {
			System.out.println(e);
			//log.info("logs printed");
		//	log.trace("loggg trace");
		//	log.error("error shown ");
		}

	}

	public List<JournalEntry> getAll() {
		List<JournalEntry> l = repo.findAll();
		return l;
	}

	public Optional<JournalEntry> getById(ObjectId id) {
		return repo.findById(id);
	}

	public void deleteById(String username, ObjectId id) {
		User u = userservice.findByUsername(username);
		u.getJournalentry().removeIf(x -> x.getId().equals(id));
		userservice.addEntry(u);
		repo.deleteById(id);
	}

	public void updateEntry(JournalEntry oldentry) {

		repo.save(oldentry);
	}

}
