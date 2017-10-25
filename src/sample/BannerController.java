package sample;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private Registry registry;

    public BannerController(AEXBanner banner,String ipAdress, int portNumber) {

        this.banner = banner;
        //this.effectenbeurs = new MockEffectenbeurs();
        this.registry = locateRegistry(ipAdress,portNumber);
        try {
            this.effectenbeurs = (IEffectenbeurs)registry.lookup("effectenBeurs");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Update();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0 , 2000);
    }

    private Registry locateRegistry(String ipAdress, int portNumber)
    {
        try
        {
         return LocateRegistry.getRegistry(ipAdress, portNumber);
        }
         catch (RemoteException ex) {
        System.out.println("Client: Cannot locate registry");
        System.out.println("Client: RemoteException: " + ex.getMessage());
            return null;
        }
    }
    public void Update() throws RemoteException {
        List<IFonds> fonds = effectenbeurs.getKoersen();
        StringBuilder koers =new StringBuilder();
        for (IFonds fond:fonds
             ) {
            koers.append(fond.getNaam() + " " + fond.getKoers() + ", ");
        }
        banner.setKoersen(koers.toString());
    }
    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }
}
