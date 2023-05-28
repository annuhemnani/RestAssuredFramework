import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)


public class Employee {

    private int id;
    private String fname;
    private String lastname;
    private String email;

    private FavFoods favFoods;

    public Employee(int id, String fname, String lastname, String email, FavFoods favFoods) {
        this.id = id;
        this.fname = fname;
        this.lastname = lastname;
        this.email = email;

        this.favFoods = favFoods;
    }

}
