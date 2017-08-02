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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class roomsinfo extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.roomsinfo"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private Table table_2;
	JDBCTools jt = new JDBCTools();
	private Text cleanroom;

	public roomsinfo() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(224, 255, 255));
		container.setForeground(SWTResourceManager.getColor(72, 209, 204));
		
		Label lblWaitting = new Label(container, SWT.NONE);
		lblWaitting.setBackground(SWTResourceManager.getColor(224, 255, 255));
		lblWaitting.setForeground(SWTResourceManager.getColor(72, 209, 204));
		lblWaitting.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblWaitting.setBounds(379, 10, 92, 36);
		lblWaitting.setText("待入住");
		
		Label lblClean = new Label(container, SWT.NONE);
		lblClean.setBackground(SWTResourceManager.getColor(224, 255, 255));
		lblClean.setForeground(SWTResourceManager.getColor(72, 209, 204));
		lblClean.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblClean.setBounds(615, 10, 96, 36);
		lblClean.setText("待清洁");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(109, 52, 106, 320);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnRoomcodes = new TableColumn(table, SWT.NONE);
		tblclmnRoomcodes.setWidth(100);
		tblclmnRoomcodes.setText("客房号码");
		String sql="select * from rooms where roomwork='已入住';";
		ArrayList<String[]> rsList = jt.query(sql);
		for(int i = 0;i<rsList.size();i++){
			TableItem qi = new TableItem(table, SWT.NONE);
			qi.setText(rsList.get(i));
		}
		
	 
		
		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(325, 52, 185, 320);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnRooms = new TableColumn(table_1, SWT.NONE);
		tblclmnRooms.setWidth(88);
		tblclmnRooms.setText("客房号码");
		
		TableColumn tblclmnPrice = new TableColumn(table_1, SWT.NONE);
		tblclmnPrice.setWidth(92);
		tblclmnPrice.setText("价格");
		String sql2="select roomcode,roomprice from rooms where roomwork='待入住';";
		ArrayList<String[]> rsList2 = jt.query(sql2);
		for(int i = 0;i<rsList2.size();i++){
			TableItem ti = new TableItem(table_1, SWT.NONE);
			ti.setText(rsList2.get(i));
		}
		
		table_2 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setBounds(605, 52, 106, 320);
		
		TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("客房号码");
		
		Label lblUsing = new Label(container, SWT.NONE);
		lblUsing.setBackground(SWTResourceManager.getColor(224, 255, 255));
		lblUsing.setForeground(SWTResourceManager.getColor(72, 209, 204));
		lblUsing.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblUsing.setBounds(122, 10, 92, 39);
		lblUsing.setText("\u5DF2\u5165\u4F4F");
		
		Label lblClean_1 = new Label(container, SWT.NONE);
		lblClean_1.setBackground(SWTResourceManager.getColor(224, 255, 255));
		lblClean_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblClean_1.setBounds(278, 453, 106, 21);
		lblClean_1.setText("清洁房间：");
		
		cleanroom = new Text(container, SWT.BORDER);
		cleanroom.setBounds(413, 452, 125, 26);
		
		Button btnSure = new Button(container, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				
				String croom=cleanroom.getText();
				String sql="update rooms set roomwork='待入住' where roomcode='"+croom+"';";
				int rows= jt.update(sql);
				 MessageBox mb=new MessageBox(new Shell());
				 if(rows>0){
					 mb.setText("系统消息");
						mb.setMessage("操作成功!!");
						mb.open();
						cleanroom.setText("");
						table.removeAll();
						String sql0="select * from rooms where roomwork='已入住';";
						ArrayList<String[]> rsList = jt.query(sql0);
						for(int i = 0;i<rsList.size();i++){
							TableItem qi = new TableItem(table, SWT.NONE);
							qi.setText(rsList.get(i));
						}
						table_1.removeAll();
						String sql2="select roomcode,roomprice from rooms where roomwork='待入住';";
						ArrayList<String[]> rsList2 = jt.query(sql2);
						for(int i = 0;i<rsList2.size();i++){
							TableItem ti = new TableItem(table_1, SWT.NONE);
							ti.setText(rsList2.get(i));
						}
						table_2.removeAll();
						String sql3="select roomcode from rooms where roomwork='待清洁';";
						ArrayList<String[]> rsList3 = jt.query(sql3);
						for(int i = 0;i<rsList3.size();i++){
							TableItem qi = new TableItem(table_2, SWT.NONE);
							qi.setText(rsList3.get(i));
						}
				 }else{
					 mb.setText("系统消息");
						mb.setMessage("操作失败!!");
						mb.open();
				 }
			}
		});
		btnSure.setBounds(356, 547, 98, 30);
		btnSure.setText("提交");
		
		
	

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
		return "roomsinfoeditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "roomsinfotooltiptext";
	}
}
