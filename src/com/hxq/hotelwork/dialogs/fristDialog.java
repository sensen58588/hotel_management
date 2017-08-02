package com.hxq.hotelwork.dialogs;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.PlatformUI;

public class fristDialog extends Dialog {

	protected Object result;
	protected Shell shell;
    private String sign;
  
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public fristDialog(Shell parent, int style) {
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
		shell.setBackground(SWTResourceManager.getColor(204, 255, 204));
		shell.setSize(654, 430);
		shell.setText("--\u97E9\u7199\u9152\u5E97\u7BA1\u7406\u7CFB\u7EDF--");
		
		Label lblWelcom = new Label(shell, SWT.NONE);
		lblWelcom.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblWelcom.setForeground(SWTResourceManager.getColor(255, 0, 51));
		lblWelcom.setFont(SWTResourceManager.getFont("华文行楷", 20, SWT.BOLD));
		lblWelcom.setBounds(234, 64, 285, 103);
		lblWelcom.setText("~\u6B22\u8FCE\u4F7F\u7528~");
		
		Button btnWorker = new Button(shell, SWT.NONE);
		btnWorker.addSelectionListener(new SelectionAdapter() {
			 Display display = PlatformUI.createDisplay();
			public void widgetSelected(SelectionEvent e) {
				loginDialog logo=new loginDialog( new Shell(display),SWT.CLOSE|SWT.APPLICATION_MODAL|SWT.MAX|SWT.MIN);
                result= logo.open();	
                shell.close();
			}
		});
		btnWorker.setBounds(296, 211, 98, 30);
		btnWorker.setText("员工登陆");
		
		Button btnCustomer = new Button(shell, SWT.NONE);
		btnCustomer.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				result=0;
				shell.close();
			}
		});
		btnCustomer.setBounds(296, 273, 98, 30);
		btnCustomer.setText("顾客消费");
		
		Label lblSelect = new Label(shell, SWT.NONE);
		lblSelect.setFont(SWTResourceManager.getFont("楷体", 12, SWT.NORMAL));
		lblSelect.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblSelect.setForeground(SWTResourceManager.getColor(0, 51, 255));
		lblSelect.setBounds(100, 159, 128, 40);
		lblSelect.setText("请选择按钮：");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(204, 255, 204));
		label.setForeground(SWTResourceManager.getColor(0, 0, 255));
		label.setBounds(234, 216, 34, 25);
		label.setText("1:");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(204, 255, 204));
		label_1.setForeground(SWTResourceManager.getColor(0, 0, 255));
		label_1.setBounds(234, 278, 34, 25);
		label_1.setText("2:");

	}

}
