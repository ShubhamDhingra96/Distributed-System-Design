package com.assignment.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Player extends Remote {

	public Boolean createPlayerAccount(String username, String firstName, String lastName, Integer age, String password,
			String IpAddress) throws RemoteException;

	public Boolean playerSignIn(String username, String password, String IpAddress, String status) throws RemoteException;

	public Boolean playerSignOut(String username, String iPAddress) throws RemoteException;

}
