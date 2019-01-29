package SeleniumTestAutomation;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.ReadOnlyFolderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.internet.InternetAddress;
 
public class readEmails 
{ 
public static String mail, errmsg;
public void readmails(String expTarget) 
{
   Session session = null;
   Store store = null;
   Folder folder = null;
   Message message = null;
   Message[] messages = null;
   Object messagecontentObject = null;
   try 
   {
      session = Session.getDefaultInstance(System.getProperties(), null);
      store = session.getStore("imaps"); 
      store.connect(Configure.server,Configure.userName,Configure.password); 
      folder = store.getDefaultFolder(); 
      folder = folder.getFolder("inbox"); 
      folder.open(Folder.READ_WRITE); 
      messages = folder.getMessages(); 
      int msgCount=messages.length;
      message = messages[msgCount-Integer.parseInt(expTarget)];
      messagecontentObject = message.getContent();
      mail=(String) messagecontentObject;
      System.out.println("Mail =" +mail);
      folder.close(true);
      store.close();
  } catch(AuthenticationFailedException e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  } catch(FolderClosedException e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  } catch(FolderNotFoundException e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  }  catch(NoSuchProviderException e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  } catch(ReadOnlyFolderException e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  } catch(StoreClosedException e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  } catch (Exception e) {
	  errmsg=e.getMessage();
     e.printStackTrace();
  }
}
}