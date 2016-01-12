import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.internal.win32.INPUT;


public class Settings extends Dialog {

	protected Object result;
	protected Shell shlBrowserSelection;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Settings(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlBrowserSelection.open();
		shlBrowserSelection.layout();
		Display display = getParent().getDisplay();
		while (!shlBrowserSelection.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlBrowserSelection = new Shell(getParent(), getStyle());
		shlBrowserSelection.setSize(380, 256);
		shlBrowserSelection.setText("Browser Selection");
		shlBrowserSelection.setLocation(getParent().getBounds().x,(getParent().getBounds().y));
		Label lblWhatInternetBrowser = new Label(shlBrowserSelection, SWT.NONE);
		lblWhatInternetBrowser.setBounds(25, 10, 348, 15);
		lblWhatInternetBrowser.setText("What internet browser would you like to use(Firefox is the slowest)");
		
		Button btnChrome = new Button(shlBrowserSelection, SWT.RADIO);
		btnChrome.setBounds(60, 56, 90, 16);
		btnChrome.setText("Chrome");
		
		Button btnFirefox = new Button(shlBrowserSelection, SWT.RADIO);
		btnFirefox.setBounds(60, 78, 81, 26);
		btnFirefox.setText("Firefox");
		
		Button btnInternetExplorer = new Button(shlBrowserSelection, SWT.RADIO);
		btnInternetExplorer.setBounds(60, 110, 123, 26);
		btnInternetExplorer.setText("Internet Explorer");
		
		Button btnOk = new Button(shlBrowserSelection, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int browser = 0;
					if (btnChrome.getSelection()==true)
					{
						browser=0;
					}
					if (btnInternetExplorer.getSelection()==true)
					{
						browser=1;
					}
					if (btnFirefox.getSelection()==true)
					{
						browser=2;
					}
				    
				    	File file = new File("settings.txt");
						
						FileWriter writer;
						try {
							Scanner input = new Scanner(file);
							writer = new FileWriter(file);
							if (input.hasNextLine())
						    {
						    	String in= input.nextLine();
				                String[] intd=in.split("=");
						    	writer.close();
						    	input.close();
						    }
						    else if(input.hasNextInt()!=false)
						    {
						    	writer.write(browser);
						    	writer.close();
						    	input.close();
						    }
					    
					    
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	
				    	
				    
				    	 shlBrowserSelection.close();
				}
			
				
			
		});
		btnOk.setBounds(144, 159, 75, 25);
		btnOk.setText("Ok");

	}
}
