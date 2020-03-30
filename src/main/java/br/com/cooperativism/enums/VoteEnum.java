package br.com.cooperativism.enums;

import java.util.Arrays;
import java.util.Optional;

public enum VoteEnum {

  YES("SIM"),
  NO("NAO");

  public String value;

  VoteEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static Optional<VoteEnum> find(String value){
    final String valueFiltered = value
        .replace("YES", "SIM")
        .replace("NO", "NAO")
        .replace("ã", "a")
        .toUpperCase();
    return Arrays.stream(values())
        .filter(v -> v.getValue().equals(valueFiltered))
        .findFirst();
  }

}
