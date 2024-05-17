package ma.enset.ebankingbackend.web;

import lombok.RequiredArgsConstructor;
import ma.enset.ebankingbackend.dtos.ApiErrorDto;
import ma.enset.ebankingbackend.exceptions.GlobalApiException;
import ma.enset.ebankingbackend.exceptions.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


/**
 * Exception handler for the controller level
 * Customizing exceptions to send a clear
 * and unified response to the client
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle all the exceptions that extends RuntimeException.
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiErrorDto> handleApiGlobalException(RuntimeException exception) {
        exception.printStackTrace();
        // Définit le statut comme 'INTERNAL_SERVER_ERROR'.
        HttpStatus status = INTERNAL_SERVER_ERROR;

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class})
    public ResponseEntity<ApiErrorDto> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception) {
        exception.printStackTrace();
        // Définit le statut comme 'INTERNAL_SERVER_ERROR'.
        HttpStatus status = BAD_REQUEST;

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }

    /**
     * Handle all the exceptions that extends GlobalApiException.
     */
    @ExceptionHandler(value = {GlobalApiException.class})
    public ResponseEntity<ApiErrorDto> handleApiGlobalException(GlobalApiException exception) {
        exception.printStackTrace();
        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(getApiError(exception));
    }

    /**
     * Handle all the exceptions that extends DataIntegrityViolationException.
     */
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<ApiErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        exception.printStackTrace();
        // Définit le statut comme 'BAD_REQUEST'.
        HttpStatus status = BAD_REQUEST;

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }


    private ApiErrorDto getApiError(RuntimeException exception, HttpStatus status) {
        return ApiErrorDto.builder()
                .message(exception.getMessage())
                .status(status.value())
                .build();
    }

    private ApiErrorDto getApiError(GlobalApiException exception) {
        return ApiErrorDto.builder()
                .message(exception.getMessage())
                .status(exception.getHttpStatus().value())
                .build();
    }


    /**
     * Gère les exceptions de type BadCredentialsException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur d'authentification non valide.
     */
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiErrorDto> handleBadCredentialsException(BadCredentialsException exception) {
        exception.printStackTrace();
        // Crée une réponse d'erreur d'authentification non valide en utilisant le message correspondant.
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }

    /**
     * Gère les exceptions de type BadCredentialsException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur d'authentification non valide.
     */
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ApiErrorDto> handleIllegalArgumentException(BadRequestException exception) {
        exception.printStackTrace();

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(getApiError(exception));
    }

    @ExceptionHandler(value = {AccountStatusException.class})
    public ResponseEntity<ApiErrorDto> handleAuthException(AccountStatusException exception) {
        exception.printStackTrace();
        HttpStatus status = HttpStatus.FORBIDDEN;
        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }

    // Handle access denied exception
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ApiErrorDto> handleAuthException(AccessDeniedException exception) {
        exception.printStackTrace();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }

    @ExceptionHandler(value = {AuthorizationServiceException.class})
    public ResponseEntity<ApiErrorDto> handleAuthException(AuthorizationServiceException exception) {
        exception.printStackTrace();
        HttpStatus status = HttpStatus.FORBIDDEN;
        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity
                .status(status)
                .body(getApiError(exception, status));
    }
    /**
     * Gère les exceptions de type AuthenticationException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur d'authentification.
     */
//    @ExceptionHandler(value = {AuthenticationException.class})
//    public ResponseEntity<ResponseModel> handleAuthenticationException(AuthenticationException exception){
//        // Définit le statut comme 'FORBIDDEN'.
//        HttpStatus status = HttpStatus.FORBIDDEN;
//
//        // Crée une réponse d'erreur en utilisant l'exception et son statut.
//        ResponseModel apiException = ResponseModel.error(
//                exception.getMessage(),
//                status
//        );
//
//        // Renvoie la ResponseEntity avec la réponse d'erreur.
//        return ResponseEntity.status(status).body(apiException);
//    }

    /**
     * Gère les exceptions de type AccessDeniedException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur d'accès refusé.
     */
//    @ExceptionHandler(value = {AccessDeniedException.class})
//    public ResponseEntity<ResponseModel> handleAccessDeniedException(AccessDeniedException exception){
//        // Définit le statut comme 'FORBIDDEN'.
//        HttpStatus status = HttpStatus.FORBIDDEN;
//
//        // Crée une réponse d'erreur en utilisant l'exception et son statut.
//        ResponseModel apiException = ResponseModel.error(
//                exception.getMessage(),
//                status
//        );
//
//        // Renvoie la ResponseEntity avec la réponse d'erreur.
//        return ResponseEntity.status(status).body(apiException);
//    }
    /**
     * Pour minimiser la répétition, cette méthode génère une réponse d'erreur d'authentification non autorisée.
     */
//    private ResponseModel getUnauthorizedException(String message) {
//        // Définit le statut comme 'UNAUTHORIZED'.
//        HttpStatus status = HttpStatus.UNAUTHORIZED;
//
//        // Crée une réponse d'erreur d'authentification non autorisée en utilisant un message spécifique.
//        return ResponseModel.error(
//                message,
//                status
//        );
//    }

}
