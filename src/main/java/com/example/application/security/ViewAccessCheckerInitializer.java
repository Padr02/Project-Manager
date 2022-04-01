package com.example.application.security;

import com.example.application.data.views.LoginView;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.auth.ViewAccessChecker;

public class ViewAccessCheckerInitializer implements VaadinServiceInitListener {

    private final ViewAccessChecker viewAccessChecker;

    public ViewAccessCheckerInitializer(){
       viewAccessChecker = new ViewAccessChecker();
       viewAccessChecker.setLoginView(LoginView.class); //redirects unauthenticated users to login

    }

    /**
     *      above is just a redirection point,
     * @param event setup interception for all the views
     *
     *
     *
     */

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(e ->{
            e.getUI().addBeforeEnterListener(viewAccessChecker);
        });
    }
}
