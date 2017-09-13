package com.example.examplexml_listview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listviewXMLAdapter extends BaseAdapter {
	Activity _context;
	List<Employee> _employees;
	
	public  listviewXMLAdapter(Activity context,List<Employee> employees) {
		this._context = context;
		this._employees = employees;
	}
	  private class ViewHolder {
	        TextView txtTitle;
	        TextView txtDepartment;
	        TextView txtEmail;
	    }
//ViewHolder :Mục đích của việc này là giúp ta sử dụng lại được biến convertView, giảm thiểu lại số lần gọi findViewById không cần thiết, giúp nó chạy mượt mà hơn
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _employees.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _employees.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return _employees.indexOf(getItem(position));
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder holder;
	     LayoutInflater inflater = _context.getLayoutInflater();
	     if (convertView == null)
	     {
	    	convertView = inflater.inflate(R.layout.item_emp, null);
	    	holder = new ViewHolder();
	    	holder.txtTitle = (TextView)convertView.findViewById(R.id.title);
	    	holder.txtDepartment = (TextView)convertView.findViewById(R.id.department);
	    	holder.txtEmail = (TextView)convertView.findViewById(R.id.email);
	    	convertView.setTag(holder); // tạo tag để lấy object tại vị trí position
	     }
	     else
	     {
	    	 holder = (ViewHolder)convertView.getTag();
	     }
	     Employee employee = (Employee)getItem(position);//lấy vị trí list Employee
	     //chuyền dữ liệu của Employee hiển thị trên item_emp
	    
	     holder.txtTitle.setText(employee.getName());
	     holder.txtDepartment.setText(employee.getdepartment() + " cost : "
	                + employee.getType());
	        holder.txtEmail.setText("Email: " + employee.getEmail());
	     
		return convertView;
	}
	
}
