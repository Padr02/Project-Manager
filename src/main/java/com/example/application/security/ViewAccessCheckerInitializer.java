package com.example.application.security;

import com.example.application.data.views.LoginView;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.auth.ViewAccessChecker;

public class ViewAccessCheckerInitializer implements VaadinServiceInitListener {

    private final ViewAccessChecker viewAccessChecker;

    /**
     * ViewAccessChecker - an access control mechanism (default = enabled)
     * Redirect unauthenticated user to login view
     *
     */
    public ViewAccessCheckerInitializer(){
       viewAccessChecker = new ViewAccessChecker();
       viewAccessChecker.setLoginView(LoginView.class);
    }

    /**
     * Event listener that intercepts attempts to enter all views
     * @param event from the client
     *
     */
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(e -> {
            e.getUI().addBeforeEnterListener(viewAccessChecker);
        });
    }
}
