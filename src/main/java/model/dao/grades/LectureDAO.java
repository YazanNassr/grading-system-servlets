package model.dao.grades;

import domain.Instructor;
import domain.Lecture;
import domain.Student;

import java.util.List;

public interface LectureDAO {
    Lecture getLecture(String lectureId);
    List<Lecture> getLectures(Student student);
    List<Lecture> getLectures(Instructor instructor);
    boolean createLecture(Lecture lecture);
    boolean deleteLecture(String id);
}
