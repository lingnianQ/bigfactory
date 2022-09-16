package enums;

public class GenderTests {
    public static void main(String[] args) {
        Gender[] values = Gender.values();
        for(Gender gender:values){
            System.out.println(gender.getName());
        }
    }
}
