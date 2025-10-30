package net.engineeringdigest.journalApp.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.JournalEntry;
import net.engineeringdigest.journalApp.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;

@RestController
@RequestMapping("/journalv2")
public class JournalEntryControllerV2 {

	@Autowired
	JournalEntryService service;

	@Autowired
	UserService userservice;

	@GetMapping("/getJournalEntriesofUser/{username}")
	public List<JournalEntry> getJournalEntriesofUser(@PathVariable String username) {
		User u = userservice.findByUsername(username);
		List<JournalEntry> l = u.getJournalentry();
		return l;
	}

	@PostMapping("/addEntry/{username}")
	public boolean addEntry(@RequestBody JournalEntry myentry , @PathVariable String username) {
		
		service.addEntry(myentry , username);

		System.out.println("Entry added");
		return true;
	}

	@GetMapping("/getbyId/{myid}")
	public JournalEntry getbyId(@PathVariable ObjectId myid) {

		return service.getById(myid).orElse(null);

	}

	@DeleteMapping("/removebyId/{username}/{myid}")
	public boolean removebyId(@PathVariable String username,@PathVariable ObjectId myid) {
		service.deleteById(username,myid);
		return true;

	}

	@PutMapping("/updatebyId/{username}/{myId}")
	public JournalEntry updatebyId(@PathVariable ObjectId myId, @RequestBody JournalEntry newentry , String username) {
		JournalEntry oldentry = service.getById(myId).orElse(null);
		if (oldentry != null) {
			oldentry.setTitle(newentry.getTitle() != null && !newentry.getTitle().equals("") ? newentry.getTitle()
					: oldentry.getTitle());
			oldentry.setContent(
					newentry.getContent() != null && !newentry.getContent().equals("") ? newentry.getContent()
							: oldentry.getContent());
		}
		service.updateEntry(oldentry);
		return oldentry;

	}
}
