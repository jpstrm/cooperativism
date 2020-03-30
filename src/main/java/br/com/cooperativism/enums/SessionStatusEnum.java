package br.com.cooperativism.enums;

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

}
