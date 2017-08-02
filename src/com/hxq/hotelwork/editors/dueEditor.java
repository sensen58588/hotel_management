package com.hxq.hotelwork.editors;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

public class dueEditor extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.dueEditor"; //$NON-NLS-1$
	private Text IDcard;
	private Text name;
	private Text datain;
	private Text dataout;
	private Text money;
    JDBCTools vt=new JDBCTools();
    private Text roomcode;
    private Text othercost;
    private Text totalcost;
    private String year1;
    private String month1;
  
	public dueEditor() {
		
	}

	

	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		
		Label lblDue = new Label(container, SWT.NONE);
		lblDue.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblDue.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblDue.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDue.setBounds(301, 10, 141, 36);
		lblDue.setText("交易核对");
		
		
		
		Label lblId = new Label(container, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblId.setAlignment(SWT.CENTER);
		lblId.setBounds(134, 76, 92, 20);
		lblId.setText("身份证号码：");
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblName.setAlignment(SWT.CENTER);
		lblName.setBounds(134, 127, 76, 20);
		lblName.setText("姓名：");
		
		Label lblRoomcode = new Label(container, SWT.NONE);
		lblRoomcode.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblRoomcode.setBounds(134, 183, 76, 20);
		lblRoomcode.setText("房间号：");
		roomcode = new Text(container, SWT.BORDER);
		roomcode.setBounds(301, 180, 126, 26);
		
		Label lblDatain = new Label(container, SWT.NONE);
		lblDatain.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblDatain.setAlignment(SWT.CENTER);
		lblDatain.setBounds(134, 231, 76, 20);
		lblDatain.setText("入住日期：");
		
		Label lblDataout = new Label(container, SWT.NONE);
		lblDataout.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblDataout.setAlignment(SWT.CENTER);
		lblDataout.setBounds(134, 290, 76, 20);
		lblDataout.setText("离开日期：");
		
		Label lblMoney = new Label(container, SWT.NONE);
		lblMoney.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblMoney.setAlignment(SWT.CENTER);
		lblMoney.setBounds(134, 350, 92, 20);
		lblMoney.setText("住宿金额：");
		
		IDcard = new Text(container, SWT.BORDER);
		IDcard.setBounds(301, 73, 292, 26);
		
		name = new Text(container, SWT.BORDER);
		name.setBounds(300, 124, 125, 26);
		
		datain = new Text(container, SWT.BORDER);
		datain.setBounds(301, 228, 212, 26);
		
		dataout = new Text(container, SWT.BORDER);
		dataout.setBounds(301, 287, 212, 26);
		
		money = new Text(container, SWT.BORDER);
		money.setBounds(301, 347, 125, 26);
		
		Label lblOthercost = new Label(container, SWT.NONE);
		lblOthercost.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblOthercost.setAlignment(SWT.CENTER);
		lblOthercost.setBounds(134, 404, 92, 20);
		lblOthercost.setText("其他消费：");
		
		othercost = new Text(container, SWT.BORDER);
		othercost.setBounds(301, 401, 126, 26);
		
		totalcost = new Text(container, SWT.BORDER);
		totalcost.setBounds(300, 453, 126, 26);
		
		Label lblTotalmoney = new Label(container, SWT.NONE);
		lblTotalmoney.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblTotalmoney.setAlignment(SWT.CENTER);
		lblTotalmoney.setBounds(134, 456, 76, 20);
		lblTotalmoney.setText("总金额：");
		
		String sql="select * from tool;";
		ArrayList<String[]> datas = vt.query(sql);
		IDcard.setText(datas.get(0)[0]);
		name.setText(datas.get(0)[1]);
		roomcode.setText(datas.get(0)[2]);
		datain.setText(datas.get(0)[3]);
		dataout.setText(datas.get(0)[4]);
		money.setText(datas.get(0)[5]);
		String fin="select costmoney from usercostdata where IDcard='"+IDcard.getText()+"';";
    	ArrayList<String[]> isnew=vt.query(fin);
    	if(isnew.size()<=0){
    		String costmoney="0";
    		othercost.setText(costmoney);
    		totalcost.setText(datas.get(0)[5]);
    	}else{
    		String costmoney=isnew.get(0)[0];
    		String totalmoney=Integer.parseInt(costmoney)+Integer.parseInt(datas.get(0)[5])+"";
    		othercost.setText(costmoney);
    		totalcost.setText(totalmoney);
    	
    	}
		
		Button btnSure = new Button(container, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				int rows=0;
				String sqlrooms="update rooms set roomwork='待清洁' where roomcode='"+roomcode.getText()+"';";
				int r=vt.update(sqlrooms);
				
				
				year1=leavingEditor.year+"";
				month1=leavingEditor.month+"";
				
				
				System.out.println(year1);
				System.out.println(month1);
				
				String sql55="select sleepmoney,costmoney,totalmoney from summoney where year='"+year1+"' and month='"+month1+"';";
				ArrayList<String []> li=vt.query(sql55);
				if(li.size()<=0){
					String sql5="insert into summoney values('"+year1+"','"+month1+"','"+dataout.getText()+"','"+money.getText()+"','"+othercost.getText()+"','"+totalcost.getText()+"');";
				rows=rows+vt.update(sql5);
				}else{
					
					String slmoney=Integer.parseInt(li.get(0)[0])+Integer.parseInt(money.getText())+"";
					String comoney=Integer.parseInt(li.get(0)[1])+Integer.parseInt(othercost.getText())+"";
					String tomoney=Integer.parseInt(li.get(0)[2])+Integer.parseInt(totalcost.getText())+"";
					String sql6="update summoney set sleepmoney='"+slmoney+"',costmoney='"+comoney+"',totalmoney='"+tomoney+"' where year='"+year1+"' and month='"+month1+"' ;";
					rows=rows+vt.update(sql6);
				}
				
				
				
				String fin="select costmoney from usercostdata where IDcard='"+IDcard.getText()+"';";
	        	ArrayList<String[]> isnew=vt.query(fin);
	        	if(isnew.size()<=0){
	        		String costmoney="0";
	        		String sqluserdata="insert into usercostdata values('"+roomcode.getText()+"','"+name.getText()+"','"+datain.getText()+"','"+dataout.getText()+"','"+money.getText()+"','"+costmoney+"','"+money.getText()+"','"+IDcard.getText()+"');";
			        rows=rows+ vt.update(sqluserdata)+r;
	        	}
	        	else{
	        		//String costmoney=isnew.get(0)[0];
	        		//String totalmoney=Integer.parseInt(costmoney)+Integer.parseInt(money.getText())+"";
	        		String sqluserdata="update usercostdata set datain='"+datain.getText()+"',dataout='"+dataout.getText()+"',sleepmoney='"+money.getText()+"',totalmoney='"+totalcost.getText()+"' where IDcard='"+IDcard.getText()+"';";
			       //update usercostdata set datain='"+datain.getText()+"',dataout='"+dataout.getText()+"',sleepmoney='"+money.getText()+"',totalmoney='"+totalcost.getText()+"' where IDcard='"+IDcard.getText()+"';
	        		rows= rows+vt.update(sqluserdata)+r;
			        
			        
	        	}
				
	         //create table usercostdata(roomcode varchar(10),username varchar(20),datain varchar(20),dataout varchar(20),sleepmoney varchar(20),costmoney varchar(20),totalmoney varchar(20),IDcard varchar(20));
		        MessageBox mb=new MessageBox(new Shell());
		        if(rows>2){
					mb.setText("系统消息");
					mb.setMessage("退房成功!!");
					mb.open();
					IDcard.setText("");
					name.setText("");
					datain.setText("");
					dataout.setText("");
					money.setText("");
					totalcost.setText("");
					othercost.setText("");
					roomcode.setText("");
					
				}
				else{
					mb.setText("系统消息");
					mb.setMessage("操作错误!!!!");
					mb.open();
				}
			}
		});
		btnSure.setBounds(290, 550, 98, 30);
		btnSure.setText("提交");
		
		
	
		
	
		

		
		
		String sql1="delete  from tool where name='"+name.getText()+"';";
        vt.update(sql1);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "dueEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "deuEditorTool";
	}
}
