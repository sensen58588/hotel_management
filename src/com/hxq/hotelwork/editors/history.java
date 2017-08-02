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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.wb.swt.SWTResourceManager;

public class history extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.history"; //$NON-NLS-1$
	private Table table;
    JDBCTools vt=new JDBCTools();
	public history() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(153, 255, 204));
		
		Label lblLogo = new Label(container, SWT.NONE);
		lblLogo.setForeground(SWTResourceManager.getColor(255, 0, 153));
		lblLogo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblLogo.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblLogo.setBounds(410, 53, 121, 40);
		lblLogo.setText("历史纪录");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(34, 122, 878, 316);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(249);
		tblclmnId.setText("身份证号");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("姓名");
		
		TableColumn tblclmnDatain = new TableColumn(table, SWT.NONE);
		tblclmnDatain.setWidth(211);
		tblclmnDatain.setText("入住时间");
		
		TableColumn tblclmnDataout = new TableColumn(table, SWT.NONE);
		tblclmnDataout.setWidth(205);
		tblclmnDataout.setText("离开时间");
		
		TableColumn tblclmnMoney = new TableColumn(table, SWT.NONE);
		tblclmnMoney.setWidth(109);
		tblclmnMoney.setText("消费金额");
		
		
		String sql="select IDcard,username,datain,dataout,totalmoney from usercostdata;";
		ArrayList<String []> rsList=vt.query(sql);
		for(int i = 0;i<rsList.size();i++){
			TableItem qi = new TableItem(table, SWT.NONE);
			qi.setText(rsList.get(i));
		}
	
		

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
		return "history";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "historytool";
	}

}
