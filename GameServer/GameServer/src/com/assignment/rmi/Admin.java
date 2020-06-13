package com.assignment.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Hashtable;

public interface Admin extends Remote {

	public Hashtable<String, HashMap<String, Integer>> getPlayerStatus(String adminUsername, String adminPassword,
			String iPAddress) throws RemoteException;

}
