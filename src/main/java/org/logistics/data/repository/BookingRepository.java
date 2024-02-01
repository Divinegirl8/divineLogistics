package org.logistics.data.repository;

import org.logistics.data.model.Booking;
import org.logistics.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking,String> {
    Booking findByUsername(String username);
}
