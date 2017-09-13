package com.example.examplexml_listview;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
		static final String ATTR_ID = "id";
	    static final String NODE_EMP = "employee";
	    static final String NODE_NAME = "name";
	    static final String NODE_DEPT = "description";
	    static final String NODE_TYPE = "cost";
	    static final String NODE_EMAIL = "email";
	    static final String URL = "http://caphesinhvien.com/example1.xml";
	    //static tất cả các activity khác đều có thể sử dụng.
	    ListView listView;
	    Button button;
	    List<Employee> employees;
	    listviewXMLAdapter listviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();//hàm findView bên dưới
        
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GetXMLTask task = new GetXMLTask(MainActivity.this);
		        task.execute(URL);
			}
		});
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
			            employees.get(position).toString(), 
			            Toast.LENGTH_SHORT).show();
			}
        	
		});
    }
    private  void findViewById()
    {
    	//ánh xạ dữ liệu trong layout cần thiết
    	button=  (Button)findViewById(R.id.button);
    	listView = (ListView)findViewById(R.id.employeeList);
    	
    }
    
    //lay xml tu url
    private String getXmlFromUrl(String urlString) {
    	 String xml = null;
         try {
             // defaultHttpClient
             DefaultHttpClient httpClient = new DefaultHttpClient();
             HttpPost httpPost = new HttpPost(urlString);
             
             HttpResponse httpResponse = httpClient.execute(httpPost);
             HttpEntity httpEntity = httpResponse.getEntity();
             xml = EntityUtils.toString(httpEntity, HTTP.UTF_8);
                 
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         // return XML
         
         return xml;
    }   
    private class GetXMLTask extends AsyncTask<String, Void, String>
    {
    	 private Activity context;
    	 
         public GetXMLTask(Activity context) {
             this.context = context;
         }
		@Override
		protected String doInBackground(String...urls) {
			// TODO Auto-generated method stub
			 String xml = null;
	          xml = getXmlFromUrl(URL);
	          return xml;
		}
		protected void onPostExecute(String xml) {
            
			java.lang.System.out.print(xml);
            
            XMLDOMParser parser = new XMLDOMParser();
            Document doc = parser.getDocument(xml);
            NodeList nodeList = doc.getElementsByTagName(NODE_EMP);
            employees = new ArrayList<Employee>();
          
            for (int i = 0; i < nodeList.getLength(); i++) {
            	Employee employee = new Employee();
                Element e = (Element) nodeList.item(i);
              //  employee.setId(Integer.parseInt(e.getAttribute(ATTR_ID)));
                
                employee.setName(parser.getValue(e, NODE_NAME));
                employee.setDepartment(parser.getValue(e, NODE_DEPT));
                employee.setType(parser.getValue(e, NODE_TYPE));
                employee.setEmail(parser.getValue(e, NODE_EMAIL));
                employees.add(employee);
            }
 
            listviewAdapter = new listviewXMLAdapter(context, employees);
            listView.setAdapter(listviewAdapter);
		}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
