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
