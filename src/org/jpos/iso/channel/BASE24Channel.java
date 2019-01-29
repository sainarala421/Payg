package org.jpos.iso.channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

public class BASE24Channel extends BaseChannel
{
  public BASE24Channel()
  {
  }

  public BASE24Channel(String host, int port, ISOPackager p)
  {
    super(host, port, p);
  }

  public BASE24Channel(ISOPackager p)
    throws IOException
  {
    super(p);
  }

  public BASE24Channel(ISOPackager p, ServerSocket serverSocket)
    throws IOException
  {
    super(p, serverSocket);
  }

  protected void sendMessageTrailler(ISOMsg m, int len)
    throws IOException
  {
    this.serverOut.write(3);
  }
  protected int getMessageLength() throws IOException, ISOException {
      byte[] b = new byte[2];
      serverIn.readFully(b,0,2);
      int rt = (int) (
          ((((int)b[0])&0xFF) << 8) |
          (((int)b[1])&0xFF));
      
      System.out.println("The RT Value ===>"+rt);
      if(rt>0)
      	rt=rt-1;

     System.out.println("BASE24Channel:Return Message length: (after adjustment)"+rt);
      return rt;
  }
  protected void sendMessageLength(int len) throws IOException {
      len=len+2;
      serverOut.write (len >> 8);
      serverOut.write (len);
  }
  protected byte[] streamReceive()
    throws IOException
  {
	  int i;
    byte[] buf = new byte[4096];
    for (i = 0; i < 4096; ++i) {
      int c = -1;
      try {
        c = this.serverIn.read(); } catch (SocketException e) {
      }
      if (c == 3)
        break;
      if (c == -1)
        throw new EOFException("connection closed");
      buf[i] = (byte)c;
    }
    if (i == 4096) {
      throw new IOException("packet too long");
    }
    byte[] d = new byte[i];
    System.arraycopy(buf, 0, d, 0, i);
    return d;
  }
}