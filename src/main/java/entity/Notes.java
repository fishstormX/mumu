package entity;

import java.sql.Timestamp;

public class Notes {
	private String title;
	private long n_id;
	private String author;
	private String uri;
	private Timestamp date;
	private long hot;
	public long getHot() {
		return hot;
	}
	public void setHot(long hot) {
		this.hot = hot;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getN_id() {
		return n_id;
	}
	public void setN_id(long n_id) {
		this.n_id = n_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
