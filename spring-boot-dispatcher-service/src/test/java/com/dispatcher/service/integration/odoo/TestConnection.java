package com.dispatcher.service.integration.odoo;

import com.dispatcher.odoo.*;
import lombok.SneakyThrows;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.Vector;

public class TestConnection {

    private static String protocol = "http";
    private static String host = "192.168.68.10";
    private static Integer port = 8069;

    private static String db = "odoo";
    private static String username = "deebendu.kumar@gmail.com";
    private static String password = "bffc8ade6d97b1f25f54506efdaf056fe76f3bf7";

    private static Integer uid = 0;

    private static Session session;

//    public static boolean login() {
//        // Try to connect to that DB to check it is still available
//        final XmlRpcClient client = new XmlRpcClient();
//        final XmlRpcClientConfigImpl xmlrpcConfigLogin = new XmlRpcClientConfigImpl();
//        try {
//            String protocolAsString = "http://";
//            xmlrpcConfigLogin.setServerURL(new URL(String.format("%s%s:%s/xmlrpc/2/common", protocol, host, port)));
//            int uid = (int) client.execute(xmlrpcConfigLogin, "authenticate", new Object[]{db, username, password, new Object[]{}});
//            System.out.println("uid " + uid);
//            // Informations are valid if user could log in.
//            return uid != 0;
//        } catch (MalformedURLException e1) {
//            // Previously saved data is causing this...
//            // We will have to request a new demo db
//            return false;
//        } catch (XmlRpcException e) {
//            // Connection to previous demo db failed somehow, we will have
//            // to request a new one...
//            return false;
//        }
//    }

    @SneakyThrows
    public static boolean login() {
        XmlRpcClient client = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcConfigLogin = new XmlRpcClientConfigImpl();
        xmlRpcConfigLogin.setEnabledForExtensions(true);
        xmlRpcConfigLogin.setServerURL(new URL(protocol, host, port, "/xmlrpc/2/common"));
        client.setConfig(xmlRpcConfigLogin);
        try {
            Vector<Object> params = new Vector<Object>();
            params.add(db);
            params.add(username);
            params.add(password);
            params.add(new Object[]{});
            var uid = client.execute(xmlRpcConfigLogin,
                    "authenticate",
                    params);
            if (uid instanceof Integer) {
                //login success
                return true;
            }
            return false;
        } catch (XmlRpcException e) {
            return false;
        }
    }

    public static void main(String[] args) {
//        boolean status = login();
        session = new Session(RPCProtocol.RPC_HTTP, host, port, db, username, password);
        try {
            session.startSession();
            ObjectAdapter partners = session.getObjectAdapter("res.partner");
            FilterCollection filters = new FilterCollection();
//            filters.add("name","=","SO001");
//            RowCollection list = partners.searchAndReadObject(filters, new String[]{});
            RowCollection list = partners.searchAndReadObject(filters, new String[]{"name", "state", "partner_id"});
            for (Row row : list) {
                System.out.println(row.getID());
                System.out.println(row.toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("status" + status);
    }
}
