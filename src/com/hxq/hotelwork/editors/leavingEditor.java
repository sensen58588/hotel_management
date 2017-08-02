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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.hxq.hotelwork.tools.JDBCTools;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;

public class leavingEditor extends EditorPart implements IEditorInput{

	public static final String ID = "com.hxq.hotelwork.editors.leavingEditor"; //$NON-NLS-1$
	private Text IDcard;
	private Text name;
	JDBCTools vt =new JDBCTools();
	private DateTime dateTime;
	private DateTime Time;
	private long timeout;
	private String timein;
	private String id;
	private int days;
	private int hour;
	private String custormer;
	private Text roomcode;
	private int roomprice;
	private int money;
	public static int year;
	public static int month;
	private Label lblOk ;
	private int right;
	public leavingEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(153, 255, 204));
		
		Label lblIdcard = new Label(container, SWT.NONE);
		lblIdcard.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblIdcard.setBounds(24, 105, 95, 20);
		lblIdcard.setText("身份证号码:");
		
		lblOk = new Label(container, SWT.NONE);
		lblOk.setForeground(SWTResourceManager.getColor(255, 51, 51));
		lblOk.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblOk.setBounds(470, 105, 76, 20);
		
		
		IDcard = new Text(container, SWT.BORDER);
		IDcard.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
			    id=IDcard.getText();
		        String sql="select name ,roomcode from customerinfo where IDcard='"+id+"' and num='0';";
		        ArrayList<String[]> list = vt.query(sql);
				if(list.size()<=0){
					right=0;
					lblOk.setText("输入错误!!");
				}else{
					right=1;
					lblOk.setText("√");
					custormer=list.get(0)[0];
				    name.setText(custormer);
				    roomcode.setText(list.get(0)[1]);
				}
				
			    
			}
		});
		IDcard.setBounds(125, 99, 315, 26);
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblName.setBounds(24, 170, 76, 20);
		lblName.setText("姓名:");
		
		name = new Text(container, SWT.BORDER);
		name.setBounds(125, 167, 92, 26);
		
		Label lblRoom = new Label(container, SWT.NONE);
		lblRoom.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblRoom.setBounds(276, 170, 67, 20);
		lblRoom.setText("房间号：");
		
		roomcode = new Text(container, SWT.BORDER);
		roomcode.setBounds(346, 164, 94, 26);
		
		Button btnSure = new Button(container, SWT.NONE);
		btnSure.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if(right==0){
					MessageBox mb=new MessageBox(new Shell());
					mb.setText("系统消息");
					mb.setMessage("身份证错误！");
					mb.open();
					name.setText("");
				    roomcode.setText("");
				    lblOk.setText("");
				}else{
					try{
						String findprice="select roomprice from rooms where roomcode='"+roomcode.getText()+"';";
						ArrayList<String[]> fprice = vt.query(findprice);
						roomprice=Integer.parseInt(fprice.get(0)[0]);
						year=dateTime.getYear();
						month=dateTime.getMonth()+1;
						int day=dateTime.getDay();
						hour=Time.getHours();
						int min=Time.getMinutes();
						int sec=Time.getSeconds();
						String newMonth = month>9?month+"":"0"+month;
						String newday = day>9?day+"":"0"+day;
						String newhour = hour>9?hour+"":"0"+hour;
						String newmin = min>9?min+"":"0"+min;
						String newsec = sec>9?sec+"":"0"+sec;
						
						String dataout= ""+year+newMonth+newday+newhour+newmin+newsec;
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
							Date idataout = sdf.parse(dataout);
							timeout=idataout.getTime();//离开日期的毫秒数
							
							String sql2="select datain from customerinfo where IDcard='"+id+"' and num='0';";
							
							ArrayList<String[]> list2 = vt.query(sql2);
							timein=list2.get(0)[0];
							long idata=Long.parseLong(timein);//获得入住日期的毫秒数
							
							String sql="update customerinfo set dataout='"+timeout+"',num='1' where IDcard='"+id+"' and num='0';";
							vt.update(sql);
							   
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy_MM_dd/HH:mm:ss");
							String dataouttool=sdf2.format(timeout);
							String dataintool= sdf2.format(idata);
							
							long daytime=timeout-idata; 
							
							
							days=(int)(daytime/1000/60/60/24);
							if(days<1){
								money=1*roomprice;
							}
							if(day>=1&&hour>=15){
								days=days+1;
								money=days*roomprice;
							}else if(day>=1&&hour<15){
								money=days*roomprice;
							}
							 
							String moneytool=""+money;
							String sqltool="insert into tool values('"+id+"','"+custormer+"','"+roomcode.getText()+"','"+dataintool+"','"+dataouttool+"','"+moneytool+"');";
							vt.update(sqltool);	
		                    }
					      catch (Exception e1) {
							
							e1.printStackTrace();
						}
						try {
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new dueEditor(), dueEditor.ID);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
				
					 IDcard.setText("");
				        name.setText("");
				        roomcode.setText("");
				}
				
			
		});
		btnSure.setBounds(230, 342, 98, 30);
		btnSure.setText("确认");
		
		Label lblData = new Label(container, SWT.NONE);
		lblData.setBackground(SWTResourceManager.getColor(153, 255, 204));
		lblData.setBounds(24, 237, 76, 20);
		lblData.setText("离开日期：");
		
		dateTime = new DateTime(container, SWT.DROP_DOWN);
		dateTime.setBounds(125, 229, 116, 28);
		
		Time = new DateTime(container, SWT.TIME);
		Time.setBounds(324, 229, 116, 28);
		
		
		
	

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
		return "leavingEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "leavingEditorToolTip";
	}
}
