package entities;

public class Comment {
	private User user;
	private RentACarObject object;
	private String commentText;
	private double rating;

	public RentACarObject getObject() {
		return object;
	}
	public void setObject(RentACarObject object) {
		this.object = object;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
