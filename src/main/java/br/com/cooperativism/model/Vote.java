package br.com.cooperativism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Vote extends AbstractModel {

  @OneToOne(optional = false)
  @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
  private Member member;

  @OneToOne(optional = false)
  @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
  private Topic topic;

  @Column(nullable = false)
  private Boolean result;

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

  public Boolean getResult() {
    return result;
  }

  public void setResult(Boolean result) {
    this.result = result;
  }

}
