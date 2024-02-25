package chapter14;

public class GymMember implements Comparable<GymMember> {

    String name;
    int dueDate;
    int age;
    int height;
    int weight;
    boolean isMale;

    GymMember(String name,
            int dueDate,
            int age,
            int height,
            int weight,
            boolean isMale) {

        this.name = name;
        this.dueDate = dueDate;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.isMale = isMale;
    }

    double getBMI() {
        return (weight / (double) (height * height)) * 10000;
    }

    String getName() {
        return name;
    }

    int getDueDate() {
        return dueDate;
    }

    int getAge() {
        return age;
    }

    int getHeight() {
        return height;
    }

    int getWeight() {
        return weight;
    }

    boolean isMale() {
        return isMale;
    }

    public String toStringoFormat() {
        return "[name, sex, age, height, weight, BMI, due date]";
    }

    public String toString() {
        return String.format("[%s, %s, %d, %d, %d, %.2f, %d]", name, isMale ? "남" : "여", age, height, weight, getBMI(),
                dueDate);

    }

    enum BMI {
        UNDERWEIGHT, NORMAL, OVERWEIGHT, OBESITY
    }

    @Override
    public int compareTo(GymMember m) {
        return m.dueDate - this.dueDate;
    }
}
