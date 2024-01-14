package com.shop.customer.role;

public enum Role {
    USER(),
    ADMIN();

    Role() {
    }

    public static Role getRole(String roleName) {
        String roleNameUpperCase = roleName.toUpperCase();
        return Role.valueOf(roleNameUpperCase);
    }

}
