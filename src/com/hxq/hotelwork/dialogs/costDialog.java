package com.hxq.hotelwork.dialogs;


import java.util.ArrayList;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

public class costDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text roomID;
	private Text name;
	private Text goodsname;
	private Text goodsnum;
	private Text cost;
	private String goodscode;
    JDBCTools vt =new JDBCTools();
    private Text txtId;
    private DateTime dateTime;
    private DateTime Time;
    private Label lblTime;
   public costDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	
	public Object open(String ch) {
		goodscode=ch;
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setBackground(SWTResourceManager.getColor(204, 255, 204));
		shell.setSize(833, 685);
		shell.setText("\u7ED3\u7B97\u6838\u5BF9");
		
		Label lblDue = new Label(shell, SWT.NONE);
		lblDue.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblDue.setForeground(SWTResourceManager.getColor(255, 69, 0));
		lblDue.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.BOLD));
		lblDue.setBounds(368, 31, 186, 44);
		lblDue.setText("结算核对");
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblId.setBounds(132, 105, 98, 20);
		lblId.setText("房间号码：");
		
		roomID = new Text(shell, SWT.BORDER);
		roomID.setBounds(320, 105, 249, 26);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblName.setBounds(132, 172, 85, 20);
		lblName.setText("顾客姓名：");
		
		name = new Text(shell, SWT.BORDER);
		name.setBounds(320, 169, 249, 26);
		
		Label lblGoodsname = new Label(shell, SWT.NONE);
		lblGoodsname.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblGoodsname.setBounds(132, 284, 76, 20);
		lblGoodsname.setText("商品名称：");
		
		goodsname = new Text(shell, SWT.BORDER);
		goodsname.setBounds(320, 281, 249, 26);
		
		Label lblGoodsnum = new Label(shell, SWT.NONE);
		lblGoodsnum.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblGoodsnum.setBounds(132, 341, 76, 20);
		lblGoodsnum.setText("购买数量：");
		
		goodsnum = new Text(shell, SWT.BORDER);
		goodsnum.setBounds(320, 338, 249, 26);
		
		Label lblCost = new Label(shell, SWT.NONE);
		lblCost.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblCost.setBounds(132, 464, 85, 20);
		lblCost.setText("此次消费：");
		
		cost = new Text(shell, SWT.BORDER);
		cost.setBounds(320, 464, 249, 26);
		
		Label lblIdcard = new Label(shell, SWT.NONE);
		lblIdcard.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblIdcard.setBounds(132, 230, 120, 20);
		lblIdcard.setText("顾客身份证号码：");
		
		txtId = new Text(shell, SWT.BORDER);
		txtId.setBounds(320, 230, 249, 26);
		
		String sql="select * from tool2;";
		ArrayList<String[]> list=vt.query(sql);
		roomID.setText(list.get(0)[0]);
		name.setText(list.get(0)[1]);
		goodsname.setText(list.get(0)[2]);
		goodsnum.setText(list.get(0)[3]);
		cost.setText(list.get(0)[4]);
		txtId.setText(list.get(0)[5]);
		
		dateTime = new DateTime(shell, SWT.DROP_DOWN);
		dateTime.setBounds(320, 404, 116, 28);
		Time = new DateTime(shell, SWT.TIME);
		Time.setBounds(453, 404, 116, 28);
		
		String sql0="select code from goods where name='"+goodsname.getText()+"'";
		ArrayList<String[]> nn=vt.query(sql0);
		goodscode=nn.get(0)[0];
		
		Button btnSureagain = new Button(shell, SWT.NONE);
		btnSureagain.setForeground(SWTResourceManager.getColor(255, 0, 0));
		btnSureagain.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				String sql2="delete from tool2 where name='"+name.getText()+"';";
				vt.update(sql2);
				int year=dateTime.getYear();
				int month=dateTime.getMonth()+1;
				int day=dateTime.getDay();
				int hour=Time.getHours();
				int min=Time.getMinutes();
				int sec=Time.getSeconds();
				String newMonth = month>9?month+"":"0"+month;
				String newday = day>9?day+"":"0"+day;
				String newhour = hour>9?hour+"":"0"+hour;
				String newmin = min>9?min+"":"0"+min;
				String newsec = sec>9?sec+"":"0"+sec;
				
				String datas= ""+year+"/"+newMonth+"/"+newday+""+newhour+":"+newmin+":"+newsec;
			
				
				// create table costdatas(data varchar(20),IDcard varchar(20),name varchar(20),
				//goodscode varchar(20),goodsname varchar(20),money varchar(20));
				String sqld="insert into costdatas values ('"+datas+"','"+txtId.getText()+"','"+name.getText()+"','"+goodscode+"','"+goodsname.getText()+"','"+cost.getText()+"');";
				vt.update(sqld);//记录消费记录
				String sqln="select numbers from goods where name='"+goodsname.getText()+"';";
				ArrayList<String []> nu=vt.query(sqln);
				String numbers=Integer.parseInt(nu.get(0)[0])-Integer.parseInt(goodsnum.getText())+"";
				String up="update goods set numbers='"+numbers+"' where name='"+goodsname.getText()+"';";
				int rows=vt.update(up);
				MessageBox mb=new MessageBox(new Shell());
		        if(rows>0){
		        	String fin="select costmoney from usercostdata where IDcard='"+txtId.getText()+"';";
		        	ArrayList<String[]> isnew=vt.query(fin);
		        
		        	try{
		        		if(isnew.size()<=0){
		        		String frist="insert into usercostdata(roomcode,username,costmoney,IDcard)values('"+roomID.getText()+"','"+name.getText()+"','"+cost.getText()+"','"+txtId.getText()+"');";
		        	    vt.update(frist);
		        	}
		        	else{
		        		String money=Integer.parseInt(isnew.get(0)[0])+Integer.parseInt(cost.getText())+"";
		        		String upmoney="update usercostdata set costmoney='"+money+"'where IDcard='"+txtId.getText()+"';";
		        		vt.update(upmoney);
		        	}
		        	}
		        	catch(Exception e2){
		        		e2.printStackTrace();
		        	}
		        	
					mb.setText("系统消息");
					mb.setMessage("消费成功!!");
					mb.open();
					shell.close();
				}
				else{
					mb.setText("系统消息");
					mb.setMessage("消费失败!");
					mb.open();
					shell.close();
				}
				
				
			}
		});
		btnSureagain.setBounds(568, 550, 98, 30);
		btnSureagain.setText("确定");
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setForeground(SWTResourceManager.getColor(255, 0, 0));
		btnDelete.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				String sql2="delete from tool2 where name='"+name.getText()+"';";
				vt.update(sql2);
				shell.close();
				
			}
		});
		btnDelete.setBounds(154, 550, 98, 30);
		btnDelete.setText("返回");
		
		lblTime = new Label(shell, SWT.NONE);
		lblTime.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblTime.setBounds(132, 414, 76, 20);
		lblTime.setText("\u6D88\u8D39\u65F6\u95F4");
		
	
		
		
		
		
		
		
	

	}

}
