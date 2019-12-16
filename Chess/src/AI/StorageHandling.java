package AI;

import java.io.*;

class StorageHandling {

    public static void saveData(Serializable data, String fileName){
        FileOutputStream file;
        try {
            file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Serializable getData(String fileName){
        FileInputStream stream;

        try {
            stream = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(stream);
            return (Serializable) input.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
