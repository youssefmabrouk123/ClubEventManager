package com.twd.SpringSecurityJWT.service;
import com.twd.SpringSecurityJWT.entity.Event;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.entity.Participation;
import com.twd.SpringSecurityJWT.repository.EventRepo;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import com.twd.SpringSecurityJWT.repository.ParticipationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EventService {

    private static final String UPLOAD_DIR = "C:\\Users\\Mabrouk Youssef\\Desktop\\Nouveau dossier\\backend\\eventImg"; // Update this with your upload directory path
//private static final String UPLOAD_DIR = "C:\\Users\\dell\\Desktop\\App_Social_Media\\backend\\eventImg"; // Update this with your upload directory path

    @Autowired
    private final EventRepo eventRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private final OurUserRepo userRepository;

    @Autowired
    private  ParticipationRepo participationRepository;

    @Autowired
    public EventService(EventRepo eventRepository, OurUserRepo userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }
    public void addEvent(Event event) {
        // Add any additional business logic or validation here
        eventRepository.save(event);
    }
    public String saveEvent(Event event, MultipartFile file) throws IOException {
        String filename = saveFile(file);
        event.setFilename(filename);
        eventRepository.save(event);
        return filename;
    }


    public List<Event> findByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }


    public String saveFile(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the upload directory
        String filename = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, filename);
        Files.write(filePath, file.getBytes());

        return UPLOAD_DIR+"/"+filename;
    }

    public List<Participation> getParticipatedEventsByUser(Long userId) {
        return participationRepository.findByUserId(userId);
    }

    public Event getEventById(Long eventid) {
        // Save the post to the database using repository methods
        return eventRepository.findById(eventid).orElse(null);}

    public boolean existsById(Long id) {
        return eventRepository.existsById(id);
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }



    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setEventName(updatedEvent.getEventName());
            event.setEventDescription(updatedEvent.getEventDescription());
            event.setEventDate(updatedEvent.getEventDate());
            event.setLocation(updatedEvent.getLocation());
            event.setOrganizer(updatedEvent.getOrganizer());

            List<Participation> participants = participationRepository.findByEventId(id);

            for (Participation participant : participants) {
                sendUpdateEmail(participant.getUser().getEmail(), event);
            }

            return eventRepository.save(event);





        }).orElse(null);
    }

    public void sendUpdateEmail(String participantEmail, Event updatedEvent) {
        // Use your email service to send an email to the participant
        String subject = "Event Updated: " + updatedEvent.getEventName();
        String body = "Dear participant, \n\nThe event '" + updatedEvent.getEventName() + "' has been updated. "
                + "Here are the new details:\n\n"
                + "Description: " + updatedEvent.getEventDescription() + "\n"
                + "Date: " + updatedEvent.getEventDate() + "\n"
                + "Location: " + updatedEvent.getLocation() + "\n"
                + "Organizer: " + updatedEvent.getOrganizer() + "\n\n"
                + "We look forward to your participation!";

        // Assuming you have a MailService to handle email sending
        emailService.sendEmail(participantEmail, subject, body);
    }


}
