package tables;

import db.MySQLConnector;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

//прописать все выборки для студентов (в других таблицах соответственно выборки для групп и кураторов)
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
        db = new MySQLConnector();
        return resultSetToArray(sqlQuery);
    }

    private ArrayList<Student> resultSetToArray(String sqlQuery) {
        ArrayList<Student> students = new ArrayList<>();
        db = new MySQLConnector();
        //Сделать запрос на выборку
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект и добавить его в результирующий массив
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

    public void selectAllStudentsWithGroupNameAndCuratorName() throws SQLException {
        String sqlQuery = String.format("SELECT students.id, students.fio, students.sex, students.id_group, groups_t.name, " +
                "curators.fio" +
                " FROM students LEFT JOIN groups_t ON groups_t.id = students.id_group LEFT JOIN curators ON " +
                "curators.id = groups_t.id_curator");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Информация о студенте: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4) + "|" +
                    rs.getString(5) + "|" +
                    rs.getString(6));
        }
    }

    public void selectStudentsNumber() throws SQLException {
        String sqlQuery = String.format("SELECT COUNT(*) FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Количество студентов: " + rs.getString(1));
        }
    }

    public void selectBySex(String sex) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM %s WHERE sex = '%s'", tableName, sex);
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Только студентки: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }

    public void selectAllStudentsFromOneGroup () throws SQLException {
        String sqlQuery = String.format("SELECT students.fio, students.id, students.sex, students.id_group FROM students" +
                " LEFT JOIN groups_t ON students.id_group = groups_t.id WHERE groups_t.name = 'OTUS1'");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Студенты, относящиеся только к группе OTUS1: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }

//    public void select(String[] columns, String[] where) {
//
//        String columnStr = "*";
//        if (columns.length > 0) {
//            columnStr = String.join(",", columns);
//        }
//
//        String sqlQuery = String.format("SELECT %s FROM students", columnStr);
//    }

    //update
    public void insert(Student student) {
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на добавление
        String sqlQuery = String.format("INSERT INTO %s (fio, sex, id_group) VALUES ('%s', '%s', '%d')",
                tableName, student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlQuery);
    }

    public void update(Student student) {
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на изменение
        final String sqlQuery = String.format("UPDATE %s SET fio= '%s', sex= '%s', id_group= '%d' WHERE id= '%d'",
                tableName, student.getFio(), student.getSex(), student.getId_group(), student.getId());
        db.executeRequest(sqlQuery);
     }

   //delete
    public void delete(long id) {
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на удаление
        final String sqlQuery = String.format("DELETE FROM %s WHERE id= '%d'",
                tableName, id);
        db.executeRequest(sqlQuery);
    }
}

