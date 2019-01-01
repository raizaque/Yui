package System.Solaire.com.WebServiceArtifact.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import System.Solaire.com.WebServiceArtifact.Model.Simulation;

public class ObjectService {
	public static Object deserializeBytes(byte[] bytes) throws IOException, ClassNotFoundException
	{
	    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}


	public static byte[] serializeObject(Simulation obj) throws IOException
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	public static Object deepClone(Object object) {
		   try {
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ObjectOutputStream oos = new ObjectOutputStream(baos);
		     oos.writeObject(object);
		     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		     ObjectInputStream ois = new ObjectInputStream(bais);
		     return ois.readObject();
		   }
		   catch (Exception e) {
		     e.printStackTrace();
		     return null;
		   }
		 }
}
