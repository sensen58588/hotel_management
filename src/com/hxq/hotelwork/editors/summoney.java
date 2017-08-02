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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.wb.swt.SWTResourceManager;

public class summoney extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.summoney"; //$NON-NLS-1$
	private Table table;
    private Combo year;
    private Combo month;
    JDBCTools vt=new JDBCTools();
	public summoney() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		
		Label lblLogo = new Label(container, SWT.NONE);
		lblLogo.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblLogo.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblLogo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblLogo.setBounds(332, 39, 120, 37);
		lblLogo.setText("季度统计");
		
		Label lblYear = new Label(container, SWT.NONE);
		lblYear.setForeground(SWTResourceManager.getColor(0, 153, 255));
		lblYear.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblYear.setBounds(236, 127, 76, 20);
		lblYear.setText("请选择年份");
		
		year = new Combo(container, SWT.NONE);
		year.setBounds(402, 124, 92, 28);
		year.add("2014");
		year.add("2015");
		year.add("2016");
		Label lblMonth = new Label(container, SWT.NONE);
		lblMonth.setForeground(SWTResourceManager.getColor(0, 153, 255));
		lblMonth.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblMonth.setBounds(236, 195, 76, 20);
		lblMonth.setText("请选择季度");
		
		month = new Combo(container, SWT.NONE);
		month.setBounds(402, 192, 92, 28);
		month.add("1");
		month.add("2");
		month.add("3");
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				String ye=year.getText();
				// month |  sleepmoney | costmoney | totalmoney
				String sql1="select month,sleepmoney,costmoney,totalmoney from summoney where year='"+ye+"';";
				ArrayList<String []> list=vt.query(sql1);
				//String sqlm="select month,sleepmoney,costmoney,totalmoney from summoney where month>;";
				ArrayList<String []> relist=new ArrayList<String []>();
				if(list.size()<=0){
					table.removeAll();
					String mm=month.getText();
					int mon=Integer.parseInt(mm)*4-3; 
					
					for(int i = 0;i<4;i++){
					   String one=mon+"月";//1 1,2,3,4,        2 5,6,7,8,         3 9,10,11,12  
						String [] v1={""+one+"","0","0","0"};
						mon++;
						relist.add(i, v1);//
						
						
					}
					String [] t1={"合计","0元","0元","0元"};
					relist.add(4,t1);
					for(int j=0;j<5;j++){
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(relist.get(j));
					}
				}
				
				else{
					String mm=month.getText();
					int mon=Integer.parseInt(mm)*4-3;
					int mon2=mon+3;
					String sqlm="select month,sleepmoney,costmoney,totalmoney from summoney where year='"+ye+"' and month>='"+mon+"' and month<='"+mon2+"';";
					ArrayList<String []> mlist=vt.query(sqlm);
					
				    int hang=mlist.size();
					if(hang==1){
						String []t2={"合计",""+mlist.get(0)[1]+"",""+mlist.get(0)[2]+"",""+mlist.get(0)[3]+""};
						relist.add(0,t2);
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(mlist.get(0));
						TableItem t21 = new TableItem(table,SWT.NONE);
						t21.setText(relist.get(0));
					}else if(hang==2){
						
						String sleepmoney=Integer.parseInt(mlist.get(0)[1])+Integer.parseInt(mlist.get(1)[1])+"";
						String costmoney=Integer.parseInt(mlist.get(0)[2])+Integer.parseInt(mlist.get(1)[2])+"";
						String totalmoney=Integer.parseInt(mlist.get(0)[3])+Integer.parseInt(mlist.get(1)[3])+"";
						String [] t3={"合计",""+sleepmoney+"",""+costmoney+"",""+totalmoney+""};
						relist.add(0,t3);
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(mlist.get(0));
						TableItem t22 = new TableItem(table,SWT.NONE);
						t22.setText(mlist.get(1));
						TableItem t23 = new TableItem(table,SWT.NONE);
						t23.setText(relist.get(0));
					}else if(hang==3){
						String sleepmoney=Integer.parseInt(mlist.get(0)[1])+Integer.parseInt(mlist.get(1)[1])+Integer.parseInt(mlist.get(2)[1])+"";
						String costmoney=Integer.parseInt(mlist.get(0)[2])+Integer.parseInt(mlist.get(1)[2])+Integer.parseInt(mlist.get(2)[2])+"";
						String totalmoney=Integer.parseInt(mlist.get(0)[3])+Integer.parseInt(mlist.get(1)[3])+Integer.parseInt(mlist.get(2)[3])+"";
						String [] t4={"合计",""+sleepmoney+"",""+costmoney+"",""+totalmoney+""};
						relist.add(0,t4);
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(mlist.get(0));
						TableItem t22 = new TableItem(table,SWT.NONE);
						t22.setText(mlist.get(1));
						TableItem t23 = new TableItem(table,SWT.NONE);
						t23.setText(mlist.get(2));
						TableItem t24 = new TableItem(table,SWT.NONE);
						t24.setText(relist.get(0));
					}else if(hang==4){
						String sleepmoney=Integer.parseInt(mlist.get(0)[1])+Integer.parseInt(mlist.get(1)[1])+Integer.parseInt(mlist.get(2)[1])+Integer.parseInt(mlist.get(3)[1])+"";
						String costmoney=Integer.parseInt(mlist.get(0)[2])+Integer.parseInt(mlist.get(1)[2])+Integer.parseInt(mlist.get(2)[2])+Integer.parseInt(mlist.get(3)[2])+"";
						String totalmoney=Integer.parseInt(mlist.get(0)[3])+Integer.parseInt(mlist.get(1)[3])+Integer.parseInt(mlist.get(2)[3])+Integer.parseInt(mlist.get(3)[3])+"";
						String [] t4={"合计",""+sleepmoney+"",""+costmoney+"",""+totalmoney+""};
						relist.add(0,t4);
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(mlist.get(0));
						TableItem t22 = new TableItem(table,SWT.NONE);
						t22.setText(mlist.get(1));
						TableItem t23 = new TableItem(table,SWT.NONE);
						t23.setText(mlist.get(2));
						TableItem t24 = new TableItem(table,SWT.NONE);
						t24.setText(mlist.get(3));
						TableItem t25 = new TableItem(table,SWT.NONE);
						t25.setText(relist.get(0));
					}
					
					
				}
				
				
			}
		});
		btnNewButton.setBounds(319, 264, 98, 30);
		btnNewButton.setText("确定");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(255, 255, 255));
		table.setBounds(154, 318, 464, 176);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnMonth = new TableColumn(table, SWT.NONE);
		tblclmnMonth.setWidth(109);
		tblclmnMonth.setText("\u6708\u4EFD");
		
		TableColumn tblclmnSleepmoney = new TableColumn(table, SWT.NONE);
		tblclmnSleepmoney.setWidth(115);
		tblclmnSleepmoney.setText("\u5BA2\u623F\u9500\u552E\u91D1\u989D");
		
		TableColumn tblclmnCostmoney = new TableColumn(table, SWT.NONE);
		tblclmnCostmoney.setWidth(112);
		tblclmnCostmoney.setText("\u9910\u996E\u6D88\u8D39\u91D1\u989D");
		
		TableColumn tblclmnTotalmoney = new TableColumn(table, SWT.NONE);
		tblclmnTotalmoney.setWidth(117);
		tblclmnTotalmoney.setText("\u4E2A\u4EBA\u603B\u6D88\u8D39\u989D");
		
		

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
		return "summoney";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "summoneyeditortool";
	}

}
