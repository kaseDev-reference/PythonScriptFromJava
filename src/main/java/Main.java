import java.io.*;
import java.net.URLDecoder;

public class Main {

	public static void main(String[] args) throws IOException {

		// Execute the python code from java in a process
		String command = "py \"" + getProjectLocation() + "/src/main/python/script.py\" hello";
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		// print out the output of the python script
		String line = "";
		while ((line = reader.readLine()) != null)
			System.out.print(line + "\n");
	}

	/**
	 * Returns the file location of this project.
	 * @return the file location of this project.
	 */
	public static String getProjectLocation() {
		try {
			String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path = URLDecoder.decode(path, "UTF-8");
			path = path.substring(1, path.length());
			return path.replace("/target/classes/", "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
