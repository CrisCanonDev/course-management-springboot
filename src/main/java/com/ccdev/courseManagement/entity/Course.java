package com.ccdev.courseManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 300)
    private String description;

    @Column(nullable = false)
    private int level;


    private String semester;

    @Column(name = "publish_state")
    private boolean published;
}
