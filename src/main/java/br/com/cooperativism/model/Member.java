package br.com.cooperativism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Entity
public class Member extends AbstractModel implements Serializable {

  private static final long serialVersionUID = -8419034114075358440L;

  private String name;

  @Column(nullable = false, unique = true)
  @Max(11)
  private String cpf;

  public Member() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

}
