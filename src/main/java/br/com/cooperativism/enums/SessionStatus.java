package br.com.cooperativism.enums;

public enum  SessionStatus {

  STARTED("EM_ANDAMENTO"),
  EXPIRED("EXPIRADA");

  private String value;

  SessionStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
