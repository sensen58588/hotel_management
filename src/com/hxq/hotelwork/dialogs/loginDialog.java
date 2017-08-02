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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;

public class loginDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtId;
	private Text txtPw;
    JDBCTools vt=new JDBCTools();
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public loginDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
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
		shell.setSize(646, 465);
		shell.setText("--\u97E9\u7199\u9152\u5E97\u7BA1\u7406\u7CFB\u7EDF--");
		
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblLogin.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblLogin.setBounds(279, 44, 92, 30);
		lblLogin.setText("登陆");
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblId.setBounds(187, 125, 86, 20);
		lblId.setText("工作编号：");
		
		txtId = new Text(shell, SWT.BORDER);
		txtId.setBackground(SWTResourceManager.getColor(204, 255, 255));
		txtId.setBounds(311, 122, 149, 26);
		
		Label lblPw = new Label(shell, SWT.NONE);
		lblPw.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblPw.setBounds(187, 214, 76, 20);
		lblPw.setText("密码：");
		
		txtPw = new Text(shell, SWT.PASSWORD);
		txtPw.setBackground(SWTResourceManager.getColor(204, 255, 255));
		txtPw.setBounds(311, 211, 149, 26);
		
		Button btnSure = new Button(shell, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				String code=txtId.getText();
				String pw=txtPw.getText();
				String sql="select password,job from worker where IDcard='"+code+"'";
				ArrayList<String[]> list=vt.query(sql);
				
				MessageBox tt=new MessageBox(new Shell());
				if(list.size()>0){
					String ispw=list.get(0)[0];
					if(ispw.equals(pw)){
						String job=list.get(0)[1];
						if(job.equals("经理")){
							result=1;
							tt.setText("系统消息");
							tt.setMessage("--欢迎经理--");
							tt.open();
							
						}else if(job.equals("工人")){
							result=2;
							tt.setText("系统消息");
							tt.setMessage("--好好工作，天天向上--");
							tt.open();
						}
						
					}else{
						tt.setText("系统消息");
						tt.setMessage("密码错误!!");
						tt.open();
					}
				}else if(list.size()<=0){
					tt.setText("系统消息");
					tt.setMessage("工作编号不存在!!");
					tt.open();
				}
				
				
			shell.close();	
					
				}	
		});
		btnSure.setBounds(257, 289, 98, 30);
		btnSure.setText("确定");

	}
}
