package org.logistics.data.repository;

import org.logistics.data.model.Booking;
import org.logistics.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking,String> {
//    Booking findByUsername(String username);

    List<Booking> findByUsername(String username);
}
