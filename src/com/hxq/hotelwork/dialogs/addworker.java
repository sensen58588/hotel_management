package com.hxq.hotelwork.dialogs;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;
import com.hxq.hotelwork.tools.StringRegexUtils;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class addworker extends Dialog {

	protected Object result;
	protected Shell shell;
    private String value;
    private Text IDcard;
    private Text name;
    private Text pw;
    private Label lblCh;
    private Button btnSure;
    private Combo jobs ;
	JDBCTools vt=new JDBCTools();
	private Label lblOk;
	StringRegexUtils vv =new StringRegexUtils();
	public addworker(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
   public Object open(String ch) {
	    value=ch;
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

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setBackground(SWTResourceManager.getColor(153, 255, 204));
		shell.setSize(830, 610);
		shell.setText("\u5458\u5DE5\u7BA1\u7406");
		
		
		lblOk = new Label(shell, SWT.NONE);
		lblOk.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblOk.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblOk.setBounds(602, 113, 212, 20);
		
		Label lblInput = new Label(shell, SWT.NONE);
		lblInput.setForeground(SWTResourceManager.getColor(0, 51, 255));
		lblInput.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblInput.setBounds(218, 113, 86, 20);
		lblInput.setText("\u5DE5\u4F5C\u7F16\u53F7:");
		
		IDcard = new Text(shell, SWT.BORDER);
		IDcard.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				
				
				
				String sql="select IDcard from worker where IDcard='"+IDcard.getText()+"';";
				
				ArrayList<String[]> list=vt.query(sql);
				if(list.size()<=0){
					if(value.equals("增加员工")){
						String id=IDcard.getText();
						boolean bo=vv.Validate(id,StringRegexUtils.workercode_regexp);
						if(bo==true){
						lblOk.setText("可以使用");
						}else{
							lblOk.setText("错误！请输入3位数字组成的编号");
						}
						}else{}
					
				}else{
					if(value.equals("删去员工")){
                         String sql2="select password,name,job from worker where IDcard='"+IDcard.getText()+"';";
                         ArrayList<String[]> li=vt.query(sql2);
                         pw.setText(li.get(0)[0]);
                         name.setText(li.get(0)[1]);
                         jobs.setText(li.get(0)[2]);
                         
						}else{
							lblOk.setText("此编号已被使用");
							
						}
				}
				
			}
		});
		IDcard.setBounds(378, 110, 201, 26);
		
		Label lblInputnum = new Label(shell, SWT.NONE);
		lblInputnum.setToolTipText("");
		lblInputnum.setForeground(SWTResourceManager.getColor(0, 51, 255));
		lblInputnum.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblInputnum.setBounds(218, 263, 76, 20);
		lblInputnum.setText("\u5458\u5DE5\u59D3\u540D:");
		
		name = new Text(shell, SWT.BORDER);
		name.setBounds(378, 260, 131, 26);
		
		Label lblInputpw = new Label(shell, SWT.NONE);
		lblInputpw.setForeground(SWTResourceManager.getColor(0, 51, 255));
		lblInputpw.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblInputpw.setBounds(218, 192, 76, 20);
		lblInputpw.setText("\u5BC6\u7801:");
		
		pw = new Text(shell, SWT.PASSWORD);
		pw.setBounds(378, 192, 201, 26);
		
		Label lblJob = new Label(shell, SWT.NONE);
		lblJob.setForeground(SWTResourceManager.getColor(0, 51, 255));
		lblJob.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblJob.setBounds(218, 352, 76, 20);
		lblJob.setText("职位");
		
	    jobs = new Combo(shell, SWT.NONE);
		jobs.setBounds(378, 349, 125, 28);
		jobs.add("工人");
		jobs.add("经理");

		
		lblCh = new Label(shell, SWT.NONE);
		lblCh.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblCh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblCh.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblCh.setBounds(341, 29, 158, 37);
		lblCh.setText(value);
		
		btnSure = new Button(shell, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if(value.equals("增加员工")){
					String sql ="insert into worker values('"+IDcard.getText()+"','"+pw.getText()+"','"+name.getText()+"','"+jobs.getText()+"');";
					int rows=vt.update(sql);
					 MessageBox mb=new MessageBox(new Shell());
				        if(rows>0){
							mb.setText("系统消息");
							mb.setMessage("操作成功!!");
							mb.open();
							shell.close();
						}
						else{
							mb.setText("系统消息");
							mb.setMessage("操作错误!!!!");
							mb.open();
						}
				}else{
					String sql="delete from worker where IDcard='"+IDcard.getText()+"';";
					int rows=vt.update(sql);
					 MessageBox mb=new MessageBox(new Shell());
				        if(rows>0){
							mb.setText("系统消息");
							mb.setMessage("操作成功!!");
							mb.open();
							shell.close();
						}
						else{
							mb.setText("系统消息");
							mb.setMessage("操作错误!!!!");
							mb.open();
						}
					
					
				}
				
			}
		});
		btnSure.setBounds(341, 463, 98, 30);
		btnSure.setText("提交");
		
		
		
	
	}
}
