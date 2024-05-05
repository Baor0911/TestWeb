

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class readinput {

	public static void main(String[] args) {
		String[] credentials = readCredentials();
		String username = credentials[0];
		String password = credentials[1];
		
		System.out.println("Username:" + username);
		System.out.println("Password:" + password);
		
		//Ghi cookies vào tệp cấu hình
		writeCookies("cookie_data");
	}
	//read cookies
	public static String readCookies() {
		try {
			Properties properties = new Properties();
			InputStream input = new FileInputStream("config.properties");
			properties.load(input);
			
			// Lấy giá trị từ tệp cấu hình
			String cookies = properties.getProperty("COOKIES");
			
			// Đóng luồng đọc
			input.close();
			
			return cookies;
		} catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	//Đọc dữ liệu từ tệp cấu hình 
	public static String[] readCredentials() {
		try {
			Properties properties = new Properties();
			InputStream input = new FileInputStream("config.properties");
			properties.load(input);
			
			//Lấy các giá trị từ tệp cấu hình 
			String username = properties.getProperty("USERNAME");
			String password = properties.getProperty("PASSWORD");
			
			//Đóng luồng đọc
			input.close();
			
			return new String[] { username, password};
		}	catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	// Ghi cookies vào tệp cấu hình
	public static void writeCookies(String cookies) {
		try {
			// Đọc dữ liệu từ tệp cấu hình gốc
			String[] credentials = readCredentials();
			String username = credentials[0];
			String password = credentials[1];
			
			// Tạo một đối tượng Properties cho tệp cấu hình tạm thời 
			Properties properties = new Properties();
			properties.setProperty("COOKIES", cookies);
			properties.setProperty("USERNAME", username);
			properties.setProperty("PASSWORD", password);
			
			// Ghi dữ liệu vào tệp cấu hình tạm thời
			FileOutputStream output = new FileOutputStream("temp_config.properties");
			properties.store(output, null);
			output.close();
			
			// Sao chép nội dung từ tệp cấu hình tạm thời vào tệp cấu hình gốc
			copyFile("temp_config.properties", "config.properties");
			
			// Xóa tệp cấu hình tạm thời
			File tempFile = new File("temp_config.properties");
			tempFile.delete();
		}	catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// Sao chép nội dung từ một tệp sang một tệp khác
	public static void copyFile(String sourcesFileName, String destFileName) throws IOException {
		FileInputStream inputStream = new FileInputStream(sourcesFileName);
		FileOutputStream outputStream = new FileOutputStream(destFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}
}	
