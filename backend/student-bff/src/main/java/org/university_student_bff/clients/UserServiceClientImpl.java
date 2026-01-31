package org.university_student_bff.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_student_bff.dtos.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserServiceClientImpl implements UserServiceClient {

    private WebClient userServiceWebClient;
    private WebClientErrorHandler webClientErrorHandler;

    @Autowired
    public UserServiceClientImpl(WebClient userServiceWebClient, WebClientErrorHandler webClientErrorHandler) {
        this.userServiceWebClient = userServiceWebClient;
        this.webClientErrorHandler = webClientErrorHandler;
    }

    @Override
    public LoginResponseDto login(String uri, LoginRequestDto loginRequestDto, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .bodyValue(loginRequestDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(LoginResponseDto.class).block();


    }


    @Override
    public SingleMessageDto register(String uri, RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(registerStudentDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        String setCookieHeader = response.headers().asHttpHeaders().getFirst(HttpHeaders.SET_COOKIE);

        if(setCookieHeader != null) {
            httpServletResponse.setHeader(HttpHeaders.SET_COOKIE, setCookieHeader);
        }

        return response.bodyToMono(SingleMessageDto.class).block();

    }


    @Override
    public SingleMessageDto resendActivationEmail(String uri, String cookie, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .get()
                .uri(uri)
                .header(HttpHeaders.COOKIE, cookie)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }


    @Override
    public Void activateRegistration(String uri, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleErrorAndRedirectForRegisterActivation(response);

        return response.bodyToMono(Void.class).block();

    }

    @Override
    public UpdateStudentDto getUpdateStudent(String uri, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(UpdateStudentDto.class).block();

    }

    @Override
    public UpdateStudentDto updateStudent(String uri, String authHeader, UpdateStudentDto updateStudentDto) {

        ClientResponse response = this.userServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(updateStudentDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(UpdateStudentDto.class).block();
    }

    @Override
    public LoginResponseDto updateEmail(String uri, String authHeader, UpdateEmailDto updateEmailDto) {

        ClientResponse response = this.userServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(updateEmailDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(LoginResponseDto.class).block();

    }

    @Override
    public SingleMessageDto updatePassword(String uri, String authHeader, ChangePasswordDto changePasswordDto) {

        ClientResponse response = this.userServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content_Type", "application/json")
                .bodyValue(changePasswordDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }

    @Override
    public Void confirmPasswordChange(String uri, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleErrorAndRedirectForChangePasswordException(response);

        return response.bodyToMono(Void.class).block();
    }

    @Override
    public List<RegisterExamDto> getRegisteredExams(String uri, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(RegisterExamDto.class)
                .collectList()
                .block();

    }



}
