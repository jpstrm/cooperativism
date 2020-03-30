package br.com.cooperativism.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SessionStatusEnum {

  STARTED("EM_ANDAMENTO"),
  EXPIRED("EXPIRADA");

  private String value;

  SessionStatusEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static Optional<SessionStatusEnum> find(String value){
    return Arrays.stream(values())
        .filter(v -> v.getValue().equals(value))
        .findFirst();
  }
}
