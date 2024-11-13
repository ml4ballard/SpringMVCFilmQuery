package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor { // You have provided a method to read the below

	public Film findFilmById(int filmId) throws SQLException;

	public String findFilmLanguage(int filmId) throws SQLException;

	public List<Film> findFilmsBySearchWord(String searchWord) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException;

} // end DatabaseAccessor
