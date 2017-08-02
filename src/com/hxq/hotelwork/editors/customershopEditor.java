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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.dialogs.costDialog;
import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class customershopEditor extends EditorPart implements IEditorInput {

	public static final String ID = "com.hxq.hotelwork.editors.customershopEditor"; //$NON-NLS-1$
	private Table table;
	private int page = 1;
	JDBCTools jt = new JDBCTools();
	private Text goodscode;//
	private Text goodsnum;
	private Text userID;
	private Text roomcode;
	private String IDcard;//
	private String goodsprice;
	private String username;//
	private Label lblOk ;
	private int tt;
	public customershopEditor() {
	}

	
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(152, 251, 152));
		
		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label.setText("~~欢迎购买~~");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBackground(SWTResourceManager.getColor(204, 255, 204));
		label.setAlignment(SWT.CENTER);
		label.setBounds(269, 10, 163, 33);
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(240, 255, 240));
		table.setBounds(77, 49, 520, 272);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(101);
		tableColumn.setText("商品编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(167);
		tableColumn_1.setText("商品名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(140);
		tableColumn_2.setText("商品价格（RMB）");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(105);
		tableColumn_3.setText("现在库存");
		
		String sql = "select * from goods";
		sql = sql+" limit "+((page-1)*10)+",10"; 
		ArrayList<String[]> rsList = jt.query(sql);
		for(int i = 0;i<rsList.size();i++){
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(rsList.get(i));
			
		}
		Button btnPagedown = new Button(container, SWT.NONE);
		btnPagedown.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
                page++;
				String sql = "select * from goods";
			    ArrayList<String[]> rsList = jt.queryForPage(sql,page);
				
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
		btnPagedown.setBounds(499, 327, 98, 30);
		btnPagedown.setText("下一页");
		
		Button btnPageup = new Button(container, SWT.NONE);
		btnPageup.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {page--;
			if(page==0){
				page++;
				MessageBox mb=new MessageBox(new Shell());
		        mb.setText("系统提示");
				mb.setMessage("已是第一页！");
			    mb.open();
			}else{
				table.removeAll();
				String sql = "select * from goods";
				ArrayList<String[]> rsList = jt.queryForPage(sql,page);
				for(int i = 0;i<rsList.size();i++){
					TableItem ti = new TableItem(table,SWT.NONE);
					ti.setText(rsList.get(i));
				}
			}
			
		}
	});
		btnPageup.setBounds(78, 332, 98, 30);
		btnPageup.setText("上一页");
		
		Label lblInput = new Label(container, SWT.NONE);
		lblInput.setBackground(SWTResourceManager.getColor(153, 255, 153));
		lblInput.setBounds(34, 419, 191, 20);
		lblInput.setText("请输入要购买的商品编号：");
		
		goodscode = new Text(container, SWT.BORDER);
		goodscode.setBounds(257, 416, 175, 26);
		
		Label lblNumber = new Label(container, SWT.NONE);
		lblNumber.setBackground(SWTResourceManager.getColor(153, 255, 153));
		lblNumber.setBounds(34, 475, 180, 20);
		lblNumber.setText("请输入要购买的商品数量：");
		
		goodsnum = new Text(container, SWT.BORDER);
		goodsnum.setBounds(257, 472, 175, 26);
		
		Label lblRoom = new Label(container, SWT.NONE);
		lblRoom.setBackground(SWTResourceManager.getColor(153, 255, 153));
		lblRoom.setBounds(36, 581, 118, 20);
		lblRoom.setText("您的房间号为：");
		
		roomcode = new Text(container, SWT.BORDER);
		roomcode.setBounds(257, 576, 175, 26);
		
		
		Label lblId = new Label(container, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(153, 255, 153));
		lblId.setBounds(32, 531, 174, 20);
		lblId.setText("请输入您的身份证号：");
		
		lblOk = new Label(container, SWT.NONE);
		lblOk.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblOk.setBackground(SWTResourceManager.getColor(153, 255, 153));
		lblOk.setBounds(472, 531, 125, 20);
		
		userID = new Text(container, SWT.BORDER);
		userID.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				
			    IDcard=userID.getText();
			    String sql="select roomcode from customerinfo where IDcard='"+IDcard+"' and num='0';";
			    ArrayList<String[]> list = jt.query(sql);
			    if(list.size()<=0){
			    	lblOk.setText("改身份不在酒店中");
			    	tt=0;
			    	
			    }else{
			    	    tt=1;
			    	    lblOk.setText("√");
			    	    String room=list.get(0)[0];
					    roomcode.setText(room);
			    }
			   
			}
		});
		userID.setBounds(256, 526, 176, 26);
		

		
		Button btnSure = new Button(container, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if(tt==1){
					String sql="select name from goods where code='"+goodscode.getText()+"'";
					ArrayList<String[]> list=jt.query(sql);
					String goodsname=list.get(0)[0];
					
					String sqln="select name from customerinfo where IDcard='"+userID.getText()+"' and num='0'";
					ArrayList<String[]> listn=jt.query(sqln);
					username=listn.get(0)[0];
					
					String sqlp="select price from goods where code='"+goodscode.getText()+"'";
					ArrayList<String[]> listp=jt.query(sqlp);
					goodsprice=listp.get(0)[0];
					int price=Integer.parseInt(goodsprice);
					int numbers=Integer.parseInt(goodsnum.getText());
					String cost=""+price*numbers;
					
					String sqlt="insert into tool2 values('"+roomcode.getText()+"','"+username+"','"+goodsname+"','"+goodsnum.getText()+"','"+cost+"','"+userID.getText()+"');";
					jt.update(sqlt);
					
					
					costDialog co=new costDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.CLOSE|SWT.APPLICATION_MODAL|SWT.MAX|SWT.MIN);
	                co.open(goodscode.getText());	
	                
	                goodscode.setText("");
	                goodsnum.setText("");
	                userID.setText("");
	                roomcode.setText("");
	                lblOk.setText("");
				}else{
					
					MessageBox mb=new MessageBox(new Shell());
					mb.setText("系统消息");
					mb.setMessage("身份证错误！");
					mb.open();
					goodscode.setText("");
	                goodsnum.setText("");
	                userID.setText("");
	                roomcode.setText("");
	                lblOk.setText("");
				}
				
				
                
			}
		});
		btnSure.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnSure.setBounds(278, 659, 98, 30);
		btnSure.setText("确定");
		
		
		
		
		
		
		
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
		return "customershop";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "customershoptooltip";
	}
}
