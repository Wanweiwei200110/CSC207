package com.example.gamedesign.systemmanager;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

/** a file saver and loader class */
public class FileSaverLoader {

  /** the address of the saved object */
  private Context userFileAddress;

  /**
   * create a fileSaverLoader with given address
   *
   * @param address context of the application as address
   */
  public FileSaverLoader(Context address) {
    this.userFileAddress = address;
  }

  /**
   * save given type of object to userFileAddress
   *
   * @param o the object
   * @param name name of the object
   * @param <T> type of the object
   */
  public <T> void save(T o, String name) {
    try {
      ObjectOutputStream outputStream =
          new ObjectOutputStream(userFileAddress.openFileOutput(name, MODE_PRIVATE));
      outputStream.writeObject(o);
      outputStream.close();
    } catch (Exception e) {
      System.out.println("Exception is caught");
      e.printStackTrace();
    }
  }

  /**
   * load given type of object by name from userFileAddress
   *
   * @param name name of the object
   * @param <T> type of the object
   * @return the object
   */
  public <T> T load(String name) {
    T o = null;
    try {
      InputStream inputStream = userFileAddress.openFileInput(name);
      if (inputStream != null) {
        ObjectInputStream input = new ObjectInputStream(inputStream);

        o = (T) input.readObject();

        inputStream.close();
        return o;
      }
    } catch (FileNotFoundException e) {
      System.out.println("FileNotFoundException is caught");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("IOException is caught");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException is caught");
      e.printStackTrace();
    }
    return o;
  }
}
