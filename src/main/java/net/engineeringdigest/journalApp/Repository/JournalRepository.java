package net.engineeringdigest.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.engineeringdigest.journalApp.JournalEntry;

public interface JournalRepository extends MongoRepository<JournalEntry,ObjectId>{

}
