package API;
public enum UserRoles {
    ADMIN("app-admin");

    private String roles;

    UserRoles(String roles){
        this.roles=roles;
    }

    public String getRoles(){
        return roles;
    }
}
