package kr.ac.MovieReview;

import java.io.Serializable;
import java.util.Comparator;

public class MovieReview implements Serializable{//Serializable
	private String movieTitle;
	private String genre;
	private String director;
	private String review;
	private String date;
	private String amount;
	private boolean like;
	
	public MovieReview(
			String movieTitle,
			String director,
			String genre,
			String date,
			String review,
			String amount,
			boolean like ) {
		this.movieTitle = movieTitle;
		this.genre = genre;
		this.director = director;
		this.review = review;
		this.date = date;
		this.amount = amount;
		this.like = like;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "MovieReview [movieTitle=" + movieTitle + ", genre=" + genre + ", director=" + director + ", review="
				+ review + ", date=" + date + ", amount=" + amount + ", like=" + like + "]";
	}
	
}
