
package SeleniumTestAutomation;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

	public class AutoDismiss implements Runnable, ActionListener 
	{
	    private JDialog dialog;

	    public AutoDismiss(JDialog dialog) 
	    {
	        this.dialog = dialog;
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	        dialog.dispose();
	    }
		//comments
	    static JOptionPane optionPane;
	    static public String showMessageDialog(Component parent, Object message) 
	    {
	    	//System.out.println(showMessageDialog(null, "Enter the Value"));
	        optionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
	        optionPane.setWantsInput(true);
	        String title = UIManager.getString("OptionPane.messageDialogTitle");
	        final JDialog dialog = optionPane.createDialog(parent, title);
	        timer = new javax.swing.Timer(100000, new AutoDismiss(dialog));
	        timer.setRepeats(false);
	        timer.start();
	        try
	        {
	        	Thread.sleep(20000);
	        }
	        catch(Exception e){}
	        if (dialog.isDisplayable()) 
	        {
	        	dialog.setAlwaysOnTop(true);
	                    dialog.setVisible(true);
	        }

	      //  System.out.println(showMessageDialog(null, "Enter the Value"));
	        return ("" + optionPane.getInputValue());

	    }
	static javax.swing.Timer timer;
	    public void run() 
	    {

	    }
	}//Class AutoDismiss
	
