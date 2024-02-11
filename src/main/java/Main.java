import db.MySQLConnector;
import objects.Student;
import objects.Group;
import objects.Curator;
import tables.StudentTable;
import tables.GroupTable;
import tables.CuratorTable;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {

            StudentTable studentTable = new StudentTable();
            ArrayList<Student> students = studentTable.selectAll();
            if (students.size() < 15) {
                studentTable.insert(new Student("Петрова Марфа Ивановна", "женский", 1));
                studentTable.insert(new Student("Гришко Тамара Константиновна", "женский", 2));
                studentTable.insert(new Student("Хаменко Эдуард Михайлович", "мужской", 2));
                studentTable.insert(new Student("Григорьев Артём Игоревич", "мужской", 2));
                studentTable.insert(new Student("Фазанова Екатерина Павловна", "женский", 1));
                studentTable.insert(new Student("Злючко Ирина Геннадьевна", "женский", 1));
                studentTable.insert(new Student("Злючко Анатолий Григорьевич", "мужской", 2));
                studentTable.insert(new Student("Петрова Валерия Ивановна", "женский", 3));
                studentTable.insert(new Student("Пивоваров Афанасий Иосифович", "мужской", 3));
                studentTable.insert(new Student("Анатоленко Анастасия Михайловна", "женский", 3));
                studentTable.insert(new Student("Федоренко Денис Денисович", "мужской", 1));
                studentTable.insert(new Student("Лазарева Галина Денисовна", "женский", 2));
                studentTable.insert(new Student("Алексеева Алиса Алексеевна", "женский", 3));
                studentTable.insert(new Student("Степанов Игорь Андреевич", "мужской", 2));
                studentTable.insert(new Student("Попугаенко Александр Сидорович", "мужской", 1));
                students = studentTable.selectAll();
            }

            GroupTable groupTable = new GroupTable();
            ArrayList<Group> groups = groupTable.selectAll();
            if (groups.size() < 3) {
                groupTable.insert(new Group("OTUS1",1 ));
                groupTable.insert(new Group("OTUS2",2 ));
                groupTable.insert(new Group("OTUS3",3 ));
                groups = groupTable.selectAll();

            }

            CuratorTable curatorTable = new CuratorTable();
            ArrayList<Curator> curators = curatorTable.selectAll();
            if (curators.size() < 4) {
                curatorTable.insert(new Curator("Сидоренко Агафья Ивановна"));
                curatorTable.insert(new Curator("Тишко Эдуард Павлович"));
                curatorTable.insert(new Curator("Дуденко Катерина Ивановна"));
                curatorTable.insert(new Curator("Григорьев Александр Алексанрович"));
                curators = curatorTable.selectAll();
            }

            studentTable.selectAllStudentsWithGroupNameAndCuratorName();
            System.out.println("--------------------------------------");

            studentTable.selectStudentsNumber();
            System.out.println("--------------------------------------");

            studentTable.selectBySex("женский");
            System.out.println("--------------------------------------");

            groups.get(2).setId_curator(3);
            groupTable.updateByCuratorId(groups.get(2));

            groupTable.selectAllGroupsAndCurators();
            System.out.println("--------------------------------------");

            studentTable.selectAllStudentsFromOneGroup();
            System.out.println("--------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MySQLConnector.close();
        }
    }
}