package org.example.DAO;

import org.example.models.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements AbstractDAO<Integer, Student> {
    @Override
    public List<Student> findAll() {
        try (Connection connection = DAOFactory.getConnection()) {
            List<Student> students = new ArrayList<>();
            String sql = "SELECT * FROM student";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getInt("course_id")
                    );
                    students.add(student);
                }

                return students;
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findEntityById(Integer id) {
        try (Connection connection = DAOFactory.getConnection()) {
            String sql = "SELECT * FROM student WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id.toString());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getInt("course_id")
                    );
                } else
                    return null;
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(Student entity) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "INSERT INTO student(name, surname, date_of_birth, course_id) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            java.sql.Date sqlDate = new java.sql.Date(entity.getDateOfBirth().getTime());
            statement.setDate(3, sqlDate);
            statement.setString(4, String.valueOf(entity.getCourse_id()));

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student update(Student entity) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "UPDATE student SET name = ?, surname = ?, date_of_birth = ?, course_id = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setDate(3, entity.getDateOfBirth());
            statement.setString(4, String.valueOf(entity.getCourse_id()));
            statement.setString(5, String.valueOf(entity.getId()));

            return statement.executeUpdate() > 0 ? entity : null;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "DELETE FROM student WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id.toString());

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Student entity) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "DELETE FROM student WHERE name = ?, surname = ?, date_of_birth = ?, course_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getDateOfBirth().toString());
            statement.setString(4, String.valueOf(entity.getCourse_id()));

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
