import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.*;

public class TodosTest {

    Meeting meeting1 = new Meeting(3, "Встретить родителей", "Родители", "1.6.23");
    Meeting meeting2 = new Meeting(41, "Накормить кота", "Кот", "Сегодня");
    SimpleTask simple1 = new SimpleTask(56, "Приготовить обед");
    SimpleTask simple2 = new SimpleTask(62, "Париж");
    String[] subtasks1 = {"Встретить родителей", "Купить машину", "Проверить почту"};
    Epic epic1 = new Epic(75, subtasks1);
    String[] subtasks2 = {"Полить цветы", "Накормить кота", "Позвонить"};
    Epic epic2 = new Epic(84, subtasks2);

    @Test
    public void shouldFindInParisInSimpleTask() {
        Todos todos = new Todos();

        todos.add(simple1);
        todos.add(simple2);

        Task[] expected = {simple2};
        Task[] actual = todos.search("Париж");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindSimpleTask() {
        Todos todos = new Todos();

        todos.add(simple1);
        todos.add(simple2);

        Task[] expected = new Task[0];
        Task[] actual = todos.search("Погладить кота");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindMeeting() {
        Todos todos = new Todos();

        todos.add(meeting1);
        todos.add(meeting2);

        Task[] expected = new Task[0];
        Task[] actual = todos.search("Конфеты");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMeetingWithParents() {
        Todos todos = new Todos();

        todos.add(meeting1);
        todos.add(meeting2);

        Task[] expected = {meeting1};
        Task[] actual = todos.search("Родители");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindFlowers() {
        Todos todos = new Todos();

        todos.add(epic1);
        todos.add(epic2);

        Task[] expected = {epic2};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindEpicTask() {
        Todos todos = new Todos();

        todos.add(epic1);
        todos.add(epic2);

        Task[] expected = new Task[0];
        Task[] actual = todos.search("Чай");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTaskInDifferentType() {
        Todos todos = new Todos();

        todos.add(meeting1);
        todos.add(meeting2);
        todos.add(epic1);
        todos.add(epic2);
        todos.add(simple1);
        todos.add(simple2);

        Task[] expected = {meeting1, epic1};
        Task[] actual = todos.search("Встретить родителей");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllTasks() {
        Todos todos = new Todos();

        todos.add(meeting1);
        todos.add(meeting2);
        todos.add(epic1);
        todos.add(epic2);
        todos.add(simple1);
        todos.add(simple2);

        Task[] expected = {meeting1, meeting2, epic1, epic2, simple1, simple2};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
}