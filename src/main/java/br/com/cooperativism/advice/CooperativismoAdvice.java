package br.com.cooperativism.advice;

import br.com.cooperativism.exception.ApiException;
import br.com.cooperativism.exception.error.ApiError;
import br.com.cooperativism.exception.error.ApiError.ApiErrorBuilder;
import br.com.cooperativism.exception.error.ValidationError;
import br.com.cooperativism.exception.error.ValidationErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@RestControllerAdvice
public class CooperativismoAdvice extends ResponseEntityExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(CooperativismoAdvice.class);

  private static final String MESSAGE_FORMAT = "{} - {}";

  /**
   * Erros da Api.
   */
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiError> handleApiException(final ApiException ex) {
    final String msg = "Api Error";
    logger.error(MESSAGE_FORMAT, msg, ex.getLocalizedMessage());
    ApiError error = ApiErrorBuilder.builder()
        .message(msg)
        .detail(ex.getLocalizedMessage())
        .status(ex.getHttpStatus())
        .build();
    return ResponseEntity.status(error.getStatus()).body(error);
  }

  /**
   * Erros de acesso a banco de dados.
   */
  @ExceptionHandler({SQLException.class, DataAccessException.class})
  @ResponseStatus(code = HttpStatus.CONFLICT)
  public ApiError databaseException(final Exception ex) {
    final String msg = "Erro de banco de dados.";
    logger.error(MESSAGE_FORMAT, msg, ex.getMessage());
    return ApiErrorBuilder.builder()
        .status(HttpStatus.CONFLICT)
        .message(msg)
        .detail(ex.getLocalizedMessage()).build();
  }

  /**
   * Tratamento de Erros com argumentos inválidos.
   */
  @Override
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.trace("=== handleMethodArgumentNotValid");
    final ValidationError error =
        ValidationErrorBuilder.fromBindingErrors(ex.getBindingResult());
    logger.error(MESSAGE_FORMAT, error.getErrorMessage(),
        ex.getLocalizedMessage());
    final ApiError apiError = ApiErrorBuilder.builder()
        .message(error.getErrorMessage())
        .status(HttpStatus.BAD_REQUEST)
        .detail(error.getErrors().toString()).build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
  }


}
