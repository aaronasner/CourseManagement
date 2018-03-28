package ProcessServer;


import authenticatedUsers.LoggedInAuthenticatedUser;

public interface IOperation {
    LoggedInAuthenticatedUser perform();
}
