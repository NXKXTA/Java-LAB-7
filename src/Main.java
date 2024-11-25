import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main
{
    // (0) Метод для вывода списка учеников по классам
    public static void printPupils(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("(0)");
        // Проходим по всем записям в журнале
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            byte num_class = entry.getKey(); // Номер класса
            ArrayList<SchoolBoy> array_school = entry.getValue(); // Список учеников класса
            System.out.println("\n" + num_class + " класс:");

            // Выводим информацию о каждом ученике
            for (SchoolBoy school_boy : array_school)
                school_boy.print();

            // Сохраняем данные класса в файл
            SchoolBoy.writeFile(array_school, num_class);
        }
    }

    // (1) Метод для вывода учеников с указанной оценкой
    public static void printGrade(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("\n\n(1)\nВведите оценку: ");

        byte grade;
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) // Проверка корректности ввода
            grade = in.nextByte();
        else
        {
            System.out.print("Вы ввели не целое число!");
            return;
        }

        // Поиск учеников с указанной оценкой
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            boolean check = false;
            System.out.println("\n" + entry.getKey() + " класс:");

            for (SchoolBoy school_boy : array_school)
            {
                if (school_boy.equal(grade))
                {
                    school_boy.print();
                    check = true;
                }
            }
            if (!check)
                System.out.print("Нет учеников с такой оценкой");
        }
    }

    // (2) Метод для вычисления средней успеваемости по каждому классу
    public static void printPerformance(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("\n\n(2)\n");
        MiddlePerformance middle_performance = new MiddlePerformance();

        // Подсчёт средней оценки для каждого класса
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            int count = 0, sum = 0;

            for (SchoolBoy school_boy : array_school)
            {
                sum += school_boy.getGrade(); // Сумма оценок
                count++; // Количество учеников
            }

            double avg_grade = (double) sum / count; // Средняя оценка
            middle_performance.add(entry.getKey(), avg_grade);
        }

        // Вывод классов в порядке убывания средней успеваемости
        middle_performance.sortPrint();
    }

    // (3) Метод для вывода учеников, изучающих указанный предмет
    public static void printSubject(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("\n(3)\nВведите предмет: ");

        Scanner in = new Scanner(System.in);
        String subject = in.nextLine();
        if (subject.isEmpty())
        {
            System.out.print("Пустая строка!");
            return;
        }

        // Поиск учеников по предмету
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            boolean check = false;
            System.out.println("\n" + entry.getKey() + " класс:");

            for (SchoolBoy school_boy : array_school)
            {
                if (school_boy.equal(subject))
                {
                    school_boy.print();
                    check = true;
                }
            }
            if (!check)
                System.out.print("Нет учеников с оценкой по этому предмету");
        }
    }

    // (4) Метод для сохранения данных указанного класса в файл
    public static void printFile(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("\n\n(4)\nВведите класс: ");

        byte num_class;
        Scanner in = new Scanner(System.in);
        if (in.hasNextByte()) // Проверка ввода
            num_class = in.nextByte();
        else
        {
            System.out.print("Вы ввели не целое число!\n");
            return;
        }

        // Проверка корректности класса
        if (num_class < 1 || num_class > 11)
        {
            System.out.print("Значение класса должно быть от 1 до 11!\n");
            return;
        }
        if (!journal.containsKey(num_class))
        {
            System.out.print("Такого класса нет в журнале!\n");
            return;
        }

        // Сохранение данных класса в файл
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            ArrayList<SchoolBoy> array_school = entry.getValue();

            TreeSet<String> subjects = new TreeSet<>(); // Список предметов
            for (SchoolBoy school_boy : array_school)
                subjects.add(school_boy.getSubject());

            if (entry.getKey() == num_class)
            {
                SchoolBoy.writeFile2(subjects, array_school, num_class);
                break;
            }
        }
    }

    // (5) Метод для поиска ученика по имени и фамилии
    public static void findPupil(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("\n(5)\nВведите фамилию: ");
        String name, surname;
        Scanner in = new Scanner(System.in);
        boolean check = false;

        surname = in.nextLine();
        if (surname.isEmpty())
        {
            System.out.print("Пустая строка!");
            return;
        }
        System.out.print("Введите имя: ");
        name = in.nextLine();
        if (name.isEmpty())
        {
            System.out.print("Пустая строка!");
            return;
        }

        // Поиск ученика
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            for (SchoolBoy school_boy : array_school)
            {
                if (school_boy.equal(surname, name))
                {
                    System.out.printf("%s %s учится в %d классе\n", surname, name, entry.getKey());
                    check = true;
                }
            }
        }
        if (!check)
            System.out.print("Такого ученика нет!");
    }

    // (6) Метод для нахождения предмета с наилучшей средней успеваемостью
    public static void findBestSubject(TreeMap<Byte, ArrayList<SchoolBoy>> journal)
    {
        System.out.print("\n\n(6)\n");
        BestSubject best_subject = new BestSubject();

        // Добавляем данные по предметам в объект BestSubject
        for (Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet())
        {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            for (SchoolBoy school_boy : array_school)
            {
                best_subject.add(school_boy);
            }
        }

        // Находим лучший предмет
        best_subject.find();
    }

    // Главный метод программы
    public static void main(String[] args)
    {
        TreeMap<Byte, ArrayList<SchoolBoy>> journal = new TreeMap<>(); // Журнал учеников

        // Читаем данные из файла
        SchoolBoy.readFile("C:\\Фигня всякая\\Java Lab 7\\src\\data_school.txt", journal);

        // Вызываем методы для выполнения задач
        printPupils(journal);    // (0)
        printGrade(journal);     // (1)
        printPerformance(journal); // (2)
        printSubject(journal);   // (3)
        printFile(journal);      // (4)
        findPupil(journal);      // (5)
        findBestSubject(journal); // (6)
    }
}
