package com.connector;

public final class Credentials {
    public final String url = "jdbc:mysql://localhost:3306/dummy";
    public final String user = SysHandler.getVariable("USER_DB_KEY");
    public final String password = SysHandler.getVariable("PASSWORD_DB_KEY");
}
