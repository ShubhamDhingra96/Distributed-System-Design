package com.assignment.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server  {

	public static void main(String[] args) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

		Player playerImpl = new Player();
		com.assignment.rmi.Player player = (com.assignment.rmi.Player) UnicastRemoteObject.exportObject(playerImpl, 0);
		
		Admin adminImpl = new Admin();		
		com.assignment.rmi.Admin admin = (com.assignment.rmi.Admin) UnicastRemoteObject.exportObject(adminImpl,0);

		registry.rebind("player", player);
		registry.rebind("admin", admin);
		System.out.println("Server is running..........");
	}

}
