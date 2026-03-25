package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class LessonDTO {

    public record LessonCreateRequest(
            String title,
            int orderIndex,
            Long courseId
    ) implements Serializable {}

    public record LessonUpdateRequest(
            Long id,
            String title,
            int orderIndex,
            Long courseId
    ) implements Serializable {}

    public record LessonResponse(
            Long id,
            String title,
            int orderIndex
          //  CourseDTO.CourseBasicResponse course
    ) implements Serializable {}


    public record LessonBasicResponse(
            Long id,
            String title,
            int orderIndex
    ) implements Serializable {}
}