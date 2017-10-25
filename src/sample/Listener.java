package sample;

import Server.IListener;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Listener extends UnicastRemoteObject implements IListener {

    private BannerController controller;
    protected Listener(BannerController controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void setKoersen(String fondsen) throws RemoteException {
        this.controller.setKoersen(fondsen);

    }
}
