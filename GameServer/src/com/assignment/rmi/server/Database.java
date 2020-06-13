package com.assignment.rmi.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class Database extends RemoteServer {
	
	 public Database() {
		 HashMap<String, Integer> euonline = new HashMap<String, Integer>();
		 euonline.put("Online", 0);
		 euonline.put("Offline", 0);
		 
		 HashMap<String, Integer> naOnline = new HashMap<String, Integer>();
		 naOnline.put("Online", 0);
		 naOnline.put("Offline", 0);
		 
		 HashMap<String, Integer> asOnline = new HashMap<String, Integer>();
		 asOnline.put("Online", 0);
		 asOnline.put("Offline", 0);	 
		 
		 actives.put("EU", euonline);
		 actives.put("NA", naOnline);
		 actives.put("AS", asOnline);
		 
		 
		
	}
	 
	public String basePath = "Myserver\\";	
	public static Hashtable<String, Map<String, Integer>> actives = new Hashtable<>();

	public String createPlayer(String username, String firstName, String lastName, Integer age, String password, String IpAdress)
			throws ServerNotActiveException {
		
	//	username = this.limit(username);
	//	password = this.limitPassword(password);
		System.out.println("Username should be less than 15 characters and more than 6 characters and "
				+ "Password should be greater than or equal to atleast 6 characters");
		String data = "\n" + username + "|" + firstName + "|" + lastName + "|" + age + "|" + password + "|" + IpAdress;
		System.out.println("5555555555555" +IpAdress);
		String ip = IpAdress.split("\\.")[0];
		System.out.println(ip);
		String path = this.basePath + ip + ".txt";

		switch (ip) {
		case "93": {
			if (this.isUserExist(username, password, path, CheckType.CREATE)) {
				return "Username already exist.";
			}
			
			try {
				this.logger(data,ip);
			} catch (ServerNotActiveException e) {
 
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			String message = this.writeFile(path, data);
			this.addActiveUser("EU", "Offline");
			try {
				this.getAllOperations(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return message;
		}
		case "132": {
			if (this.isUserExist(username, password, path, CheckType.CREATE)) {
				return "Username already exist.";
			}
			try {
				this.logger(data,ip);
			} catch (ServerNotActiveException e) {
 
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			String message = this.writeFile(path, data);
			this.addActiveUser("NA", "Offline");
			try {
				this.getAllOperations(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return message;
		}
		
		case "182": {
			if (this.isUserExist(username, password, path, CheckType.CREATE)) {
				return "Username already exist.";
			}
			
			try {
				this.logger(data,ip);
			} catch (ServerNotActiveException e) {
 
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			String message = this.writeFile(path, data);
			this.addActiveUser("AS", "Offline");
			try {
				this.getAllOperations(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return message; 
		}
		default: {
			throw new RuntimeException("Invalid IP address ERORRFLIFN");

		}
		}

	}

	public Boolean signInPlayer(String username, String password, String IpAdress) throws ServerNotActiveException {
		String ip = IpAdress.split("\\.")[0];
		String path = this.basePath + ip + ".txt";

		switch (ip) {
		case "93": {
			if (this.isUserExist(username, password, path, CheckType.SIGNIN)) {
				try {
					this.logger(username + "," + password, ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.addActiveUser("EU", "Online");
				
				return true;						
			}
			return false;
		}
		case "132": {
			if (this.isUserExist(username, password, path, CheckType.SIGNIN)) {
				try {
					this.logger(username + "," + password, ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.addActiveUser("NA", "Online");
				return true;
			}
			return false;
		}
		case "182": {
			if (this.isUserExist(username, password, path, CheckType.SIGNIN)) {
				try {
					this.logger(username + "," + password, ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.addActiveUser("AS", "Online");
				return true;
			}
			return false;
		}
		default: {
			throw new RuntimeException("Invalid IP address.");

		}
		}
	}

	public String signOutPlayer(String username, String IpAdress) throws ServerNotActiveException {
		String ip = IpAdress.split("\\.")[0];
		String path = this.basePath + ip + ".txt";

		switch (ip) {
		case "93": {
			if (this.isUserExist(username, null, path, CheckType.SIGNOUT)) {
				try {
					this.logger(username, ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				this.logOutUser("EU", "Offline");
				return "Successfully Signout, Hence the status is Offline";
			} else {
				return "User does not exist.";
			}
		}
		case "132": {
			if (this.isUserExist(username, null, path, CheckType.SIGNOUT)) {
				try {
					this.logger(username, ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.logOutUser("NA", "Offline");
				return "Successfully Signout, Hence the status is Offline";
			} else {
				return "User does not exist.";
			}
		}
		case "182": {
			if (this.isUserExist(username, null, path, CheckType.SIGNOUT)) {
				try {
					this.logger(username, ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.logOutUser("AS", "Offline");
				return "Successfully Signout, Hence the status is Offline";
			} else {
				return "User does not exist.";
			}
		}
		default: {
			throw new RuntimeException("Invalid IP address.");

		}
		}

	}

	public String createAdmini(String username, String firstName, String lastName, String password, String IpAddress) {
		String data = "\n" + username + "|" + firstName + "|" + lastName  + "|" + password + "|" + IpAddress;

		String path = this.basePath + "ADMIN\\ACCOUNT.txt";
		String ip = IpAddress.split("\\.")[0];

		if (this.isUserExist("Admin", "Admin", path, CheckType.CREATE)) {
			
			  try { 
				  this.logger(data, ip); 
				  } catch (ServerNotActiveException e) {
			  
			  e.printStackTrace(); 
			  } catch (IOException e) {
			  
			  e.printStackTrace(); 
			  
			  }
			  return this.writeFile(path, data);
			 			
		} else 
			return "Record doesnot exist"; 		

	}

	public Boolean signInAdmin(String username, String password, String IpAddress) {
		String data = "\n" + username + "|" + password + "|" + IpAddress;
		String path = this.basePath + "ADMIN\\ACCOUNT.txt";
		String ip = IpAddress.split("\\.")[0];
			
			if(username.equals("Admin") && password.equals("Admin")) {
				
				System.out.println("Success");

				try {
					this.logger(data,ip);
				} catch (ServerNotActiveException e) {
	 
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.writeFile(path, data);
				return true;
				
			} else {
				System.out.println("Username and Password should be Admin only");
				return false;
			}
						
	}

	private String writeFile(String path, String data) {
		try {
			Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (Exception e) {
			e.printStackTrace();
			return "Oops, there is some technical issues. Please try latter.";
		}
		return "User is created successfully";
	}

	private Boolean isUserExist(String username, String password, String path, CheckType checkType) {
		try {
			Map<String, String> users = this.setAllUsername(Files.readAllLines(Paths.get(path)));
			if ((checkType == CheckType.CREATE || checkType == CheckType.SIGNOUT) && users.containsKey(username))
				return true;
			else if (checkType == CheckType.SIGNIN && users.containsKey(username)
					&& password.equals(users.get(username))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private Map<String, String> setAllUsername(List<String> users) {
		Map<String, String> userNames = new HashMap<>();
		Integer count = 0;		
		for (String readLine : users) {
			if (!readLine.trim().isEmpty())
			if (count > 0) {
				String data[] = readLine.split("\\|");
				userNames.put(data[0], data[4]);
			}
			count++;
		}
		return userNames;
	}

	public Hashtable<String, Map<String, Integer>> activeUsers() {
		return actives;
	}

	public Boolean addActiveUser(String location, String status) {
		String data = "\n" + status + "|" + location;
		String path = this.basePath + "ADMIN\\ACCOUNT.txt";
		Map<String, Integer> innerData = actives.get(location);		
		if (innerData != null) {

			if (status.equals("Online")) {
				Integer active = innerData.get("Online");
				if (active != null) {
					innerData.put("Online", active + 1);
					actives.put(location, innerData);
					this.writeFile(path, data);
					return true;
				}
			} else if (status.equals("Offline")) {
				Integer offline = innerData.get("Offline");				
				if (offline != null) {
					innerData.put("Offline", (offline ==0) ?1: offline + 1);
					actives.put(location, innerData);
					return true;
				}
			}
		} else {
			innerData = new Hashtable<String, Integer>();
			if (status.equals("Online")) {

				innerData.put("Online", 1);
				actives.put(location, innerData);
				return true;

			} else if (status.equals("Offline")) {

				innerData.put("Offline", 1);
				actives.put(location, innerData);
				return true;
			}
		}

		return false;
	}

	public String limit(String username) {
		Integer minlimit = 6;
		Integer maxlimit = 15;

		if (username.length() >= minlimit && username.length() <= maxlimit) {
			
			System.out.println("Success");

		} else {
			System.out.println("Username should be less than 15 characters and more than 6 characters and");
		}
		return username;

	}
	
	public String limitPassword(String password) {
		
		if(password.length() >=6) {
			System.out.println("Success");
			
		} else {
			System.out.println("Password should be greater than or equal to atleast 6 characters");
		}
		return password;
	}
	
	public Hashtable<String, String> getDir(String fn) throws IOException {
		String editPath = this.basePath.substring(0, this.basePath.length() - 1);
		
		File folder = new File(editPath);
		File[] files = folder.listFiles();
		Hashtable<String, String> userTable = new Hashtable<>();

		for (File file : files) {

			if (file.isFile() && file.getName().startsWith(fn)) {

				List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
				int counter = 0;
				for (String line : lines) {
					if (counter == 0) {
						counter++;
						continue;
					}

					String data[] = line.split("\\|");
					if(data.length > 0 && !data[0].trim().isEmpty()) {
					if (userTable.isEmpty()) {
						userTable.put(String.valueOf(data[0].charAt(0)),line);
					} else {
						if (userTable.containsKey(String.valueOf(data[0].charAt(0)))) {
							String value = userTable.get(String.valueOf(data[1].charAt(0)));
							value += ",\n" + line;
							userTable.put(String.valueOf(data[0].charAt(0)), value);
						} else {
							userTable.put(String.valueOf(data[0].charAt(0)), line);
						} 
							
						
					} 
					}
					counter++;
				}
			}

		}

		return userTable;
	}
	
	private void logger(String logData, String IpAddress) throws ServerNotActiveException, IOException {
		
//		String ip = getClientHost().split("\\.")[0];
		String ip = IpAddress.split("\\.")[0];
		switch (ip) {
		case "93": {
			String path = this.basePath + "log\\93\\" + formatFile() + ".txt";
			System.out.println(path);			
			this.log(path, "\n" + new Date()+ " : " + logData.substring(2, logData.length()));
		}
		break;
		
		case "132": {
			String path = this.basePath + "log\\132\\" + formatFile() + ".txt";
			this.log(path,"\n" + new Date()+ " : " + logData.substring(2, logData.length()));
		}
			break;
			
			case "182": {
				String path = this.basePath + "log\\182\\" + formatFile() + ".txt";
				this.log(path,"\n" + new Date()+ " : " + logData.substring(2, logData.length()));
				
			}		
			break;
		}
		
	}

	private void log(String path, String data) throws IOException {
		Files.write(Paths.get(path), data.getBytes() , StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		
	}

	private String formatFile() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		return sdf.format(new Date());
	}
	
	
	public String getAllOperations(String ipAddress) throws IOException {
		String ip = ipAddress.split("\\.")[0];
		String path = this.basePath + "Directory_Logs\\Directory_" + ip + ".txt";
		System.out.println(path);
		
		if(ip.equals("93")) {
		Hashtable<String, String> file = this.getDir(ip);
		FileWriter writer = new FileWriter(path,true);
		for (String key : file.keySet()) {
			writer.write(key + " : " +file.get(key) + "\n");
			
		} 
		writer.close();
		
		
			
		} else if (ip.equals("132")) {
			Hashtable<String, String> file = this.getDir(ip);
			FileWriter writer = new FileWriter(path,true);
			for (String key : file.keySet()) {
				writer.write(key + " : " +file.get(key) + "\n");
				
			} 
			writer.close();
			
		} else if (ip.equals("182")) {
			Hashtable<String, String> file = this.getDir(ip);
			FileWriter writer = new FileWriter(path,true);
			for (String key : file.keySet()) {
				writer.write(key + " : " +file.get(key) + "\n");
				
			} 
			writer.close();
		}
		return "Success";
		
	}
	
	public Boolean logOutUser(String location, String status) {
		String data = "\n" + status + "|" + location;
		String path = this.basePath + "ADMIN\\ACCOUNT.txt";
		Map<String, Integer> innerData = actives.get(location);		
		if (innerData != null) {

			 if (status.equals("Offline")) {
				Integer offline = innerData.get("Offline");				
				if (offline != null) {
					innerData.put("Offline", (offline ==0) ?1: offline + 1);
					actives.put(location, innerData);
					
				}
				Integer active = innerData.get("Online");
				if (active != null) {
					innerData.put("Online", active - 1);
					actives.put(location, innerData);
					this.writeFile(path, data);
				
				}
				return true;
			}
			
		} else {
			innerData = new Hashtable<String, Integer>();
			 if (status.equals("Offline")) {
				

				innerData.put("Offline", 1);
				actives.put(location, innerData);
				return true;
			}
		}

		return false;
	}
	
	public String accountTransfer(String username, String password, String oldIpAddress, String newIpAddress) {
		String path = "\n" + username + "|" + password + "|" + oldIpAddress + "|" + newIpAddress;
		String ip = oldIpAddress.split("\\")[0];
		
		switch (ip) {
		case "93":
			
			break;

		default:
			break;
		}
		
		
		return null;
	}


	

}
