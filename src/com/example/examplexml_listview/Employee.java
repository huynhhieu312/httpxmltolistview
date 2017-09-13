package com.example.examplexml_listview;

public class Employee {
	private int _id;
	private String _name;
	private String _department; //là decription
	private String _type; //là code
	private String _email;
	
	public int getId() {return this._id;}
	public String getName(){ return this._name;}
	public String getdepartment(){return this._department;}
	public String getType() {return this._type;}
	public String getEmail() {return this._email;}
	public String toString() {return "Employee [name=" + this._name + "]"; }
	
	public void setName(String name) {this._name = name;}
	public void setId(int id){this._id = id; }
	public void setDepartment(String department) {this._department = department;}
	public void setType(String type){this._type = type;}
	public void setEmail(String email){this._email = email;}
	
}
