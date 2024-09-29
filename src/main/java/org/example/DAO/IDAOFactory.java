package org.example.DAO;

public interface IDAOFactory {
    CourseDAOImp getCourseDAO();
    StudentDAOImp getStudentDAO();
}

