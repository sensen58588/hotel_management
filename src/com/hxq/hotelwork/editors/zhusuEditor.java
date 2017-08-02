package com.hxq.hotelwork.editors;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.tools.JDBCTools;
import com.hxq.hotelwork.tools.StringRegexUtils;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class zhusuEditor extends EditorPart implements IEditorInput {

	public static final String ID = "com.hxq.hotelwork.editors.zhusuEditor"; //$NON-NLS-1$
	private Text IDcard;
	private Text name;
	private DateTime dateTime;
	private DateTime dateTime_2;
    private String sex;
    Combo roomid;
    private String useroom;
    private long timein;
    private Label isok;
    private boolean bol;
    JDBCTools vt =new JDBCTools();
	public zhusuEditor() {
	}


	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		
		Label lblGoin = new Label(container, SWT.NONE);
		lblGoin.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblGoin.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblGoin.setAlignment(SWT.CENTER);
		lblGoin.setFont(SWTResourceManager.getFont("Microsoft New Tai Lue", 14, SWT.NORMAL));
		lblGoin.setBounds(397, 38, 164, 26);
		lblGoin.setText("入住身份登记");
		
		Label lblIdcard = new Label(container, SWT.NONE);
		lblIdcard.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblIdcard.setBounds(203, 108, 98, 20);
		lblIdcard.setText("身份证号码：");
		
		isok = new Label(container, SWT.NONE);
		isok.setBackground(SWTResourceManager.getColor(204, 255, 204));
		isok.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		isok.setBounds(701, 108, 242, 20);
	
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblName.setBounds(203, 161, 76, 20);
		lblName.setText("姓名：");
		
		IDcard = new Text(container, SWT.BORDER);
		IDcard.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				StringRegexUtils vv=new StringRegexUtils();
				String idcard=IDcard.getText();
				bol = StringRegexUtils.Validate(idcard, StringRegexUtils.ID_card_regexp);
				if(bol==true){
					isok.setText("格式正确！");
				}else{
					isok.setText("错误！！请输入18位数字格式的身份证号");
				}
			}
		});
		IDcard.setBounds(354, 105, 315, 26);
		
		name = new Text(container, SWT.BORDER);
		name.setBounds(354, 158, 98, 26);
		
		Label lblSex = new Label(container, SWT.NONE);
		lblSex.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblSex.setBounds(203, 214, 76, 20);
		lblSex.setText("性别：");
		
		Button btnMan = new Button(container, SWT.RADIO);
		btnMan.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				sex="男";
			}
		});
		btnMan.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnMan.setBounds(354, 214, 39, 20);
		btnMan.setText("男");
		
		Button btnWoman = new Button(container, SWT.RADIO);
		btnWoman.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			sex="女";
			}
		});
		btnWoman.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnWoman.setBounds(450, 214, 39, 20);
		btnWoman.setText("女");
		
		Label lblDatain = new Label(container, SWT.NONE);
		lblDatain.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblDatain.setBounds(203, 267, 76, 20);
		lblDatain.setText("入住日期：");
		
		dateTime = new DateTime(container, SWT.DROP_DOWN);
		dateTime.setBounds(354, 259, 116, 28);
        dateTime_2 = new DateTime(container, SWT.TIME);
		dateTime_2.setBounds(539, 259, 116, 28);
		Label lblRoomid = new Label(container, SWT.NONE);
		lblRoomid.setBackground(SWTResourceManager.getColor(204, 255, 204));
		lblRoomid.setBounds(203, 319, 76, 20);
		lblRoomid.setText("房间号码：");
		
	    roomid = new Combo(container, SWT.NONE);
		roomid.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 useroom=roomid.getText();
			}
		});
		
		String sql0="select roomcode from rooms where roomwork='待入住';";
		ArrayList<String []> list=vt.query(sql0);
		for(int i=0;i<list.size();i++){
			
			roomid.add(list.get(i)[0]);
			
		}
		roomid.setBounds(354, 316, 92, 28);
		
		
		Button btnSure = new Button(container, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if(bol==true){
					String ID=IDcard.getText();
					String Na=name.getText();
					int year=dateTime.getYear();
					int month=dateTime.getMonth()+1;
					int day=dateTime.getDay();
					int hour = dateTime_2.getHours();
					int min = dateTime_2.getMinutes();
					int sec = dateTime_2.getSeconds();

					String newMonth = month>9?month+"":"0"+month;
					String newday = day>9?day+"":"0"+day;
					String newhour = hour>9?hour+"":"0"+hour;
					String newmin = min>9?min+"":"0"+min;
					String newsec = sec>9?sec+"":"0"+sec;
					
					String datain= ""+year+newMonth+newday+newhour+newmin+newsec;
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						Date newDate = sdf.parse(datain);
						timein = newDate.getTime();
	               } catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					int r;
					int ro;
					int rows;
					
					String sql="insert into customerinfo (IDcard,name,roomcode,sex,datain,num) values ('"+ID+"','"+Na+"','"+useroom+"','"+sex+"','"+timein+"','0') ;";
					String sql2="update rooms set roomwork='已入住' where roomcode='"+useroom+"' ;";
					ro=vt.update(sql);
					r=vt.update(sql2);
				     
				     rows=r+ro;
				     MessageBox mb=new MessageBox(new Shell());
						if(rows>1){
							mb.setText("系统消息");
							mb.setMessage("添加成功!!");
							mb.open();
							IDcard.setText("");
							name.setText("");
						}
						else{
							mb.setText("系统消息");
							mb.setMessage("操作错误!!!!");
							mb.open();
						}
				    
					}
				else{
					MessageBox mb=new MessageBox(new Shell());
					mb.setText("系统消息");
					mb.setMessage("身份证格式错误!!");
					mb.open();
				}
				}
				
		});
		btnSure.setBounds(428, 431, 98, 30);
		btnSure.setText("确认");
		

		
	
		
		
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
		return "zhusuEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "zhusuEditorToolTipText";
	}
}
