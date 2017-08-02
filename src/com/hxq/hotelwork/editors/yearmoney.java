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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;

public class yearmoney extends EditorPart implements IEditorInput {

	public static final String ID = "com.hxq.hotelwork.editors.yearmoney"; //$NON-NLS-1$
	private Text text;
	private Combo combo ;
    JDBCTools vt=new JDBCTools();
	public yearmoney() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 153));
		
		Label lblLogo = new Label(container, SWT.NONE);
		lblLogo.setBackground(SWTResourceManager.getColor(204, 255, 153));
		lblLogo.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblLogo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblLogo.setBounds(285, 45, 152, 40);
		lblLogo.setText("年客房销售总额");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(204, 255, 153));
		lblNewLabel.setBounds(210, 180, 143, 23);
		lblNewLabel.setText("请选择查看年份：");
		
		combo = new Combo(container, SWT.NONE);
		combo.setBounds(414, 179, 92, 28);
		
		combo.add("2014");
		combo.add("2015");
		combo.add("2016");
		
		
		Label lblWei = new Label(container, SWT.NONE);
		lblWei.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblWei.setBackground(SWTResourceManager.getColor(204, 255, 153));
		lblWei.setBounds(209, 319, 131, 23);
		lblWei.setText("年度总金额为：");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(408, 318, 98, 26);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				String year=combo.getText();
				String sql="select sleepmoney from summoney where year='"+year+"';";
				ArrayList<String []> list=vt.query(sql);
				int money=0;
				if(list.size()<=0){
					text.setText("0元");
				}else{
				for(int i=0;i<list.size();i++){
					String q=list.get(i)[0];
					money=money+Integer.parseInt(q);
				}
				String mon=money+"元";
				text.setText(mon);
				}
			}
		});
		btnNewButton.setBounds(210, 251, 98, 30);
		btnNewButton.setText("确定");

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
		return "yeartotal";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "yeartotaltooltip";
	}

}
