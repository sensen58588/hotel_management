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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import com.hxq.hotelwork.tools.JDBCTools;

public class deroomDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text code;
	private Text recode;
    private Label Ok;
	JDBCTools vt=new JDBCTools();
	private Label lblFind;
	public deroomDialog(Shell parent, int style) {
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
		shell.setSize(716, 503);
		shell.setText("\u5BA2\u623F\u7BA1\u7406");
		
		Label lblDelte = new Label(shell, SWT.NONE);
		lblDelte.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblDelte.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDelte.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblDelte.setBounds(300, 10, 160, 51);
		lblDelte.setText("���ٿͷ�");
		
		lblFind = new Label(shell, SWT.NONE);
		lblFind.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblFind.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblFind.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblFind.setBounds(481, 122, 110, 20);
		
		
		Label lblInout = new Label(shell, SWT.NONE);
		lblInout.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblInout.setBounds(221, 121, 76, 20);
		lblInout.setText("�ͷ���ţ�");
		
		code = new Text(shell, SWT.BORDER);
		code.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				String sql="select * from rooms where roomcode='"+code.getText()+"';";
				ArrayList<String[]> list=vt.query(sql);
				if(list.size()<=0){
					lblFind.setText("���󣡲����ڸÿͷ��ţ�");
				}else{
					lblFind.setText("��");
				}
			}
		});
		code.setBounds(353, 118, 98, 26);
		
		
		Label lblAgain = new Label(shell, SWT.NONE);
		lblAgain.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblAgain.setBounds(221, 172, 76, 20);
		lblAgain.setText("�ٴ�ȷ�ϣ�");
		
		Ok = new Label(shell, SWT.NONE);
		Ok.setBackground(SWTResourceManager.getColor(153, 255, 204));
		Ok.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		Ok.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		Ok.setBounds(481, 176, 110, 20);
		
		
		recode = new Text(shell, SWT.BORDER);
		recode.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {

				String chh=recode.getText();
				if(chh.equals(code.getText())){
					Ok.setText("��");
				}
			}
		});
		recode.setBounds(353, 172, 98, 26);
		
		Button btnSure = new Button(shell, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if(lblFind.getText().equals("��")&&Ok.getText().equals("��")){
					String sql ="delete from rooms where roomcode='"+recode.getText()+"';";
					int rows=vt.update(sql);
					 MessageBox mb=new MessageBox(new Shell());
				        if(rows>0){
							mb.setText("ϵͳ��Ϣ");
							mb.setMessage("�����ɹ�!!");
							mb.open();
							shell.close();
						}
						else{
							mb.setText("ϵͳ��Ϣ");
							mb.setMessage("��������!!!!");
							mb.open();
						}
				        
				}
			}
		});
		btnSure.setBounds(286, 249, 98, 30);
		btnSure.setText("ȷ��");
		
		
		
	


	}

}
