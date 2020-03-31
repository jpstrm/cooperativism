package br.com.cooperativism.model;

import br.com.cooperativism.enums.VoteEnum;
import br.com.cooperativism.enums.VoteStatusEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Vote extends AbstractModel implements Serializable {

  private static final long serialVersionUID = -5141408527987836636L;

  @OneToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
  private Member member;

  @ManyToOne(optional = false)
  @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
  private Session session;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private VoteEnum vote;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private VoteStatusEnum status = VoteStatusEnum.PENDING;

  private String errorMsg;

  public Vote() {}

  public Vote(Member member, Session session, VoteEnum vote) {
    this.member = member;
    this.session = session;
    this.vote = vote;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public VoteEnum getVote() {
    return vote;
  }

  public void setVote(VoteEnum vote) {
    this.vote = vote;
  }

  public VoteStatusEnum getStatus() {
    return status;
  }

  public void setStatus(VoteStatusEnum status) {
    this.status = status;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

}
