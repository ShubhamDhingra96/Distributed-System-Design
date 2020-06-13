package com.assignment.player;

import java.rmi.RemoteException;

public class Player extends Database implements com.assignment.rmi.Player {

	@Override
	public Boolean createPlayerAccount(String username, String firstName, String lastName, Integer age, String password,
			String IpAddress) throws RemoteException {
		try {
			return this.createPlayer(username, firstName, lastName, age, password, IpAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean playerSignIn(String username, String password, String IpAddress, String status)
			throws RemoteException {
		try {
			return this.signIn(username, password, IpAddress, status);
		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	@Override
	public Boolean playerSignOut(String username, String iPAddress) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Player p = new Player();
		try {
			p.createPlayerAccount("shubha","shubhamdhingra", "dhingra", 12, "test", "132.0.32.12");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
