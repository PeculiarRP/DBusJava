package org.example.Server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "id")
    private UUID studentId;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "class")
    private String studentClass;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student")
    private List<Journal> journals = new ArrayList<>();

    @Override
    public String toString(){
        return studentId.toString() + ":"
                + name + ":" + surname + ":"
                + studentClass + ":" + journals.stream().map(Journal::toString).toList();
    }

}
