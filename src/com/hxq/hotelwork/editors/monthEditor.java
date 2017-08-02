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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class monthEditor extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.monthEditor"; //$NON-NLS-1$
	private Table table;
	private int page = 1;
	JDBCTools vt=new JDBCTools();
	public monthEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		
		Label lblMonth = new Label(container, SWT.NONE);
		lblMonth.setForeground(SWTResourceManager.getColor(51, 0, 255));
		lblMonth.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblMonth.setFont(SWTResourceManager.getFont("微软雅黑 Light", 12, SWT.BOLD));
		lblMonth.setBounds(424, 24, 135, 44);
		lblMonth.setText("消费记录");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(21, 92, 896, 300);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(182);
		tblclmnData.setText("消费日期");
		
		TableColumn tblclmnIdcard = new TableColumn(table, SWT.NONE);
		tblclmnIdcard.setWidth(216);
		tblclmnIdcard.setText("身份证号");
		
		TableColumn tblclmnUsername = new TableColumn(table, SWT.NONE);
		tblclmnUsername.setWidth(119);
		tblclmnUsername.setText("顾客姓名");
		
		TableColumn tblclmnCode = new TableColumn(table, SWT.NONE);
		tblclmnCode.setWidth(123);
		tblclmnCode.setText("商品编号");
		
		TableColumn tblclmnGoodsname = new TableColumn(table, SWT.NONE);
		tblclmnGoodsname.setWidth(150);
		tblclmnGoodsname.setText("商品名称");
		
		TableColumn tblclmnMoney = new TableColumn(table, SWT.NONE);
		tblclmnMoney.setWidth(100);
		tblclmnMoney.setText("消费金额");
		
		String sql = "select * from costdatas";
		sql = sql+" limit "+((page-1)*10)+",10"; 
		ArrayList<String[]> rsList = vt.query(sql);
		for(int i = 0;i<rsList.size();i++){
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(rsList.get(i));}
		
		Button btnPageup = new Button(container, SWT.NONE);
		btnPageup.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				page--;
				if(page==0){
					page++;
					MessageBox mb=new MessageBox(new Shell());
			        mb.setText("系统提示");
					mb.setMessage("已是第一页！");
				    mb.open();
				}else{
					table.removeAll();
					String sql = "select * from costdatas";
					ArrayList<String[]> rsList = vt.queryForPage(sql,page);
					for(int i = 0;i<rsList.size();i++){
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(rsList.get(i));
					}
				}
				
			
			}
		});
		btnPageup.setBounds(67, 411, 98, 30);
		btnPageup.setText("上一页");
		
		Button btnPagedown = new Button(container, SWT.NONE);
		btnPagedown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			    page++;
				String sql = "select * from costdatas";
			    ArrayList<String[]> rsList = vt.queryForPage(sql,page);
				
				if(rsList.size()<=0){
					page--;
					MessageBox mb=new MessageBox(new Shell());
			        mb.setText("系统提示");
					mb.setMessage("已是最后一页！");
				    mb.open();
					
				
				}else{
					table.removeAll();
					for(int i = 0;i<rsList.size();i++){
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(rsList.get(i));
					}
				}
			}
		});
		btnPagedown.setBounds(813, 411, 98, 30);
		btnPagedown.setText("下一页");
		

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
		return "monthEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "montheditrotooltip";
	}
}
