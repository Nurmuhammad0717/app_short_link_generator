package uz.pdp.appshortlink.enums;

import java.util.List;

public enum PermissionEnum {

    //FOR USER ROLE
    CREATE_URL,//
    VIEW_OWN_URL,
    EDIT_OWN_URL,
    DELETE_OWN_URL,


    //FOR ADMIN ROLE
    VIEW_ANY_URL,
    EDIT_ANY_URL,
    DELETE_ANY_URL,

    //ROLE
    VIEW_ROLE,
    CREATE_ROLE,
    EDIT_ROLE,
    DELETE_ROLE,

    //USER
    VIEW_USERS,
    BLOCK_USER,


    ;

    public static List<PermissionEnum> userPermissions() {
        return List.of(
                CREATE_URL,
                VIEW_OWN_URL,
                EDIT_OWN_URL,
                DELETE_OWN_URL
        );
    }
}
