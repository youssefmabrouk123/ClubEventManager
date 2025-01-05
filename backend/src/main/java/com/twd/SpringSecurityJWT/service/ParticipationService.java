package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Event;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.entity.Participation;
import com.twd.SpringSecurityJWT.repository.ParticipationRepo;
import com.twd.SpringSecurityJWT.service.EmailService;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParticipationService {

    @Autowired
    private ParticipationRepo participationRepository;

    @Autowired
    private EmailService emailService; // Injection du service email

    public void addParticipation(Event event, OurUsers user) throws MessagingException {
        Participation participation = new Participation();
        participation.setParticipationDate(LocalDateTime.now());
        participation.setEvent(event);
        participation.setUser(user);
        participationRepository.save(participation);

        // Envoi de l'email de confirmation
        emailService.sendConfirmationEmail(user.getEmail(), event.getEventName() , event.getUser().getUsername());
    }

    public boolean deleteParticipation(Event event, OurUsers user) {
        // Check if the participation exists for the given event and user
        Participation participation = participationRepository.findByEventAndUser(event, user);

        if (participation != null) {
            participationRepository.delete(participation);
            return true;
        } else {
            return false;
        }
    }

    public boolean existsByEventAndUser(Event event, OurUsers user) {
        return participationRepository.existsByEventAndUser(event, user);
    }

    public List<Participation> getParticipationsByEvent(Long eventId) {
        return participationRepository.findByEventId(eventId);
    }

    @Transactional
    public void deleteParticipationsByEventId(Long eventId) {
        participationRepository.deleteByEventId(eventId);
    }


}