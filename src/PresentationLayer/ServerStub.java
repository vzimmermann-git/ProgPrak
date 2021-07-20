package PresentationLayer;

import ServiceLayer.IServer;

import java.rmi.Naming;

public class ServerStub {

    public IServer stub = null;

    private static ServerStub serverStub = null;

    private ServerStub() {
        try {
            stub = (IServer) Naming.lookup("rmi://localhost:5555/Service");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ServerStub getS() {
        if (serverStub == null) {
            serverStub = new ServerStub();
        } return serverStub;
    }
}
