package com.example.one_to_many.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.one_to_many.entity.Instructor;
import com.example.one_to_many.entity.InstructorDetail;
import com.example.one_to_many.repository.AppDao;

import java.util.List;

@RestController
@AllArgsConstructor
public class DemoController {

    private final AppDao appDao;

    @PostMapping("/save")
    public Instructor saveInstructor(@RequestBody Instructor instructor) {

        return appDao.saveInstructor(instructor);
    }

    @GetMapping("/getbyid/{instructorId}")
    public Instructor getInstructorById(@PathVariable int instructorId) {
        return appDao.getInstructorById(instructorId);
    }

    @GetMapping("/getall")
    public List<Instructor> getAllInstructors() {
        return appDao.getAllInstructors();
    }

    @DeleteMapping("/deletebyid/{instructorId}")
    public String deleteByInstructorId(@PathVariable int instructorId) {
        String message = appDao.deleteInstructorById(instructorId);

        return message;

    }

    @PutMapping("/updatebyid/{instructorId}")
    public Instructor updateByInstructorId(@PathVariable int instructorId, @RequestBody Instructor instructor) {
        Instructor savedInstructor = appDao.getInstructorById(instructorId);
        if (savedInstructor != null) {
            InstructorDetail updatedInstructorDetail = instructor.getInstructorDetail();
            updatedInstructorDetail.setInstructorDetailId(savedInstructor.getInstructorDetail().getInstructorDetailId());


//            appDao.saveInstructorDetail(updatedInstructorDetail);

            instructor.setInstructorId(savedInstructor.getInstructorId());
            instructor.setInstructorDetail(updatedInstructorDetail);

            return appDao.saveInstructor(instructor);


        } else {
            return null;
        }
    }


    @GetMapping("/find-detail/{instructorDetailId}")
    public InstructorDetail getInstructorDetailById(@PathVariable int instructorDetailId) {

        return appDao.findInstructorDetailById(instructorDetailId);
    }

    @PostMapping("/saveDetail")
    public Instructor saveInstructorDetail(@RequestBody InstructorDetail instructorDetail) {

        Instructor instructor = instructorDetail.getInstructor();
        instructor.setInstructorDetail(instructorDetail);

        return appDao.saveInstructor(instructor);

    }

    @DeleteMapping("/delete-detail/{instructorDetailId}")
    public String deleteByInstructorDetailId(@PathVariable int instructorDetailId) {
        String message = appDao.deleteInstructorDetailById(instructorDetailId);

        return message;

    }
}