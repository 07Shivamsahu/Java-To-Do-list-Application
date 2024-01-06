package com.todolist.binding;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Todotasks")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private boolean completed;
}
