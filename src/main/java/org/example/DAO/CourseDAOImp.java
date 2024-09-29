package org.example.DAO;

import org.example.models.Course;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements AbstractDAO<Integer, Course> {
    @Override
    public List<Course> findAll() {
        try (Connection connection = DAOFactory.getConnection()) {
            List<Course> courses = new ArrayList<>();
            String sql = "SELECT * FROM course";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Course course = new Course(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("capacity")
                    );
                    courses.add(course);
                }

                return courses;
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findEntityById(Integer id) {
        try (Connection connection = DAOFactory.getConnection()) {
            String sql = "SELECT * FROM course WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id.toString());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return new Course(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("capacity")
                    );
                } else
                    return null;
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(Course entity) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "INSERT INTO course(name, description, capacity) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, String.valueOf(entity.getCapacity()));

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course update(Course entity) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "UPDATE course SET name = ?, description = ?, capacity = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, String.valueOf(entity.getCapacity()));
            statement.setString(4, String.valueOf(entity.getId()));

            return statement.executeUpdate() > 0 ? entity : null;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "DELETE FROM course WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id.toString());

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Course entity) {
        try (Connection connection = DAOFactory.getConnection()) {
            PreparedStatement statement;
            String sql = "DELETE FROM course WHERE name = ?, description = ?, capacity = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, String.valueOf(entity.getCapacity()));

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
