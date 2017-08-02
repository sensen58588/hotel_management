package com.hxq.hotelwork.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hxq.hotelwork.editors.customershopEditor;
import com.hxq.hotelwork.editors.shopEditor;
import com.hxq.hotelwork.editors.teltphone;

public class CustomerView extends ViewPart {

	public static final String ID = "com.hxq.hotelwork.views.CustomerView"; //$NON-NLS-1$

	public CustomerView() {
	}
    public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ExpandBar expandBar = new ExpandBar(container, SWT.NONE);
		
		ExpandItem xpndtmShop = new ExpandItem(expandBar, SWT.NONE);
		xpndtmShop.setExpanded(true);
		xpndtmShop.setText("欢迎消费");
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		xpndtmShop.setControl(composite);
		composite.setLayout(new GridLayout(1, false));
		
		Link link = new Link(composite, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try{
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new customershopEditor(),customershopEditor.ID);
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		link.setText("<a>\u9152\u6C34\u5FEB\u9910</a>");
		xpndtmShop.setHeight(xpndtmShop.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem xpndtmHelp = new ExpandItem(expandBar, SWT.NONE);
		xpndtmHelp.setExpanded(true);
		xpndtmHelp.setText("极速帮助");
		
		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		xpndtmHelp.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		Link link_2 = new Link(composite_1, SWT.NONE);
		link_2.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new teltphone(), teltphone.ID);
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		link_2.setText("<a>\u524D\u53F0\u7535\u8BDD</a>");
		xpndtmHelp.setHeight(xpndtmHelp.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	private void createActions() {
		// Create the actions
	}


	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
