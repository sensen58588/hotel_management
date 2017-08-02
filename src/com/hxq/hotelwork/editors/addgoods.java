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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;
import com.hxq.hotelwork.tools.StringRegexUtils;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class addgoods extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.addgoods"; //$NON-NLS-1$
	private Text code;
	private Text txtName;
	private Text txtNum;
	private Text txtPrice;
    JDBCTools vt=new JDBCTools();
    private int nu;
    private Label isok;
    private String nums;
    StringRegexUtils vv=new StringRegexUtils();
    private boolean bo;
	public addgoods() {
	}


	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		
		Label lblTitle = new Label(container, SWT.NONE);
		lblTitle.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTitle.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblTitle.setBounds(348, 29, 131, 35);
		lblTitle.setText("商品进货");
		
		Label lblCode = new Label(container, SWT.NONE);
		lblCode.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblCode.setBounds(214, 114, 98, 20);
		lblCode.setText("商品编号:");
		
		code = new Text(container, SWT.BORDER);
		code.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				String co=code.getText();
				String sql="select name,price,numbers from goods where code='"+co+"';";
				ArrayList<String []> list=vt.query(sql);
				if(list.size()<=0){
					boolean bo=StringRegexUtils.Validate(co, StringRegexUtils.goodscode_regexp);
					if(bo==true){isok.setText("这是一个新的编号~");
					nu=0;
					}else{	isok.setText("错误!输入4位数字组成的编号");
					nu=0;
					}
				
					
				}else{
					nu=1;
					isok.setText("请核对信息并填写进货数量");
					txtName.setText(list.get(0)[0]);
					txtPrice.setText(list.get(0)[1]);
					nums=list.get(0)[2];
				}
			}
		});
		code.setBounds(385, 111, 131, 26);
		
		isok = new Label(container, SWT.NONE);
		isok.setBackground(SWTResourceManager.getColor(204, 255, 204));
		isok.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		isok.setBounds(542, 114, 208, 20);
		
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblName.setBounds(214, 180, 98, 20);
		lblName.setText("商品名称");
		
		txtName = new Text(container, SWT.BORDER);
		
		txtName.setBounds(385, 177, 131, 26);
		
		Label lblNum = new Label(container, SWT.NONE);
		lblNum.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblNum.setBounds(214, 311, 121, 20);
		lblNum.setText("商品数量");
		
		txtNum = new Text(container, SWT.BORDER);
		txtNum.setBounds(385, 308, 131, 26);
		
		Label lblPrice = new Label(container, SWT.NONE);
		lblPrice.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblPrice.setBounds(214, 250, 121, 20);
		lblPrice.setText("商品单价（/元）");
		
		txtPrice = new Text(container, SWT.BORDER);
		
		txtPrice.setBounds(385, 247, 131, 26);
		
		Button btnSure = new Button(container, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb=new MessageBox(new Shell());
				if(nu==0){
					if(bo==true){
						String sql="insert into goods values ('"+code.getText()+"','"+txtName.getText()+"','"+txtPrice.getText()+"','"+txtNum.getText()+"');";
						int rows=vt.update(sql);
						
						if(rows>0){
							mb.setText("系统消息");
							mb.setMessage("进货成功！");
							mb.open();
							code.setText("");
							txtName.setText("");
							txtNum.setText("");
							txtPrice.setText("");
						}else{
							mb.setText("系统消息");
							mb.setMessage("操作错误！！");
							mb.open();
						}
					}else{
						mb.setText("系统消息");
						mb.setMessage("请正确填写编号！");
						mb.open();
					}
				
				}else if(nu==1){
					String ch=Integer.parseInt(txtNum.getText())+Integer.parseInt(nums)+"";
					String sql="update goods set numbers='"+ch+"' where code='"+code.getText()+"';";
					int rows=vt.update(sql);
					
					if(rows>0){
						mb.setText("系统消息");
						mb.setMessage("进货成功！");
						mb.open();
						code.setText("");
						txtName.setText("");
						txtNum.setText("");
						txtPrice.setText("");
					}else{
						mb.setText("系统消息");
						mb.setMessage("操作错误！！");
						mb.open();
					}
				}
				
				
			}
		});
		btnSure.setBounds(326, 400, 98, 30);
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
		return "addgoods";
	}


	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "addgoodstooltip";
	}
}
