package net.engineeringdigest.journalApp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

	private Map<ObjectId, JournalEntry> entries = new HashMap<>();

	@GetMapping("/getAll")
	public List<JournalEntry> getAll() {
		return new ArrayList<>(entries.values());
	}

	@PostMapping("/addEntry")
	public boolean addEntry(@RequestBody JournalEntry myentry) {

		entries.put(myentry.getId(), myentry);
		System.out.println("Entry added");
		return true;
	}

	@GetMapping("/getbyId/{myid}")

	public JournalEntry getbyId(@PathVariable ObjectId myid) {

		return entries.get(myid);

	}

	@DeleteMapping("/removebyId/{myid}")
	public JournalEntry removebyId(@PathVariable ObjectId myid) {

		return entries.remove(myid);

	}

	@PutMapping("/updatebyId/{myId}")
	public JournalEntry updatebyId(@PathVariable ObjectId myId, @RequestBody JournalEntry entry) {
		return entries.put(myId, entry);

	}
}
