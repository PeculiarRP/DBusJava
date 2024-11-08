package org.example.Server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "journal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journal {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "student")
    private UUID studentId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "object")
    private Subject object;

    @Column(name = "grade")
    public int grade;
    @Override
    public String toString(){
        return getId().toString() + ":" + object.getObjectName() + ":" + grade;
    }
}
