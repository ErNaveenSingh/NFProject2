package nav.com.nfproject2;

/**
 * Created by naveensingh on 15/05/16.
 */
public class MovieDataModel {
    String title;
    String description;
    String year;
    String imageUrl;

    public MovieDataModel(){

    }

    public MovieDataModel(String title, String description, String year, String imageUrl) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
