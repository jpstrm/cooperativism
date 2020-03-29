package br.com.cooperativism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Session extends AbstractModel {

  @Column(name = "voting_start", nullable = false)
  private Date votingStart;

  @Column(name = "voting_end", nullable = false)
  private Date votingEnd;

  @OneToOne(optional = false)
  @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
  private Topic topic;

  public Session() {}

  public Date getVotingStart() {
    return votingStart;
  }

  public void setVotingStart(Date votingStart) {
    this.votingStart = votingStart;
  }

  public Date getVotingEnd() {
    return votingEnd;
  }

  public void setVotingEnd(Date votingEnd) {
    this.votingEnd = votingEnd;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

}
