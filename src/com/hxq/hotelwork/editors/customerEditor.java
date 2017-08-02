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

public class customerEditor extends EditorPart implements IEditorInput {

	public static final String ID = "com.hxq.hotelwork.editors.customerEditor"; //$NON-NLS-1$
	private Table table;
    JDBCTools vt=new JDBCTools();
	public customerEditor() {
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
		lblLogo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblLogo.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblLogo.setBounds(259, 45, 114, 44);
		lblLogo.setText("×¡ËÞÈËÔ±");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(95, 95, 418, 237);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u5BA2\u623F\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u987E\u5BA2\u59D3\u540D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(214);
		tableColumn_2.setText("\u987E\u5BA2\u8EAB\u4EFD\u8BC1\u53F7\u7801");
		
		String sql ="select roomcode,name,IDcard from customerinfo where num='0';";
		ArrayList<String []> rsList=vt.query(sql);
		for(int i = 0;i<rsList.size();i++){
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(rsList.get(i));
			
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
		return "customEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Editotooltip";
	}
}
