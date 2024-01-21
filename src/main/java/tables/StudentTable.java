package tables;

import db.MySQLConnector;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

//Тут прописать все выборки для студентов (в других таблицах соответственно выборки для групп и кураторов)
public class StudentTable extends AbsTable {
    private final static String TABLE_NAME = "students";

    //create
    public StudentTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(50)");
        columns.put("sex", "varchar(15)");
        columns.put("id_group", "bigint");
        create();
    }

    //read
    public ArrayList<Student> selectAll() {
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Student> selectBySex(String sex) {
        String sqlQuery = String.format("SELECT * FROM %s WHERE sex = '%s'", tableName, sex);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Student> selectByQuery(String sqlQuery) {
        ArrayList<Student> students = new ArrayList<>();
        //Сделать запрос на выборку
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                students.add(
                        new Student(
                                rs.getLong("id"),
                                rs.getString("fio"),
                                rs.getString("sex"),
                                rs.getLong("id_group")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return students;
    }

    public void select(String[] columns, String[] where) {

        String columnStr = "*";
        if (columns.length > 0) {
            columnStr = String.join(",", columns);
        }

        String sqlQuery = String.format("SELECT %s FROM students", columnStr);
    }

    //update
    public void insert(Student student) {
        //Подключиться к БД
        //Сделать запрос на добавление
        final String sqlQuery = String.format("INSERT INTO %s (fio, sex, id_group) VALUES ('%s', '%s', '%d')",
                tableName, student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlQuery);
    }

    public void update(Student student) {
        //Подключиться к БД
        //Сделать запрос на изменение
        final String sqlQuery = String.format("UPDATE %s SET fio= '%s', sex= '%s', id_group= '%d' WHERE id= '%d'",
                tableName, student.getFio(), student.getSex(), student.getId_group(), student.getId());
        db.executeRequest(sqlQuery);
     }

   //delete
    public void delete(long id) {
        //Подключиться к БД
        //Сделать запрос на удаление
        final String sqlQuery = String.format("DELETE FROM %s WHERE id= '%d'",
                tableName, id);
        db.executeRequest(sqlQuery);
    }
}

