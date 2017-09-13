package projects.jatin.moviesdemo.Model;

/**
 * Created by Jateen on 01-09-2017.
 */

public class NowPlayingModel {

    String title,language,overview,release_date,image_url;
    int votes,vote_average;

    public NowPlayingModel(String title, String language, String overview, String release_date, int votes, int vote_average) {
        this.title = title;
        this.language = language;
        this.overview = overview;
        this.release_date = release_date;
        this.votes = votes;
        this.vote_average = vote_average;
    }

    public NowPlayingModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = "https://image.tmdb.org/t/p/w500"+image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }
}
