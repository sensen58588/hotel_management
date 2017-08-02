package com.hxq.hotelwork.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.editors.addgoods;
import com.hxq.hotelwork.editors.customershopEditor;
import com.hxq.hotelwork.editors.leavingEditor;
import com.hxq.hotelwork.editors.roomsinfo;
import com.hxq.hotelwork.editors.zhusuEditor;

public class workerView extends ViewPart {

	public static final String ID = "com.hxq.hotelwork.views.workerView"; //$NON-NLS-1$

	public workerView() {
	}


	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ExpandBar expandBar = new ExpandBar(container, SWT.NONE);
		
		ExpandItem xpndtmZhusu = new ExpandItem(expandBar, SWT.NONE);
		xpndtmZhusu.setExpanded(true);
		xpndtmZhusu.setText("顾客业务");
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		xpndtmZhusu.setControl(composite);
		composite.setLayout(new GridLayout(5, false));
		
		Link link = new Link(composite, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new zhusuEditor(), zhusuEditor.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		link.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		link.setText("<a>登记入住</a>");
		new Label(composite, SWT.NONE);
		
		Link link_1 = new Link(composite, SWT.NONE);
		link_1.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new customershopEditor(), customershopEditor.ID);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		link_1.setText("<a>顾客消费</a>");
		new Label(composite, SWT.NONE);
		
		Link link_4 = new Link(composite, SWT.NONE);
		link_4.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new leavingEditor(), leavingEditor.ID);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		link_4.setText("<a>退房打扫</a>");
		xpndtmZhusu.setHeight(xpndtmZhusu.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem xpndtmJob = new ExpandItem(expandBar, SWT.NONE);
		xpndtmJob.setExpanded(true);
		xpndtmJob.setText("日常工作");
		
		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		xpndtmJob.setControl(composite_1);
		composite_1.setLayout(new GridLayout(3, false));
		
		Link link_2 = new Link(composite_1, SWT.NONE);
		link_2.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new roomsinfo(), roomsinfo.ID);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		link_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		link_2.setText("<a>客房工作</a>");
		new Label(composite_1, SWT.NONE);
		
		Link link_3 = new Link(composite_1, SWT.NONE);
		link_3.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new addgoods(), addgoods.ID);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		link_3.setText("<a>\u8FDB\u8D27</a>");
		xpndtmJob.setHeight(xpndtmJob.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
