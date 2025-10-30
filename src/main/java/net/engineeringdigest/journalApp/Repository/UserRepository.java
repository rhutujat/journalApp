package net.engineeringdigest.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.engineeringdigest.journalApp.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	User findByUsername(String name);

}
