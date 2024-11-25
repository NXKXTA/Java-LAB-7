import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

// Класс для вычисления и вывода средней успеваемости по классам
public class MiddlePerformance
{
    private ArrayList<Map.Entry<Byte, Double>> average_grade; // Список, хранящий пары <Класс, Средняя оценка>

    // Конструктор без параметров, инициализирующий пустой список
    public MiddlePerformance()
    {
        average_grade = new ArrayList<>(); // Создаем новый пустой ArrayList
    }

    // Метод для добавления данных о среднем значении оценок для определенного класса
    public void add(Byte key, double avg_grade)
    {
        // Добавляем пару <Класс, Средняя оценка> в список
        average_grade.add(new AbstractMap.SimpleEntry<>(key, avg_grade));
    }

    // Метод для сортировки и вывода списка классов по средней оценке в порядке убывания
    public void sortPrint()
    {
        // Сортируем список в порядке убывания средней оценки
        average_grade.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Проходим по всем элементам списка и печатаем их в формате "Класс - Средняя оценка"
        for (Map.Entry<Byte, Double> entry : average_grade)
        {
            System.out.printf("%d класс - %.2f\n", entry.getKey(), entry.getValue());
        }
    }
}
