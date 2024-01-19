import objects.Student;
import objects.Group;
import objects.Curator;
import tables.StudentTable;
import tables.GroupTable;
import tables.CuratorTable;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        StudentTable studentTable = new StudentTable();
        ArrayList<Student> students = studentTable.selectAll();
        if (students.isEmpty()) {
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

        for (Student tmp : students) {
            System.out.println(tmp.toString());
            System.out.println("--------------------------------------");
        }

        students.get(0).setFio("Кря");
        studentTable.update(students.get(0));

        students = studentTable.selectAll();

        for (Student tmp : students) {
            System.out.println(tmp.toString());
            System.out.println("--------------------------------------");
        }

        studentTable.delete(17);

        for (Student tmp : students) {
            System.out.println(tmp.toString());
            System.out.println("--------------------------------------");
        }
    }
}

