package Controller;

import View.ViewClient;
import View.ViewClientLogin;

/**
 * Esta clase arranca y controla la aplicación
 */
public class MainCliente {

    public static void main(String[] args) {

        ViewClientLogin viewClient = new ViewClientLogin();
        viewClient.chargeLayout();
        viewClient.show();

    }
}
