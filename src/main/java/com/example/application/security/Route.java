package com.example.application.security;

import com.vaadin.flow.component.Component;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Route {
   private String route;
    private String pageTitle;
    Class<? extends Component> view;
}
