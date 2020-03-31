package br.com.cooperativism.enums;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Optional;

public enum VoteStatusEnum {

  PENDING("PENDENTE"),
  VALID("VALIDO"),
  INVALID("INVALIDO");

  public String value;

  VoteStatusEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static Optional<VoteStatusEnum> find(String value){

    return Arrays.stream(values())
        .filter(v -> v.getValue().equals(Strings.toRootUpperCase(value)))
        .findFirst();
  }

}
