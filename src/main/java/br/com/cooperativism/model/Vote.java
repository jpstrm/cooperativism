package br.com.cooperativism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Vote extends AbstractModel implements Serializable {

  private static final long serialVersionUID = -5141408527987836636L;

  @OneToOne(optional = false)
  @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
  private Member member;

  @OneToOne(optional = false)
  @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
  private Topic topic;

  @Column(nullable = false)
  private Boolean vote;

  public Vote() {}

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public Boolean getVote() {
    return vote;
  }

  public void setVote(Boolean vote) {
    this.vote = vote;
  }

}
