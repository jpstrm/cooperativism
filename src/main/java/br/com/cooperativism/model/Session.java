package br.com.cooperativism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Session extends AbstractModel implements Serializable {

  private static final long serialVersionUID = 7211846874754010863L;

  @Column(name = "voting_start", nullable = false)
  private Date votingStart = Calendar.getInstance().getTime();

  @Column(name = "voting_end", nullable = false)
  private Date votingEnd = Calendar.getInstance().getTime();

  @OneToOne(optional = false)
  @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
  private Topic topic;

  public Session() {}

  public Session(Topic topic) {
    this.topic = topic;
  }

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
