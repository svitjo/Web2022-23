package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import entities.Comment;

public class CommentDAO {
	private Map<Integer, Comment> comments = new HashMap<>();
	private String contextPath;

	public CommentDAO() {
		loadComments();
	}

	public CommentDAO(String contextPath) {
		this.contextPath = contextPath;
		loadComments();
	}
	
	public Comment findById(int id) {
		for(Comment r : comments.values()) {
			if(r.getId() == id) 
				return r;
		}
		return null;
	}
	
	public Collection<Comment> findAll() {
		return comments.values();
	}
	
	public void loadComments() {
		BufferedReader reader = null;
		try {
			File file = new File(contextPath + "storage\\comments.txt");
			reader = new BufferedReader(new FileReader(file));
			String json = reader.lines().collect(Collectors.joining());
			System.out.println(json);
			Collection<Comment> rList = new ObjectMapper().readValue(json, new TypeReference<List<Comment>>(){});
			
			for(Comment r : rList) {
				comments.put(r.getId(), r);
			}
		} catch (Exception ex) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	public Comment addComment(Comment Comment) {
		
		Comment.setId(calculateLastIndex());
		comments.put(Comment.getId(), Comment);
		saveComments();
		return Comment;
	}
	
	public void saveComments() {
		File file = new File(contextPath + "storage\\comments.txt");
	
		BufferedWriter writer = null;
		try {
		    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		    String json = ow.writeValueAsString(comments.values());

		    writer = new BufferedWriter(new FileWriter(file));
		    writer.write(json);
		} catch (Exception e) {
		} finally {
			if ( writer != null ) {
				try {
					writer.close();
				}
				catch (Exception e) { }
			}
		}
	}

	private int calculateLastIndex() {
		int maxId = -1;
		for(Comment r : comments.values()) {
			if(r.getId()>maxId)
				maxId=r.getId();
		}
		maxId++;
		return maxId;
	}
}
