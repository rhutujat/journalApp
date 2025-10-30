package net.engineeringdigest.journalApp.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.JournalApplication;
import net.engineeringdigest.journalApp.JournalEntry;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.User;
import net.engineeringdigest.journalApp.Repository.UserRepository;

@Component
@Slf4j
public class UserService {

	@Autowired
	UserRepository repo;


	public void addEntry(User entry) {
		try {
		repo.save(entry);
		}
		catch(Exception e)
		{
			System.out.println("inside userservice catch block");
			log.info("error");
			log.warn("warning");
			log.error("errrrorr");
		}
	}

	public List<User> getAll() {
		List<User> l = repo.findAll();
		return l;
	}

	public Optional<User> getById(ObjectId id) {
		return repo.findById(id);

	}

	public void deleteById(ObjectId id) {
		repo.deleteById(id);
	}

	public User findByUsername(String name) {

		return repo.findByUsername(name);
	}

}
