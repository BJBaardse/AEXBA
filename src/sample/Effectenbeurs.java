package sample;

import Server.IListener;
import javafx.fxml.FXML;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {
    List<IFonds> fonds = new ArrayList<>();
    private Timer pollingTimer;
    private Random random;


    public Effectenbeurs() throws RemoteException {
        fonds.add(new Fonds("Apple",980.29));
        fonds.add(new Fonds("Samsung",560.24));
        fonds.add(new Fonds("OnePlus",356.34));
        random = new Random();
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Update();
            }
        }, 0 , 2000);
    }

    private void Update() {
        for (IFonds fond: fonds) {
            String koers = String.valueOf((random.nextInt() + random.nextDouble())).substring(0,4);
            fond.setKoers(Double.parseDouble(koers));


        }

    }

    @Override
    public List<IFonds> getKoersen() {
        return fonds;
    }

    @Override
    public void addListener(IListener listener) throws RemoteException {

    }

    @Override
    public void removeListener(IListener listener) throws RemoteException {

    }
}
