package com.twd.SpringSecurityJWT.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private Long postId;
    private Long userId;
    private Long savedId;
    private Integer interactions;
    private Integer statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDate;
    private String role;
    private String password;
    private Integer age;
    private String bio;
    private String filiere;
    private String image;
    private OurUsers ourUsers;
    private String caption;
    private String filename;
    private MultipartFile fileMLTP ;
    private String location;
    private String tags;
    // Add a field for saved posts
    private byte[] imageData;
    private byte[] imageProfilData;
    private LocalDateTime creationdate;
    private List<ReqRes> post;
    private Boolean liked ;
    private Boolean saved ;
    private Resource imge ;
    ///
    private List<ReqRes> event;
    private Long eventId ;
    private String userid;
    private String eventName;
    private String eventDescription;
    private String organizer ;
    private String eventDate;
}
