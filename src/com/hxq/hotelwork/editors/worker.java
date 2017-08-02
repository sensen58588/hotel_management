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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;

public class worker extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.worker"; //$NON-NLS-1$
	private Table table;
    JDBCTools vt =new JDBCTools();
	public worker() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		
		Label lblInfo = new Label(container, SWT.NONE);
		lblInfo.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblInfo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblInfo.setBounds(377, 10, 140, 42);
		lblInfo.setText("查看员工信息");
		
		Button btnLeader = new Button(container, SWT.NONE);
		btnLeader.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				String sql="select IDcard,name,job from worker where job='经理';";
				ArrayList<String[]> list=vt.query(sql);
				for(int i = 0;i<list.size();i++){
					TableItem qi = new TableItem(table, SWT.NONE);
					qi.setText(list.get(i));
				}
			}
		});
		btnLeader.setBounds(238, 448, 98, 30);
		btnLeader.setText("查看经理");
		
		Button btnWorkers = new Button(container, SWT.NONE);
		btnWorkers.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				String sql="select IDcard,name,job from worker where job='工人';";
				ArrayList<String[]> list=vt.query(sql);
				for(int i = 0;i<list.size();i++){
					TableItem qi = new TableItem(table, SWT.NONE);
					qi.setText(list.get(i));
				}
			}
		});
		btnWorkers.setBounds(540, 448, 98, 30);
		btnWorkers.setText("查看工人");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(238, 93, 400, 319);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnIdcard = new TableColumn(table, SWT.NONE);
		tblclmnIdcard.setWidth(152);
		tblclmnIdcard.setText("工作编号");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(123);
		tblclmnName.setText("姓名");
		
		TableColumn tblclmnJobs = new TableColumn(table, SWT.NONE);
		tblclmnJobs.setWidth(119);
		tblclmnJobs.setText("职位");

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
		return "workerEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "workertooltip";
	}
}
