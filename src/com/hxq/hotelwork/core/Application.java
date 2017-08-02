package com.hxq.hotelwork.core;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.hxq.hotelwork.dialogs.fristDialog;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	public static int value;
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		fristDialog fr=new fristDialog(new Shell(display),SWT.CLOSE|SWT.APPLICATION_MODAL|SWT.MAX|SWT.MIN);
		Object result = fr.open();
		if(result==null){
			return null;
		}else{
			value = Integer.parseInt(result.toString());
		}
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}

	
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
