package com.assignment.rmi.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

import com.assignment.rmi.Admin;
import com.assignment.rmi.Player;

public class Main {

	public static void main(String[] args) throws NotBoundException, ServerNotActiveException, IOException {

		Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);

		Player player = (Player) registry.lookup("player");
		Admin admin = (Admin) registry.lookup("admin");

		Scanner scanner = new Scanner(System.in);
		Integer signInAs = null;
		Integer step = 0;
		while (true) {
			if (step == 0 || step == -1) {
				System.out.println("Please signin as below user\n \t 1)Player\n  \t2)Admin\n  \t3)Exit");
				System.out.println();
				signInAs = scanner.nextInt();
			}
			if (signInAs == 3) {
				step = 99;
			}

			if (signInAs > 0 && signInAs < 3 && step < 99) {
				step = 1;
				if (signInAs == 1) {
					Integer playerStep = 0;
					Integer playeraction = null;
					while (true) {
						if (playerStep == 0) {
							System.out.println("\t1)Create Account\t2)SignIn\t3)SignOut");
							System.out.println();
							playeraction = scanner.nextInt();
						}
						if (playeraction == 1) {
							playerStep = 1;
							System.out.println("Please Enter below details to Create an Account.");
							System.out.println("Username");							
							String username = scanner.next();
							
							
							if (username.length() >= 6 && username.length() <= 15) {

								//System.out.println("Success");

							} else {
								System.out.println(
										"Username should be less than 15 characters and more than 6 characters");
								System.out.println("Create Account Again");

							}

							System.out.println("First Name");
							String fn = scanner.next();
							System.out.println("Last Name");
							String ln = scanner.next();
							System.out.println("Age");
							Integer age = scanner.nextInt();
							System.out.println("Password");
							String password = scanner.next();

							
							if (password.length() >= 6) { 
								// System.out.println("Success"); 
								
								} else {
								System.out.println("Password should be greater than or equal to atleast 6 characters");
								System.out.println("Create Account Again");
							}
 
							System.out.println("IpAddress");
							String ipAdress = scanner.next();
							String message = player.createPlayerAccount(username, fn, ln, age, password, ipAdress);								
							if (message.equals("Username already exist.")) {
								System.out.println(message + "Press 1 to Try again Or press 2 for Back Menu.");
								System.out.println();
								Integer act = scanner.nextInt();
								if (act == 2) {
									playerStep = 0;
								}						
							} else {
								System.out.println(message);
							}

						} else if (playeraction == 2) {
							playerStep = 2;
							System.out.println("Please Enter below details to Sign in.");
							System.out.println("Username");
							String username = scanner.next();
							System.out.println("Password");
							String password = scanner.next();
							System.out.println("IpAddress");
							String ipAddress = scanner.next();
							boolean flag = player.playerSignIn(username, password, ipAddress);
							System.out.println(flag);
							if (flag) {
								System.out.println(
										"You have successfully logged in.Press 1 to logout Or press 2 for Back Menu.");
								System.out.println();
								Integer act = scanner.nextInt();
								if (act == 2) {
									playerStep = 0;
								} else if (act == 1) {
									step = 0;
								}
							}
						}

						else if (playeraction == 3) {
							playerStep = 3;							
							System.out.println("Username");
							String username = scanner.next();
							System.out.println("IpAdress");
							String IpAdress = scanner.next();
							String message = player.playerSignOut(username, IpAdress);
							System.out.println("Player Signout Successfull, Hence the status is Offline");
							Integer logut = scanner.nextInt();
							if (logut == 1) {
								scanner.close();
								System.exit(0);
							} else {
								step = 0;
							}

						}

					}

				} else {

					Integer playerStep = 0;
					Integer playeraction = null;
					while (true) {
						if (playerStep == 0) {
							System.out.println("\t1)SignIn\t2)Exit");
							System.out.println();
							playeraction = scanner.nextInt();
						}
						if (playeraction == 1) {
							playerStep = 2;
							System.out.println("Please Enter below details to Sign In.");
							System.out.println("Username");
							String username = scanner.next();
							System.out.println("Password");
							String password = scanner.next();
							System.out.println("IpAddress");
							String ipAddress = scanner.next();
							boolean flag = admin.playerSignIn(username, password, ipAddress);
							if(flag == false) 
							System.out.println("Username and Password should be Admin only");
							if (flag) {

								System.out.println(
										"You have successfully logged in.\n Press 1 to Get Active Players List\n Press 2 for logout\n press 3 for back");
								System.out.println();
								Integer act = scanner.nextInt();
								if (act == 2) {
									playerStep = 0;
								} else if (act == 1) {
									System.out.println(admin.getPlayerStatus());									
									System.out.println(
											"You have successfully logged in.Press 1 to Signout Or press 2 for Back Menu.");
									System.out.println();
									Integer acts = scanner.nextInt();
									if (acts == 2) {
										playerStep = 0;
									} else if (acts == 1) {	
										step = 0;
									}
								} else if (act == 2) {
									step = 1;
								}
							}
						} else if (playeraction == 2) {
							step = 99;
							break;
						}

					}

				}

			} else if (step == 99) {
				System.out.println("Do you want to Exit?\n \t 1)Yes\n  \t2)No");
				System.out.println();
				Integer logout = scanner.nextInt();
				if (logout == 1) {
					scanner.close();
					System.exit(0);
				} else {
					step = 0;
				}
			} else {
				System.out.println("Invalid User please try again");
				step = 0;
			}
		}

	}

}
