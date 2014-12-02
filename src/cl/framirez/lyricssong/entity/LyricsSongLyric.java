/**
Copyright 2014 Fabian Ramirez Barrios
This file is part of GeoComm.

GeoComm is free software: you can redistribute it and/or modify 
it under the terms of the GNU General Public License as published by 
the Free Software Foundation, either version 3 of the License, or 
(at your option) any later version.

GeoComm is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of 
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
GNU General Public License for more details.

You should have received a copy of the GNU General Public License 
along with GeoComm.  If not, see <http://www.gnu.org/licenses/>.

**/
package cl.framirez.lyricssong.entity;

public class LyricsSongLyric {
	
	private String id;
	private String name;
	private String contein;
	private String author;
	
	
	public LyricsSongLyric(String id, String name, String contein, String author) {
		super();
		this.id = id;
		this.name = name;
		this.contein = contein;
		this.author = author;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContein() {
		return contein;
	}
	public void setContein(String contein) {
		this.contein = contein;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
