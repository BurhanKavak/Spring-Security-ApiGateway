package com.example.taskservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    private String description;

    private Long projectId;

}