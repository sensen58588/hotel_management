package com.hxq.hotelwork.core;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

//import com.hxq.hotelwork.views.CustomerView;
import com.hxq.hotelwork.views.CustomerView;
import com.hxq.hotelwork.views.admin;
import com.hxq.hotelwork.views.workerView;
//import com.hxq.hotelwork.views.admin;


public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		int num=Application.value;
		if(num==0){
			layout.addView(CustomerView.ID, IPageLayout.LEFT, 0.4f, layout.getEditorArea());
			}else if(num==1){
				layout.addView(admin.ID, IPageLayout.LEFT, 0.4f, layout.getEditorArea());
			}else if(num==2){
				layout.addView(workerView.ID, IPageLayout.LEFT, 0.4f, layout.getEditorArea());
			}
		
		
		
	}
}
