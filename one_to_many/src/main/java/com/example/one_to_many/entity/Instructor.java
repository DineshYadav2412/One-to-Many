package com.example.one_to_many.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instructorId;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    @JsonManagedReference
    private InstructorDetail instructorDetail;

    // Many Courses
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")

    private List<Course> courses;

    void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<Course>();
        }
        courses.add(course);
        course.setInstructor(this);

    }

}