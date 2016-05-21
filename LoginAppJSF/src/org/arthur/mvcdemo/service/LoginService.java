package org.arthur.mvcdemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.arthur.mvcdemo.database.DataConnect;
import org.arthur.mvcdemo.model.Language;
import org.arthur.mvcdemo.model.LanguageDescription;
import org.arthur.mvcdemo.model.User;

public class LoginService {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean authenticate(String username, String password){

		Boolean result = true;
		try{
			con = DataConnect.getConnection();
			String query = "SELECT * FROM users where username = ? and passwd = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if(!rs.next()){
				//System.out.println("This user does not exist");
				result = false;
			}
		}catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}

		return result;
	}

	public User getUserInformation(String username) {
		User user = null;

		try {
			con = DataConnect.getConnection();

			String query = "SELECT * FROM users where username = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUser_oid(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setLang_oid(rs.getInt(4));

			}
		} catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}
		return user;
	}

	public boolean ifUserExists(String username){

		Boolean result = true;
		try{

			con = DataConnect.getConnection();
			String query = "SELECT username FROM users where username = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if(!rs.next()){
				//System.out.println("This user does not exist");
				result = false;
			}

		}catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}

		return result;
	}


	/** Maps language oid to corresponding description **/
	public String mapLanguageOIDToDescription(int languageOID){

		String languageDescription = null;
		try{

			con = DataConnect.getConnection();
			String query = "select language_description from language_description "
					+ "where language_oid = ?" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, languageOID);

			rs = ps.executeQuery();

			while(rs.next()){
				LanguageDescription lgd = new LanguageDescription();

				lgd.setLang_description(rs.getString(1));
				languageDescription = lgd.getLang_description();

			}
		}catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}
		return languageDescription;
	}

	/**Maps language description to the corresponding oid **/
	public int mapLanguageDescriptionToOID(String languageDescription){

		int languageOID = 0;
		try{

			con = DataConnect.getConnection();
			String query = "select language_oid from language_description "
					+ "where language_description = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, languageDescription);

			rs = ps.executeQuery();

			while(rs.next()){
				LanguageDescription lgd = new LanguageDescription();

				lgd.setLang_oid(rs.getInt(1));
				languageOID = lgd.getLang_oid();

			}
		}catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}
		return languageOID;
	}

	/** Maps language oid to corresponding language code **/
	public String mapLanguageOIDToCode(int languageOID){

		String languageCode = null;
		try{
			con = DataConnect.getConnection();
			String query = "select language_code from languages "
					+ "where language_oid = ?";

			ps = con.prepareStatement(query);
			ps.setInt(1, languageOID);

			rs = ps.executeQuery();

			while(rs.next()){
				Language lang = new Language();

				lang.setLang_code(rs.getString(1));
				languageCode = lang.getLang_code();

			}
		}catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}
		return languageCode;
	}

	/**Maps language code to the corresponding oid **/
	public int mapLanguageCodeToOID(String languageCode){

		int languageOID = 0;
		try{

			con = DataConnect.getConnection();
			String query = "select language_oid from languages "
					+ "where language_code = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, languageCode);

			rs = ps.executeQuery();
			while(rs.next()){
				Language lang = new Language();

				lang.setLang_oid(rs.getInt(1));
				languageOID = lang.getLang_oid();

			}
		}catch (Exception ex) {

		} finally {
			DataConnect.closeConnection(ps, con);
		}
		return languageOID;
	}

	public List<LanguageDescription> getAllLanguageDescription(){

		List<LanguageDescription> lgdList = new ArrayList<>();
		try{

			con = DataConnect.getConnection();
			String query = "select * from language_description;";
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			while(rs.next()){
				LanguageDescription lgd = new LanguageDescription();
				lgd.setLang_description_oid(rs.getInt(1));
				lgd.setLang_description(rs.getString(2));
				lgd.setLang_oid(rs.getInt(3));
				lgdList.add(lgd);
			}
		}catch (Exception ex) {

		} finally {

			DataConnect.closeConnection(ps, con);
		}
		return lgdList;
	}
}
