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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;

public class shopEditor extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.shopEditos"; //$NON-NLS-1$
	private Table table;
	private int page = 1;
	public shopEditor() {
	}


	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("~~\u6B22\u8FCE\u5165\u4F4F\u9152\u5E97~~");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label.setAlignment(SWT.CENTER);
		label.setBounds(214, 10, 143, 20);
		
		Label lblGoods = new Label(container, SWT.NONE);
		lblGoods.setBounds(26, 54, 76, 20);
		lblGoods.setText("选购商品");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(74, 97, 402, 285);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnGoodscode = new TableColumn(table, SWT.NONE);
		tblclmnGoodscode.setWidth(100);
		tblclmnGoodscode.setText("商品编号");
		
		TableColumn tblclmnGoodsname = new TableColumn(table, SWT.NONE);
		tblclmnGoodsname.setWidth(100);
		tblclmnGoodsname.setText("商品名称");
		
		TableColumn tblclmnGoodsprice = new TableColumn(table, SWT.NONE);
		tblclmnGoodsprice.setWidth(100);
		tblclmnGoodsprice.setText("商品价格");
		
		TableColumn tblclmnNumbers = new TableColumn(table, SWT.NONE);
		tblclmnNumbers.setWidth(100);
		tblclmnNumbers.setText("现在库存");
		
		Button btnFind = new Button(container, SWT.NONE);
		btnFind.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt = new JDBCTools();
				String sql = "select * from goods";
				sql = sql+" limit "+((page-1)*10)+",10"; 
				ArrayList<String[]> rsList = jt.query(sql);
				for(int i = 0;i<rsList.size();i++){
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(rsList.get(i));
					
				}
			}
		});
		btnFind.setBounds(108, 54, 98, 30);
		btnFind.setText("商品清单");

	}

	@Override
	public void setFocus() {
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void doSaveAs() {
		
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
		
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		
		return null;
	}

	@Override
	public String getName() {
		
		return "shopEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "shopEditorToolTipText";
	}
}
