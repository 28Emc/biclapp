package com.biclapp.controller;

import com.biclapp.model.Roles;
import com.biclapp.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RolesController {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/roles")
    public List<Roles> getAllRoles() {
        return rolesService.findAll();
    }

}
