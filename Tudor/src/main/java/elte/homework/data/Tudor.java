package elte.homework.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tudor")
public class Tudor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_topicid", nullable = false)
    private Topic topic;

    @Column(name = "fk_topicid", insertable = false, updatable = false)
    private int topicIdId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
