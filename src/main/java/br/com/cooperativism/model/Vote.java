package br.com.cooperativism.model;

import br.com.cooperativism.enums.VoteEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Vote extends AbstractModel implements Serializable {

  private static final long serialVersionUID = -5141408527987836636L;

  @OneToOne(optional = false)
  @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
  private Member member;

  @ManyToOne(optional = false)
  @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
  private Session session;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private VoteEnum vote;

  public Vote() {}

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

}
