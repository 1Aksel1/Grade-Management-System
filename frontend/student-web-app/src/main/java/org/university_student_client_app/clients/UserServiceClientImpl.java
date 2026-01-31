package org.university_student_client_app.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_student_client_app.dto.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;

@Service
public class UserServiceClientImpl implements UserServiceClient {

    private WebClient studentBffServiceClient;

    @Autowired
    public UserServiceClientImpl(WebClient studentBffServiceClient) {
        this.studentBffServiceClient = studentBffServiceClient;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        String uri = "/login";

        return this.studentBffServiceClient
                .post()
                .uri(uri)
                .bodyValue(loginRequestDto)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToMono(LoginResponseDto.class)
                .block();

    }

    @Override
    public SingleMessageDto register(RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse) {

        String uri = "/register";

        ClientResponse response = this.studentBffServiceClient
                .post()
                .uri(uri)
                .bodyValue(registerStudentDto)
                .exchange().block();


        if (response.statusCode().is4xxClientError()) {

            String errorMessage = response.bodyToMono(String.class).block();
            throw new RuntimeException("Client Error: " + errorMessage);

        } else if (response.statusCode().is5xxServerError()) {

            String errorMessage = response.bodyToMono(String.class).block();
            throw new RuntimeException("Server Error: " + errorMessage);

        }


        String jsessionId = response.cookies()
                .getFirst("JSESSIONID")
                .getValue();

        ResponseCookie responseCookie = ResponseCookie.from("JSESSIONID", jsessionId)
                .httpOnly(true)
                .path("/") //resendActivationEmail
                .maxAge(Duration.ofMinutes(5))
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return response.bodyToMono(SingleMessageDto.class).block();

    }

    @Override
    public UpdateStudentDto getUpdateStudentDto(String jwt) {

        String uri = "/profile";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<UpdateStudentDto>() {})
                .block()
                .getBody();

    }

    @Override
    public UpdateStudentDto updateStudent(String jwt, UpdateStudentDto updateStudentDto) {

        String uri = "/profile";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(updateStudentDto)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToMono(UpdateStudentDto.class)
                .block();

    }


    @Override
    public LoginResponseDto updateEmail(String jwt, UpdateEmailDto updateEmailDto) {

        String uri = "/profile/changeEmail";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(updateEmailDto)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToMono(LoginResponseDto.class)
                .block();

    }


    @Override
    public SingleMessageDto updatePassword(String jwt, ChangePasswordDto changePasswordDto) {

        String uri = "/profile/changePassword";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(changePasswordDto)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToMono(SingleMessageDto.class)
                .block();

    }


    @Override
    public List<RegisterExamDto> getRegisteredExams(String jwt) {

        String uri = "/getAllRegisteredExams";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToFlux(RegisterExamDto.class)
                .collectList()
                .block();


    }
}
