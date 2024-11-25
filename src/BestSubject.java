import java.util.HashMap;

// Класс для вычисления предмета с лучшей средней успеваемостью
public class BestSubject
{
    private HashMap<String, Integer> sum;   // Хранит сумму всех оценок по каждому предмету
    private HashMap<String, Integer> count; // Хранит количество учеников по каждому предмету

    // Конструктор без параметров, инициализирующий пустые HashMap
    public BestSubject()
    {
        sum = new HashMap<>();   // Инициализация HashMap для хранения суммы оценок
        count = new HashMap<>(); // Инициализация HashMap для хранения количества учеников
    }

    // Метод для добавления информации об оценке ученика по предмету
    public void add(SchoolBoy school_boy)
    {
        String subject = school_boy.getSubject(); // Получаем название предмета
        int grade = school_boy.getGrade();        // Получаем оценку ученика

        // Обновляем сумму оценок по предмету, добавляя текущую оценку
        sum.put(subject, sum.getOrDefault(subject, 0) + grade);

        // Увеличиваем количество учеников по предмету на 1
        count.put(subject, count.getOrDefault(subject, 0) + 1);
    }

    // Метод для нахождения предмета с наилучшей средней успеваемостью
    public void find()
    {
        double highest_grade = 0.0; // Хранит наивысшее среднее значение оценки
        double avg_grade;           // Хранит временное значение средней оценки
        String best_subject = null; // Хранит название предмета с наивысшей средней оценкой

        // Проходим по всем предметам в HashMap `sum`
        for (String subject : sum.keySet())
        {
            // Вычисляем среднюю оценку для текущего предмета
            avg_grade = (double) sum.get(subject) / count.get(subject);

            // Сравниваем среднюю оценку с текущей наивысшей
            if (avg_grade > highest_grade)
            {
                highest_grade = avg_grade; // Обновляем наивысшую оценку
                best_subject = subject;   // Сохраняем предмет с этой оценкой
            }
        }

        // Выводим предмет с наилучшей средней успеваемостью и её значение
        System.out.printf("Предмет с лучшей средней успеваемостью (%.2f) - (%s)\n", highest_grade, best_subject);
    }
}
