package com.englishlearning.evaluasi_service.Service;

import com.englishlearning.evaluasi_service.Dto.StudentDto;
import com.englishlearning.evaluasi_service.Dto.TeacherDto;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.englishlearning.evaluasi_service.Dto.OurStudentDto;
import com.englishlearning.evaluasi_service.Dto.OurTeacherDto;
import com.englishlearning.evaluasi_service.Dto.ReqRes;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_BASE_URL = "http://localhost:8080";

    public StudentDto getUserProfile(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token.trim());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = USER_SERVICE_BASE_URL + "/adminuser/get-profile";

        try {
            // Call the external API
            ResponseEntity<OurStudentDto> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    OurStudentDto.class);

            // Return the profile data if the response body is not null
            if (response.getBody() != null) {
                return response.getBody().getOurUser();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found");
        } catch (HttpClientErrorException e) {
            // Handle specific HTTP error statuses
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to user profile");
            } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access to user profile is forbidden");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found");
            } else {
                throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
            }
        } catch (Exception e) {
            // Handle any other exceptions
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching the user profile", e);
        }
    }

    public List<StudentDto> getStudentList(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = USER_SERVICE_BASE_URL + "/teacher/student-list";

        try {
            // Change the response type to ReqRes
            ResponseEntity<ReqRes> response = restTemplate.exchange(url, HttpMethod.GET, entity, ReqRes.class);

            // Extract the list of students from the ReqRes object
            return response.getBody().getOurUserList();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to student list");
            } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access to student list is forbidden");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student list not found");
            } else {
                throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching the student list", e);
        }
    }

    public StudentDto getStudentId(String token, String studentId) {
        try {
            List<StudentDto> students = getStudentList(token);

            // Filter the student by ID and return the StudentDto
            return students.stream().filter(student -> studentId.equals(student.getId())).findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching the student details", e);
        }
    }

    public String getUserId(String token) {
        try {
            StudentDto userProfile = getUserProfile(token);

            if (userProfile != null) {
                return userProfile.getId();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID not found");
        } catch (ResponseStatusException e) {
            // Re-throw the exception to maintain status codes
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching the user ID", e);
        }
    }

    public TeacherDto getTeacherProfile(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token.trim());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = USER_SERVICE_BASE_URL + "/adminuser/get-profile";

        try {
            // Call the external API
            ResponseEntity<OurTeacherDto> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    OurTeacherDto.class);

            // Check if the response body is not null
            if (response.getBody() != null) {
                TeacherDto teacherDto = response.getBody().getOurUser();
                // Check if the role is "teacher"
                if (!"teacher".equalsIgnoreCase(teacherDto.getRole())) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized as a teacher");
                }
                return teacherDto; // Return the teacher profile if the role is valid
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher profile not found");
        } catch (HttpClientErrorException e) {
            // Handle specific HTTP error statuses
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to teacher profile");
            } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access to teacher profile is forbidden");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher profile not found");
            } else {
                throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
            }
        } catch (Exception e) {
            // Handle any other exceptions
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching the teacher profile", e);
        }
    }

}