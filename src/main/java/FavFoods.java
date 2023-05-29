import java.util.List;

public class FavFoods {
    private String breakfast;
    private String lunch;
    private List<String> dinner;
    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> list) {
        this.dinner = list;
    }



    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }
}
