package elte.homework.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="question")
public class Question implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date creationDate;
    private String text;

    @ManyToOne
    @JoinColumn(name = "fk_clientid", nullable = false)
    private Client client;

    @Column(name = "fk_clientid", insertable = false, updatable = false)
    private int clientId;

    @ManyToOne
    @JoinColumn(name = "fk_topicid", nullable = false)
    private Topic topic;

    @Column(name = "fk_topicid", insertable = false, updatable = false)
    private int topicId;

    @OneToOne
    @JoinColumn(name = "fk_answerid", nullable = true)
    private Answer answer;

    @Column(name = "fk_answerid", insertable = false, updatable = false)
    private int answerId;

    public int getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
