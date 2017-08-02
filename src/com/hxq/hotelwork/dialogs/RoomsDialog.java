package com.hxq.hotelwork.dialogs;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class RoomsDialog extends Dialog {

	protected Object result;
	protected Shell shell;
    private String value;
    private Text addnum;
    private Text upnum;
    JDBCTools vt=new JDBCTools();
    private Label lOk;
    private Label lupdate;
	public RoomsDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
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

	
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setBackground(SWTResourceManager.getColor(204, 255, 204));
		shell.setSize(916, 675);
		shell.setText("\u5BA2\u623F\u7BA1\u7406");
		
		lOk = new Label(shell, SWT.NONE);
		lOk.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lOk.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lOk.setBounds(526, 196, 192, 20);
		
		
	
		
		Label lblAdd = new Label(shell, SWT.NONE);
		lblAdd.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblAdd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblAdd.setBounds(385, 47, 192, 54);
		lblAdd.setText(value);
		
		Label lblInput = new Label(shell, SWT.NONE);
		lblInput.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblInput.setBounds(157, 196, 121, 20);
		lblInput.setText("增加客房号：");
		
		addnum = new Text(shell, SWT.BORDER);
		addnum.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				String chh=addnum.getText();
				String sql="select * from rooms where roomcode='"+chh+"';";
				ArrayList<String[]> list=vt.query(sql);
				if(list.size()<=0){
					lOk.setText("输入正确~");
				}else{
					lOk.setText("错误!!该客房号已被使用!!");
				}
				
			}
		});
		addnum.setBounds(385, 190, 121, 26);
		
		Label lblUpdate = new Label(shell, SWT.NONE);
		lblUpdate.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblUpdate.setBounds(157, 294, 206, 20);
		lblUpdate.setText("新增客房价位（单位：/晚）：");
		
		upnum = new Text(shell, SWT.BORDER);
		upnum.setBounds(385, 291, 121, 26);
		
		Button btnSure = new Button(shell, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				String chh=lOk.getText();
				String newroom="待入住";
				if(chh.equals("输入正确~")){
					String sql2="insert into rooms values('"+addnum.getText()+"','"+upnum.getText()+"','"+newroom+"');";
					int rows=vt.update(sql2);
					MessageBox mb=new MessageBox(new Shell());
					if(rows>0){
						mb.setText("系统消息");
						mb.setMessage("增加成功!!");
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
		btnSure.setBounds(385, 425, 98, 30);
		btnSure.setText("提交");
		
		
		

	}
}
