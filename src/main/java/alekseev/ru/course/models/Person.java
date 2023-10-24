package alekseev.ru.course.models;

public class Person {

    private int id;
    private String fullname;
    private String birthday;

    public Person(int id, String fullname, String birthday) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
