package org.example;

import org.example.DAO.CourseDAOImp;
import org.example.DAO.DAOFactory;
import org.example.DAO.IDAOFactory;
import org.example.DAO.StudentDAOImp;
import org.example.models.Course;
import org.example.models.Student;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        IDAOFactory factory = DAOFactory.get_instance();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("0 - Exit\n1 - Manage courses\n2 - Manage students\nYour choice: ");
            switch (sc.nextInt()) {
                case 0:
                    System.out.println("\tGoodbye!");
                    return;
                case 1:
                    manageCourses(factory);
                    break;
                case 2:
                    manageStudents(factory);
                    break;
                default:
                    System.out.println("\tWrong choice. Try again.");
                    break;
            }
        }
    }

    private static void manageCourses(IDAOFactory factory) {
        CourseDAOImp dao = factory.getCourseDAO();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\n0 - Back\n1 - Show\n2 - Add\n3 - Edit\n4 - Delete\n\tYour choice: ");
            switch (sc.nextInt()) {
                case 0:
                    return;
                case 1:
                    System.out.println();
                    for (Course course : dao.findAll())
                        System.out.println(course + "\n");

                    System.out.print("\n\tFind by id (leave empty if not): ");
                    sc = new Scanner(System.in);
                    String text = sc.nextLine();
                    if (!text.isEmpty())
                        System.out.println("\n" + dao.findEntityById(Integer.parseInt(text)));
                    break;
                case 2: {
                    sc = new Scanner(System.in);
                    System.out.print("\nName: ");
                    String name = sc.nextLine();
                    System.out.print("\nDescription: ");
                    String description = sc.nextLine();
                    System.out.print("\nCapacity: ");
                    int capacity = sc.nextInt();

                    System.out.println(
                            dao.create(new Course(name, description, capacity)) ?
                                    "\n\tSuccess!" : "\n\tFailed to add new course!");
                    break;
                }
                case 3: {
                    System.out.println();
                    for (Course course : dao.findAll())
                        System.out.println(course + "\n");

                    sc = new Scanner(System.in);
                    System.out.print("Id: ");
                    int id = sc.nextInt();

                    System.out.println("\tNew");

                    sc = new Scanner(System.in);
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Capacity: ");
                    int capacity = sc.nextInt();

                    Course course = dao.update(new Course(id, name, description, capacity));
                    System.out.println(course != null ?
                            "\n\tSuccess!\n" + course : "\n\tFailed to update course!");
                    break;
                }
                case 4:
                    System.out.println();
                    for (Course course : dao.findAll())
                        System.out.println(course + "\n");

                    sc = new Scanner(System.in);
                    System.out.print("Id: ");
                    System.out.println(dao.delete(sc.nextInt()) ? "\n\tSuccess!" : "\n\tFailed to delete course!");
                    break;
            }
        }
    }

    private static void manageStudents(IDAOFactory factory) {
        StudentDAOImp dao = factory.getStudentDAO();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\n0 - Back\n1 - Show\n2 - Add\n3 - Edit\n4 - Delete\n\tYour choice: ");
            switch (sc.nextInt()) {
                case 0:
                    return;
                case 1:
                    System.out.println();
                    for (Student student : dao.findAll())
                        System.out.println(student + "\n");

                    sc = new Scanner(System.in);
                    System.out.print("\tFind by id (leave empty if not): ");
                    String text = sc.nextLine();
                    if (!text.isEmpty())
                        System.out.println("\n" + dao.findEntityById(Integer.parseInt(text)));
                    break;
                case 2: {
                    sc = new Scanner(System.in);
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Surname: ");
                    String surname = sc.nextLine();
                    System.out.print("Date of birth (dd-MM-yyyy): ");
                    String inputDate = sc.nextLine();

                    CourseDAOImp courseDAO = factory.getCourseDAO();
                    System.out.println();
                    for (Course course : courseDAO.findAll())
                        System.out.println(course + "\n");

                    System.out.print("Course id: ");
                    int course_id = sc.nextInt();

                    Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$");
                    Matcher matcher = pattern.matcher(inputDate);

                    if (matcher.matches()) {
                        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                        System.out.println(
                                dao.create(new Student(name, surname, Date.valueOf(date), course_id)) ?
                                        "\n\tSuccess!" : "\n\tFailed to add new student!");
                    } else
                        System.out.println("\n\tInvalid date!");

                    break;
                }
                case 3: {
                    System.out.println();
                    for (Student student : dao.findAll())
                        System.out.println(student + "\n");

                    sc = new Scanner(System.in);
                    System.out.print("Id: ");
                    int id = sc.nextInt();

                    System.out.println("\tNew");

                    sc = new Scanner(System.in);
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Surname: ");
                    String surname = sc.nextLine();
                    System.out.print("Date of birth (dd-MM-yyyy): ");
                    String inputDate = sc.nextLine();

                    CourseDAOImp courseDAO = factory.getCourseDAO();
                    System.out.println();
                    for (Course course : courseDAO.findAll())
                        System.out.println(course + "\n");

                    System.out.print("Course id: ");
                    int course_id = sc.nextInt();

                    Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$");
                    Matcher matcher = pattern.matcher(inputDate);

                    if (matcher.matches()) {
                        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        Student newStudent = dao.update(new Student(name, surname, Date.valueOf(date), course_id));

                        System.out.println(newStudent != null ?
                                "\n\tSuccess!\n" + newStudent :
                                "\n\tFailed to update student!");
                    } else
                        System.out.println("\n\tInvalid date!");

                    break;
                }
                case 4:
                    System.out.println();
                    for (Student student : dao.findAll())
                        System.out.println(student + "\n");

                    sc = new Scanner(System.in);
                    System.out.print("Id: ");
                    System.out.println(dao.delete(sc.nextInt()) ? "\n\tSuccess!" : "\n\tFailed to delete student!");
                    break;
            }

        }
    }
}