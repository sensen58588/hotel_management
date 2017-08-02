package com.hxq.hotelwork.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.dialogs.RoomsDialog;
import com.hxq.hotelwork.dialogs.addworker;
import com.hxq.hotelwork.dialogs.deroomDialog;
import com.hxq.hotelwork.editors.customerEditor;
import com.hxq.hotelwork.editors.customershopEditor;
import com.hxq.hotelwork.editors.history;
import com.hxq.hotelwork.editors.leavingEditor;
import com.hxq.hotelwork.editors.monthEditor;
import com.hxq.hotelwork.editors.roomsinfo;
import com.hxq.hotelwork.editors.shoppingEditor;
import com.hxq.hotelwork.editors.summoney;
import com.hxq.hotelwork.editors.worker;
import com.hxq.hotelwork.editors.yearmoney;
import com.hxq.hotelwork.editors.zhusuEditor;
import com.hxq.hotelwork.tools.JDBCTools;

public class admin extends ViewPart {

	public static final String ID = "com.hxq.hotelwork.views.admin"; //$NON-NLS-1$

	public admin() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ExpandBar expandBar = new ExpandBar(container, SWT.NONE);
		
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(true);
		expandItem.setText("\u4F4F\u5BBF\u7BA1\u7406");
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		expandItem.setControl(composite);
		composite.setLayout(new GridLayout(5, false));
		
		Link link = new Link(composite, SWT.NONE);//住宿管理
		link.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new zhusuEditor(), zhusuEditor.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		link.setText("<a>\u767B\u8BB0\u5165\u4F4F</a>");
		new Label(composite, SWT.NONE);
		
		Link link_2 = new Link(composite, SWT.NONE);
		link_2.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new leavingEditor(), leavingEditor.ID);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		link_2.setText("<a>\u9000\u623F\u6253\u626B</a>");
		new Label(composite, SWT.NONE);
		
		Link link_1 = new Link(composite, SWT.NONE);
		link_1.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new customershopEditor(), customershopEditor.ID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link_1.setText("<a>\u987E\u5BA2\u6D88\u8D39</a>");
		expandItem.setHeight(expandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("\u5458\u5DE5\u7BA1\u7406\r\n");
		
		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(5, false));
		
		Link link_4 = new Link(composite_1, SWT.NONE);
		link_4.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new worker(),worker.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		link_4.setText("<a>\u5458\u5DE5\u4FE1\u606F</a>");
		new Label(composite_1, SWT.NONE);
		
		Link link_3 = new Link(composite_1, SWT.NONE);
		link_3.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				/*try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new worker(),worker.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}*/
				addworker add=new addworker(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.CLOSE|SWT.MAX|SWT.MIN|SWT.APPLICATION_MODAL);
				   String ch="增加员工";
					add.open(ch);
				
				
			}
		});
		link_3.setText("<a>\u589E\u52A0\u5458\u5DE5</a>");
		new Label(composite_1, SWT.NONE);
		
		Link link_5 = new Link(composite_1, SWT.NONE);
		link_5.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				/*try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new worker(),worker.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}*/
				addworker add=new addworker(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.CLOSE|SWT.MAX|SWT.MIN|SWT.APPLICATION_MODAL);
				   String ch="删去员工";
					add.open(ch);
				
			}
		});
		link_5.setText("<a>\u5220\u53BB\u5458\u5DE5</a>");
		xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem expandItem_1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_1.setExpanded(true);
		expandItem_1.setText("\u5BA2\u623F\u7BA1\u7406\r\n");
		
		Composite composite_2 = new Composite(expandBar, SWT.NONE);
		expandItem_1.setControl(composite_2);
		composite_2.setLayout(new GridLayout(5, false));
		
		Link link_13 = new Link(composite_2, SWT.NONE);//房间信息
		link_13.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new roomsinfo(), roomsinfo.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		link_13.setText("<a>\u5BA2\u623F\u72B6\u6001</a>");
		new Label(composite_2, SWT.NONE);
		
		Link link_6 = new Link(composite_2, SWT.NONE);
		link_6.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				RoomsDialog addroom=new RoomsDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.CLOSE|SWT.APPLICATION_MODAL|SWT.MAX|SWT.MIN);
				String add="增加客房";
				addroom.open(add);
				
			}
		});
		link_6.setText("<a>增加客房</a>");
		new Label(composite_2, SWT.NONE);
		
		Link link_8 = new Link(composite_2, SWT.NONE);
		link_8.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				
				deroomDialog dete=new deroomDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.CLOSE|SWT.MAX|SWT.MIN|SWT.APPLICATION_MODAL);
			dete.open();
			}
		});
		link_8.setText("<a>减少客房</a>");
		expandItem_1.setHeight(expandItem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem expandItem_2 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_2.setExpanded(true);
		expandItem_2.setText("\u4F4F\u5BBF\u4EBA\u5458\u7BA1\u7406");
		
		Composite composite_3 = new Composite(expandBar, SWT.NONE);
		expandItem_2.setControl(composite_3);
		composite_3.setLayout(new GridLayout(3, false));
		
		Link link_9 = new Link(composite_3, SWT.NONE);
		link_9.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new customerEditor(), customerEditor.ID);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		link_9.setText("<a>\u4F4F\u5BBF\u4EBA\u5458</a>");
		new Label(composite_3, SWT.NONE);
		
		Link link_10 = new Link(composite_3, SWT.NONE);
		link_10.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
			    try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new history(), history.ID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link_10.setText("<a>\u5386\u53F2\u7EAA\u5F55</a>");
		expandItem_2.setHeight(expandItem_2.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem expandItem_3 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_3.setExpanded(true);
		expandItem_3.setText("\u6536\u5165\u660E\u7EC6");
		
		Composite composite_4 = new Composite(expandBar, SWT.NONE);
		expandItem_3.setControl(composite_4);
		composite_4.setLayout(new GridLayout(5, false));
		
		Link link_11 = new Link(composite_4, SWT.NONE);
		link_11.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new monthEditor(), monthEditor.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		link_11.setText("<a>消费记录</a>");
		new Label(composite_4, SWT.NONE);
		
		Link link_12 = new Link(composite_4, SWT.NONE);
		link_12.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new summoney() , summoney.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		link_12.setText("<a>\u5B63\u5EA6\u7EDF\u8BA1</a>");
		new Label(composite_4, SWT.NONE);
		
		Link link_14 = new Link(composite_4, SWT.NONE);
		link_14.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new yearmoney(), yearmoney.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		link_14.setText("<a>年客房销售总额</a>");
		expandItem_3.setHeight(expandItem_3.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	
	private void initializeToolBar() {
	}

	private void initializeMenu() {
	}

	@Override
	public void setFocus() {
		
	}
}
