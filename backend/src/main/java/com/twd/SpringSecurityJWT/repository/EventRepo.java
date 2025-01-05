package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByUserId(Long userId);


}
