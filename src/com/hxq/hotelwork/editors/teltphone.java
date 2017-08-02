package com.hxq.hotelwork.editors;

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
import org.eclipse.wb.swt.SWTResourceManager;

public class teltphone extends EditorPart implements IEditorInput {

	public static final String ID = "com.hxq.hotelwork.editors.teltphone"; //$NON-NLS-1$

	public teltphone() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(204, 255, 204));
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(204, 255, 204));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		label.setBounds(314, 76, 219, 48);
		label.setText("»¶Ó­ÖÂµç");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_1.setBounds(240, 165, 76, 20);
		label_1.setText("\u524D\u53F0\u5EA7\u673A\uFF1A");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_2.setBounds(375, 165, 108, 20);
		label_2.setText("1818-666666");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_3.setBounds(240, 228, 76, 20);
		label_3.setText("\u4E00\u697C\u670D\u52A1\uFF1A");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_4.setBounds(375, 228, 123, 20);
		label_4.setText("1818-866661");
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_5.setBounds(240, 293, 76, 20);
		label_5.setText("\u4E8C\u697C\u670D\u52A1\uFF1A");
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_6.setBounds(375, 293, 96, 20);
		label_6.setText("1818-866662");
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_7.setBounds(240, 356, 76, 20);
		label_7.setText("\u4E09\u697C\u670D\u52A1\uFF1A");
		
		Label label_8 = new Label(container, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(153, 255, 204));
		label_8.setBounds(377, 353, 102, 20);
		label_8.setText("1818-866663");

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
		return "telephoneEditor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "teleEditortooltip";
	}

}
