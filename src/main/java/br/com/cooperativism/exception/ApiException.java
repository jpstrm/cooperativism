package br.com.cooperativism.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApiException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -5603660003741702875L;
  private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

  public ApiException(final String msg) {
    super(msg);
  }

  public ApiException(final String msg, final HttpStatus httpStatus) {
    super(msg);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
