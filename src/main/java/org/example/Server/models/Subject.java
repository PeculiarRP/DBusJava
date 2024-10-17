package org.example.Server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "object_name")
    private String objectName;

    @Override
    public String toString(){
        return getId().toString() + ":" + getObjectName();
    }

}
