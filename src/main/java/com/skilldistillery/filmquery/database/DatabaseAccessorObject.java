package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String name = "student";
		String pwd = "student";

		String sql = "SELECT * from film where id = ?";

		Connection conn = DriverManager.getConnection(URL, name, pwd);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			Integer releaseYear = rs.getInt("release_year");
			int languageId = rs.getInt("language_id");
			int rentalDuration = rs.getInt("rental_duration");
			double rentalRate = rs.getDouble("rental_rate");
			int length = rs.getInt("length");
			double replacementCost = rs.getDouble("replacement_cost");
			String rating = rs.getString("rating");
			String specialFeatures = rs.getString("special_features");
			String language = findFilmLanguage(id);

			film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
					replacementCost, rating, specialFeatures);
			film.setLanguage(language);
			// the film exists now
			// now set its actors

			// film.setActors(finish this);

		} // end while and if

		ps.close();
		conn.close();
		return film;

	} // end findFilmById

// Find film language
	@Override
	public String findFilmLanguage(int filmId) throws SQLException {
		String name = "student";
		String pwd = "student";
		String languageName = " ";
		String sql = "SELECT language.name FROM film JOIN language on language.id = film.language_id WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, name, pwd);
		PreparedStatement psLang = conn.prepareStatement(sql);
		psLang.setInt(1, filmId);
		ResultSet rsLang = psLang.executeQuery();

		while (rsLang.next()) {
			languageName = rsLang.getString("name");
		} // end while

		psLang.close();
		conn.close();
		return languageName;

	} // end findFilmLanguage

// Find actors in film
	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		String name = "student";
		String pwd = "student";
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.first_name, actor.last_name FROM actor JOIN film_actor on film_actor.actor_id = actor.id JOIN film"
				+ " on film_actor.film_id = film.id where film.id = ?";
		Connection conn = DriverManager.getConnection(URL, name, pwd);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			Actor filmActor = new Actor(firstName, lastName);
			actors.add(filmActor);
//		    System.out.println("First Name: " + firstName + "Last Name: " + lastName);
		} // end while

		ps.close();
		conn.close();
		return actors;

	} // end findActorsByFilmId

// Find film by search word
	@Override
	public List<Film> findFilmsBySearchWord(String searchWord) throws SQLException {
		String name = "student";
		String pwd = "student";
		List<Film> films = new ArrayList<>();
		String sql = "SELECT * FROM film WHERE film.title LIKE ? OR film.description LIKE ?";

		Connection conn = DriverManager.getConnection(URL, name, pwd);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + searchWord + "%");
		ps.setString(2, "%" + searchWord + "%");
		ResultSet rs = ps.executeQuery();

		int numberOfFilms = 0;

		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			String language = findFilmLanguage(id);

			numberOfFilms++;
			films.add(new Film(id, title, description, language));
		} // end while

		if (numberOfFilms == 0) {
			System.out.println("No film name or description found containing" + " searchWord.");
		} else {
			System.out.println("Number of films found with searchword " + searchWord + ": " + numberOfFilms);
		}
		ps.close();
		conn.close();
		return films;

	} // end findFilmsBySearchWord

//	@Override
//	public Actor findActorById(int actorId) {
//		return null;
//	} //end findActorById 

} // end class DatabaseAccessorObject implements DatabaseAccessor
