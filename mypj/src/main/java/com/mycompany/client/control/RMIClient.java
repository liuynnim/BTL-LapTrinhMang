/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.control;

import com.mycompany.test.RMIInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author quang
 */
public class RMIClient{

    RMIInterface rmiServer;
    Registry registry;

    public RMIClient(String address, int port) {
        try {
            registry = LocateRegistry.getRegistry(address, port);
            rmiServer = (RMIInterface) (registry.lookup("rmiserver"));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getNames() {
        try {
            return rmiServer.getListStu();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}